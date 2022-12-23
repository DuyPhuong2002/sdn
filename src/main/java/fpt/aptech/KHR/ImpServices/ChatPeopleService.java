/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.ChatPeople;
import fpt.aptech.KHR.NewEntities.Groupchat;
import fpt.aptech.KHR.Repository.ChatPeopleRepository;
import fpt.aptech.KHR.Services.IChatPeople;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class ChatPeopleService implements IChatPeople{
    @Autowired
    ChatPeopleRepository chatPeopleRepository;
    @Override
    public void Join(ChatPeople chatPeople) {
        chatPeopleRepository.save(chatPeople);
    }

    @Override
    public void Leave(ChatPeople chatPeople) {
        chatPeopleRepository.delete(chatPeople);
    }

    @Override
    public List<ChatPeople> FindByAccount(Account a) {
       return chatPeopleRepository.findByAccountRes(a);
    }

    @Override
    public boolean Checkuserbygp(Account a, int i) {
       ChatPeople ch = chatPeopleRepository.CheckExcute(a, i);
       if(ch==null){
       return true;
       }else{
       return false;
       }
    }

    @Override
    public boolean CheckAccount(Account a, Groupchat i) {
        ChatPeople s = chatPeopleRepository.CheckAccount(a, i);
        if(s==null){
            return true;
        }else{
        return false;
        }
    }
    
    
}
