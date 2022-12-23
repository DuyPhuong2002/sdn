/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Services;

import fpt.aptech.KHR.NewEntities.PostComment;
import fpt.aptech.KHR.NewEntities.Posts;
import java.util.List;

/**
 *
 * @author LÊ HỮU TÂM
 */
public interface IPostComment {
    List<PostComment> addCommennt(Posts post);
    void addCommennt(PostComment comment);
    void RemoveCommennt(PostComment comment);
    void deleteCommennt(PostComment comment);
    PostComment findbyid(int id);
}
