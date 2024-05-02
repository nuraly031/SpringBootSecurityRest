package ru.itmentor.Task_314.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itmentor.Task_314.model.User;
import ru.itmentor.Task_314.services.UserService;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final UserService userService;

    @Autowired
    public AuthProviderImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userdetails = userService.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();
        if (!password.equals(userdetails.getPassword())) {
            throw new BadCredentialsException("Nepravilno nabran nomer!");
        }
        return new UsernamePasswordAuthenticationToken(userdetails, password, ((User) userdetails).getRoles());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
