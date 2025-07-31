package com.JoaoMarcos.demoMongoDB.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JoaoMarcos.demoMongoDB.Services.PostService;
import com.JoaoMarcos.demoMongoDB.controllers.Util.URL;
import com.JoaoMarcos.demoMongoDB.domain.Post;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAllPost(){
        List<Post> list = postService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{idPost}")
    public ResponseEntity<Post> findById(@PathVariable String idPost){
        Post post = postService.findById(idPost);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTilePost(
        @RequestParam(value = "text", defaultValue = "") String text){

       text = URL.decodeParam(text);
       List<Post> list = postService.findByTitlePost(text);
       return ResponseEntity.ok().body(list);
    }
}
