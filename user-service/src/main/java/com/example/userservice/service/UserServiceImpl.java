package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.jpa.UserEntity;
import com.example.userservice.jpa.UserRepository;
import com.example.userservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto create(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity user = mapper.map(userDto, UserEntity.class);
        user.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        repository.save(user);

        return mapper.map(user, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity user = repository.findByUserId(userId);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserDto userDto = new ModelMapper().map(user, UserDto.class);

        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public Iterable<UserEntity> getUserByAll() {
        return repository.findAll();
    }
}
