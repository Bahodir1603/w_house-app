package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.RegisterDto;
import com.example.w_houseapp.entity.Role;
import com.example.w_houseapp.entity.User;
import com.example.w_houseapp.redis.UserInformation;
import com.example.w_houseapp.redisRepository.UserInformationRepository;
import com.example.w_houseapp.repository.RoleRepository;
import com.example.w_houseapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    @Value("${spring.mail.username}")
    String sender;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    private final PasswordEncoder passwordEncoder;
    private final UserInformationRepository userInformationRepository;
    private final RoleRepository roleRepository;

//    public AuthService(UserRepository userRepository, JavaMailSender javaMailSender, PasswordEncoder passwordEncoder, UserInformationRepository userInformationRepository) {
//        this.userRepository = userRepository;
//        this.javaMailSender = javaMailSender;
//        this.passwordEncoder = passwordEncoder;
//        this.userInformationRepository = userInformationRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetails> userDetails = userRepository.findByPhone(username);
        log.info("UserDetails {}", userDetails);
        return userDetails.orElse(null);
    }

    public ApiResponse<?> register(RegisterDto registerDto) {
        Role role = roleRepository.findById(registerDto.getRoleId()).orElseThrow(() -> new RuntimeException("Role Not Found"));
        User user = new User();
        user.setEnabled(false);
        user.setPhone(registerDto.getPhone());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setEmail(registerDto.getEmail());
        user.setRoles(Collections.singleton(role));



        //email xabar jo'natish kerak UUID.randomUUID()
        //6-xonali
//        String code = String.valueOf(Math.random() * 899999 + 100000);
//
//        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(sender);
//        simpleMailMessage.setTo(user.getEmail());
//        simpleMailMessage.setSubject("Tasdiqlash kodi : " + code);
//        simpleMailMessage.setText("Salom");
//        javaMailSender.send(simpleMailMessage);
//
//        user.setCode(code);
        User save = userRepository.save(user);
        userInfo(save.getId());
        return ApiResponse.builder().message("Added!").success(true).build();
    }

//    public ApiResponse<?> verify(String email, String code) {
//        Optional<User> byEmailAndCode = userRepository.findByEmailAndCode(email, code);
//
//        if (byEmailAndCode.isPresent()) {
//            User user = byEmailAndCode.get();
//            user.setEnabled(true);
//        }
//        return ApiResponse.builder().message("Tasdiqlandi").success(true).build();
//    }

    public void userInfo(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User Not Found"));

        UserInformation userInformation = new UserInformation();
        userInformation.setId(user.getId());
        userInformation.setEmail(user.getEmail());
        userInformation.setPhone(user.getPhone());
        userInformation.setPhone(user.getPhone());
        userInformationRepository.save(userInformation);
    }
}
