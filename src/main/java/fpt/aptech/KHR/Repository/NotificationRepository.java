/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Notifacation;
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
public interface NotificationRepository extends JpaRepository<Notifacation, Integer> {
  @Query("SELECT f FROM Notifacation f WHERE f.idAccount = :idAccount ")
  List<Notifacation> findNotifacationAccout(@PathVariable("idAccount") Account idAccount);
}
