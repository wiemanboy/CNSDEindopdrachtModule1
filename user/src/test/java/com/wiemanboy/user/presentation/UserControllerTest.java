package com.wiemanboy.user.presentation;

import com.wiemanboy.user.application.UserService;
import com.wiemanboy.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Test
    void registerUser() {
        UserService userService = Mockito.mock(UserService.class);
        when(userService.registerUser("Jan")).thenReturn(new User("Jan"));
        UserController userController = new UserController(userService);
        assertEquals("Jan",userController.registerUser("Jan").getUsername());
        Mockito.verify(userService, Mockito.times(1)).registerUser("Jan");
    }

    @Test
    void updateUser() {
        UUID id = UUID.randomUUID();
        UserService userService = Mockito.mock(UserService.class);
        when(userService.updateUser(id, "Jan")).thenReturn(new User(id,"Jan" ));
        UserController userController = new UserController(userService);
        assertEquals(id,userController.updateUser(id, "Jan").getId());
        Mockito.verify(userService, Mockito.times(1)).updateUser(id, "Jan");
    }

    @Test
    void deleteUser() {
        UUID id = UUID.randomUUID();
        UserService userService = Mockito.mock(UserService.class);
        doNothing().when(userService).deleteUser(id);
        UserController userController = new UserController(userService);
        userController.deleteUser(id);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(id);
    }

    @Test
    void getUser() {
        UUID id = UUID.randomUUID();
        UserService userService = Mockito.mock(UserService.class);
        when(userService.getUser(id)).thenReturn(new User(id, "Jan"));
        UserController userController = new UserController(userService);
        assertEquals(id,userController.getUser(id).getId());
        Mockito.verify(userService, Mockito.times(1)).getUser(id);
    }

    @Test
    void getUsers() {
        UserService userService = Mockito.mock(UserService.class);
        when(userService.getUsers()).thenReturn(null);
        UserController userController = new UserController(userService);
        userController.getUsers();
        Mockito.verify(userService, Mockito.times(1)).getUsers();
    }

    @Test
    void userExists() {
        UUID id = UUID.randomUUID();
        UserService userService = Mockito.mock(UserService.class);
        when(userService.userExists(id)).thenReturn(true);
        UserController userController = new UserController(userService);
        assertTrue(userController.userExists(id));
        Mockito.verify(userService, Mockito.times(1)).userExists(id);
    }
}