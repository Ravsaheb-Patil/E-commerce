package com.Electro.Service;
import com.Electro.Dto.UserDto;
import com.Electro.Entity.User;
import com.Electro.Repository.UserRepo;
import com.Electro.ServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    @InjectMocks
    ModelMapper modelMapper;

    User user;
    @BeforeEach
    public void init(){
       user = User.builder().userName("Sachin")
                .userGender("Male").userEmail("sachin@gmail.com")
                .about("Java Dev").userPassword("sach@1212").imageName("isd.img")
                .userId(UUID.randomUUID().toString()).build();
    }
    @Test
    public void createUserTest(){
        Mockito.when(userRepo.save(Mockito.any())).thenReturn(user);
        UserDto user1 = userServiceImpl.createUser(modelMapper.map(user, UserDto.class));
        Assertions.assertNotNull(user1);
    }


}
