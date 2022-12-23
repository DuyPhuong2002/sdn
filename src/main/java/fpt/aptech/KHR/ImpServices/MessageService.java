/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Groupchat;
import fpt.aptech.KHR.NewEntities.Message;
import fpt.aptech.KHR.Repository.MesageRepository;
import fpt.aptech.KHR.Services.IMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class MessageService implements IMessage{
    
    @Autowired
    MesageRepository mesageRepository;
    @Override
    public List<Message> FindByGroup(Groupchat groupchat) {
       return mesageRepository.findByIdRes(groupchat);
    }

    @Override
    public void addMess(Message message) {
        mesageRepository.save(message);
    }

    @Override
    public void remove(Message message) {
        mesageRepository.delete(message);
    }
    
}
