package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Child;
import com.example.childcareHelp.entity.Contract;
import com.example.childcareHelp.entity.Family;
import com.example.childcareHelp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*
     * execute Login
     */
    @RequestMapping("login")
    public String doLogin(@ModelAttribute("loginInfo") LoginInfo loginInfo,
                          Model model, HttpServletRequest req) {

        System.out.println("[LOG]_doLogin_userType="+loginInfo.getUserType());
        //test 2
        HttpSession session = req.getSession();

        String landingPage = "login";

        if(loginInfo.getUserType() == 1){ //babysitter
            if(loginService.doLoginForBabysitter(loginInfo.getEmail(), loginInfo.getPassword()) != null) {
                System.out.println("[LOG]_doLogin_Succeed_Babysitter");

                session.setAttribute("USER_INFO", loginInfo);
                landingPage = "contract/listOfAcceptContracts";
            }else{
                System.out.println("[LOG]_doLogin_Failed_Babysitter");
            }
        }else{  //family

            if(loginService.doLoginForFamily(loginInfo.getEmail(), loginInfo.getPassword()) != null) {
                System.out.println("[LOG]_doLogin_Succeed_Family");
                session.setAttribute("USER_INFO", loginInfo);
                landingPage = "babysitter/listOfBabysitters";
            }else{
                System.out.println("[LOG]_doLogin_Failed_Family");
            }
        }

        System.out.println("[LOG]_doLogin_return");
        model.addAttribute("loginInfo", loginInfo);

        /*
        String loginType = (String) req.getAttribute("loginType");
        String email = (String) req.getAttribute("email");
        String password = (String) req.getAttribute("password");
        if (loginType != null && loginType.equals("FAMILY")) {
            Family family = loginService.doLoginForFamily(email, password);
            session.setAttribute("USER_INFO", family);
            landingPage = "babysitter/listOfBabysitter";
        } else {
            Babysitter babysitter = loginService.doLoginForBabysitter(email, password);
            session.setAttribute("USER_INFO", babysitter);
            landingPage = "contract/listOfAcceptContracts";
        }
        //landingPage = "babysitter/listOfBabysitters";
        //landingPage = "contract/listOfAcceptContracts";
        landingPage = "login";
         */
        return landingPage;
    }
    //testing
    /*
     * execute Logout
     */
    @RequestMapping("logout")
    public String doLogout() {

        loginService.doLogout();
        return "login";
    }

    /*
     * Welcome page
     */
    @RequestMapping("")
    public String goMain() {
        return "main";
    }
}
