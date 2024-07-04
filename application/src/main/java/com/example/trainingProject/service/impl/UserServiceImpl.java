package com.example.trainingProject.service.impl;

import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.Role;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.exceptions.EmailException;
import com.example.trainingProject.exceptions.PasswordException;
import com.example.trainingProject.exceptions.UserNameException;
import com.example.trainingProject.repository.UserRepo;
import com.example.trainingProject.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepo userRepo;

    public Optional<UserDto> findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(value -> new UserDto(value.getName(), value.getEmail()));
    }

    public boolean createUser(UserDto userDto) {
        if (userDto.getName().isEmpty() || userDto.getName() == null) {
            throw new UserNameException("username cannot be empty");
        } else {
            Optional<User> optionalUser = userRepo.findByName(userDto.getName());
            if (optionalUser.isPresent()) {
                return false;
            }
        }

        if (userDto.getPassword().isEmpty() || userDto.getPassword() == null) {
            throw new PasswordException("password cannot be empty");
        } else {
            userDto.setPassword(userDto.getPassword());
        }
        if (userDto.getEmail().isEmpty() || userDto.getEmail() == null || !EmailValidator.isValid(userDto.getEmail())) {
            throw new EmailException("Email is not correct!");
        }
        userDto.setCreateDate(LocalDateTime.now());
        userDto.setRole(Role.USER.toString());
        Long userId = userRepo.createUser(userDto);
        userDto.setId(userId);
        userDto.setActivateCode(UUID.randomUUID().toString());
        userRepo.generateActivateCode(userId, userDto.getActivateCode());
        return true;
    }

    @Override
    public void encodingUserPassword(UserDto userDto) {
        Optional<User> optionalUser = userRepo.findByName(userDto.getName());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        optionalUser.ifPresent(user -> userDto.setId(user.getId()));
        userRepo.updateUser(userDto);
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepo.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "There is not such user in Repo"));
    }

    @Override
    public boolean activateUser(String code) {
        Optional<User> optionalUser = userRepo.findNotActivatedUserByCode(code);
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        user.setActivate(true);
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreateDate(),
                user.getPassword(), user.getRole(), user.isActivate());
        userRepo.activateUser(userDto);
        userRepo.updateUser(userDto);
        return true;
    }
}
