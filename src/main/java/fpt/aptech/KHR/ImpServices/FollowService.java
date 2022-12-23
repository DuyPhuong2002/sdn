/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Follow;
import fpt.aptech.KHR.Repository.FollowRepository;
import fpt.aptech.KHR.Services.IFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class FollowService implements IFollow{
    @Autowired
    FollowRepository fr;
    @Override
    public Follow Follownew(Follow follow) {
        return fr.save(follow);
    }

    @Override
    public void UnFollow(Follow follow) {
         fr.delete(follow);
    }

    @Override
    public boolean Checkexit(Account a1, Account a2) {
       Follow s = fr.CheckExitFollow(a1, a2);
       if(s==null){
       return true;
       }else{
       return false;
       }
    }

    @Override
    public int numberfollow(Account a1) {
        return fr.findAllFolow(a1).size();
    }

    @Override
    public int numberfollowed(Account a1) {
        return fr.findAllFolowed(a1).size();
    }

    @Override
    public Follow FFfolow(Account acc, Account acced) {
      return fr.FFollow(acc, acced);
    }
}
