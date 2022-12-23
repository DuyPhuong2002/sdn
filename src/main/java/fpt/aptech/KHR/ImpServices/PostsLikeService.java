/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.PostLike;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.Repository.PostLikeRepository;
import fpt.aptech.KHR.Services.IPostsLike;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class PostsLikeService implements IPostsLike{
    @Autowired
    PostLikeRepository repository;
    @Override
    public boolean CheckExitLikePost(Account accout, Posts posts) {
        PostLike a = repository.findPostLike(accout, posts);
        if(a!=null) {
            return true;
            
        }else{
            return false;
        }
    }

    @Override
    public PostLike LikePosts(Account accout, Posts posts) {
        PostLike a = new PostLike();
        a.setIdAccount(accout);
        a.setIdPosts(posts);
        a.setTotalScore(0);
        Date date = new Date();
        a.setCreatDate(date);
        PostLike resuls = repository.save(a);
        return resuls;
    }

    @Override
    public boolean UnLikePosts(Account accout, Posts posts) {
        PostLike a = repository.findPostLike(accout, posts);
        if(a!=null) {
            repository.delete(a);
            return true;
            
        }else{
            return false;
        }
    }
    
}
