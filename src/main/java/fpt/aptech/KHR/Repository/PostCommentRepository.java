/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.PostComment;
import fpt.aptech.KHR.NewEntities.Posts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface PostCommentRepository extends JpaRepository<PostComment, Integer> {
  @Query("SELECT f FROM PostComment f WHERE f.idPosts = :idPosts ")
  List<PostComment> findPostLike(@PathVariable("idPosts") Posts idPosts); 
  @Query("SELECT f FROM PostComment f WHERE f.id = :id ")
  PostComment findbyID (@PathVariable("id") int id);
}
