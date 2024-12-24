package com.example.mybatis.exeption;

/**
 * ユーザが見つからない場合の例外
 */
public class UserNotFoundExeption extends Exception {

    public UserNotFoundExeption(String message) {
        super(message);
    }
}
