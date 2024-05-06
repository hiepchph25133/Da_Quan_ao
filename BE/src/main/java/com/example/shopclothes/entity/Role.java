//package com.example.shopclothes.entity;
//
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Getter
//@RequiredArgsConstructor
//public enum Role {
//
//    USER(Collections.emptySet()),
//    ADMIN(
//            Set.of(
//                    Permission.ADMIN_READ,
//                    Permission.ADMIN_UPDATE,
//                    Permission.ADMIN_DELETE,
//                    Permission.ADMIN_CREATE,
//                    Permission.EMPLOYEE_READ,
//                    Permission.EMPLOYEE_UPDATE,
//                    Permission.EMPLOYEE_DELETE,
//                    Permission.EMPLOYEE_CREATE
//            )
//    ),
//    EMPLOYEE(
//            Set.of(
//                    Permission.EMPLOYEE_READ,
//                    Permission.EMPLOYEE_UPDATE,
//                    Permission.EMPLOYEE_DELETE,
//                    Permission.EMPLOYEE_CREATE
//            )
//    );
//
//    private final Set<Permission> permissions;
//
//    public List<SimpleGrantedAuthority> getAuthorities() {
//        var authorities = getPermissions()
//                .stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
//    }
//}

package com.example.shopclothes.entity;

import com.example.shopclothes.entity.propertis.Propertis;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Role")

public class Role extends Propertis {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "idRole")
    List<Account> accounts;
}