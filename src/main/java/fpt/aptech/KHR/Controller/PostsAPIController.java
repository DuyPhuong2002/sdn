/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import fpt.aptech.KHR.Entities.ModelString;
import fpt.aptech.KHR.Entities.RandomString;
import fpt.aptech.KHR.FileUpload.FileUploadUtil;
import fpt.aptech.KHR.ImpServices.FirebaseMessagingService;
import fpt.aptech.KHR.ImpServices.JsonServices;
import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.PostComment;
import fpt.aptech.KHR.NewEntities.PostLike;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.IPostComment;
import fpt.aptech.KHR.Services.IPosts;
import fpt.aptech.KHR.Services.IPostsLike;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
public class PostsAPIController {
    @Autowired
    IPosts iPosts;
    @Autowired
    IAccount iAccount;
    @Autowired
    IPostsLike iPostsLike;
    @Autowired
    IPostComment ipc;
    @Autowired
    FirebaseMessagingService fms ;
    @RequestMapping(value = {"api/new/posts"}, method = RequestMethod.POST)
    public ResponseEntity<ModelString> NewPosts(@RequestParam("avatar") MultipartFile file, @RequestParam("title") String title,@RequestParam("content") String content,@RequestParam("token") String token) throws IOException {
        //Upload file to source static image
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        // String easy = RandomString.digits + fileName;    
        RandomString session = new RandomString();
        String uploadDir = "src/main/resources/static/images/posts/";
        Date d = new Date();
        String fildeDB = session.nextString()+fileName;
        FileUploadUtil.saveFile(uploadDir,fildeDB , file);
        //update user photo
       
        Posts post = new Posts();
        Account b = iAccount.findByTokenUser(token);
        post.setTitle(title);
        post.setContent(content);
        post.setImage(fildeDB);
        post.setIdAccount(b);
        Date date = new Date();
        post.setCreateDate(date);
        Posts postend = iPosts.Newpost(post);
        ModelString modelString = new ModelString();
        if(postend!=null){
        modelString.setData1("oke Bro");
        }
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    
    @RequestMapping(value = {"api/get/posts"}, method = RequestMethod.GET)
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
        List<Posts> l = iPosts.GetListPost();
        List<ModelString> list = new ArrayList<>();
        if(b!=null){
            for (Posts post : l) {
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
                    String s = String.valueOf(post.getPostLikeList().size());
                    inData.setData7(s);
                    inData.setData8(String.valueOf(iPostsLike.CheckExitLikePost(b, post)));
                    inData.setData9(post.getImage());
                    inData.setData10("false");
                    list.add(inData);
                } catch (ParseException ex) {
                    Logger.getLogger(PostsAPIController.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    
        @RequestMapping(value = {"api/like/posts"}, method = RequestMethod.POST)
        public ResponseEntity<ModelString> Likeposts(@RequestParam("posts") String posts,@RequestParam("token") String token) throws IOException {
            int id = Integer.valueOf(posts);

            Posts post = iPosts.FindByIdPost(id);
            Account b = iAccount.findByTokenUser(token);
            PostLike like = iPostsLike.LikePosts(b, post);
            ModelString modelString = new ModelString();
            if(like!=null){
            modelString.setData1("oke Bro");
            }else{
             modelString.setData1("Fails");
            }

            return new ResponseEntity<>(modelString, HttpStatus.OK);
        }
    
    
    @RequestMapping(value = {"api/unlike/posts"}, method = RequestMethod.POST)
    public ResponseEntity<ModelString> UnLikePosts(@RequestParam("posts") String posts,@RequestParam("token") String token) throws IOException {
        int id = Integer.valueOf(posts);
        
        Posts post = iPosts.FindByIdPost(id);
        Account b = iAccount.findByTokenUser(token);
        boolean like = iPostsLike.UnLikePosts(b, post);
        ModelString modelString = new ModelString();
        if(like==true){
        modelString.setData1("oke Bro");
        }else{
         modelString.setData1("Fails");
        }
        
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/like/posts/total"}, method = RequestMethod.GET)
    public ResponseEntity<ModelString> TotalLikePosts(@RequestParam("posts") String posts) throws IOException {
        int id = Integer.valueOf(posts); 
        Posts post = iPosts.FindByIdPost(id);
        ModelString modelString = new ModelString();
        if(post!=null){
        modelString.setData1("oke Bro");
        modelString.setData2(String.valueOf(post.getPostLikeList().size()));
        }else{
         modelString.setData1("Fails");
        }
        
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/posts/commment"}, method = RequestMethod.GET)
    public ResponseEntity<List<ModelString>> PostsComment(@RequestParam("posts") String posts) throws IOException {
        int id = Integer.valueOf(posts); 
        Posts post = iPosts.FindByIdPost(id);
        List<PostComment> postComments = post.getPostCommentList();
        List<ModelString> l = new ArrayList<>();
        for (PostComment sa : postComments ){
             ModelString modelString = new ModelString();
             modelString.setData1(String.valueOf(sa.getId()));
             modelString.setData2(sa.getIdAccount().getUsername());
             modelString.setData3(sa.getIdAccount().getAvatar());
             modelString.setData5(sa.getContent());
             modelString.setData6(sa.getIdAccount().getToken());
             try {
                modelString.setData4(ConditionDate(sa.getCreateDate()));
            } catch (Exception e) {
                modelString.setData4("???");
            }
            l.add(modelString);
        }
            
        return new ResponseEntity<>(l, HttpStatus.OK);
    }
    @RequestMapping(value = {"api/add/posts/commment"}, method = RequestMethod.POST)
    public ResponseEntity<ModelString> AddComments(@RequestBody ModelString ms,HttpServletResponse response) throws IOException {
//        System.out.print(ms.getData4());
//         System.out.print(ms.getData2());
        int idpost = Integer.valueOf(ms.getData1());
        String token = String.valueOf(ms.getData2()); 
        Account a = iAccount.findByTokenUser(token);
        Posts p = iPosts.FindByIdPost(idpost);
        PostComment comment = new PostComment();
        comment.setIdAccount(a);
        Date ad = new Date();
        comment.setCreateDate(ad);
        comment.setIdPosts(p);        
        comment.setContent(ms.getData4());
        ipc.addCommennt(comment);
        ModelString modelString = new ModelString();
        modelString.setData1(String.valueOf(comment.getId()));
        modelString.setData2(comment.getContent());
        modelString.setData3(comment.getIdAccount().getUsername());
        modelString.setData4(comment.getIdAccount().getAvatar());
             try {
                modelString.setData5(ConditionDate(comment.getCreateDate()));
            } catch (ParseException e) {
                modelString.setData9("???");
            }
        return new ResponseEntity<>(modelString, HttpStatus.OK);
    }
//        @RequestMapping(value = {"api/posts/commment"}, method = RequestMethod.GET)
//    public ResponseEntity<List<ModelString>> PostsComment(@RequestParam("posts") String posts) throws IOException {
//        int id = Integer.valueOf(posts); 
//        Posts post = iPosts.FindByIdPost(id);
//        List<PostComment> postComments = post.getPostCommentList();
//        List<ModelString> l = new ArrayList<>();
//        for (PostComment sa : postComments ){
//             ModelString modelString = new ModelString();
//             modelString.setData1(String.valueOf(sa.getId()));
//             modelString.setData2(sa.getIdAccount().getUsername());
//             modelString.setData3(sa.getIdAccount().getAvatar());
//             modelString.setData3(sa.getContent());
//             try {
//                modelString.setData4(ConditionDate(sa.getCreateDate()));
//            } catch (Exception e) {
//                modelString.setData4("???");
//            }
//             
//             l.add(modelString);
//        }
//            
//        return new ResponseEntity<>(l, HttpStatus.OK);
//    }
//    
//    
    
    
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
