/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.Repository.AccountRepository;
import fpt.aptech.KHR.Services.IAccount;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class AccountServices implements IAccount{
    
    @Autowired
    private AccountRepository repository; 
    
    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account save(Account account) {
        return repository.save(account);
    }

    @Override
    public void delete(Account account) {
        repository.delete(account);
    }

    @Override
    public Account findByMail(String mail) {
        return repository.SDN_findByEmail(mail);
    }

    @Override
    public Account findByMailAdmin(String mail) {
        return repository.findByEmailAdmin(mail);
    }

    @Override
    public Account findByMailUser(String mail) {
        return repository.findByEmailUser(mail);
    }

    @Override
    public Account findByUserName(String username){
        return repository.SDN_findByUsername(username);
    }

    @Override
    public Account findByTokenUser(String token) {
         return repository.SDN_findByToken(token);
    }

    @Override
    public Account loginByToken(String token) {
        return repository.SDN_findByToken(token);
    }

    @Override
    public List<Account> findExTokenUser(String token) {
        return repository.SDN_findAllExToken(token);
    }

    @Override
    public Account findByUserNameAdmin(String username) {
        return repository.findByUserNameAdmin(username);
    }

    @Override
    public Account findByUserNameUser(String username) {
        return repository.findByUserNameUser(username);

    }

    @Override
    public boolean checkExitUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkExitPhone(String Phone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Account lockactive(String username) {
        return repository.LockActive(username);
    }

    @Override
    public List<Account> search(String colum) {
        switch(colum){
            case "username":return repository.SortName();
            case "fullName":return repository.SortFullName();
            case "email":return repository.SortEmail(colum);
            case "phone":return repository.SortPhone();
            case "Male":return repository.SortSex("Male");
            case "Female":return repository.SortSex("Female");
            case "Active":return repository.Sortstatus(0);
            case "Lock":return repository.Sortstatus(1);
            default:return repository.findAll();
            
        }
            
        
    }

}
