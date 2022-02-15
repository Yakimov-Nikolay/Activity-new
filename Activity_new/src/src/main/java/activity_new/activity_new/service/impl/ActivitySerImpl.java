package activity_new.activity_new.service.impl;

import activity_new.activity_new.model.entity.UserEntity;
import activity_new.activity_new.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivitySerImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public ActivitySerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var userEntity=
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + "not found"));
        return mapToUserDetails(userEntity);
    }

    private static UserDetails mapToUserDetails(UserEntity userEntity) {

        Set<GrantedAuthority> authorities =
            userEntity
                    .getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_"+role.getRole().name()))
                    .collect(Collectors.toUnmodifiableSet());

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities
        );
    }

}
