package com.mountain.infrastructure.model;

public class AuthRole {
    private Integer id;

    private Integer roleId;

    private Integer authId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getroleId() {
        return roleId;
    }

    public void setroleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
}