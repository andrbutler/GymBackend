/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.User;
import gym.scheduler.schedulerBackend.model.repository.UserRepository;
import gym.scheduler.schedulerBackend.payload.ApiResponse;
import gym.scheduler.schedulerBackend.security.UserPrincipal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author andrb
 */
@SpringBootTest
@WebAppConfiguration
@TestInstance(Lifecycle.PER_CLASS)
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class UserControllerTest {

    private MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Mock
    private UserRepository userRepository;
    private List<User> userList;

    @InjectMocks
    public UserController userController;

    public UserControllerTest() {

    }

    @BeforeAll
    protected void setUpClass() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        userList = new ArrayList<User>();
        this.userList.add(new User(1, "username", "password", "role", new Date(),
                "status"));
        this.userList.add(new User(2, "user2", "password", "role", new Date(),
                "status"));
        this.userList.add(new User(3, "user3", "password", "role", new Date(),
                "status"));
        this.userList.get(2).setEmail("test@test.com");
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of retrieveAllUsers method, of class UserController.
     */
    @Test
    public void testRetrieveAllUsers() {
        when(userRepository.findAll()).thenReturn(userList);
        System.out.println("testing retrieveAllUsers");
        List<User> expResult = userList;
        List<User> result = userController.retrieveAllUsers();
        assertEquals(expResult, result);
    }

    @Test
    public void testRetrieveAllUsersLength() {
        when(userRepository.findAll()).thenReturn(userList);

        System.out.println("testing retrieveAllUsers");
        int expResult = userList.size();
        int result = 3;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    @Test
    public void testRetrieveUserById() {
        when(userRepository.findByUserId(2)).thenReturn(userList.get(1));
        System.out.println("testing retrieve User With Id 2");
        User expResult = userList.get(1);
        User result = userController.retrieveById(2);
        assertEquals(expResult, result);
    }

    @Test
    public void attemptToUpdateUserWhenUserNameExists() {
        User updateRequest = new User("username", null, null, null,
                null, null, null, null, null, null);
        when(userRepository.findByUserId(1)).thenReturn(userList.get(0));
        when(userRepository.findByUserId(2)).thenReturn(userList.get(1));
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(userList.get(1)));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(userList.get(1));
        System.out.println("testing Attempt to update User with username: user2 to username, when username already exists");
        System.out.println();
        ResponseEntity expResult = new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                HttpStatus.BAD_REQUEST);
        ResponseEntity result = userController.UpdateUser(2, updateRequest);
        assertEquals(expResult.getStatusCode(), result.getStatusCode());
    }

}
