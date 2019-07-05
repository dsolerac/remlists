package com.remlists.user.write.infrastructure.jpa.valueObjects;

import com.remlists.shared.domain.valueObjects.ValueObject;
import com.remlists.shared.infrastructure.jpa.valueObjects.IdJPA;
import com.remlists.user.write.infrastructure.jpa.entities.RoleJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UserRolesIdJPA implements ValueObject {

    private static final Logger LOG = LoggerFactory.getLogger(UserRolesIdJPA.class);


    @Column(name = "id_user", columnDefinition = "uuid")
    private IdJPA userId;

    @Column(name = "id_role", columnDefinition = "uuid")
    private IdJPA roleId;

    public UserRolesIdJPA(IdJPA userId, IdJPA roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    public UserRolesIdJPA() {
    }

    public IdJPA getUserId() {
        return userId;
    }

    public void setUserId(IdJPA userId) {
        this.userId = userId;
    }

    public IdJPA getRoleId() {
        return roleId;
    }

    public void setRoleId(IdJPA roleId) {
        this.roleId = roleId;
    }

    /*

//    @Column(name = "id_user", columnDefinition = "uuid")
    @Embedded
    private IdEmbeddedUserJPA userId;

//    @Column(name = "id_role", columnDefinition = "uuid")
    @Embedded
    private IdEmbeddedRoleJPA roleId;

    public UserRolesIdJPA(UUID userId, UUID roleId) {
        this.userId = new IdEmbeddedUserJPA(userId);
        this.roleId = new IdEmbeddedRoleJPA(roleId);
    }

    public UserRolesIdJPA() {
    }

    public IdEmbeddedUserJPA getUserId() {
        return userId;
    }

    public void setUserId(IdEmbeddedUserJPA userId) {
        this.userId = userId;
    }

    public IdEmbeddedRoleJPA getRoleId() {
        return roleId;
    }

    public void setRoleId(IdEmbeddedRoleJPA roleId) {
        this.roleId = roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = new IdEmbeddedRoleJPA(roleId);
    }

    public void setRoleId(RoleJPA roleId) {
        this.roleId = new IdEmbeddedRoleJPA(roleId.getId().getUuid());
    }

    public void setRoleId(IdJPA roleId) {
        this.roleId = new IdEmbeddedRoleJPA(roleId.getUuid());
    }
*/

    /* --------------------------------------- */


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRolesIdJPA)) return false;

        UserRolesIdJPA that = (UserRolesIdJPA) o;

        if (getUserId() != null ? !getUserId().equals(that.getUserId()) : that.getUserId() != null) return false;
        return getRoleId() != null ? getRoleId().equals(that.getRoleId()) : that.getRoleId() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getRoleId() != null ? getRoleId().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserRolesIdJPA{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                '}';
    }
}

/*
@Embeddable
class IdEmbeddedUserJPA implements Serializable{
    private static final Logger LOG = LoggerFactory.getLogger(IdEmbeddedUserJPA.class);

    @Column(name = "id_user", columnDefinition = "uuid")
    private UUID uuid;

    public IdEmbeddedUserJPA(UUID uuid) {
        this.uuid = uuid;
    }

    protected IdEmbeddedUserJPA() {
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEmbeddedUserJPA)) return false;

        IdEmbeddedUserJPA that = (IdEmbeddedUserJPA) o;

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IdEmbeddedUserJPA{" +
                "uuid=" + uuid +
                '}';
    }
}
@Embeddable
class IdEmbeddedRoleJPA implements Serializable{
    private static final Logger LOG = LoggerFactory.getLogger(IdEmbeddedRoleJPA.class);

    @Column(name = "id_role", columnDefinition = "uuid")
    private UUID uuid;

    public IdEmbeddedRoleJPA(UUID uuid) {
        this.uuid = uuid;
    }

    protected IdEmbeddedRoleJPA() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEmbeddedRoleJPA)) return false;

        IdEmbeddedRoleJPA that = (IdEmbeddedRoleJPA) o;

        return uuid != null ? uuid.equals(that.uuid) : that.uuid == null;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "IdEmbeddedRoleJPA{" +
                "uuid=" + uuid +
                '}';
    }
}*/
