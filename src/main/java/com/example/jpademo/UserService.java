package com.example.jpademo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service 
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserClassSpringBoot createUser(UserClassSpringBoot user) {
        return userRepository.save(user);
    }
    public List<UserClassSpringBoot> getUsers(){
        return userRepository.findAll();
    }


}
