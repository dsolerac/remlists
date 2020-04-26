package com.remlists.user.domain.entities;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.RoleGroupName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class RoleGroup implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(RoleGroup.class);


    private Id id;
    @Valid
    private RoleGroupName groupName;
    @Valid
    private RoleGroup groupParent;

    @NotEmpty(message = "{user.roles.notEmpty}")
    private Set<Role> roles;


    private RoleGroup() {
        roles = new HashSet<>();
    }

    public RoleGroup(Id id, RoleGroupName groupName) {

        this();

        this.id = id;
        this.groupName = groupName;

    }

    public RoleGroup(RoleGroupName roleGroupName){
        this(new Id(), roleGroupName);
    }

    public RoleGroup(String groupName) {
        this(new RoleGroupName(groupName));
    }

    public static RoleGroup createBasicUSerRoleGroup(){
        Role user = Role.createBasicUSerRole();

        RoleGroup role_group_users = new RoleGroup(new Id(UUID.fromString("e72bb89d-f873-4c2b-83f0-03869c08da79")), new RoleGroupName("ROLE_GROUP_USERS"));
        role_group_users.addRole(user);

        return role_group_users;
    }

    public static RoleGroup createAdminUSerRoleGroup(){

        Role user = Role.createBasicUSerRole();
        Role admin = Role.createAdminUSerRole();

        RoleGroup role_group_users = new RoleGroup(new Id(UUID.fromString("613d2ebf-97a1-487a-884c-77e1494afb93")), new RoleGroupName("ROLE_GROUP_ADMINS") );

        role_group_users.addRole(user);
        role_group_users.addRole(admin);

        return role_group_users;
    }

    public static RoleGroup createCustomerUSerRoleGroup(){

        Role user = Role.createBasicUSerRole();
        Role customer = Role.createCustomerUSerRole();

        RoleGroup role_group_users = new RoleGroup(new Id(UUID.fromString("a32518b1-ecf8-46ec-8ca2-30deb9ee00c1")), new RoleGroupName("ROLE_GROUP_CUSTOMER") );

        role_group_users.addRole(user);
        role_group_users.addRole(customer);

        return role_group_users;
    }

    public static RoleGroup createEmployeeUSerRoleGroup(){

        Role user = Role.createBasicUSerRole();
        Role employee = Role.createEmployeeUSerRole();

        RoleGroup role_group_users = new RoleGroup(new Id(UUID.fromString("8201ff0e-0c41-466f-a460-6d88a28e11b1")), new RoleGroupName("ROLE_GROUP_EMPLOYEE") );

        role_group_users.addRole(user );
        role_group_users.addRole(employee );

        return role_group_users;
    }


    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public RoleGroupName getGroupName() {
        return groupName;
    }

    public void setGroupName(RoleGroupName groupName) {
        this.groupName = groupName;
    }

    public RoleGroup getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(RoleGroup groupParent) {
        this.groupParent = groupParent;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        this.roles.add(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleGroup)) return false;

        RoleGroup roleGroup = (RoleGroup) o;

        if (getId() != null ? !getId().equals(roleGroup.getId()) : roleGroup.getId() != null) return false;
        if (getGroupName() != null ? !getGroupName().equals(roleGroup.getGroupName()) : roleGroup.getGroupName() != null)
            return false;
        if (getGroupParent() != null ? !getGroupParent().equals(roleGroup.getGroupParent()) : roleGroup.getGroupParent() != null)
            return false;
        return getRoles() != null ? getRoles().equals(roleGroup.getRoles()) : roleGroup.getRoles() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getGroupName() != null ? getGroupName().hashCode() : 0);
        result = 31 * result + (getGroupParent() != null ? getGroupParent().hashCode() : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleGroup{" +
                "id=" + id +
                ", groupName=" + groupName +
                ", groupParent=" + groupParent +
                ", roles=" + roles +
                '}';
    }
}
