package com.example.spring_filter.entity;
import com.example.spring_filter.constant.ConstantTable;
import com.example.spring_filter.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.ROLE)
public class Role {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.spring_filter.utils.RoleCustomId")
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
