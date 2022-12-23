/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Groupchat;
import fpt.aptech.KHR.NewEntities.Message;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IMessage {
    List<Message> FindByGroup(Groupchat groupchat);
    void addMess (Message message);
    void remove (Message message);
    
}
