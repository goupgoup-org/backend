package com.goupgoup_backend.user.domain;

import com.goupgoup_backend.common.domain.BaseEntity;
import com.goupgoup_backend.user.dto.AuthProvider;
import com.goupgoup_backend.user.dto.SignUpRequest;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity implements UserDetails, OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String oauth2Id;
    private String password;
    private String email;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    public User update(String name) {
        this.name = name;
        return this;
    }

    public static User from(SignUpRequest parameter) {
        return User.builder()
                .email(parameter.getEmail())
                .name(parameter.getNickname())
                .build();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }
}
