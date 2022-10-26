package com.example.w_houseapp.redisRepository;

import com.example.w_houseapp.redis.UserInformation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserInformationRepository extends CrudRepository<UserInformation, UUID> {
    List<UserInformation> findAllByPasswordContainingIgnoreCase(String password);
}
