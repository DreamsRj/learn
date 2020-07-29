package com.dreams.rj.learn.mapper;

import com.dreams.rj.learn.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User selectOne(Long id);

    @Select("select * from user")
    List<User> selectAll();
}
