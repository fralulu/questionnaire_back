package com.infore.mapper;

import com.infore.model.Menu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> queryByParam(Menu param);

    List<Menu> queryMenuIdByRoleId(@Param("roleId") Integer roleId, @Param("menu") Menu record);

    List<Menu> querySourcesByRoleId(@Param("roleId") Integer roleId);

    List<Menu> querySources(@Param("type") Integer type, @Param("queryLike") String queryLike);

    List<Menu> queryMenuByRoleIds(@Param("roleIds") List<Integer> roleIds);

}