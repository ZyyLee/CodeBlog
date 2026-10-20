package com.api.blog.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
import com.api.blog.model.PostModel;

//@Repository 
public interface PostRepository extends JpaRepository<PostModel, UUID> {

}
