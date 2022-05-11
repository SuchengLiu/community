package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper//MyBatis的注解，实际上用Repository注解也可以
public interface UserMapper {

    User selectedById(int id);

    User selectedByName(String username);

    User selectedByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id,int status);

    int updateHeader(int id,String headerUrl);

    int updatePassword(int id,String password);
}
