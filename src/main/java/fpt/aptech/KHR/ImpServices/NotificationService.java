/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Notifacation;
import fpt.aptech.KHR.Repository.NotificationRepository;
import fpt.aptech.KHR.Services.INotification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class NotificationService implements INotification{
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public List<Notifacation> findAll() {
      return notificationRepository.findAll();
    }

    @Override
    public List<Notifacation> findByUser(Account account) {
        return notificationRepository.findNotifacationAccout(account);
    }
    
}
