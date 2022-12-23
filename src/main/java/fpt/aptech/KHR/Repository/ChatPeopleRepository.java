/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.ChatPeople;
import fpt.aptech.KHR.NewEntities.Groupchat;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface ChatPeopleRepository extends JpaRepository<ChatPeople, Integer> {
    @Query("SELECT f FROM ChatPeople f WHERE f.idAccout = :idAccout ")
    List<ChatPeople> findByAccountRes(@PathVariable("idAccout") Account idAccout);
    
    @Query("SELECT f FROM ChatPeople f WHERE f.idAccout = :idAccout AND f.id = :id")
    ChatPeople CheckExcute(@PathVariable("idAccout") Account idAccout,@PathVariable("id") int id);
    @Query("SELECT f FROM ChatPeople f WHERE f.idAccout = :idAccout AND f.idGroup = :idGroup")
    ChatPeople CheckAccount(@PathVariable("idAccout") Account idAccout,@PathVariable("idGroup") Groupchat idGroup);
}
