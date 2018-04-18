package com.infore.mapper;

import com.infore.model.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    User getUserByParams(@Param("loginName") String loginName,@Param("user") User user);

    List<User> queryUsersByParam(User record);
}
