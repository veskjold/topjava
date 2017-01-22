package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Rivera on 04.01.2017.
 */
public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
              new User(null, "User1", "test@mail.ru",  "12345", Role.ROLE_USER, null),
              new User(null, "User2", "test2@mail.ru",  "12345", Role.ROLE_USER, null),
              new User(null, "User3", "test3@mail.ru",  "12345", Role.ROLE_USER, null),
              new User(null, "User4", "test4@mail.ru",  "12345", Role.ROLE_USER, null),
              new User(null, "User5", "test5@mail.ru",  "12345", Role.ROLE_USER, null)
    );

}
