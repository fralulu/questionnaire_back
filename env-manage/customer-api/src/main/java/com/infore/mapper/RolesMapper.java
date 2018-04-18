package com.infore.mapper;

import com.infore.model.Roles;
import java.util.List;

public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    List<Roles> queryByParam(Roles record);

    List<Roles> queryByUserId(Integer userId);
}