package ru.itmentor.Task_314.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters long")
    @Column(name = "user_name")
    private String userName;

    @NotEmpty(message = "Lastname must not be empty")
    @Size(min = 2, max = 100, message = "Lastname must be between 2 and 100 characters long")
    @Column(name = "user_lastname")
    private String userLastname;
    @Email(message = "Email should be as email")
    @NotEmpty(message = "Mail must not be empty")
    @Column(name = "user_mail")
    private String userMail;

    @DecimalMin(message = "User age should be positive",
            value = "0", inclusive = false)
    @Column(name = "user_age")
    private int userAge;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    public String getUserName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getShotRoles() {
        return roles.toString().replace(",", " ").replaceAll("[^A-Za-zА-Яа-я0-9,' ']", "");
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return userMail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

