/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Repository;

import fpt.aptech.KHR.NewEntities.Account;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Autowired
    @Query("SELECT a FROM Account a WHERE a.email = :email")
    Account findByEmail(@PathVariable("email") String email);
    @Query("SELECT a FROM Account a WHERE a.role = 'user' ORDER BY username")
    List<Account> Search();
    @Query("SELECT a FROM Account a WHERE a.role = 'user' ORDER BY username")
    List<Account> SortName();
    @Query("SELECT a FROM Account a WHERE a.role = 'user' ORDER BY fullName")
    List<Account> SortFullName( );
    @Query("SELECT a FROM Account a WHERE a.role = 'user' ORDER BY email")
    List<Account> SortEmail( String colum);
    @Query("SELECT a FROM Account a WHERE a.role = 'user' ORDER BY phone")
    List<Account> SortPhone();
    @Query("SELECT a FROM Account a WHERE a.role = 'user' ORDER BY phone")
    List<Account> SortDate();
    @Query("SELECT a FROM Account a WHERE a.role = 'user' AND a.gender = :gender")
    List<Account> SortSex(@PathVariable("gender") String gender);
    @Query("SELECT a FROM Account a WHERE a.role = 'user' AND a.status = :status")
    List<Account> Sortstatus(@PathVariable("status") int status);
    
    

//    @Query("SELECT a FROM Account a WHERE a.idStore = :idStore")
//    List<Account> findByStore(@PathVariable("idStore") Store idStore);

    @Query("SELECT a FROM Account a WHERE a.email = :email AND a.role = 'admin' AND a.status = 0")
    Account findByEmailAdmin(@PathVariable("email") String email);

    @Query("SELECT a FROM Account a WHERE a.email = :email AND a.role = 'user' AND a.status = 0")
    Account findByEmailUser(@PathVariable("email") String email);
//
//    @Query("SELECT a FROM Account a WHERE  a.role = 0")
//    List<Account> findAllUser();
//
//    @Query("SELECT a FROM Account a WHERE a.code = :code")
//    Account findByCode(@PathVariable("code") String code);
//
//    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.password = :password")
//    Account loginAccount(@PathVariable("mail") String mail, @PathVariable("password") String password);
//
//    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.password = :password")
//    Account checkOldPass(String mail, String password);
//
//    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.recoverycode = :recoverycode")
//    Account checkRecoveryCode(String mail, String recoverycode);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("UPDATE Account a SET a.password = :password WHERE a.mail = :mail")
//    void updatePassword(@Param("password") String password, @Param("mail") String mail);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("UPDATE Account a SET a.recoverycode = :recoverycode WHERE a.mail = :mail")
//    void updateRecoveryCode(@Param("recoverycode") String recoverycode, @Param("mail") String mail);
//
//    @Query("SELECT a FROM Account a WHERE CONCAT(a.mail,' ', a.fullname,' ', a.status) LIKE %?1%")
//    public List<Account> search(String keyword);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query("UPDATE Account a SET a.fullname = :fullname , a.phone = :phone , a.birthdate = :birthdate , a.gender = :gender WHERE a.mail = :mail")
//    void updateBasicInfoMobile(@Param("fullname") String fullname, @Param("phone") String phone, @Param("birthdate") Date birthdate, @Param("gender") boolean gender, @Param("mail") String mail);
//
//    @Query("SELECT a FROM Account a WHERE a.mail = :mail AND a.googleid = :googleid")
//    Account checkGoogleId(String mail, String googleid);
    //
    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account findByUsername(@PathVariable("username") String username);
    // Accoutn SND
    @Query("SELECT a FROM Account a WHERE a.role = 'user'")
    List<Account> findAllUser();
    
    @Query("SELECT a FROM Account a WHERE a.username = :username")
    Account SDN_findByUsername(@PathVariable("username") String username);
    
    @Query("SELECT a FROM Account a WHERE a.token = :token")
    Account SDN_LoginByToken(@PathVariable("token") String token);
    
    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.password = :password ")
    Account SDN_LoginByUsername(@PathVariable("username") String username,@PathVariable("password") String password);
    
    @Query("SELECT a FROM Account a WHERE a.phone = :phone")
    Account SDN_findByPhone(@PathVariable("phone") String phone);
    
    @Query("SELECT a FROM Account a WHERE a.email = :email")
    Account SDN_findByEmail(@PathVariable("email") String email);
    
    @Query("SELECT a FROM Account a WHERE a.token = :token")
    Account SDN_findByToken(@PathVariable("token") String token);
    
    @Query("SELECT a FROM Account a WHERE a.token != :token OR a.token IS NULL AND a.role = 'user' AND a.status = 0")
    List<Account> SDN_findAllExToken(@PathVariable("token") String token);
    
    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.role = 'admin' AND a.status = 0")
    Account findByUserNameAdmin(@PathVariable("username") String username);

    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.role = 'user' AND a.status = 0")
    Account findByUserNameUser(@PathVariable("username") String username);
    @Query("SELECT a FROM Account a WHERE a.phone = :phone AND a.role = 'user'")
    Account findByUserNamePhone(@PathVariable("phone") String phone);
    @Query("SELECT a FROM Account a WHERE a.email = :email AND a.role = 'user'")
    Account findByUserNameEmail(@PathVariable("email") String email);
    
    @Query("SELECT a FROM Account a WHERE a.username = :username AND a.role = 'user'")
    Account LockActive(@PathVariable("username") String username);
    
    
}
