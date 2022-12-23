/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.IPosts;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class PostsController {
    @Autowired
    IPosts ip;
    
    @RequestMapping(value = {"posts/index"}, method = RequestMethod.GET)
    public String AccountList(Model model, @Param("keyword") String keyword ,HttpServletResponse response, HttpServletRequest request) {
        request.setAttribute("sidebar","2");
        //HttpSession session = request.getSession();
        // int IdStore = Integer.parseInt(session.getAttribute("IdStore").toString());
        List<Posts> list1 = ip.GetListPost();
        List<Posts> list = new ArrayList<>();

        if (list1.size() > 0) {

            for (Posts item : list1
            ) {
              
                        list.add(item);
            }

        }

        boolean check = false;
        for (Posts item : list) {
            if (item.getId()!= null) {
                check = true;
                break;
            }
        }
        
        model.addAttribute("list", list);
        model.addAttribute("check", check);
        model.addAttribute("keyword", keyword);
        return "admin/posts/index";
    }
}
