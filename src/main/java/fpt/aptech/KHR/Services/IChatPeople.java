/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.ChatPeople;
import fpt.aptech.KHR.NewEntities.Groupchat;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IChatPeople {
   void Join(ChatPeople chatPeople);
   void Leave(ChatPeople chatPeople);
   List<ChatPeople> FindByAccount(Account a);
   boolean Checkuserbygp(Account a,int i);
   boolean CheckAccount(Account a,Groupchat i);
}
