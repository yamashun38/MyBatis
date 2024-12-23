package com.example.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.mybatis.entity.UserEntity;

@Mapper
public interface UserMapper {

    /**
     * 全データを取得
     */
    List<UserEntity> findAll();

    /**
     * URLで指定したIDのデータを取得
     */
    UserEntity findUser(int id);
}
