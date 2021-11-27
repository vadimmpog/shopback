package com.example.shopback.services;

import com.example.shopback.models.User;
import com.example.shopback.repos.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public Integer createUser(User user){
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        User p = userRepository.save(user);
        return p.getId();
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public Integer updateUser(User user){
        Integer id = user.getId();
        if(id != null && id != 0){
            String password = user.getPassword();
            if(password != null && !user.passwordIsEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            User oldUser = userRepository.getById(id);
            user.update(oldUser);
            User u = userRepository.save(user);
            return u.getId();
        }
        return 0;
    }

    public Optional<User> findUserById(Integer id){
        return userRepository.findById(id);
    }

    public Optional<User> findUserByLoginPassword(String login, String password){
        Optional<User> user = userRepository.getByLogin(login);
        if(user.isPresent()){
            if(passwordEncoder.matches(password, user.get().getPassword()))
                return user;
        }
        return Optional.empty();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}
