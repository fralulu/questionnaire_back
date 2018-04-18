package com.infore.mapper;

import com.infore.model.MenuRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuRoleMapper {
    int deleteByPrimaryKey(@Param("menuId") Integer menuId, @Param("roleId") Integer roleId);

    int insert(MenuRole record);

    int insertSelective(MenuRole record);

    MenuRole selectByPrimaryKey(@Param("menuId") Integer menuId, @Param("roleId") Integer roleId);

    int updateByPrimaryKeySelective(MenuRole record);

    int updateByPrimaryKey(MenuRole record);

    int countByUrl(@Param("userId") int userId, @Param("url") String url);

    List<MenuRole> queryByParam(MenuRole menuRole);

    int deleteByRoleId(Integer roleId);

    int countByRoleId(Integer roleId);

    int countByMenuId(Integer menuId);

    int batchInsert(List<MenuRole> menuRoles);

    int deleteRoleByMenuIds(@Param("roleId") int roleId, @Param("menuIds") List<Integer> menuIds);
}