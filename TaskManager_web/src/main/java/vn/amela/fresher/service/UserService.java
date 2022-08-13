package vn.amela.fresher.service;

import vn.amela.fresher.entity.User;
import vn.amela.fresher.model.UserDto;

public interface UserService {
    User findByUsername(String username);

    User save(User user);

    User register(UserDto userDto);


}
