package com.remlists.user.domain.valueObjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RoleGroupName {

    private static final Logger LOG = LoggerFactory.getLogger(RoleGroupName.class);


    @NotEmpty(message = "rolegroup.rolegroupname.notempty")
    @Size(max = 20, min = 3, message = "rolegroup.rolegroupname.size")
    private String roleGroup;

    public RoleGroupName(String roleGroup) {
        this.roleGroup = roleGroup;
    }

    public String getRoleGroup() {
        return roleGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleGroupName)) return false;

        RoleGroupName that = (RoleGroupName) o;

        return getRoleGroup() != null ? getRoleGroup().equals(that.getRoleGroup()) : that.getRoleGroup() == null;
    }

    @Override
    public int hashCode() {
        return getRoleGroup() != null ? getRoleGroup().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RoleGroupName{" +
                "roleGroup='" + roleGroup + '\'' +
                '}';
    }
}
