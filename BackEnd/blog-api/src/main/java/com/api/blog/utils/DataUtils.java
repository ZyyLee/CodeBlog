package com.api.blog.utils;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.api.blog.model.PostModel;
import com.api.blog.repository.PostRepository;

//import jakarta.annotation.PostConstruct;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component 
public class DataUtils {

    final PostRepository postRepository;

    DataUtils(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // @PostConstruct
    public void savePost(){

        List<PostModel> postList = new ArrayList<>();
        PostModel post1 = new PostModel();
        post1.setAutor("Sonic");
        post1.setData(LocalDate.now());
           post1.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry."
        		+ " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
        		+ "remaining essentially unchanged. It was popularised in the 1960s with the release of"
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
        		+ "remaining essentially unchanged. It was popularised in the 1960s with the release of"
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
        		+ "remaining essentially unchanged. It was popularised in the 1960s with the release of"
        		+ " Letraset sheets containing Lorem Ipsum passages, and more recently with desktop "
        		+ "publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        post1.setTitulo("Docker");
        
        PostModel post2 = new PostModel();
        post2.setAutor("Ben10");
        post2.setData(LocalDate.now());
        post2.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
        		+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting,"
        		+ " remaining essentially unchanged. It was popularised in the 1960s with the release of "
        		+ "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
        		+ "software like Aldus PageMaker including versions of Lorem Ipsum.");
        post2.setTitulo("API REST"); 

        PostModel post3 = new PostModel();
        post3.setAutor("Aaaaaa");
        post3.setData(LocalDate.now());
        post3.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
        		+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting,"
        		+ " remaining essentially unchanged. It was popularised in the 1960s with the release of "
        		+ "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
        		+ "software like Aldus PageMaker including versions of Lorem Ipsum.");
        post3.setTitulo("DTO");

        PostModel post4 = new PostModel();
        post4.setAutor("Tech Brasil");
        post4.setData(LocalDate.now());
        post4.setTexto("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
        		+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "
        		+ "when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
        		+ "It has survived not only five centuries, but also the leap into electronic typesetting,"
        		+ " remaining essentially unchanged. It was popularised in the 1960s with the release of "
        		+ "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
        		+ "software like Aldus PageMaker including versions of Lorem Ipsum.");
        post4.setTitulo("CONTROLLER"); 



        postList.add(post1);
        postList.add(post2);
        postList.add(post3);
        postList.add(post4);

        for(PostModel post: postList){
            PostModel postSaved = postRepository.save(post);
            System.out.println(postSaved.getId());
        }
    }
 }

