/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.Repository.AccountRepository;
import fpt.aptech.KHR.Services.IAccount;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class AccountSevice implements UserDetailsService{
    @Autowired
    IAccount repository;
    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account adminaccount = repository.findByUserNameAdmin(username);
        Account useraccount = repository.findByUserNameUser(username);
        if (adminaccount != null) {
            List<GrantedAuthority> grantListAdmin = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantListAdmin.add(authority);
            UserDetails userDetails = new User(adminaccount.getUsername(), adminaccount.getPassword(), grantListAdmin);
            return userDetails;
        } else if (useraccount != null) {
            List<GrantedAuthority> grantListUser = new ArrayList<>();
            GrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
            grantListUser.add(userAuthority);
            UserDetails userDetails = new User(useraccount.getUsername(), useraccount.getPassword(), grantListUser);
            return userDetails;
        }
        else {
            new UsernameNotFoundException("Login failed!");
        }
        return null;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public List<Account> findAllUser() {
        return accountRepository.findAllUser();
    }

    public Account findByMail(String mail) {
        return accountRepository.findByEmail(mail);
    }

    public Account loginAccount(String mail, String password) {
        return accountRepository.SDN_LoginByUsername(mail, password);
    }
    
}
