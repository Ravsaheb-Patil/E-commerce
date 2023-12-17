package com.Electro.ServiceI;

import com.Electro.Dto.PageableResponse;
import com.Electro.Dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServiceI {



    UserDto createUser(UserDto userDto);

    UserDto updateUser(UserDto userDto ,String userId);

    UserDto getUserById(String userId);

    PageableResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String direction);


    void deleteUser(String userId);



    UserDto getUserByEmailId(String email);

    List<UserDto> searchUser(String keyword);

    UserDto getUserByEmailAndPassword(String email, String password);


    UserDto getUserImage(String userId);


    UserDto getSingleUser(String userId);
}
