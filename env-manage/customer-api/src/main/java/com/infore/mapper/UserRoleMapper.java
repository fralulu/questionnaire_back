package com.infore.mapper;

import com.infore.model.UserRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {

    int deleteByPrimaryKey(@Param("roleId") Integer roleId, @Param("userId") Integer userId);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(@Param("roleId") Integer roleId, @Param("userId") Integer userId);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<UserRole> queryByParam(UserRole record);

    int countByUserId(@Param("userId") Integer userId);

    int deleteByUserId(@Param("userId") Integer userId);

    int batchInsert(List<UserRole> userRoles);
}