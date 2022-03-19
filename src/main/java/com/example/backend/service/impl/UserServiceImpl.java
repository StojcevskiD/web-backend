package com.example.backend.service.impl;

import com.example.backend.model.Role;
import com.example.backend.model.User;
import com.example.backend.model.UserRoles;
import com.example.backend.repository.RoleRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.UserRoleRepository;
import com.example.backend.service.interfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

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
    public void register(String email, String password, String username) {

        User user = new User(email, passwordEncoder.encode(password), username);
        userRepository.save(user);
        UserRoles userRole = new UserRoles();
        Role role = roleRepository.findByName("ROLE_ADMIN");
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
//        return user;
    }

    @Override
    public boolean passwordMatches(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

//    @Override
//    public void resetPassword(String password) {
//
//    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository.findByUsername(username);
//    }
}
