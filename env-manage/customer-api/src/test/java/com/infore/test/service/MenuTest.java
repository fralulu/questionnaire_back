package com.infore.test.service;

import com.infore.mapper.MenuRoleMapper;
import com.infore.model.MenuRole;
import com.infore.service.MenuService;
import com.infore.service.UserRoleService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by xuyao on 29/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MenuTest {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void getjson() {
        String trees = menuService.getMenuTrees();
        System.out.println("--" + trees);
    }

    @Test
    public void getTreeByRoleids() {
        List<Integer> roleIds = new LinkedList<Integer>(){{
            add(1);
            add(2);
        }};
        String trees = menuService.getMenuTreeByRoleIds(roleIds);
        System.out.println("--" + trees);
    }

    @Test
    public void count() {
//        int trees = menuRoleMapper.countByxzRoleId(1);
        int trees = menuRoleMapper.deleteByRoleId(2);
        System.out.println("--" + trees);
    }
    @Test
    public void del() {
        int trees = menuRoleMapper.deleteRoleByMenuIds(2,new ArrayList<Integer>(){{
            add(1);
            add(2);
        }});
        System.out.println("--" + trees);
    }
    @Test
    public void batchIns() {
        int trees = menuRoleMapper.batchInsert(new LinkedList<MenuRole>(){{
            add(new MenuRole(){{setRoleId(2);setMenuId(1);}});
            add(new MenuRole(){{setRoleId(2);setMenuId(2);}});
            add(new MenuRole(){{setRoleId(2);setMenuId(3);}});
        }});
        System.out.println("--" + trees);
    }

    @Test
    public void testQueryRoleids() {
        List<Integer> rids = userRoleService.queryRoleIdsByUserId(3);
        System.out.println("---rids:"+rids);
    }
}
