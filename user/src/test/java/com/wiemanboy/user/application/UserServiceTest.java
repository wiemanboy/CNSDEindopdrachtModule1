package com.wiemanboy.user.application;

import com.wiemanboy.user.data.UserRepository;
import com.wiemanboy.user.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Test
    void registerUser() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.save(any())).thenReturn(new User("test"));

        UserService userService = new UserService(userRepository);

        assertNotNull(userService.registerUser("test"));
    }

    @Test
    void deleteUser() {
        UUID testID = UUID.randomUUID();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        doNothing().when(userRepository).deleteById(testID);

        UserService userService = new UserService(userRepository);
        userService.deleteUser(testID);

        verify(userRepository, times(1)).deleteById(testID);
    }

    @Test
    void updateUser() {
        UUID testID = UUID.randomUUID();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findById(testID)).thenReturn(java.util.Optional.of(new User("test")));
        when(userRepository.save(new User("test2"))).thenReturn(new User("test2"));

        UserService userService = new UserService(userRepository);
        User user = userService.updateUser(testID, "test2");
        assertNotNull(user);
    }

    @Test
    void getUser() {
        UUID testID = UUID.randomUUID();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findById(testID)).thenReturn(java.util.Optional.of(new User("test")));

        UserService userService = new UserService(userRepository);

        assertNotNull(userService.getUser(testID));
    }

    @Test
    void getUsers() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(java.util.List.of(new User("test")));

        UserService userService = new UserService(userRepository);

        assertNotNull(userService.getUsers());
    }

    @Test
    void userExists() {
        UUID testID = UUID.randomUUID();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        when(userRepository.existsById(testID)).thenReturn(true);

        UserService userService = new UserService(userRepository);

        assertTrue(userService.userExists(testID));
    }
}