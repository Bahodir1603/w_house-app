package com.example.w_houseapp.controller;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.LoginDto;
import com.example.w_houseapp.dto.RegisterDto;
import com.example.w_houseapp.entity.User;
import com.example.w_houseapp.redis.SaveToken;
import com.example.w_houseapp.redisRepository.SaveTokenRepository;
import com.example.w_houseapp.security.JwtProvider;
import com.example.w_houseapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final SaveTokenRepository saveTokenRepository;

    //login
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody  LoginDto loginDTO) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);





        //jwt yasab berishimz kk
        String token = jwtProvider.generateToken(loginDTO.getUsername());
        saveToken1(token);
        return ResponseEntity.ok(token);

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDto registerDto) {
        ApiResponse response = authService.register(registerDto);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/verify")
//    public ResponseEntity<?> verify(@RequestParam String email, @RequestParam String code) {
//        ApiResponse<?> response = authService.verify(email, code);
//        return ResponseEntity.ok(response);
//    }
    public void saveToken1(String token) {
        SaveToken saveToken = new SaveToken();
//        saveToken.setToken(token);
        saveToken.setId(token);
        saveTokenRepository.save(saveToken);
    }
}
