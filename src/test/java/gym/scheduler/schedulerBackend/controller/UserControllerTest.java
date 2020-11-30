/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gym.scheduler.schedulerBackend.controller;

import gym.scheduler.schedulerBackend.model.entity.User;
import gym.scheduler.schedulerBackend.security.UserPrincipal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author andrb
 */
public class UserControllerTest {
    
    public UserControllerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of retrieveAllUsers method, of class UserController.
     */
    @Test
    public void testRetrieveAllUsers() {
        System.out.println("retrieveAllUsers");
        UserController instance = new UserController();
        List<User> expResult = null;
        List<User> result = instance.retrieveAllUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveById method, of class UserController.
     */
    @Test
    public void testRetrieveById() {
        System.out.println("retrieveById");
        int id = 0;
        UserController instance = new UserController();
        User expResult = null;
        User result = instance.retrieveById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retrieveCurrentUser method, of class UserController.
     */
    @Test
    public void testRetrieveCurrentUser() {
        System.out.println("retrieveCurrentUser");
        UserPrincipal u = null;
        UserController instance = new UserController();
        User expResult = null;
        User result = instance.retrieveCurrentUser(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdateUser method, of class UserController.
     */
    @Test
    public void testUpdateUser() {
        System.out.println("UpdateUser");
        int id = 0;
        User request = null;
        UserController instance = new UserController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.UpdateUser(id, request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of UpdatePassword method, of class UserController.
     */
    @Test
    public void testUpdatePassword() {
        System.out.println("UpdatePassword");
        int id = 0;
        User request = null;
        UserController instance = new UserController();
        ResponseEntity expResult = null;
        ResponseEntity result = instance.UpdatePassword(id, request);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
