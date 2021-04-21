package com.nagarro.hrmanagement.controller;

import com.nagarro.hrmanagement.model.User;
import com.nagarro.hrmanagement.service.EmployeeService;
import com.nagarro.hrmanagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {


    @Autowired
    private LoginService loginService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/Login")
    public String getLoginPage(@ModelAttribute("user")User user){
        return "Login";
    }

    @PostMapping("/Login")
    public String verifyUser(@ModelAttribute("user")User user,HttpServletRequest request){
        User validUser = loginService.validateUser(user);
        if(validUser !=null){
            request.getSession().setAttribute("user",validUser.getFullName());
            return "redirect:EmployeeManager";
        }
        return "redirect:Login";
    }

    @GetMapping("/Logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        return "redirect:Login";
    }
}
