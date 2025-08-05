package com.JoaoMarcos.demoMongoDB.Repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.JoaoMarcos.demoMongoDB.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
    
    @Query("{ 'titlePost': { $regex: ?0, $options: 'i' } }")
    List<Post> findTitleRegex(String text);
    
    List<Post> findByTitlePostContainingIgnoreCase(String text);

    @Query("{ $and: [ { date: { $gte: ?1 } },{ date: { $lte: ?2 } },{ $or: [ {'titlePost': { $regex: ?0, $options: 'i' } }, {'bodyPost': { $regex: ?0, $options: 'i' }  }, {'listComents.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    List<Post> fullSearch (String text, LocalDateTime dateMin, LocalDateTime dateMax);
    
}
