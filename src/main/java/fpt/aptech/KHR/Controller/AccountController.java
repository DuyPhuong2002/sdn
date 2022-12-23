/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import fpt.aptech.KHR.Entities.ModelString;
import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.NewEntities.Posts;
import fpt.aptech.KHR.NewEntities.Reels;
import fpt.aptech.KHR.Routes.RouteWeb;
import fpt.aptech.KHR.Services.IAccount;
import fpt.aptech.KHR.Services.IReels;
import fpt.aptech.KHR.Services.ISendMail;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class AccountController {
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    IAccount ia;
    @Autowired
    ISendMail sendMail;
    @Autowired
    IReels ir;
    @RequestMapping(value = {"/account/index"}, method = RequestMethod.GET)
    public String AccountList(Model model, @Param("keyword") String keyword ,HttpServletResponse response, HttpServletRequest request) {
        request.setAttribute("sidebar","2");
        //HttpSession session = request.getSession();
        // int IdStore = Integer.parseInt(session.getAttribute("IdStore").toString());
        List<Account> list1 = ia.findAll();
        List<Account> list = new ArrayList<>();

        if (list1.size() > 0) {

            for (Account item : list1
            ) {
                if (item.getRole().equals("user")) {
                        list.add(item);
                }
            }

        }

        boolean check = false;
        for (Account item : list) {
            if (item.getId()!= null) {
                check = true;
                break;
            }
        }
        
        model.addAttribute("list", list);
        model.addAttribute("check", check);
        model.addAttribute("keyword", keyword);
        return "admin/account/index";
    }   
    @RequestMapping(value = {"/account/recovery/code"}, method = RequestMethod.GET)
    public String SendRecoverCode(Model model,HttpServletResponse response, HttpServletRequest request) {
        String username = request.getParameter("username");
        sendMail.sendRecoveryCode(username);
        return "admin/account/index";
    }
    @RequestMapping(value = {"/account/look"}, method = RequestMethod.GET)
    public String LockAccount(Model model,HttpServletResponse response, HttpServletRequest request) {
        String username = request.getParameter("username");
        Account a = ia.lockactive(username);
        if(a.getStatus()==0){
        a.setStatus(2);
        ia.save(a);}
        else if(a.getStatus()==2){
        a.setStatus(0);
        ia.save(a);
        }
        return "redirect:index";
    }
    @RequestMapping(value = {"/account/details"}, method = RequestMethod.GET)
    public String AccountDetails(Model model, @Param("keyword") String keyword ,HttpServletResponse response, HttpServletRequest request) {
        request.setAttribute("sidebar","2");
        String a = request.getParameter("username");
        Account acc =ia.findByUserNameUser(a);
        //HttpSession session = request.getSession();
        // int IdStore = Integer.parseInt(session.getAttribute("IdStore").toString());
        List<Posts> list = acc.getPostsList();
        boolean check = false;
        for (Posts item : list) {
            if (item.getId()!= null) {
                check = true;
                break;
            }
        }
        List<Reels> list2 = acc.getReelsList();
        boolean check2 = false;
        for (Reels item1 : list2) {
            if (list2.size()> 0) {
                check2 = true;
                break;
            }
        }
         model.addAttribute("acc", acc);
        model.addAttribute("list", list);
        model.addAttribute("list2", list2);
        model.addAttribute("check", check);
        model.addAttribute("check2", check2);
        model.addAttribute("keyword", keyword);
        return "admin/account/details";
    }
    @RequestMapping(value = {"/account/search"}, method = RequestMethod.GET)
    public String AccountSearch(Model model, @Param("keyword") String keyword ,HttpServletResponse response, HttpServletRequest request) {
        String colum = request.getParameter("colum");
        List<Account> list1 = ia.search(colum);
        List<Account> list = new ArrayList<>();

        if (list1.size() > 0) {

            for (Account item : list1
            ) {
                if (item.getRole().equals("user")) {
                        list.add(item);
                }
            }

        }

        boolean check = false;
        for (Account item : list) {
            if (item.getId()!= null) {
                check = true;
                break;
            }
        }
        
        model.addAttribute("list", list);
        model.addAttribute("check", check);
        model.addAttribute("keyword", keyword);
        return "admin/account/index";
    }

}
