package com.example.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mybatis.dto.UserDto;
import com.example.mybatis.exception.DuplicateKeyException;
import com.example.mybatis.exception.UserNotFoundException;
import com.example.mybatis.model.UserLogic;

@Controller
public class MybatisController {

    @Autowired
    private UserLogic userLogic;

    UserDto userDto = new UserDto();

    /**
     * メイン画面表示
     */
    @GetMapping("/main")
    public String mybatis() {

        return "main";
    }

    /**
     * 全ユーザ表示
     */
    @GetMapping("/allUsers")
    public String allUsers(Model model) {

        List<UserDto> allUserList = userLogic.findAll();
        model.addAttribute("users", allUserList);
        return "allUsers";
    }

    /**
     * ユーザ検索画面表示
     */
    @GetMapping("/userSearch")
    public String userSearch() {

        return "search/userSearch";
    }

    /**
     * 検索結果表示
     */
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("id") int id, Model model) {
        
        try {
            userDto.setId(id);
            userDto = userLogic.findUser(userDto);
            model.addAttribute("user", userDto);
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "search/userInfo";
        }
        return "search/userInfo";
    }

    /**
     * ユーザ登録画面表示
     */
    @GetMapping("/userRegist")
    public String entry(Model model) {

        model.addAttribute("userDto", new UserDto());
        return "regist/userRegist";
    }

    /**
     * ユーザ登録完了画面表示
     */
    @PostMapping("/registComplete")
    public String regist(@ModelAttribute UserDto userDto) throws DuplicateKeyException {

        userLogic.insert(userDto);
        return "regist/registComplete";
    }

    /**
     * ユーザ削除画面表示
     */
    @GetMapping("/userDelete")
    public String delete() {

        return "delete/userDelete";
    }

    /**
     * ユーザ削除完了画面表示
     */
    @GetMapping("/deleteComplete")
    public String delete(@RequestParam("id") int id, Model model) {

        try {
            userDto.setId(id);
            userLogic.deleteUser(userDto);
        } catch (UserNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "delete/deleteComplete";
        }
        model.addAttribute("deleteMessage", "削除が完了しました。");
        return "delete/deleteComplete";
    }
}
