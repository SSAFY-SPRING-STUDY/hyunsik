package com.example.springpractice.repository;

import com.example.springpractice.entity.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<PostEntity> findById(Long id) {
        for (PostEntity entity : postList) {
            if (entity.getId() == id) {
                return Optional.of(entity);
            }
        }
        return Optional.empty();
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
