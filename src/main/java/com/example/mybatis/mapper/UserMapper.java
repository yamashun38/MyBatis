package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.entity.UserEntity;
import com.example.mybatis.exception.UserNotFoundException;

@Mapper
public interface UserMapper {

    /**
     * 全データを取得
     */
    List<UserEntity> findAll();

    /**
     * 入力フォームで指定したIDのデータを取得
     */
    UserEntity findUser(int id) throws UserNotFoundException;

    /**
     * データを登録
     */
    void insert(UserEntity userEntity);

    /**
     * データを削除
     */
    int delete(int id) throws UserNotFoundException;

    /**
     * データを更新
     */
    void update(UserEntity userEntity);
}
