/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import fpt.aptech.KHR.Entities.ModelString;
import fpt.aptech.KHR.Entities.RandomString;
import fpt.aptech.KHR.FileUpload.FileUploadUtil;
import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.NewEntities.Reels;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.IPostComment;
import fpt.aptech.KHR.Services.IPosts;
import fpt.aptech.KHR.Services.IPostsLike;
import fpt.aptech.KHR.Services.IReels;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class ReelsAPIController {
    @Autowired
    IPosts iPosts;
    @Autowired
    IAccount iAccount;
    @Autowired
    IPostsLike iPostsLike;
    @Autowired
    IPostComment ipc; 
    @Autowired
    IReels ir; 
    @RequestMapping(value = {"api/new/reels"}, method = RequestMethod.POST)
    public ResponseEntity<ModelString> NewPosts(@RequestParam("reels") MultipartFile file, @RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("token") String token) throws IOException {
        //Upload file to source static image
        // String easy = RandomString.digits + fileName;    
        RandomString session = new RandomString();
        String uploadDir = "src/main/resources/static/images/video/";
        Date d = new Date();
        String fildeDB = session.nextString()+".mp4";
        FileUploadUtil.saveFile(uploadDir,fildeDB , file);
        //update user photo
       
        Reels post = new Reels();
        Account b = iAccount.findByTokenUser(token);
        post.setTitle(title);
        post.setContent(content);
        post.setVideo(fildeDB);
        post.setIdAccount(b);
        Date date = new Date();
        post.setCreateDate(date);
        Reels postend = ir.NewReels(post);
        ModelString modelString = new ModelString();
        if(postend!=null){
        modelString.setData1("oke Bro");
        }
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/get/reels"}, method = RequestMethod.GET)
    public ResponseEntity<List<ModelString>> GetListPost(@RequestParam("token") String token) throws IOException {
        //Upload file to source static image
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // String easy = RandomString.digits + fileName;    
//        RandomString session = new RandomString();
//        String uploadDir = "src/main/resources/static/images/posts/";
//        FileUploadUtil.saveFile(uploadDir, session.nextString()+fileName, file);
        //update user photo
        //Posts post = new Posts();
        Account b = iAccount.findByTokenUser(token);
        List<Reels> l = ir.GetListPost();
        List<ModelString> list = new ArrayList<>();
        if(b!=null){
            for (Reels post : l) {
                try {
                    ModelString inData = new ModelString();
                    inData.setData1(post.getId().toString());
                    inData.setData2(post.getIdAccount().getUsername());
                    String savatar = post.getIdAccount().getAvatar();
                    if(savatar!=null){
                    inData.setData3(post.getIdAccount().getAvatar());
                    }else{
                    inData.setData3("defaultUserIcon.png");
                    }
                    
                    String date = ConditionDate(post.getCreateDate());
                    inData.setData4(date);
                    inData.setData5(post.getTitle());
                    inData.setData6(post.getContent());
                    String s = String.valueOf(post.getReelsLikeList().size());
                    inData.setData7(s);
//                    inData.setData8(String.valueOf(iPostsLike.CheckExitLikePost(b, post)));
                    inData.setData9(post.getVideo());
                    inData.setData10("false");
                    list.add(inData);
                } catch (ParseException ex) {
                    Logger.getLogger(PostsAPIController.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/get/reels/main"}, method = RequestMethod.GET)
    public ResponseEntity<List<ModelString>> GetListReelsMain(@RequestParam("token") String token) throws IOException {
        //Upload file to source static image
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // String easy = RandomString.digits + fileName;    
//        RandomString session = new RandomString();
//        String uploadDir = "src/main/resources/static/images/posts/";
//        FileUploadUtil.saveFile(uploadDir, session.nextString()+fileName, file);
        //update user photo
        //Posts post = new Posts();
        List<Account> b = iAccount.findExTokenUser(token);
        List<ModelString> list = new ArrayList<>();
        if(b!=null){
            for (Account acc : b) {
                if(acc.getReelsList().size()>0)
                try {
                    ModelString inData = new ModelString();
                    inData.setData1(String.valueOf(acc.getReelsList().size()));
                    inData.setData2(acc.getUsername());
                    String savatar = acc.getAvatar();
                    if(savatar!=null){
                    inData.setData3(acc.getAvatar());
                    }else{
                    inData.setData3("defaultUserIcon.png");
                    }
                    list.add(inData);
                } catch (Exception ex) {
                    Logger.getLogger(PostsAPIController.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
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
        if(diffHours<12 && diffHours > 0){
         String outdata = String.valueOf(diffHours) + " hours";
         return outdata;
        }
        if(diffDay<7 && diffDay > 0){
         String outdata = String.valueOf(diffDay) + " day";
         return outdata;
        }
        if(diffWeek<4 && diffWeek > 0){
         String outdata = String.valueOf(diffWeek) + " week";
         return outdata;
        }
        if(diffMounth < 12 && diffMounth > 0){
         String outdata = String.valueOf(diffMounth) + " mounth";
         return outdata;
        }else{
        String outdata = String.valueOf(diffYear) + " year";
         return outdata;
        }
    }
}
    