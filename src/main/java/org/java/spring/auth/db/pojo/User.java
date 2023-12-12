package org.java.spring.auth.db.pojo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User() {}

    // Costruttore che imposta il nome utente, la password e le autorizzazioni (ruoli) dell'utente.
    public User(String username, String password, Role... roles) {
        setUsername(username);
        setPassword(password);
        setRoles(roles);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    // Imposta i ruoli dell'utente.
    public void setRoles(Role... roles) {
        setRoles(List.of(roles));
    }

    // Restituisce le autorizzazioni (ruoli) dell'utente.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getRoleName()))
                .toList();
    }

    // Verifica se l'account dell'utente è scaduto (sempre vero in questo caso).
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Verifica se l'account dell'utente è bloccato (sempre vero in questo caso).
    @Override
    public boolean isAccountNonLocked() {
        return true;    
    }

    // Verifica se le credenziali dell'utente sono scadute (sempre vero in questo caso).
    @Override
    public boolean isCredentialsNonExpired() {
        return true;    
    }

    // Verifica se l'utente è abilitato (sempre vero in questo caso).
    @Override
    public boolean isEnabled() {
        return true;    
    }

    // Restituisce una rappresentazione testuale dell'oggetto User.
    @Override
    public String toString() {
        return "[" + getId() + "] " + getUsername();
    }
}
