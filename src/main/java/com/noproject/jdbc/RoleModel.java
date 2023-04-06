/*
 *
 */
package com.noproject.jdbc;

import com.noproject.jdbc.sql.annotations.IdColumn;
import com.noproject.jdbc.sql.annotations.MapToColumn;
import com.noproject.jdbc.sql.annotations.MapToTable;

/**
 *
 * @author DELL
 */
@MapToTable("role")
public class RoleModel {

    @MapToColumn("role_id")
    @IdColumn(identity = false)
    private int roleId;
    @MapToColumn("role_name")
    private String roleName;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Role{");
        sb.append("roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append('}');
        return sb.toString();
    }

}
