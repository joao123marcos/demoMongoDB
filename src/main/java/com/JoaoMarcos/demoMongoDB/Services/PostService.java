package com.JoaoMarcos.demoMongoDB.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JoaoMarcos.demoMongoDB.Repositories.PostRepository;
import com.JoaoMarcos.demoMongoDB.Services.Execptions.ObjectNotfound;
import com.JoaoMarcos.demoMongoDB.domain.Post;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Post findById(String idPost){
        String teste = idPost;
        System.out.println("idPost: "+teste);

        Optional<Post> opPost = postRepository.findById(idPost);
        if (opPost.isPresent()){
            return opPost.get();
        } else {
            throw new ObjectNotfound(idPost);
        }
    }

    public List<Post> findByTitlePost(String text){
        return postRepository.findByTitlePostContainingIgnoreCase(text);
    }
}