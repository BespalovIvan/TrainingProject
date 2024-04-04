package com.example.trainingProject.service.impl;

import com.example.trainingProject.config.MyUserDetails;
import com.example.trainingProject.dto.UserDto;
import com.example.trainingProject.entity.Role;
import com.example.trainingProject.entity.User;
import com.example.trainingProject.repository.UserRepo;
import com.example.trainingProject.service.SmtpMailSender;
import com.example.trainingProject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepo userRepo;
    private final SmtpMailSender smtpMailSender;

    public UserServiceImpl(UserRepo userRepoSpring, SmtpMailSender smtpMailSender) {
        this.userRepo = userRepoSpring;
        this.smtpMailSender = smtpMailSender;
    }

    public Optional<UserDto> findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(value -> new UserDto(value.getName(), value.getEmail()));
    }

    public boolean createUser(UserDto userDto) {
        Optional<User> optionalUser = userRepo.findByName(userDto.getName());
        if (optionalUser.isPresent()) {
            return false;
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userDto.setCreateDate(LocalDateTime.now());
        userDto.setRole(Role.USER.toString());
        userDto.setActivateCode(UUID.randomUUID().toString());

        userRepo.createUser(userDto);
        if (!StringUtils.isEmpty(userDto.getEmail())) {
            String message = String.format("Hello, %s! \n" +
                            "Welcome to Shop. Please, visit next link: http://localhost:8080/activate/%s"
                    , userDto.getName(), userDto.getActivateCode());
            smtpMailSender.send(userDto.getEmail(), "Activate code", message);
        }

        return true;
    }

    public UserDetails loadUserByUsername(String username) {
        Optional<User> user = userRepo.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "There is not such user in Repo"));
    }

    @Override
    public boolean activateUser(String code) {
        Optional<User> optionalUser = userRepo.findUserByActivatedCode(code);
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();
        user.setActivateCode(null);
        UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail(), user.getCreateDate(), user.getPassword(),
                user.getRole(), user.getActivateCode());
        userRepo.updateUser(userDto);
        return true;
    }
}
