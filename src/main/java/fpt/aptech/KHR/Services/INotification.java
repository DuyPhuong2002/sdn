/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Notifacation;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface INotification {
    List<Notifacation> findAll();
    List<Notifacation> findByUser(Account account);
    
    
}
