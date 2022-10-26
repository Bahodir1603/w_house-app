package com.example.w_houseapp.service;

import com.example.w_houseapp.dto.ApiResponse;
import com.example.w_houseapp.dto.UserDto;
import com.example.w_houseapp.entity.Role;
import com.example.w_houseapp.entity.User;
import com.example.w_houseapp.entity.Warehouse;
import com.example.w_houseapp.entity.enums.PositionType;
import com.example.w_houseapp.redis.UserInformation;
import com.example.w_houseapp.redisRepository.UserInformationRepository;
import com.example.w_houseapp.repository.RoleRepository;
import com.example.w_houseapp.repository.UserRepository;
import com.example.w_houseapp.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final WarehouseRepository warehouseRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInformationRepository userInformationRepository;


    public ApiResponse save(UserDto userDto) {

        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new RuntimeException("Bunday Id li  Role topilmadi"));
        Warehouse warehouse = warehouseRepository.findById(userDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Bunday Id li Warehouse topilmadi"));

        User user = new User();
        user.setState(userDto.getState());
        user.setRoles((Collections.singleton(role)));
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setSalary(userDto.getSalary());
        user.setWarehouse(warehouse);
        String encode = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encode);

        for (PositionType value : PositionType.values()) {
            if (value.toString().equals(userDto.getPositionType())){
                user.setPositionType(value);
            }
        }
        User save = userRepository.save(user);
        return ApiResponse.builder().message("Added!").success(true).data(save).build();

    }

    public ApiResponse getAll() {
        List<User> all = userRepository.findAll();
//        Iterable<UserInformation> all = userInformationRepository.findAll();
        return ApiResponse.builder().message("Mana").success(true).data(all).build();
    }

    public ApiResponse getOne(UUID uuid) {
//        User user = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User Not Found"));
        UserInformation user = userInformationRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User Not Found"));
        return ApiResponse.builder().message("Mana").success(true).data(user).build();
    }

    public ApiResponse edit(UUID uuid, UserDto userDto) {
        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new RuntimeException("Role Not Found"));
        Warehouse warehouse = warehouseRepository.findById(userDto.getWarehouseId()).orElseThrow(() -> new RuntimeException("Warehouse Not Found"));
        User user = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setState(userDto.getState());
        user.setRoles((Collections.singleton(role)));
        user.setFullName(userDto.getFullName());
        user.setPhone(userDto.getPhone());
        String encode = passwordEncoder.encode(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setSalary(userDto.getSalary());
        user.setWarehouse(warehouse);
        for (PositionType value : PositionType.values()) {
        user.setPassword(encode);

            if (value.toString().equals(userDto.getPositionType())){
                user.setPositionType(value);
            }
        }
        User save = userRepository.save(user);
        return ApiResponse.builder().message("Added!").success(true).data(save).build();
    }

    public ApiResponse remove(UUID uuid) {
        User user = userRepository.findById(uuid).orElseThrow(() -> new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return ApiResponse.builder().message("Deleted").success(true).build();
    }

}
