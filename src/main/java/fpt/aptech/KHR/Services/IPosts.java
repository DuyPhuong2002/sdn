/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.PostLike;
import fpt.aptech.KHR.NewEntities.Posts;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IPosts {
   public Posts Newpost(Posts newpPosts);
   public PostLike Likepost(PostLike likePosts);
   public List<Posts> GetListPost();
   public Posts FindByIdPost(int id);
    
}
