package com.example.onetoone.inrastructure.output.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class UserRolePermissionPK implements Serializable {

    @Column(name = "user_role_id")
    private Long userRoleId;

    @Column(name = "user_permission_id")
    private Long userPermissionId;
}
