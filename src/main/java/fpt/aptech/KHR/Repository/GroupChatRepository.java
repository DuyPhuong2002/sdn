/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fpt.aptech.KHR.NewEntities.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface GroupChatRepository extends JpaRepository<Groupchat, Integer> {
  @Query("SELECT f FROM Groupchat f WHERE f.id = :id ")
  Groupchat findByIdRes(@PathVariable("id") int id);
}
