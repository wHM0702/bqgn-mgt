package com.kjde1033.bqgnmgt.model.entity;

import java.util.List;

public class Menu {

    private int menuId,level,subId,index;
    private String content,iconPath,path;
    private List<Menu> children;

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getSubId() {
        return subId;
    }

    public void setSubId(int subId) {
        this.subId = subId;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", level=" + level +
                ", subId=" + subId +
                ", index=" + index +
                ", content='" + content + '\'' +
                ", iconPath='" + iconPath + '\'' +
                ", path='" + path + '\'' +
                ", children=" + children +
                '}';
    }
}
