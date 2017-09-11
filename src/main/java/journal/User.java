package journal;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class User implements UserDetails{
    @Id
    public String id;

    private String password;
    private String username;
    private List<SimpleGrantedAuthority> authorities;
    private boolean notExpired = true;
    private boolean notLocked = true;
    private boolean notExpiredCreds = true;
    private boolean enabled = true;

    public void assignAsUser() {
        authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public void assignAsAdmin() {
        authorities = new ArrayList<SimpleGrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public void setAuthorities(List<SimpleGrantedAuthority> auths) {
        authorities = auths;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String name) {
        username = name;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return notExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return notLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return notExpiredCreds;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
