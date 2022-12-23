/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Follow;
import fpt.aptech.KHR.NewEntities.PostLike;
import fpt.aptech.KHR.NewEntities.Posts;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {
    @Query("SELECT f FROM PostLike f WHERE f.idAccount = :idAccount AND f.idPosts = :idPosts ")
    PostLike findPostLike(@PathVariable("idAccount") Account idAccount ,@PathVariable("idPosts") Posts idPosts);
}
