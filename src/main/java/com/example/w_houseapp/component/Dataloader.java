package com.example.w_houseapp.component;

import com.example.w_houseapp.entity.Role;
import com.example.w_houseapp.entity.User;
import com.example.w_houseapp.repository.RoleRepository;
import com.example.w_houseapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Dataloader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String mode;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")) {
            Role admin = roleRepository.save(new Role(1l, "ADMIN",true));
            Role user = roleRepository.save(new Role(2l, "USER",true));

            Set<Role> roles = new HashSet<>();
            roles.add(admin);
            roles.add(user);

            userRepository.save(new User("jekka",roles, "ABA", "2020", passwordEncoder.encode("123"), true));
            userRepository.save(new User("pekka",roles, "AAA", "1010", passwordEncoder.encode("123"), true));
        }
    }

}
