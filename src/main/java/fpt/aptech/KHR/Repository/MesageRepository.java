/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Groupchat;
import fpt.aptech.KHR.NewEntities.Message;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface MesageRepository extends JpaRepository<Message, Integer> {
  @Query("SELECT f FROM Message f WHERE f.idGroup = :idGroup ")
  List<Message> findByIdRes(@PathVariable("idGroup") Groupchat idGroup);
  
    
}
