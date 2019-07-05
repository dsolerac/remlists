package com.remlists.user.write.infrastructure.jpa.entities;

import com.remlists.shared.infrastructure.jpa.valueObjects.CreatedAtJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.UserRolesIdJPA;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "UserRoles")
@Table(name = "user_roles", schema = "write")
public class UserRolesJPA implements Serializable {

    @EmbeddedId
    private UserRolesIdJPA id;

    @Embedded
    private CreatedAtJPA createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "id_user")
    private UserJPA user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("roleId")
    @JoinColumn(name = "id_role")
    private RoleJPA role;



    public UserRolesJPA(UserRolesIdJPA id) {
        this.id = id;
        createdAt = new CreatedAtJPA(LocalDateTime.now());
    }

    protected UserRolesJPA() {
        createdAt = new CreatedAtJPA(LocalDateTime.now());
    }

    public UserRolesJPA(UserJPA user, RoleJPA role) {
        this.user = user;
        this.role = role;
        this.id = new UserRolesIdJPA(user.getId(), role.getId());
        createdAt = new CreatedAtJPA(LocalDateTime.now());
    }



    public UserRolesIdJPA getId() {
        return id;
    }

    public void setId(UserRolesIdJPA id) {
        this.id = id;
    }

    public CreatedAtJPA getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAtJPA createdAt) {
        this.createdAt = createdAt;
    }

    public UserJPA getUser() {
        return user;
    }

    public void setUser(UserJPA user) {
        this.user = user;
    }

    public RoleJPA getRole() {
        return role;
    }

    public void setRole(RoleJPA role) {
        this.role = role;
    }




    /* --------------------------------------- */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRolesJPA)) return false;

        UserRolesJPA that = (UserRolesJPA) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() != null)
            return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        return getRole() != null ? getRole().equals(that.getRole()) : that.getRole() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
//        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
//        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRolesJPA{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", user=" + user +
//                ", role=[deliberately left in blank]" + //role +
                ", role=" + role +
                '}';
    }
}
