/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Groupchat;
import fpt.aptech.KHR.Repository.GroupChatRepository;
import fpt.aptech.KHR.Services.IGroupChat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class GroupChatService implements IGroupChat{
    @Autowired
    GroupChatRepository gcr;
            
    @Override
    public Groupchat Addgroup(Groupchat groupchat) {
       return gcr.save(groupchat);
    }

    @Override
    public void delete(Groupchat groupchat) {
        gcr.delete(groupchat);
    }

    @Override
    public Groupchat FindById(int id) {
       return gcr.findByIdRes(id);
    }

    @Override
    public boolean CheckexitGc(String uesr1, String user2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Groupchat> findAll() {
        return gcr.findAll();
    }

    @Override
    public List<Groupchat> findByAccount() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
