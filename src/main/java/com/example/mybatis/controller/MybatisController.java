package com.example.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mybatis.dto.UserDto;
import com.example.mybatis.model.UserLogic;

@Controller
public class MybatisController {

    @Autowired
    private UserLogic userLogic;

    UserDto userDto;

    public MybatisController() {
        this.userDto = new UserDto();
    }

    @GetMapping("/mybatis")
    public String mybatis() {
        return "index";
    }

    @GetMapping("/users")
    public String allUsers(Model model) {

        List<UserDto> allUserList = userLogic.findAll();
        model.addAttribute("users", allUserList);
        return "allUsers";
    }

    @GetMapping("/users/{id}")
    public String user(@PathVariable("id") int id, Model model) {
        
        userDto.setId(id);
        userDto = userLogic.findUser(userDto);
        model.addAttribute("user", userDto);
        return "user";
    }

}
