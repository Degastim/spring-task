package com.epam.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.epam.entity.User;
import com.epam.storage.Storage;

class UserDaoTest {
    UserDao userDao;
    Storage storage;

    @BeforeEach
    void setup() {
        storage = Mockito.mock(Storage.class);
        userDao = new UserDao();
        userDao.setStorage(storage);
    }

    @Test
    void getUserById() {
        //Given
        long userId = 3;
        Map<Long, User> map = new HashMap<>();
        User user1 = new User(userId, "Dave", "dave@gmail.com");
        User user2 = new User(4, "Milana", "milana@gmail.com");
        map.put(userId, user1);
        map.put(4L, user2);
        User expected = new User(userId, "Dave", "dave@gmail.com");
        Mockito.when(storage.getUserMap()).thenReturn(map);

        //When
        User actual = userDao.getUserById(userId);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUserByEmail() {
        //Given
        Map<Long, User> map = new HashMap<>();
        User user1 = new User(3, "Dave", "dave@gmail.com");
        User user2 = new User(4, "Milana", "milana@gmail.com");
        map.put(3L, user1);
        map.put(4L, user2);
        Optional<User> expected = Optional.of(new User(3L, "Dave", "dave@gmail.com"));
        Mockito.when(storage.getUserMap()).thenReturn(map);

        //When
        Optional<User> actual = userDao.getUserByEmail("dave@gmail.com");

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getUsersByName() {
        //Given
        Map<Long, User> map = new HashMap<>();
        User user1 = new User(3, "Dave", "dave1@gmail.com");
        User user2 = new User(4, "Milana", "milana@gmail.com");
        User user3 = new User(5, "Dave", "dave2@gmail.com");
        User user4 = new User(6, "Dave", "dave3@gmail.com");
        User user5 = new User(7, "Dave", "dave4@gmail.com");
        map.put(3L, user1);
        map.put(4L, user2);
        map.put(5L, user3);
        map.put(6L, user4);
        map.put(7L, user5);
        List<User> expected = List.of(user4, user5);
        Mockito.when(storage.getUserMap()).thenReturn(map);

        //When
        List<User> actual = userDao.getUsersByName("Dave", 2, 2);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void create_correctUser_equals() {
        //Given
        User user = new User(2, "Dave", "dave2@gmail.com");
        User mapUser = new User(1, "Dave", "dave1@gmail.com");
        Map<Long, User> userMap = new HashMap<>();
        userMap.put(1L, mapUser);
        Mockito.when(storage.getUserMap()).thenReturn(userMap);
        User expected = new User(1, "Dave", "dave2@gmail.com");

        //When
        User actual = userDao.create(user);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void create_incorrectUser_exception() {
        //Given
        User user = new User(2, "Dave", "dave1@gmail.com");
        User mapUser = new User(1, "Dave", "dave1@gmail.com");
        Map<Long, User> userMap = new HashMap<>();
        userMap.put(1L, mapUser);
        Mockito.when(storage.getUserMap()).thenReturn(userMap);

        //When-Then
        Assertions.assertThrows(IllegalStateException.class, () -> userDao.create(user));
    }

    @Test
    void update() {
        //Given
        Map<Long, User> userMap = new HashMap<>();
        User user = new User(1, "Dave", "dave1@gmail.com");
        userMap.put(1L, user);
        User updateEvent = new User(1, "Jacob", "dave1@gmail.com");
        User expected = new User(1, "Jacob", "dave1@gmail.com");
        Mockito.when(storage.getUserMap()).thenReturn(userMap);

        //When
        User actual = userDao.update(updateEvent);

        //Then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteTrue() {
        //Given
        Map<Long, User> userMap = new HashMap<>();
        User user = new User(1, "Dave", "dave1@gmail.com");
        userMap.put(1L, user);
        Mockito.when(storage.getUserMap()).thenReturn(userMap);

        //When
        boolean actual = userDao.delete(1L);

        //Then
        Assertions.assertTrue(actual);
    }

    @Test
    void deleteFalse() {
        //Given
        Map<Long, User> userMap = new HashMap<>();
        User user = new User(1, "Dave", "dave1@gmail.com");
        userMap.put(1L, user);
        Mockito.when(storage.getUserMap()).thenReturn(userMap);

        //When
        boolean actual = userDao.delete(2L);

        //Then
        Assertions.assertFalse(actual);
    }
}