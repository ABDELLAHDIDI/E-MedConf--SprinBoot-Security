package com.EMedConf.demo.Service.Implementation;

import com.EMedConf.demo.Model.User;
import com.EMedConf.demo.Repository.UserRepository;
import com.EMedConf.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

@Autowired
UserRepository userRepository;
    @Override
    public void CreateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User FindByUsername(String Username) {
        return userRepository.findByUsername(Username);
    }

    @Override
    public User ExistsByUsername(String Username) {
        try{
            return userRepository.existsUserByUsername(Username);
        }
        catch (Exception e){
            return null;
        }

    }
}
