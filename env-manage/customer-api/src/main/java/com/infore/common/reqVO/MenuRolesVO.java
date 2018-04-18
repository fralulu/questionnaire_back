package com.infore.common.reqVO;

import java.util.List;

/**
 * Created by xuyao on 04/01/2018.
 */
public class MenuRolesVO {
    Integer roleId;
    List<Integer> menuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
