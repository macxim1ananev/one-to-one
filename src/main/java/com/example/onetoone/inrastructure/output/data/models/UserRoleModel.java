package com.example.onetoone.inrastructure.output.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = {"permissions"})
@ToString(exclude = {"permissions"})
@Table(name = "user_roles", schema = "public")
public class UserRoleModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    @NotBlank
    private String code;

    @ManyToMany
    @JoinTable(
            name = "user_roles_permissions",
            joinColumns = {@JoinColumn(name = "user_role_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_permission_id")}
    )
    private Set<UserPermissionModel> permissions;
}
