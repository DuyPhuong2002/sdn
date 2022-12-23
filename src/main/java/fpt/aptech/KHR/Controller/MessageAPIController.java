/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import fpt.aptech.KHR.Entities.ModelString;
import fpt.aptech.KHR.ImpServices.FirebaseMessagingService;
import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.ChatPeople;
import fpt.aptech.KHR.NewEntities.Groupchat;
import fpt.aptech.KHR.NewEntities.Mail;
import fpt.aptech.KHR.NewEntities.Message;
import fpt.aptech.KHR.NewEntities.Notifacation;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.IChatPeople;
import fpt.aptech.KHR.Services.IGroupChat;
import fpt.aptech.KHR.Services.IMessage;
import fpt.aptech.KHR.Services.ISendMail;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class MessageAPIController {
    @Autowired
    IAccount ia;
    @Autowired
    IGroupChat iGroupChat;
    
    @Autowired
    IChatPeople iChatPeople;
    @Autowired
    IMessage iMessage;
    @Autowired
    ISendMail sendMail;
    @Autowired
    FirebaseMessagingService fms;
            
    @RequestMapping(value = {"api/mess/getgroup"}, method = RequestMethod.POST)
     public ResponseEntity<List<ModelString>> ListNotifaction(@RequestParam("token") String token) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        List<ChatPeople> chat = iChatPeople.FindByAccount(accountfl);
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
         for (ChatPeople s : chat) {
                ModelString modelString = new ModelString();
                modelString.setData2(s.getIdGroup().getGroupName());
                modelString.setData1(String.valueOf(s.getIdGroup().getId()));
                List<Message> ss = s.getIdGroup().getMessageList();
                if(ss.size()>0){
                String sa = ss.get(ss.size()-1).getContent();
                modelString.setData3(sa);
                }else{
                modelString.setData3("No message");
                }
                for(ChatPeople p : chat){
                if(p.getIdAccout()!=accountfl){
                modelString.setData4(p.getIdAccout().getAvatar());
                }
                }
                responesdata.add(modelString);    
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/get/mess"}, method = RequestMethod.POST)
     public ResponseEntity<List<ModelString>> Getmessage(@RequestParam("token") String token,@RequestParam("id") String id) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        int idgroup = Integer.parseInt(id);
        Groupchat gr = iGroupChat.FindById(idgroup);
        List<Message> mess = iMessage.FindByGroup(gr);
        List<ModelString> responesdata = new ArrayList<ModelString>();
        //System.out.println(token);
        //update user photo
         for (Message s : mess){
                ModelString modelString = new ModelString();
                modelString.setData2(s.getIdAccount().getUsername());
                 modelString.setData3(s.getIdAccount().getAvatar());
                try {
                 modelString.setData4(ConditionDate(s.getCreateDate()));
                } catch (Exception e) {
                }
                modelString.setData5(s.getContent());
                modelString.setData6(s.getIdAccount().getToken());
                modelString.setData1(String.valueOf(s.getIdGroup().getId()));
                responesdata.add(modelString);    
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
     @RequestMapping(value = {"api/send/mess"}, method = RequestMethod.POST)
     public ResponseEntity<List<ModelString>> SendMess(@RequestParam("token") String token,@RequestParam("id") String id,@RequestParam("content") String content) throws IOException {
        //Upload token
        Account accountfl = ia.findByTokenUser(token);
        int idgroup = Integer.parseInt(id);
        Groupchat gr = iGroupChat.FindById(idgroup);
        Message messsage = new Message();
        messsage.setContent(content);
        messsage.setIdAccount(accountfl);
        messsage.setIdGroup(gr);
        messsage.setCreateDate(new Date());
        iMessage.addMess(messsage);
        List<Message> mess = iMessage.FindByGroup(gr);
        List<ChatPeople> accl = gr.getChatPeopleList();
         for (ChatPeople account : accl) {

             if(account.getIdAccout()!=accountfl){
                 try {
                     Notifacation noti = new Notifacation();
                     noti.setTitle("New Message");
                     noti.setConntent("You have new message");
                     noti.setDatatran("1");
                     fms.sendDataPeople(noti, account.getIdAccout(), String.valueOf(gr.getId()));
                 } catch (FirebaseMessagingException ex) {
                     Logger.getLogger(MessageAPIController.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
        List<ModelString> responesdata = new ArrayList<ModelString>();
        
         
        //System.out.println(token);
        //update user photo
         for (Message s : mess){
                ModelString modelString = new ModelString();
                modelString.setData2(s.getIdAccount().getUsername());
                 modelString.setData3(s.getIdAccount().getAvatar());
                try {
                 modelString.setData4(ConditionDate(s.getCreateDate()));
                } catch (Exception e) {
                }
                modelString.setData5(s.getContent());
                modelString.setData6(s.getIdAccount().getToken());
                modelString.setData1(String.valueOf(s.getIdGroup().getId()));
                responesdata.add(modelString);    
         }
        return new ResponseEntity<>(responesdata, HttpStatus.OK);
    }
        @RequestMapping(value = {"api/get/no/group"}, method = RequestMethod.GET)
     public ResponseEntity<List<ModelString>> GetNoList(@RequestParam("token") String token,String id) throws IOException {
        //Upload token
        Account s1 = ia.findByTokenUser(token);
        List<Account> s2 = ia.findExTokenUser(token);
        List<ModelString> hi = new ArrayList<ModelString>();
        for(Account acc2 : s2){
                  if(CheckExitAccount(s1, acc2)==true){
                  ModelString modelString = new ModelString();
                  modelString.setData4(acc2.getAvatar());
                  modelString.setData2(acc2.getUsername());
                  modelString.setData3(acc2.getFulllName());
                  modelString.setData1(String.valueOf(acc2.getId()));
                  hi.add(modelString);
                  }
                  
              }
        return new ResponseEntity<>(hi, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/add/group"}, method = RequestMethod.GET)
    public ResponseEntity<ModelString> addgroup(@RequestParam("token") String token,@RequestParam("username")String username) throws IOException {
        //Upload token
        Account a = ia.findByTokenUser(token);
        Account v = ia.findByUserNameUser(username);
        Groupchat s1 = new Groupchat();
        s1.setGroupName(a.getUsername()+" & "+v.getUsername());
        s1.setType("1");
        s1.setSatus("1");
        Groupchat ress = iGroupChat.Addgroup(s1);
        ChatPeople ca = new ChatPeople();
        ca.setIdAccout(a);
        ca.setIdGroup(ress);
        ca.setCreateDate(new Date());
        ca.setType("1");
        ChatPeople cv = new ChatPeople();
        cv.setIdAccout(v);
        cv.setIdGroup(ress);
        cv.setCreateDate(new Date());
        cv.setType("0");
        iChatPeople.Join(ca);
        iChatPeople.Join(cv);
        ModelString resp = new ModelString();
        resp.setData1("oke");  
        resp.setData2(String.valueOf(ress.getId()));
        resp.setData3(v.getAvatar());
        resp.setData4(ress.getGroupName());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
     
     public boolean CheckExit(Account a,Account b){
        //Upload token
        List<Groupchat> list = iGroupChat.findAll();
        boolean check=true;
         for (Groupchat groupchat : list) {
             List<ChatPeople> listacc = groupchat.getChatPeopleList();
             boolean val1 = false;
             boolean val2=false;
             for (ChatPeople chatPeople : listacc) {
                 if(chatPeople.getIdAccout().equals(a)){
                 val1 = true;
                 }else if(chatPeople.getIdAccout().equals(a)){
                 val2 = true;
                 }
             }
             if(val1==true||val2==true){
             check=true;
             
             }else{
             check=false;
             }         
         }
         return check;
        
    }
    public boolean CheckExitAccount(Account a,Account b){
        //Upload token
        List<Groupchat> list = iGroupChat.findAll();
        int dem = 0;
         for (Groupchat groupchat : list) {
             if(iChatPeople.CheckAccount(a, groupchat)==false && iChatPeople.CheckAccount(b, groupchat)==false){
             dem++;
             }    
         }
         if(dem==0){
         return true;
         }else{
         return false;
         }
         
        
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
        String outdata = "now";
         return outdata;
        }
    }
}
