package com.example.onetoone.inrastructure.output.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_roles_permissions", schema = "public")
public class UserRolePermissionModel implements Serializable {

    @EmbeddedId
    private UserRolePermissionPK id;

}
