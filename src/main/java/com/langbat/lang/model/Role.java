package com.langbat.lang.model;

public enum  Role {

    ROLE_SUPER_ADMIN,
    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_MODERATOR,
    ROLE_USER;

    @Override
    public String toString() {
        return name();
    }
}
