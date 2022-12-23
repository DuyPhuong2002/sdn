/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.ImpServices;

import fpt.aptech.KHR.NewEntities.PostComment;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.Repository.PostCommentRepository;
import fpt.aptech.KHR.Services.IPostComment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Service
public class PostCommentService implements IPostComment{
    @Autowired
    PostCommentRepository pcr;
    @Override
    public List<PostComment> addCommennt(Posts post) {
        return pcr.findPostLike(post);
    }

    @Override
    public void addCommennt(PostComment comment) {
        pcr.save(comment);
    }

    @Override
    public void RemoveCommennt(PostComment comment) {
        pcr.delete(comment);
    }

    @Override
    public void deleteCommennt(PostComment comment) {
        pcr.delete(comment);
    }

    @Override
    public PostComment findbyid(int id) {
        return pcr.findbyID(id);
    }
    
}
