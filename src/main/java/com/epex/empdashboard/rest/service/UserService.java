package com.epex.empdashboard.rest.service;

import com.epex.empdashboard.domain.auth.UserDTO;
import com.epex.empdashboard.repository.auth.Role;
import com.epex.empdashboard.repository.auth.RoleRepository;
import com.epex.empdashboard.repository.auth.User;
import com.epex.empdashboard.repository.auth.UserRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(final UserRepository userRepository,
                       final BCryptPasswordEncoder bCryptEncoder,
                       final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptEncoder = bCryptEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    user.get().getUsername(), user.get().getPassword(), getAuthority(user.get()));
        }
        throw new UsernameNotFoundException("User Not Found For Username : " + username);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = Sets.newHashSet();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public List<User> findAll() {
        List<User> list = Lists.newArrayList();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User findOne(String username) throws Exception {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new Exception("User Not Found : " + username + " !"));
    }

    public User save(UserDTO user) {
        User nUser = new User(user);
        nUser.setPassword(bCryptEncoder.encode(user.getPassword()));

        Set<Role> role = roleRepository.findByNameIn(UserDTO.convertStringsToEnumCollection(user.getRole()));
        Set<Role> roleSet = Sets.newHashSet();
        roleSet.addAll(role);

        nUser.setRoles(roleSet);
        return userRepository.save(nUser);
    }

}
