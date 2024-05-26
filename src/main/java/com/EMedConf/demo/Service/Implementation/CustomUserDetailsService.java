package com.EMedConf.demo.Service.Implementation;

import com.EMedConf.demo.Model.User;
import com.EMedConf.demo.Service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

@Autowired
UserServiceImplementation userServiceImplementation;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user =
                userServiceImplementation.FindByUsername(username) ;

       if(user==null){
           throw new UsernameNotFoundException("userNot Found ");
       }
          return new CustomUserDetails(user);
    }
}