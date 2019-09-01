package com.shiro.mapper;

import com.shiro.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE NAME = #{name}")
    public User findByName(String name);

    @Select("SELECT * FROM USER WHERE id = #{id}")
    public User findById(Integer id);
}
