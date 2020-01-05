package com.gdziepotanczymy.service;

import com.gdziepotanczymy.controller.exception.NotFound;
import com.gdziepotanczymy.model.User;
import com.gdziepotanczymy.repository.UserRepository;
import com.gdziepotanczymy.service.dto.CreateUpdateUserDto;
import com.gdziepotanczymy.service.dto.UserDto;
import com.gdziepotanczymy.service.mapper.UserDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserDtoMapper mapper;

    @Transactional
    public List<UserDto> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public UserDto getUserById(Long id) throws NotFound {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFound::new);
    }

    @Transactional
    public UserDto createUser(CreateUpdateUserDto createUpdateUserDto) {
        User user = User.builder()
                .login(createUpdateUserDto.getLogin())
                .createdAt(OffsetDateTime.now())
                .build();

        User savedUser = repository.save(user);

        return mapper.toDto(savedUser);
    }

    @Transactional
    public UserDto updateUserById(Long id, CreateUpdateUserDto createUpdateUserDto) throws NotFound {
        User existingUser = repository.findById(id).orElseThrow(NotFound::new);

        existingUser.setLogin(createUpdateUserDto.getLogin());
        existingUser.setUpdatedAt(OffsetDateTime.now());

        User savedUser = repository.save(existingUser);

        return mapper.toDto(savedUser);
    }

    @Transactional
    public UserDto deleteUserById(Long id) throws NotFound {
        User existingUser = repository.findById(id).orElseThrow(NotFound::new);

        repository.delete(existingUser);

        return mapper.toDto(existingUser);
    }
}
