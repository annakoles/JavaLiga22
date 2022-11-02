package ru.ligaintenship.prerevolutionarytinder.domain;

import ru.ligaintenship.prerevolutionarytinder.SpringJdbcConnectionProvider;

import java.util.ArrayList;
import java.util.List;

public class UserFinder {
    private final SpringJdbcConnectionProvider provider;
    private List<User> userList = new ArrayList<>();

    public UserFinder(SpringJdbcConnectionProvider provider) {
        this.provider = provider;
    }

    public List<User> findAll() {
        return userList;
    }

    public User findById(Long id) {
        User foundedUser = userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .get();
        return foundedUser;
    }
}
