package com.EMedConf.demo.Service;

import com.EMedConf.demo.Model.User;

public interface UserService {
void CreateUser(User user);
User FindByUsername(String Username);
User ExistsByUsername(String Username);
}
