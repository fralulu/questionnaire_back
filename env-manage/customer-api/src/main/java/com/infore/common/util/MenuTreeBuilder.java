package com.infore.common.util;

import com.alibaba.fastjson.JSON;
import com.infore.model.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyao on 29/12/2017.
 */
public class MenuTreeBuilder {
    List<Menu> nodes = new ArrayList<>();

    public String buildTree(List<Menu> nodes) {

        MenuTreeBuilder treeBuilder = new MenuTreeBuilder(nodes);

        return treeBuilder.buildJSONTree();
    }

    public MenuTreeBuilder() {
    }

    public MenuTreeBuilder(List<Menu> nodes) {
        this.nodes = nodes;
    }

    // 构建JSON树形结构
    public String buildJSONTree() {
        List<Menu> nodeTree = buildTree();
        return JSON.toJSONString(nodeTree);
    }

    // 构建树形结构
    public List<Menu> buildTree() {
        List<Menu> treeNodes = new ArrayList<>();
        List<Menu> rootNodes = getRootNodes();
        for (Menu rootNode : rootNodes) {
            buildChildNodes(rootNode);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    // 递归子节点
    public void buildChildNodes(Menu node) {
        List<Menu> children = getChildNodes(node);
        if (!children.isEmpty()) {
            for (Menu child : children) {
                buildChildNodes(child);
            }
            node.setChildren(children);
        }
    }

    // 获取父节点下所有的子节点
    public List<Menu> getChildNodes(Menu pnode) {
        List<Menu> childNodes = new ArrayList<>();
        for (Menu n : nodes) {
            if (pnode.getId().intValue() == n.getPid().intValue()) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public boolean rootNode(Menu node) {
        boolean isRootNode = true;
        for (Menu n : nodes) {
            if (node.getPid().intValue() == n.getId().intValue()) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    public List<Menu> getRootNodes() {
        List<Menu> rootNodes = new ArrayList<>();
        for (Menu n : nodes) {
            if (rootNode(n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }
}
