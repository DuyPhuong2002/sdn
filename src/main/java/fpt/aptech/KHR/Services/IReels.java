/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.Reels;
import fpt.aptech.KHR.NewEntities.ReelsLike;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IReels {
   public Reels NewReels(Reels newpPosts);
   public ReelsLike LikeReels(ReelsLike likePosts);
   public List<Reels> GetListPost();
   public Reels FindByIdReels(int id);
   
    
}
