package com.cgwx.data.entity;

public class PdmPermissionInfo {
    private String permissionId;

    private String permissionDescription;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription == null ? null : permissionDescription.trim();
    }
}