/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.Reels;
import fpt.aptech.KHR.NewEntities.ReelsLike;
import fpt.aptech.KHR.Repository.ReelsRepository;
import fpt.aptech.KHR.Services.IReels;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class ReelsService implements IReels{
    @Autowired
    ReelsRepository rr;
    
    @Override
    public Reels NewReels(Reels newpPosts) {
        return rr.save(newpPosts);
    }

    @Override
    public ReelsLike LikeReels(ReelsLike likePosts) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reels> GetListPost() {
        return rr.findAll();
    }

    @Override
    public Reels FindByIdReels(int id) {
        return rr.findById(id);
    }
    
}
