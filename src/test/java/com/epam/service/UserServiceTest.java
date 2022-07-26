package com.epam.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.dao.UserDao;
import com.epam.entity.User;

class UserServiceTest {
    UserService userService;
    UserDao userDao;

    @BeforeEach
    void setup() {
        userDao = Mockito.mock(UserDao.class);
        userService = new UserService();
        userService.setUserDao(userDao);
    }

    @Test
    void getUserById() {
        //Given
        long userId = 3;
        User user1 = new User(userId, "Dave", "dave@gmail.com");
        User expected = new User(userId, "Dave", "dave@gmail.com");
        Mockito.when(userDao.getUserById(userId)).thenReturn(user1);

        //When
        User actual = userService.getUserById(userId);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUserByEmail() {
        //Given
        User user = new User(3, "Dave", "dave@gmail.com");
        User expected = new User(3L, "Dave", "dave@gmail.com");
        Mockito.when(userDao.getUserByEmail("dave@gmail.com")).thenReturn(Optional.of(user));

        //When
        User actual = userService.getUserByEmail("dave@gmail.com");

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUsersByName() {
        //Given
        User user1 = new User(3, "Dave", "dave1@gmail.com");
        User user2 = new User(5, "Dave", "dave2@gmail.com");
        List<User> userList = List.of(user1, user2);
        Mockito.when(userDao.getUsersByName("Dave", 2, 2)).thenReturn(userList);
        List<User> expected = List.of(user1, user2);

        //When
        List<User> actual = userService.getUsersByName("Dave", 2, 2);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void create_correctUser_equals() {
        //Given
        User user = new User(1, "Dave", "dave2@gmail.com");
        Mockito.when(userDao.create(user)).thenReturn(user);
        User expected = new User(1, "Dave", "dave2@gmail.com");

        //When
        User actual = userService.create(user);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void create_incorrectUser_exception() {
        //Given
        User user = new User(2, "Dave", "dave1@gmail.com");
        Mockito.when(userDao.create(user)).thenThrow(new IllegalStateException());

        //When-Then
        Assertions.assertThrows(IllegalStateException.class, () -> userDao.create(user));
    }

    @Test
    void update() {
        //Given
        User user = new User(1, "Jacob", "dave1@gmail.com");
        User expected = new User(1, "Jacob", "dave1@gmail.com");
        Mockito.when(userDao.update(user)).thenReturn(user);

        //When
        User actual = userService.update(user);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTrue() {
        //Given
        Mockito.when(userDao.delete(1L)).thenReturn(true);

        //When
        boolean actual = userService.delete(1L);

        //Then
        Assertions.assertTrue(actual);
    }

    @Test
    void deleteFalse() {
        //Given
        Mockito.when(userDao.delete(1L)).thenReturn(false);

        //When
        boolean actual = userService.delete(1L);

        //Then
        Assertions.assertFalse(actual);
    }
}