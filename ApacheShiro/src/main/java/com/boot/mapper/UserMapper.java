package com.boot.mapper;

import com.boot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select(" SELECT *  " +
            " FROM USER " +
            " WHERE username=#{username}")
    User findByUserName(@Param("username") String username);

    @Select(" SELECT p.`name` " +
            " FROM USER u " +
            " INNER JOIN user_role ur ON u.`uid`=ur.`uid` " +
            " INNER JOIN role r ON ur.`rid`=r.`rid` " +
            " INNER JOIN permission_role pr ON pr.`rid`=r.`rid` " +
            " INNER JOIN permission p ON pr.`pid`=p.`pid` " +
            " WHERE u.`username`=#{username}")
    List<String> getPermissionsByUsername(@Param("username") String username);

    @Select("SELECT r.`rname` " +
            " FROM USER u " +
            " INNER JOIN user_role ur ON u.`uid`=ur.`uid` " +
            " INNER JOIN role r ON ur.`rid`=r.`rid` " +
            " WHERE u.`username`=#{username}")
    List<String> getRoleNameByUsername(@Param("username") String username);
}
