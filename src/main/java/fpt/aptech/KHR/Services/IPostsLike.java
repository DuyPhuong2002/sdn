/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.PostLike;
import fpt.aptech.KHR.NewEntities.Posts;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IPostsLike {
    public boolean CheckExitLikePost(Account accout,Posts posts);
    public PostLike LikePosts(Account accout,Posts posts);
    public boolean UnLikePosts(Account accout,Posts posts);
}
