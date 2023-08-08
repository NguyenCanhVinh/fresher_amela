package vn.amela.fresher.service.Impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.amela.fresher.entity.User;
import vn.amela.fresher.mapper.UserMapper;
import vn.amela.fresher.model.UserDto;
import vn.amela.fresher.repository.UserRepository;
import vn.amela.fresher.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository, ModelMapper modelMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }


    @Override
    public User save(User user){
        return  userRepository.save(user);
    }

    @Override
    public User register(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        User user= new User();
        modelMapper.map(userDto,user);
        return  save(user);

    }
}
