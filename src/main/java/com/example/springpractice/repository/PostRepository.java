package com.example.springpractice.repository;

import com.example.springpractice.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/*
레포,서비스, 컨트롤러가 아닌 애들은
@Component로 작성
 */
@Repository
public class PostRepository {
    List<PostEntity> postList = new ArrayList<>();

    public PostEntity save(PostEntity postEntity){
        postList.add(postEntity);

        return postEntity;
    }
    public List<PostEntity> findAll(){
      return postList;
    }

    public PostEntity findById(Long id) {
        for (PostEntity entity : postList) {
            if (entity.getId() == id) {
                return entity;
            }
        }
        return null;
    }

    public void deleteById(Long id) {
        for (PostEntity entity : postList) {
            if (entity.getId() == id) {
                postList.remove(entity);
                break;
            }
        }
    }
}
