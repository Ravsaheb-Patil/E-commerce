package com.Electro.Controller;

import com.Electro.Dto.ApiResponseMassage;
import com.Electro.Dto.PageableResponse;
import com.Electro.Dto.UserDto;
import com.Electro.ServiceI.UserServiceI;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceI userServiceI;


    /**
     * @author Ravsaheb Patil
     * @apiNote save user into database
     * @param user
     * @return UserDto
     * @since 1.0v
     */


    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
        log.info("Enter the  request for Save the User : {} user");
        UserDto user1 = this.userServiceI.createUser(user);
        log.info("Comlpete the  request for Save the User : {} user");

        return new ResponseEntity<>(user1, HttpStatus.CREATED);


    }

    /**
     * @author Sujit Patil
     * @apiNote get  User By Id
     * @param userId
     * @return UserDto
     * @since 1.0v
     */



    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        UserDto userById = this.userServiceI.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);

    }
    /**
     * @author Ravsaheb Patil
     * @apiNote get All Users
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param direction
     * @return PageableResponse
     * @since 1.0v
     */

    @GetMapping("/")
    public ResponseEntity<PageableResponse> getAllUsers(

            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "name", required = false) String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc", required = false) String direction) {
        log.info("Enter the  request for get all users  ");
        PageableResponse allUsers = this.userServiceI.getAllUsers(pageNumber, pageSize, sortBy, direction);
        log.info("Completed the request for get all users  ");
        return new ResponseEntity<PageableResponse>(allUsers, HttpStatus.OK);
    }

    /**
     * @author Ravsaheb Patil
     * @param userDto
     * @param userId
     * @return UserDto
     * @since 1.0v
     */

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable String userId) {
        log.info("Enter the  request for update  the user with UserId :{} str");
        UserDto userDto1 = this.userServiceI.updateUser(userDto, userId);
        log.info("Complete the  request for update  the user with UserId :{} str");
        return new ResponseEntity<>(userDto1, HttpStatus.OK);


    }
    /**
     * @author Ravsaheb Patil
     * @apiNote get User  Containing keyword
     * @param keyword
     * @return List<UserDto>
     * @since 1.0v
     */


    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<UserDto>> getUserContaing(@PathVariable String keyword) {

        log.info("Enter the  request for get the user containing :{} ", keyword);
        List<UserDto> userDtos = this.userServiceI.searchUser(keyword);
        log.info("Completed the  request for get the user containing :{} ", keyword);

        return new ResponseEntity<List<UserDto>>(userDtos, HttpStatus.OK);

    }

    /**
     * @author Ravsaheb Patil
     * @apiNote get user by email
     * @param email
     * @return UserDto
     * @since 1.0v
     */

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        log.info("Enter the  request for get the user with EmailId :{} ",email);
        UserDto userByEmailId = this.userServiceI.getUserByEmailId(email);
        log.info("Completed the  request for get the user with EmailId :{} ",email);
        return new ResponseEntity<UserDto>(userByEmailId, HttpStatus.OK);
    }
    /**
     * @author Ravsaheb Patil
     * @apiNote get User By Email Id And Password
     * @param email
     * @param password
     * @return UserDto
     * @since 1.0v
     */




    @GetMapping("/email/{email}/pass/{password}")
    public ResponseEntity<UserDto> getUserByEmailAndPass(@PathVariable String email, @PathVariable String password) {

        log.info("Enter the  request for get the user with EmailId And Password :{} :{} ",email,password);
        UserDto userByEmailAndPassword = this.userServiceI.getUserByEmailAndPassword(email, password);
        log.info("Completed the  request for get the user with EmailId And Password :{} :{} ",email,password);
        return  new ResponseEntity<UserDto>(userByEmailAndPassword,HttpStatus.OK);
    }







}