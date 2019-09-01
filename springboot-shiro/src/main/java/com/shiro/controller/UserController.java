package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }
    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/")
    @ResponseBody
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "/noAuth";
    }

    @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("name","土豆");
        //返回test.html
        return "test";
    }

    //登出
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "成功登出";
    }

    /**
     * 登录逻辑处理
     */
    @RequestMapping("/login")
    public String login(String name,String password,Model model){
//        System.out.println("name="+name+"   password="+password);
        /**
         * 使用shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            return "redirect:/testThymeleaf";
        } catch (UnknownAccountException e) {
//            e.printStackTrace();
            //登录失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
