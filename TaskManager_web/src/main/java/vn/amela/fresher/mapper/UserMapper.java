package vn.amela.fresher.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vn.amela.fresher.entity.User;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    void save(@Param("user") User user);
}
