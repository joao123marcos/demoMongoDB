package com.JoaoMarcos.demoMongoDB.Repositories;

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
    
}
