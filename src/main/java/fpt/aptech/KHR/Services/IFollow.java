/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IFollow{
    Follow Follownew(Follow follow);
    void UnFollow(Follow follow);
    boolean Checkexit(Account a1,Account a2);
    int numberfollow(Account a1);
    int numberfollowed(Account a1);
    Follow FFfolow(Account acc,Account acced);
    
}
