package com.remlists.user.domain.entities;


import com.remlists.shared.domain.valueObjects.CreatedAt;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.UpdatedAt;
import com.remlists.user.domain.valueObjects.EmailAddress;
import com.remlists.user.domain.valueObjects.EmailVerified;
import com.remlists.user.domain.valueObjects.Password;
import com.remlists.user.domain.valueObjects.ShortName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//Agregate
public class User implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(User.class);


    private Id id;
    @Valid
    private ShortName shortName;
    @Valid
    private EmailAddress email;
    @Valid
    private CreatedAt createdAt;
    @Valid
    private UpdatedAt updatedAt;
    @Valid
    private EmailVerified verified;
    @Valid
    private Password password;

    @NotEmpty(message = "{User.roles.NotEmpty}")
    private Set<Role> roles;




    public User() {
        this.roles = new HashSet<>();
    }

    public User(Id id, ShortName shortName, EmailAddress email, Password password) {
        this.id = id;
        this.shortName = shortName;
        this.email = email;
        this.password = password;

        this.createdAt = new CreatedAt(LocalDateTime.now());
        this.updatedAt = new UpdatedAt(LocalDateTime.now());
        this.verified = new EmailVerified(false);

        this.roles = new HashSet<>();
    }


    public void setId(Id id) {
        this.id = id;
    }

    public void setShortName(ShortName shortName) {
        this.shortName = shortName;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public ShortName getShortName() {
        return shortName;
    }

    public EmailAddress getEmail() {

        return email;
    }

    public Id getId() {
        return id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setArrayRoles(Role... roles) {
        this.roles = Set.of(roles);
    }


    public CreatedAt getCreatedAt() {
        return createdAt;
    }


    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }


    public EmailVerified getVerified() {
        return verified;
    }


    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setVerified(EmailVerified verified) {
        this.verified = verified;
    }


    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    //------ Domain logic -----------------


    public boolean sameIdentityAs(User other) {
        return equals(other);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", shortName=" + shortName +
                ", email=" + email +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", verified=" + verified +
                ", password=" + password +
                ", roles=" + (roles != null? roles.size(): "0")+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getShortName() != null ? !getShortName().equals(user.getShortName()) : user.getShortName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getCreatedAt() != null ? !getCreatedAt().equals(user.getCreatedAt()) : user.getCreatedAt() != null)
            return false;
        if (getUpdatedAt() != null ? !getUpdatedAt().equals(user.getUpdatedAt()) : user.getUpdatedAt() != null)
            return false;
        if (getVerified() != null ? !getVerified().equals(user.getVerified()) : user.getVerified() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        return getRoles() != null ? getRoles().equals(user.getRoles()) : user.getRoles() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getShortName() != null ? getShortName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getCreatedAt() != null ? getCreatedAt().hashCode() : 0);
        result = 31 * result + (getUpdatedAt() != null ? getUpdatedAt().hashCode() : 0);
        result = 31 * result + (getVerified() != null ? getVerified().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRoles() != null ? getRoles().hashCode() : 0);
        return result;
    }
}
