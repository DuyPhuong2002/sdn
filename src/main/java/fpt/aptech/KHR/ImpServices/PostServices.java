/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.PostLike;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.Repository.PostsRepository;
import fpt.aptech.KHR.Services.IPosts;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class PostServices implements IPosts{
    @Autowired
    PostsRepository ps;
    
    @Override
    public Posts Newpost(Posts newpPosts) {
        return ps.save(newpPosts);
    }

    @Override
    public PostLike Likepost(PostLike likePosts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Posts> GetListPost() {
       return ps.findAll();
    }

    @Override
    public Posts FindByIdPost(int id) {
        return ps.findById(id);
    }

    
}
