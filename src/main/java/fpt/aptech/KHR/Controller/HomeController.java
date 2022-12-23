/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KHR.Controller;

import fpt.aptech.KHR.NewEntities.Account;
import fpt.aptech.KHR.Routes.RouteWeb;
import fpt.aptech.KHR.Services.IAccount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author LÊ HỮU TÂM
 */
@Controller
public class HomeController {
    
    @Autowired
    IAccount ia;
    @RequestMapping(value = {"/home/index"}, method = RequestMethod.GET)
    public String Index(Model model, HttpServletRequest request, HttpServletResponse response) {


        request.setAttribute("sidebar","1");


        String idUser = request.getRemoteUser();
        Account account = ia.findByUserNameAdmin(idUser);

        if (account.getStatus()== 1) {
            return "redirect:/logout";
        }


        switch (account.getRole()) {
            case "0":
                return "redirect:/logout";
            case "admin":

                HttpSession session = request.getSession();
//                session.setAttribute("IdStore", account.getIdStore().getId());
//                session.setAttribute("NameStore", account.getIdStore().getNameStore());

                return "index";
            case "2":
                return "redirect:/logout";
        }


        return "index";

    }
    @RequestMapping(value = {"/redirectlogout"}, method = RequestMethod.GET)
        public String RedirectLogout(Model model, HttpServletResponse response, HttpServletRequest request) {

        HttpSession session = request.getSession();
        //session.removeAttribute("IdStore");
        

        String redirectUrl = "/logout";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    @GetMapping("/login")
    public String Login(Model model) {

        return "login";
    }
    

    
}
