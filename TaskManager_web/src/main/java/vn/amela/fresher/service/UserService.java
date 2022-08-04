package vn.amela.fresher.service;

import vn.amela.fresher.entity.User;

public interface UserService {
    User findByUsername(String username);

    void save(User user);
}
