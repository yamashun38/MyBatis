package com.example.mybatis.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mybatis.dto.UserDto;
import com.example.mybatis.entity.UserEntity;
import com.example.mybatis.exeption.UserNotFoundExeption;
import com.example.mybatis.mapper.UserMapper;

@Component
public class UserLogic {

    @Autowired
    private UserMapper userMapper;

    /**
     * findAllメソッドを呼び出し
     * USERSテーブルの全てのデータを取得
     */
    public List<UserDto> findAll() {

        List<UserEntity> userEntities = userMapper.findAll();
        return toDto(userEntities);
    }

    /**
     * findUserメソッドを呼び出し
     * URLで指定したIDのデータを取得
     */
    public UserDto findUser(UserDto userDto) throws UserNotFoundExeption {

        UserEntity userEntity = userMapper.findUser(userDto.getId());

        if (userEntity == null) {
            throw new UserNotFoundExeption("指定したユーザは存在しません。");
        }
        return toDto(userDto, userEntity);
    }

    /**
     * UserEntityからUserDtoに変換
     */
    public List<UserDto> toDto(List<UserEntity> userEntities) {

        List<UserDto> allUsers = new ArrayList<>();
        for (UserEntity entity: userEntities) {
            UserDto userDto = new UserDto();
            userDto.setId(entity.getId());
            userDto.setUsername(entity.getUsername());
            userDto.setAge(entity.getAge());
            userDto.setAddress(entity.getAddress());
            allUsers.add(userDto);
        }
        return allUsers;
    }

    /**
     * UserEntityからUserDtoに変換
     */
    public UserDto toDto(UserDto userDto, UserEntity userEntity) {

        userDto.setUsername(userEntity.getUsername());
        userDto.setAge(userEntity.getAge());
        userDto.setAddress(userEntity.getAddress());
        return userDto;
    }
}
