package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.global.util.UserValidator;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository){this.userRepository = userRepository;}

    //회원가입 기능
    @Transactional
    public void createUser(final String name){
        UserValidator.validateNameFormat(name);

        User user = new User(name);
        userRepository.save(user);
    }
}
