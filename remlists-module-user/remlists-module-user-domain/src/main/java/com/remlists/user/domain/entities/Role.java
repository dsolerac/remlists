package com.remlists.user.domain.entities;

import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.user.domain.valueObjects.RoleDescription;
import com.remlists.user.domain.valueObjects.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Role implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Role.class);

    @Valid
    private Id id;
    @Valid
    private RoleName roleName;
    @Valid
    private RoleDescription description;

//    @Valid
    private Set<User> users;




    private Role() {
        this.users = new HashSet<>();
    }

    /**
     * Create new Role with a specific Id
     * @param id
     * @param roleName
     */
    public Role(Id id, RoleName roleName) {

        this();

        this.id = id;
        this.roleName = roleName;

    }

    /**
     * Create new Role with a random Id
     * @param roleName
     */
    public Role(RoleName roleName){
        this(new Id(), roleName);
    }

    /**
     * Create new Role with a random Id
     * @param roleName
     */
    public Role(String roleName){
        this(new RoleName(roleName));
    }

    public static Role createBasicUSerRole(){
        return new Role(new Id(UUID.fromString("3070afc6-efbc-464e-adc3-40e5e5487e9a")), new RoleName("ROLE_USER"));
    }

    public static Role createAdminUSerRole(){
        return new Role(new Id(UUID.fromString("dff9ea9a-376c-47b4-8cb6-4db4dce18f1f")), new RoleName("ROLE_ADMIN") );
    }

    public static Role createCustomerUSerRole(){
        return new Role(new Id(UUID.fromString("a32518b1-ecf8-46ec-8ca2-30deb9ee00c1")), new RoleName("ROLE_CLIENT") );
    }

    public static Role createEmployeeUSerRole(){
        return new Role(new Id(UUID.fromString("ced2e1c5-f9a2-45d9-a7d4-ee0ce83b27a1")), new RoleName("ROLE_EMPLOYEE") );
    }



    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
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

        Role role = (Role) o;

        if (getRoleName() != null ? !getRoleName().equals(role.getRoleName()) : role.getRoleName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(role.getDescription()) : role.getDescription() != null)
            return false;
        return getUsers() != null ? getUsers().equals(role.getUsers()) : role.getUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getRoleName() != null ? getRoleName().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role=" + roleName +
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
