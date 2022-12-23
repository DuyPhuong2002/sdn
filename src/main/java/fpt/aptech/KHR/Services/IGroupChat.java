/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Groupchat;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IGroupChat {
    Groupchat Addgroup(Groupchat groupchat);
    void delete(Groupchat groupchat);
    Groupchat FindById(int id);
    boolean CheckexitGc(String uesr1,String user2);
    List<Groupchat> findAll();
    List<Groupchat> findByAccount();
    
    
}
