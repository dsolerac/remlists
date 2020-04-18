package com.remlists.user.domain.entities;


import com.remlists.shared.domain.valueObjects.CreatedAt;
import com.remlists.shared.domain.valueObjects.Id;
import com.remlists.shared.domain.valueObjects.UpdatedAt;
import com.remlists.user.domain.valueObjects.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

//Agregate
public final class User implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

//    common attributes
    private Id id;
    @Valid
    private CreatedAt createdAt;
    @Valid
    private UpdatedAt updatedAt;

//    mandatory attributes
    @Valid
    private ShortName shortName;
    @Valid
    private EmailAddress email;
    @Valid
    private Password password;

//    other attributes
    @Valid
    private Country country;
    @Valid
    private EmailVerified verified;
    @Valid
    private Language language;
    @Valid
    private Twitter twitter;
    @Valid
    private DateOfBirth dateOfBirth;
    @Valid
    private MobilePhone mobilePhone;
    @Valid
    private UserStatus status;
    @Valid
    private URLWeb urlWeb;
    @Valid
    private UserDescription description;

    //relations
    @Valid
    private UserAddress address;
    @NotEmpty(message = "{User.roles.NotEmpty}")
    private Set<Role> roles;
    @NotEmpty(message = "{User.roles.NotEmpty}")
    private Set<RoleGroup> roleGroups;



    private User() {

        this.createdAt = new CreatedAt(LocalDateTime.now());
        this.updatedAt = new UpdatedAt(LocalDateTime.now());
        this.verified = new EmailVerified(false);

        this.roles = Collections.emptySet();
        this.roleGroups = Collections.emptySet();
    }

    private User (UserBuilder userBuilder){
        this();

        this.id = userBuilder.id;
        this.shortName = userBuilder.shortName;
        this.country = userBuilder.country;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.language = userBuilder.language;
        this.twitter = userBuilder.twitter;
        this.dateOfBirth = userBuilder.dateOfBirth;
        this.mobilePhone = userBuilder.mobilePhone;
        this.status = userBuilder.status;
        this.urlWeb = userBuilder.urlWeb;
        this.description = userBuilder.description;

        this.roles = userBuilder.roles;
        this.roleGroups = userBuilder.roleGroups;

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Twitter getTwitter() {
        return twitter;
    }

    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public URLWeb getUrlWeb() {
        return urlWeb;
    }

    public void setUrlWeb(URLWeb urlWeb) {
        this.urlWeb = urlWeb;
    }

    public UserDescription getDescription() {
        return description;
    }

    public void setDescription(UserDescription description) {
        this.description = description;
    }

    public UserAddress getAddress() {
        return address;
    }

    public void setAddress(UserAddress address) {
        this.address = address;
    }

    public Set<RoleGroup> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(Set<RoleGroup> roleGroups) {
        this.roleGroups = roleGroups;
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

    //------ Builder  -----------------

    public static class UserBuilder {

        //mandatory attributes
        private Id id;
        private ShortName shortName;
        private EmailAddress email;
        private Password password;
        //optional attributes
        private Language language;
        private Country country;
        private Twitter twitter;
        private DateOfBirth dateOfBirth;
        private MobilePhone mobilePhone;
        private UserStatus status;
        private URLWeb urlWeb;
        private UserDescription description;
        private UserAddress address;
        private Set<Role> roles;
        private Set<RoleGroup> roleGroups;



        public UserBuilder(Id id, ShortName shortName, EmailAddress email, Password password) {
            this.id = id;
            this.shortName = shortName;
            this.email = email;
            this.password = password;

            this.roles = new HashSet<>();
            this.roleGroups = new HashSet<>();
        }

        public UserBuilder(ShortName shortName, EmailAddress email, Password password) {

            this(new Id(),shortName,email, password);

        }

        public UserBuilder withLanguage(Language language){
            this.language=language;
            return this;
        }
        public UserBuilder withCountry(Country country){
            this.country=country;
            return this;
        }
        public UserBuilder withTwitter(Twitter twitter){
            this.twitter = twitter;
            return this;
        }
        public UserBuilder withDateOfBirth(DateOfBirth dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public UserBuilder withMobilePhone(MobilePhone mobilePhone){
            this.mobilePhone = mobilePhone;
            return this;
        }
        public UserBuilder withUserStatus(UserStatus userStatus){
            this.status = userStatus;
            return this;
        }
        public UserBuilder withURLWeb(URLWeb urlWeb){
            this.urlWeb=urlWeb;
            return this;
        }
        public UserBuilder withUserDescription(UserDescription userDescription){
            this.description = description;
            return this;
        }
        public UserBuilder withUserAddress(UserAddress userAddress){
            this.address = userAddress;
            return this;
        }
        public UserBuilder withRoles(Set<Role> roles){
            this.roles = roles;
            return this;
        }
        public UserBuilder addRole(Role role){
            this.roles.add(role);
            return this;
        }
        public UserBuilder withRoleGroups(Set<RoleGroup> roleGroups){
            this.roleGroups = roleGroups;
            return this;
        }
        public UserBuilder addRoleGroup(RoleGroup roleGroup){
            this.roleGroups.add(roleGroup);
            return this;
        }



        public User build() {
            return new User(this);
        }


    }
}