/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import fpt.aptech.KHR.Entities.ModelString;
import fpt.aptech.KHR.Entities.RandomString;
import fpt.aptech.KHR.FileUpload.FileUploadUtil;
import fpt.aptech.KHR.ImpServices.AccountServices;
import fpt.aptech.KHR.ImpServices.FirebaseMessagingService;
import fpt.aptech.KHR.ImpServices.JsonServices;
import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Follow;
import fpt.aptech.KHR.NewEntities.Notifacation;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.Routes.RouteAPI;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.IFollow;
import fpt.aptech.KHR.Services.INotification;
import fpt.aptech.KHR.Services.IPosts;
import fpt.aptech.KHR.Services.ISendMail;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class AccountAPIController {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    IAccount ia;
    @Autowired
    IPosts ip;
    @Autowired
    IFollow iFollow;
    @Autowired
    INotification iNotification;
    @Autowired
    FirebaseMessagingService fms;
    @Autowired
    ISendMail sendMail;
    @RequestMapping(value = {"api/create/newaccount"}, method = RequestMethod.POST)
    public void AddNewAccount(@RequestBody ModelString account, HttpServletRequest request, HttpServletResponse response) {
        
        Account a = new Account();
        a.setUsername(account.getData1());
        a.setPassword(passwordEncoder.encode(account.getData2()));
        a.setEmail(account.getData3());
        a.setPhone(account.getData4());
        a.setGender(account.getData5());
        a.setRole("user");
        System.out.println(account.getData5());
        Account b = ia.save(a);
        JsonServices.dd(JsonServices.ParseToJson(account), response);
        

//        JsonServices.dd(JsonServices.ParseToJson(ListYear), response);


    }
    @RequestMapping(value = {"api/login"}, method = RequestMethod.POST)
    public void Login(@RequestBody ModelString account, HttpServletRequest request, HttpServletResponse response) {
        Account b = ia.findByUserName(account.getData1());
        ModelString responseContent = new ModelString();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(b!=null){

           if(encoder.matches(account.getData2(),b.getPassword()))
           {    
               b.setToken(account.getData3());
                Account c =   ia.save(b);
               try {
                   
                   Notifacation s = new Notifacation();
                   s.setTitle("test");
                   s.setConntent("yes");
                   s.setDatatran("1");  
                   fms.sendDataPeople(s, c, "1");
                   
               } catch (FirebaseMessagingException ex) {
                   System.out.println("Nothing");   
               }
               responseContent.setData5("Thanh cong");
                   JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
        
           }else{
            responseContent.setData6("Mk Sai");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
           }
        }else{
            responseContent.setData7("UserName ko ton tai");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
        
        }
        
//        JsonServices.dd(JsonServices.ParseToJson(ListYear), response);

    
    }
    @RequestMapping(value = {"api/autologin"}, method = RequestMethod.POST)
    public void AutoLogin(@RequestBody ModelString account, HttpServletRequest request, HttpServletResponse response) {
        Account b = ia.loginByToken(account.getData1());
        ModelString responseContent = new ModelString();
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(b!=null){
           
            responseContent.setData5("Thanh cong");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
        
           }else{
            responseContent.setData6("Failll");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
           }
        }
        
    @RequestMapping(value = {"api/logout"}, method = RequestMethod.POST)
    public void Logout(@RequestBody ModelString account, HttpServletRequest request, HttpServletResponse response) {
        Account b = ia.findByTokenUser(account.getData1());
        ModelString responseContent = new ModelString();
        if(b!=null){
            b.setToken(null);
            ia.save(b);
            responseContent.setData6("Thanh cong");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
 
        }else{
            responseContent.setData7("Khong tim thay tai khoan ton tai tren thiet bi");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
        
        }
        
//        JsonServices.dd(JsonServices.ParseToJson(ListYear), response);


    }
    
    
    @RequestMapping(value = {"api/login/token"}, method = RequestMethod.POST)
    public void LoginByToken(@RequestBody ModelString account, HttpServletRequest request, HttpServletResponse response) {
        Account b = ia.findByUserName(account.getData1());
        ModelString responseContent = new ModelString();
        if(b!=null){  
            responseContent.setData1("Thanh cong");
            responseContent.setData2("true");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
        
        }else{
            responseContent.setData1("That Bai");
            responseContent.setData2("false");
            JsonServices.dd(JsonServices.ParseToJson(responseContent), response);
        }
        
//        JsonServices.dd(JsonServices.ParseToJson(ListYear), response);


    }
    @RequestMapping(value = {"api/test"}, method = RequestMethod.GET)
    public void test(HttpServletRequest request, HttpServletResponse response) {
        
        JsonServices.dd(JsonServices.ParseToJson(ia.findAll()), response);
    }
    @RequestMapping(value = {"api/changed/avatar"}, method = RequestMethod.POST)
    public ResponseEntity<ModelString> updatePhotoProfile(@RequestParam("avatar") MultipartFile file, @RequestParam("account") String account) throws IOException {
        //Upload file to source static image
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        RandomString session = new RandomString();
        String fildeDB = session.nextString()+fileName;
        String uploadDir = "src/main/resources/static/images/user-photos/";
        FileUploadUtil.saveFile(uploadDir, fildeDB, file);
            System.out.println(account);
        //update user photo

        ///
        Account accountserver = ia.findByTokenUser(account);
        accountserver.setAvatar(fildeDB);
        Account b = ia.save(accountserver);
        ModelString modelString = new ModelString();
        modelString.setData1("oke Bro");
        modelString.setData2(b.getAvatar());
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/get/user/infor"}, method = RequestMethod.GET)
    public ResponseEntity<ModelString> GetInforByToken(@RequestParam("token") String token) throws IOException {
        //Upload token
        Account accountserver = ia.findByTokenUser(token);
        //System.out.println(token);
        //update user photo
        ModelString modelString = new ModelString();
        modelString.setData1("oke Bro");
        modelString.setData2(accountserver.getFulllName());
        modelString.setData3(accountserver.getUsername());
        //modelString.setData4(accountserver.getDob().toString());
        modelString.setData5(accountserver.getEmail());
        modelString.setData6(accountserver.getPhone());
        modelString.setData7(accountserver.getAvatar());
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/save/user/infor"}, method = RequestMethod.POST)
    public ResponseEntity<ModelString> SaveInforByToken(@RequestBody ModelString account) throws IOException {
        //Upload token
        Account accountserver = ia.findByTokenUser(account.getData1());
        //update user photo
       // setdata account
        accountserver.setFulllName(account.getData2());
        accountserver.setUsername(account.getData3());
        //accountserver.setDob(account.getData4());
        accountserver.setEmail(account.getData5());
        accountserver.setPhone(account.getData6());
       //
        ia.save(accountserver);
        ModelString modelString = new ModelString();
        modelString.setData1("oke Bro");
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/get/user/details"}, method = RequestMethod.GET)
    public ResponseEntity<ModelString> GetInforDetailByToken(@RequestParam("token") String token) throws IOException {
        //Upload token
        Account accountserver = ia.findByTokenUser(token);
        //System.out.println(token);
        //update user photo
        ModelString modelString = new ModelString();
        modelString.setData1("oke Bro");
        modelString.setData2(accountserver.getFulllName());
        modelString.setData3(accountserver.getUsername());
        modelString.setData4(String.valueOf(accountserver.getFollowList1().size()));
        modelString.setData5(String.valueOf(accountserver.getPostsList().size()));
        modelString.setData6(String.valueOf((accountserver.getFollowList().size())));
        modelString.setData7(accountserver.getAvatar());
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/newfollow/user/list"}, method = RequestMethod.GET)
    public void GetListDetailByToken(@RequestParam("token") String token ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Upload token
        Account s = ia.findByTokenUser(token);
        List<Account> Lacctserver = ia.findExTokenUser(token);
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
        for (Account acc : Lacctserver) {
            if(iFollow.Checkexit(s, acc)==true){
                        ModelString modelString = new ModelString();
        modelString.setData1(acc.getId().toString());
        modelString.setData2(acc.getFulllName());
        modelString.setData3(acc.getUsername());
        List<Posts> a = acc.getPostsList();
        int d = acc.getPostsList().size();
        System.out.println(d);   
        if(d>=1){
        modelString.setData4(a.get(d-1).getImage());
        }else{
        modelString.setData4("noimage.png");
        }
        
        if(d>=2){
        modelString.setData5(a.get(d-2).getImage());
        }else{
        modelString.setData5("noimage.png");
        }
        
        if(d>=3){
        modelString.setData6(a.get(d-3).getImage());
        }else{
        modelString.setData6("noimage.png");
        }
        
        
        if(acc.getAvatar()!= null){
        modelString.setData7(acc.getAvatar());
        }else{
        modelString.setData7("default-avatar.png");
        }
        
        responesdata.add(modelString); 
        }
        }
//        ModelString modelString = new ModelString();
//        modelString.setData1("oke Bro");
//        modelString.setData2(accountserver.getFulllName());
//        modelString.setData3(accountserver.getUsername());
//        //modelString.setData4(accountserver.getDob().toString());
////        modelString.setData5(accountserver.getEmail());
////        modelString.setData6(accountserver.getPhone());
//        modelString.setData7(accountserver.getAvatar());
        JsonServices.dd(JsonServices.ParseToJson(responesdata), response);
        //return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
    
    @RequestMapping(value = {"api/follow/add"}, method = RequestMethod.POST)
     public ResponseEntity<ModelString> FollowNew(@RequestParam("token") String token,@RequestParam("username") String username) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        Account accountfled = ia.findByUserName(username);
        //System.out.println(token);
        //update user photo
        Follow f = new Follow();
        f.setIdAccountFl(accountfl);
        f.setIdAccountFled(accountfled);
        f.setStatus("lv1");
        Date date = new Date();
        f.setCreatDate(date);
        iFollow.Follownew(f);
        ModelString modelString = new ModelString();
        modelString.setData1("oke Bro");
        //modelString.setData4(accountserver.getDob().toString());
//        modelString.setData5(accountserver.getEmail());
//        modelString.setData6(accountserver.getPhone());
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
     @RequestMapping(value = {"api/notifacation/user"}, method = RequestMethod.POST)
     public ResponseEntity<List<ModelString>> ListNotifaction(@RequestParam("token") String token) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
        List<Notifacation> list = iNotification.findByUser(accountfl);
         for (Notifacation notifacation : list) {
            try {
                ModelString modelString = new ModelString();
                modelString.setData1(notifacation.getIdAccount().getUsername());
                modelString.setData3(notifacation.getConntent());
                modelString.setData2(notifacation.getTitle());
                modelString.setData4(String.valueOf(notifacation.getSeen()));
                modelString.setData5(ConditionDate(notifacation.getCreateDate()));
                responesdata.add(modelString);
                
            } catch (ParseException ex) {
                Logger.getLogger(AccountAPIController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
     @RequestMapping(value = {"api/validate/user"}, method = RequestMethod.POST)
     public ResponseEntity<List<ModelString>> ValidateForm(@RequestBody ModelString check) throws IOException {
        //Upload token
        Account accountfl = ia.findByUserName(check.getData1());
        
        
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
        List<Notifacation> list = iNotification.findByUser(accountfl);
         for (Notifacation notifacation : list) {
            try {
                ModelString modelString = new ModelString();
                modelString.setData1(notifacation.getIdAccount().getUsername());
                modelString.setData3(notifacation.getConntent());
                modelString.setData2(notifacation.getTitle());
                modelString.setData4(String.valueOf(notifacation.getSeen()));
                modelString.setData5(ConditionDate(notifacation.getCreateDate()));
                responesdata.add(modelString);
                
            } catch (ParseException ex) {
                Logger.getLogger(AccountAPIController.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
     @RequestMapping(value = {"api/get/follow/listuser"}, method = RequestMethod.GET)
     public ResponseEntity<List<ModelString>> GetSDNfl(@RequestParam("token") String token) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
        List<Follow> list = accountfl.getFollowList1();
         for (Follow a : list) {
            try {
                ModelString modelString = new ModelString();
                modelString.setData1(a.getIdAccountFl().getUsername());
                modelString.setData3(a.getIdAccountFl().getAvatar());
                modelString.setData2(a.getIdAccountFl().getFulllName());
//                modelString.setData4(String.valueOf(notifacation.getSeen()));
//                modelString.setData5(ConditionDate(notifacation.getCreateDate()));
                responesdata.add(modelString);   
            } catch (Exception ex) {
                Logger.getLogger(AccountAPIController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(responesdata, HttpStatus.BAD_REQUEST);
            }
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
     @RequestMapping(value = {"api/get/follower/listuser"}, method = RequestMethod.GET)
     public ResponseEntity<List<ModelString>> GetSDNfler(@RequestParam("token") String token) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
        List<Follow> list = accountfl.getFollowList();
         for (Follow a : list) {
            try {
                ModelString modelString = new ModelString();
                modelString.setData1(a.getIdAccountFled().getUsername());
                modelString.setData3(a.getIdAccountFled().getAvatar());
                modelString.setData2(a.getIdAccountFled().getFulllName());
//                modelString.setData4(String.valueOf(notifacation.getSeen()));
//                modelString.setData5(ConditionDate(notifacation.getCreateDate()));
                responesdata.add(modelString);   
            } catch (Exception ex) {
                Logger.getLogger(AccountAPIController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(responesdata, HttpStatus.BAD_REQUEST);
            }
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
     @RequestMapping(value = {"api/user/unfollow"}, method = RequestMethod.GET)
     public ResponseEntity<ModelString> UnFollow(@RequestParam("token") String token,@RequestParam("username") String username) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        Account accountfled = ia.findByUserNameUser(username);
        Follow follow = iFollow.FFfolow(accountfl, accountfled);
        iFollow.UnFollow(follow);
        
        ModelString modelString = new ModelString();
            try {
                
                modelString.setData1("oke");
                modelString.setData3("no thing");
                modelString.setData2("????");
//                modelString.setData4(String.valueOf(notifacation.getSeen()));
//                modelString.setData5(ConditionDate(notifacation.getCreateDate())); 
            } catch (Exception ex) {
                Logger.getLogger(AccountAPIController.class.getName()).log(Level.SEVERE, null, ex);
                return new ResponseEntity<>(modelString, HttpStatus.BAD_REQUEST);
            }
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"/api/recovery/code"}, method = RequestMethod.GET)
    public ResponseEntity<ModelString> SendRecoverCode(@RequestParam("username") String username) {

        sendMail.sendRecoveryCode(username);
        ModelString modelString = new ModelString();
        modelString.setData1("oke");
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    
     private String ConditionDate(Date d2) throws ParseException{
//        String dateStart = "2012-03-14 09:33:58";
//
//            String dateStop = "2012-03-14 10:34:59";

        // Custom date format

        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date();
       //d1 = format.parse(dateStart);
//
//            d2 = format.parse(dateStop);


        // Get msec from each, and subtract.
            
        long diff = d1.getTime() - d2.getTime();

        long diffSeconds = diff / 1000;

        long diffMinutes = diff / (60 * 1000);

        long diffHours = diff / (60 * 60 * 1000);
        
        long diffDay = diff / (60 * 60 * 1000 * 24 );
        
        long diffWeek = diff / (60 * 60 * 1000 * 24 * 7);
        
        long diffMounth = diff / (60 * 60 * 1000 * 24 * 30);
        
        long diffYear = diff / (60 * 60 * 1000 * 24 * 360);
        if(diffSeconds < 60 && diffSeconds > 0 ){
         String outdata = String.valueOf(diffSeconds) + " seconds";
         return outdata;
        }
        if(diffMinutes < 60 && diffMinutes > 0){
         String outdata = String.valueOf(diffMinutes) + " minutes";
         return outdata;
        }
        if(diffHours <= 24 && diffHours > 0){
         String outdata = String.valueOf(diffHours) + " hours";
         return outdata;
        }
        if(diffDay <= 7 && diffDay > 0){
         String outdata = String.valueOf(diffDay) + " day";
         return outdata;
        }
        if(diffWeek <=4 && diffWeek > 0){
         String outdata = String.valueOf(diffWeek) + " week";
         return outdata;
        }
        if(diffMounth <= 12 && diffMounth > 0){
         String outdata = String.valueOf(diffMounth) + " mounth";
         return outdata;
        }
        if(diffYear > 0){
        String outdata = String.valueOf(diffYear) + " year";
        return outdata;
        }
        else{
        String outdata = String.valueOf(diffDay);
         return outdata;
        }
    }
    

    
}
