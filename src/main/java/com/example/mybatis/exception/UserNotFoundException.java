package com.example.mybatis.exception;

/**
 * ユーザが見つからない場合の例外
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
