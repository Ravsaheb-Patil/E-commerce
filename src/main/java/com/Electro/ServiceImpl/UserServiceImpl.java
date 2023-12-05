package com.Electro.ServiceImpl;

import com.Electro.Dto.PageableResponse;
import com.Electro.Dto.UserDto;
import com.Electro.Entity.User;
import com.Electro.Exception.ResourceNotFoundException;
import com.Electro.Helper.Helper;
import com.Electro.Repository.UserRepo;
import com.Electro.ServiceI.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {

   @Autowired
    private UserRepo userRepo;

   @Autowired
   ModelMapper modelMapper;
    @Value("${user.profile.image.path}")
    private String path;


    @Override
    public UserDto createUser(UserDto userDto) {
        log.info("Entering Dao call for Create User");
        String str = UUID.randomUUID().toString();
        userDto.setUserId(str);
        User user = this.modelMapper.map(userDto, User.class);
        this.userRepo.save(user);
        UserDto userDto1 = this.modelMapper.map(user, UserDto.class);
        log.info("Complete Dao Call create User");
        return userDto1;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        log.info("\"Entering the Dao call for update the user with userId :{}\",id ");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("ResourceNotFound with Id "));

        user.setUserName(userDto.getUserName());
        user.setAbout(userDto.getAbout());
        user.setUserGender(userDto.getUserGender());
        user.setUserPassword(userDto.getUserPassword());
        user.setImageName(userDto.getImageName());
        this.userRepo.save(user);
        UserDto userDto1 = this.modelMapper.map(user, UserDto.class);
        log.info("\"Complete the Dao call for update the user with userId :{}\",id ");
        return userDto1;
    }

    @Override
    public UserDto getUserById(String userId) {
        log.info("Entering the Dao call for get Single user with userId :{} id");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("ResourceNotFound with Id "));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        log.info("Completed the Dao call for get Single user with userId :{} id");
        return userDto;
    }


    @Override
    public PageableResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String direction) {
        log.info("Entering the Dao call for getAll the users ");
        Sort desc = (direction.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        PageRequest pr = PageRequest.of(pageNumber, pageSize, desc);
        Page<User> pages = this.userRepo.findAll(pr);
        List<User> users = pages.getContent();
        List<UserDto> dtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        PageableResponse<UserDto> pageableResponse = Helper.getPageableResponse(pages, UserDto.class);
        log.info("Completed the Dao call for getAll the users ");
        return (PageableResponse) dtos;
    }

    @Override
    public void deleteUser(String userId) {
        log.info("Entering the Dao call for delete the user with userId :{} id");
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("ResourceNotFound with Id "));
        String imageName = user.getImageName();
        String fullPath = path + imageName;

        File Image=new File(fullPath);
        if(Image.exists()){
            Image.delete();
        }

        log.info("Completed the Dao call for delete the user with userId :{} id");
        this.userRepo.delete(user);

    }
    @Override
    public UserDto getUserByEmailId(String email) {
        log.info("Entering the Dao call for get Single user with emailId :{}  id");
        User user = this.userRepo.findByUserEmail(email).orElseThrow(() -> new ResourceNotFoundException("ResourceNotFound with Id "));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        log.info("Completed the Dao call for get Single user with emailId :{} id");
        return userDto;
    }

    @Override
    public List<UserDto> searchUser(String keyword) {
        log.info("Entering the Dao call for get  users with Containing :{} ");
        List<User> users = this.userRepo.findAll();
        List<UserDto> dtos = users.stream().map(user -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        log.info("Comleted the Dao call for get  users with Containing :{} ");
        return dtos;
    }

    @Override
    public UserDto getUserByEmailAndPassword(String email, String password) {
        log.info("Entering the Dao call for get Single user with Email And Password  :{} :{} ",email,password);
        User user = this.userRepo.findByUserEmailAndUserPassword(email, password).get();
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        log.info("Completed the Dao call for get Single user with Email And Password  :{} :{} ",email,password);
        return userDto;
    }

}

