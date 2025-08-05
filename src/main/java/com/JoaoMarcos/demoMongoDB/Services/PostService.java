package com.JoaoMarcos.demoMongoDB.Services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoaoMarcos.demoMongoDB.Repositories.PostRepository;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.NoResourceFoundException;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.IllegalArgumentException;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.ObjectNotfound;
import com.JoaoMarcos.demoMongoDB.controllers.Util.URL;
import com.JoaoMarcos.demoMongoDB.domain.Post;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String idPost) {
        String teste = idPost;
        System.out.println("idPost: " + teste);

        Optional<Post> opPost = postRepository.findById(idPost);
        if (opPost.isPresent()) {
            return opPost.get();
        } else {
            throw new ObjectNotfound(idPost);
        }
    }

    public List<Post> findByTitlePost(String text) {
        return postRepository.findTitleRegex(text);
    }

    public List<Post> fullSearch(String text, String minDate, String maxDate) {

        LocalDateTime minDateTime = null, maxDateTime = null;

        if (text == null || text.isEmpty()) {
            throw new NoResourceFoundException("parameter is not a valid");
        } else {
            text = URL.decodeParam(text);
        }

        minDateTime = URL.convertDate(minDate, false);
        maxDateTime = URL.convertDate(maxDate, true);

        if (minDateTime == null) {
            minDateTime = maxDateTime.withHour(0).withMinute(0).withSecond(0);
        }

        if (maxDateTime == null) {
            maxDateTime = minDateTime.withHour(23).withMinute(59).withSecond(59);
        }

        if (minDateTime.isAfter(maxDateTime)) {
            throw new IllegalArgumentException("A data mínima não pode ser posterior "+
                "à data máxima, nem vice versa");
            
        }
        return postRepository.fullSearch(text, minDateTime, maxDateTime);
    }
}