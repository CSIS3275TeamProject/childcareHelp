package com.example.childcareHelp.controller;

import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Family;
import com.example.childcareHelp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /*
     * execute Login
     */
    @RequestMapping("login")
    public String doLogin(ModelAndView model, HttpServletRequest req) {
        //test
        HttpSession session = req.getSession();
        String landingPage = "";
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
        landingPage = "contract/listOfAcceptContracts";
        return landingPage;
    }
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
        return "login";
    }
}
