/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Account;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author LÊ HỮU TÂM
 */

public interface IAccount {
    public List<Account> findAll();

    public Account save(Account account);

    public void delete(Account account);

    public Account findByMail(String mail);

    public Account findByMailAdmin(String mail);

    public Account findByMailUser(String mail);
    //
    public Account findByUserName(String username);
    
    public Account findByTokenUser(String token);
    
    public Account loginByToken(String token);
    
    public List<Account> findExTokenUser(String token);
    
    public Account findByUserNameAdmin(String username);

    public Account findByUserNameUser(String username);
    public boolean checkExitUsername(String username);
    public boolean checkExitPhone(String Phone);
    public Account lockactive(String username);
    public List<Account> search(String colum);
//
//    public List<Account> findAllAdminAccounts();
//
//    public List<Account> findAllUserAccounts();
//
//    public Account findByCode(String code);
//
////    public List<Account> findByStore(Store id);
//
//    public boolean checkOldPassword(String mail, String password);
//
//    public boolean checkRecoveryCode(String mail, String recoverycode);
//
//    public void updatePassword(String password, String mail);
//    
//    public void updateBasicInfoMobile(String fullname, String phone, Date birthdate, boolean gender, String mail);
//
//    public void updateRecoveryCode(String recoverycode, String mail);
//
//    public List<Account> listAll(String keyword);
//    
//    public Account checkGoogleId(String mail, String googleid);
}
