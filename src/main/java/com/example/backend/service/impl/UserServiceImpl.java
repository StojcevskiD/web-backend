package com.example.backend.service.impl;

import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.UserRoles;
import com.example.backend.model.dto.UserDetailsDto;
import com.example.backend.model.helpers.UserRegisterHelper;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserRoleRepository;
import com.example.backend.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void register(String email, String password, UserRegisterHelper helper) {

        User user = new User(email, passwordEncoder.encode(password), helper.getUsername(), helper.getName(),
                helper.getSurname(),  helper.getAddress(), helper.getPhone(), LocalDateTime.now());
        userRepository.save(user);

        UserRoles userRole = new UserRoles();
        Role role = roleRepository.findByName("ROLE_USER");
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
    }

    @Override
    public boolean passwordMatches(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public UserDetailsDto getUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String email = authentication.getPrincipal().toString();
        User user = userRepository.findByEmail(email);

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUsername(user.getUsername());
        userDetailsDto.setEmail(user.getEmail());

        List<UserRoles> roles = user.getRoles();
        List<String> userRoleNames = new ArrayList<>();
        for(UserRoles role : roles){
            userRoleNames.add(role.getRole().getName());
        }
        userDetailsDto.setRoles(userRoleNames);

        return userDetailsDto;

    }

//    @Override
//    public void resetPassword(String password) {
//
//    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.findUserByEmail(email);

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (var role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
