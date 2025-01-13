package com.example.mybatis.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.mybatis.dto.UserDto;
import com.example.mybatis.entity.UserEntity;
import com.example.mybatis.exception.DuplicateKeyException;
import com.example.mybatis.exception.UserNotFoundException;
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
     * findUserメソッドを呼び出し
     * URLで指定したIDのデータを取得
     */
    public UserDto findUser(UserDto userDto) throws UserNotFoundException {

        UserEntity userEntity = userMapper.findUser(userDto.getId());

        if (userEntity == null) {
            throw new UserNotFoundException("指定したユーザは存在しません。");
        }
        return toDto(userDto, userEntity);
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

    /**
     * insertメソッドを呼び出し
     * データを登録
     */
    public void insert(UserDto userDto) {

        UserEntity userEntity = toEntity(userDto);
        userMapper.insert(userEntity);
    }

    /**
     * UserDtoからUserEntityに変換
     */
    public UserEntity toEntity(UserDto userDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setAge(userDto.getAge());
        userEntity.setAddress(userDto.getAddress());
        return userEntity;
    }

    /**
     * deleteメソッドを呼び出し
     * データを削除
     */
    public void deleteUser(UserDto userDto) throws UserNotFoundException {

        int deleteCount = userMapper.delete(userDto.getId());
        if(deleteCount == 0) {
            throw new UserNotFoundException("指定したユーザは存在しません。");
        }
    }

    /**
     * updateメソッドを呼び出し
     */
    public void updateUser(UserDto userDto) {

        UserEntity userEntity = toEntity(userDto);
        userMapper.update(userEntity);
    }
}
