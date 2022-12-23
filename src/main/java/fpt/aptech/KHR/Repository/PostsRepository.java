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
public interface PostsRepository extends JpaRepository<Posts, Integer> {
    
    @Query("SELECT p FROM Posts p WHERE p.id = :id ORDER BY p.id DESC")
    Posts findById(@PathVariable("id") int id);
    //
    @Query("SELECT p FROM Posts p WHERE p.idAccount = :idAccount")
    Posts findByAuthur(@PathVariable("idAccount") Account idAccount);
}
