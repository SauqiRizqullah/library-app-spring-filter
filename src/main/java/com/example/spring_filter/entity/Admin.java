package com.example.spring_filter.entity;

import com.example.spring_filter.constant.ConstantTable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = ConstantTable.ADMIN)
public class Admin {
    @Id
    @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "com.example.spring_filter.utils.AdminCustomId")
    @Column(name = "admin_id")
    private String adminId;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "admin_name", nullable = false)
    private String adminName;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
