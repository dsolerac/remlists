package com.remlists.user.write.infrastructure.jpa.entities;

import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleDescriptionJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.RoleNameJPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity(name = "Role")
@Table(name = "roles", schema = "write")
public class RoleJPA implements Serializable {


    @EmbeddedId
    private IdJPA id;

    @Embedded
    private RoleNameJPA roleName;

    @Embedded
    private RoleDescriptionJPA description;

    @OneToMany(mappedBy = "role",
            orphanRemoval = true
    )
    private Set<UserRolesJPA> users; // = new HashSet<>();


    public RoleJPA() {
    }

    public RoleJPA(IdJPA id, RoleNameJPA roleName, RoleDescriptionJPA description) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
    }

    public IdJPA getId() {
        return id;
    }

    public void setId(IdJPA id) {
        this.id = id;
    }

    public RoleNameJPA getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleNameJPA roleName) {
        this.roleName = roleName;
    }

    public RoleDescriptionJPA getDescription() {
        return description;
    }

    public void setDescription(RoleDescriptionJPA description) {
        this.description = description;
    }

    public Set<UserRolesJPA> getUsers() {
        return users;
    }

    public void setUsers(Set<UserRolesJPA> users) {
        this.users = users;
    }

//    public void setUsers(Set<UserJPA> users) {
//
//        this.rolUsers = users.stream()
//                .map(UserJPA::getId)
//                .map(IdJPA::getUuid)
//                .map(x -> new UserRolesJPA(new UserRolesIdJPA(x, getId().getUuid())))
//                .collect(Collectors.toList());
//
//    }

    public void addUser(UserJPA user){

        UserRolesJPA userRoles = new UserRolesJPA(user, this);
        this.users.add(userRoles);
        user.getRoles().add(userRoles);

    }

    public void removeUser(UserJPA user){


        for (Iterator<UserRolesJPA> iterator = this.users.iterator(); iterator.hasNext(); ) {

            UserRolesJPA userRoles = iterator.next();

            if ( userRoles.getRole().equals(this) && userRoles.getUser().equals(user) ){

                iterator.remove();
                userRoles.getUser().getRoles().remove(userRoles);
                userRoles.setRole(null);
                userRoles.setUser(null);

            }

        }

    }





    /* --------------------------------------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleJPA)) return false;

        RoleJPA roleJPA = (RoleJPA) o;

        if (getId() != null ? !getId().equals(roleJPA.getId()) : roleJPA.getId() != null) return false;
        if (getRoleName() != null ? !getRoleName().equals(roleJPA.getRoleName()) : roleJPA.getRoleName() != null)
            return false;
        if (getDescription() != null ? !getDescription().equals(roleJPA.getDescription()) : roleJPA.getDescription() != null)
            return false;
        return getUsers() != null ? getUsers().equals(roleJPA.getUsers()) : roleJPA.getUsers() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getRoleName() != null ? getRoleName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
//        result = 31 * result + (getUsers() != null ? getUsers().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoleJPA{" +
                "id=" + id +
                ", roleName=" + roleName +
                ", description=" + description +
                ", users=" + users.size() +
                '}';
    }
}
