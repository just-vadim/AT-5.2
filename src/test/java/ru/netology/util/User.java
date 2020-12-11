package ru.netology.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class User {
    Random random = new Random();
    private final String name;
    private final String password;
    private final List<String> statusList = new ArrayList(Arrays.asList("active", "blocked"));
    private final String status = statusList.get(random.nextInt(statusList.size()));

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }
}