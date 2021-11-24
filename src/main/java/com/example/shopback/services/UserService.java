package com.example.shopback.services;

import com.example.shopback.support.MessageResponse;
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


    public MessageResponse createUser(User user){
        String password = user.getPassword();
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        User p = userRepository.save(user);
        return new MessageResponse("User successfully created", p.getId());
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public MessageResponse updateUser(User user){
        User p = userRepository.save(user);
        return new MessageResponse("User successfully updated", p.getId());
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
        return null;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


}
