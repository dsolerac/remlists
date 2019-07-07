package com.remlists.user.write.infrastructure.jpa.entities;

import com.remlists.shared.infrastructure.jpa.valueObjects.CreatedAtJPA;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.shared.infrastructure.jpa.valueObjects.UpdatedAtJPA;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailAddressJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.EmailVerifiedJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.PasswordJPA;
import com.remlists.user.write.infrastructure.jpa.valueObjects.ShortNameJPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users", schema = "write")
public class UserJPA implements Serializable {


    @EmbeddedId
    private IdJPA id;

    @Embedded
    private ShortNameJPA shortName;

    @Embedded
    private EmailAddressJPA email;

    @Embedded
    private CreatedAtJPA createdAt;

    @Embedded
    private UpdatedAtJPA updatedAt;

    @Embedded
    private EmailVerifiedJPA verified;

    @Embedded
    private PasswordJPA password;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY, //Son pocos los roles que tiene cada usuario.
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<UserRolesJPA> roles = new HashSet<>();




    public UserJPA() {
    }

    public UserJPA(IdJPA id, ShortNameJPA shortName, EmailAddressJPA email) {
        this.id = id;
        this.shortName = shortName;
        this.email = email;
    }

    public void setId(IdJPA id) {
        this.id = id;
    }

    public void setShortName(ShortNameJPA shortName) {
        this.shortName = shortName;
    }

    public void setEmail(EmailAddressJPA email) {
        this.email = email;
    }

    public IdJPA getId() {
        return id;
    }

    public ShortNameJPA getShortName() {
        return shortName;
    }

    public EmailAddressJPA getEmail() {
        return email;
    }

    public CreatedAtJPA getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAtJPA createdAt) {
        this.createdAt = createdAt;
    }

    public UpdatedAtJPA getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UpdatedAtJPA updatedAt) {
        this.updatedAt = updatedAt;
    }

    public EmailVerifiedJPA getVerified() {
        return verified;
    }

    public void setVerified(EmailVerifiedJPA verified) {
        this.verified = verified;
    }

    public Set<UserRolesJPA> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRolesJPA> user) {
        this.roles = user;
    }

    public PasswordJPA getPassword() {
        return password;
    }

    public void setPassword(PasswordJPA password) {
        this.password = password;
    }





    /*    public void setArrayRoles(List<RoleJPA> roles) {

        userRoles = roles.stream()
                                .map(RoleJPA::getId)
                                .map(IdJPA::getUuid)
                                .map(x -> new UserRolesJPA(new UserRolesIdJPA(x, getId().getUuid())))
                                .collect(Collectors.toList());

    }*/

    public void addRole(RoleJPA role){

        UserRolesJPA userRoles = new UserRolesJPA(this, role);
        this.roles.add(userRoles);
        role.getUsers().add(userRoles);
    }

    public void removeRole(RoleJPA role) {

        for (Iterator<UserRolesJPA> iterator = this.roles.iterator(); iterator.hasNext(); ) {

            UserRolesJPA userRoles = iterator.next();

            if (userRoles.getUser().equals(this) && userRoles.getRole().equals(role)) {

                iterator.remove();

                userRoles.getRole().getUsers().remove(userRoles);
                userRoles.setRole(null);
                userRoles.setUser(null);

            }


        }
    }


/* --------------------------------------- */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserJPA)) return false;

        UserJPA userJPA = (UserJPA) o;

        if (getId() != null ? !getId().equals(userJPA.getId()) : userJPA.getId() != null) return false;
        if (getShortName() != null ? !getShortName().equals(userJPA.getShortName()) : userJPA.getShortName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(userJPA.getEmail()) : userJPA.getEmail() != null) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(userJPA.getCreatedAt()) : userJPA.getCreatedAt() != null)
            return false;
        if (getUpdatedAt() != null ? !getUpdatedAt().equals(userJPA.getUpdatedAt()) : userJPA.getUpdatedAt() != null)
            return false;
        if (getVerified() != null ? !getVerified().equals(userJPA.getVerified()) : userJPA.getVerified() != null)
            return false;
        return getRoles() != null ? getRoles().equals(userJPA.getRoles()) : userJPA.getRoles() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getShortName() != null ? getShortName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (getVerified() != null ? getVerified().hashCode() : 0);
//        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserJPA{" +
                "id=" + id +
                ", shortName=" + shortName +
                ", email=" + email +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", verified=" + verified +
                ", roles=" + roles.size() +
                '}';
    }
}