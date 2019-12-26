package com.gdziepotanczymy.service;

import com.gdziepotanczymy.repository.UserRepository;
import com.gdziepotanczymy.service.dto.UserDto;
import com.gdziepotanczymy.service.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserDtoMapper mapper;

    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
