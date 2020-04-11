package com.remlists.user.domain.entities;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.RoleDescription;
import com.remlists.user.domain.valueObjects.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public final class Role implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(User.class);


    private Id id;
    @Valid
    private RoleName role;
    @Valid
    private RoleDescription description;

    private Set<User> users;




    public Role() {
        this.users = new HashSet<>();
    }

    public Role(Id id, @Valid RoleName role) {

        this();

        this.id = id;
        this.role = role;

    }




    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public RoleDescription getDescription() {
        return description;
    }

    public void setDescription(RoleDescription description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setArrayUsers(User... users) {
        this.users = Set.of(users);
    }

    //------ Domain logic -----------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;

        Role role1 = (Role) o;

        if (getId() != null ? !getId().equals(role1.getId()) : role1.getId() != null) return false;
        if (getRole() != null ? !getRole().equals(role1.getRole()) : role1.getRole() != null) return false;
        if (getDescription() != null ? !getDescription().equals(role1.getDescription()) : role1.getDescription() != null)
            return false;
        return getUsers() != null ? getUsers().equals(role1.getUsers()) : role1.getUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + role +
                ", description=" + description +
                ", users=" + users +
                '}';
    }


    /*    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + role +
                ", description=" + description +
//                ", users=[left intentionally in blank]" + //users +
                ", users=" + (users!=null?users.size():"0") +
                '}';
    }*/
}
