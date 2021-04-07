package com.example.childcareHelp.controller;

import com.example.childcareHelp.DTO.LoginInfoDto;
import com.example.childcareHelp.DTO.UserInfoDto;
import com.example.childcareHelp.entity.Babysitter;
import com.example.childcareHelp.entity.Family;
import com.example.childcareHelp.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String doLogin(@ModelAttribute("loginInfo") LoginInfoDto loginInfoDto, Model model, HttpServletRequest req) {

        System.out.println("[LOG]_doLogin_userType="+ loginInfoDto.getUserType());

        String landingPage = "login";

        HttpSession session = req.getSession();
        UserInfoDto userInfoDto = new UserInfoDto();

        if(loginInfoDto.getUserType() == 1){ //babysitter
            Babysitter babysitter = loginService.doLoginForBabysitter(loginInfoDto.getEmail(), loginInfoDto.getPassword());
            if(babysitter != null) {
                userInfoDto.setId(babysitter.getSnn());
                userInfoDto.setEmail(babysitter.getEmail());
                userInfoDto.setUserType(1);
                session.setAttribute("USER_INFO", userInfoDto);
                System.out.println("[LOG]_doLogin_Succeed_Babysitter");

                landingPage = "redirect:/contract/listOfAcceptContracts";
            }else{
                System.out.println("[LOG]_doLogin_Failed_Babysitter");
            }
        }else{  //family

            Family family = loginService.doLoginForFamily(loginInfoDto.getEmail(), loginInfoDto.getPassword());
            if(loginService.doLoginForFamily(loginInfoDto.getEmail(), loginInfoDto.getPassword()) != null) {
                userInfoDto.setId(family.getFamilyID());
                userInfoDto.setEmail(family.getEmail());
                userInfoDto.setUserType(0);
                session.setAttribute("USER_INFO", userInfoDto);
                System.out.println("[LOG]_doLogin_Succeed_Family");
                landingPage = "redirect:/babysitter/listOfBabysitters";
            }else{
                System.out.println("[LOG]_doLogin_Failed_Family");
            }
        }

        System.out.println("[LOG]_doLogin_return");
        model.addAttribute("loginInfo", loginInfoDto);

        return landingPage;
    }
    //testing
    /*
     * execute Logout
     */
    @RequestMapping("logout")
    public String doLogout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        session.invalidate();
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
