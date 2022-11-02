package ru.ligaintenship.prerevolutionarytinder.domain;

import ru.ligaintenship.prerevolutionarytinder.SpringJdbcConnectionProvider;

public class UserUpdater {
    private final SpringJdbcConnectionProvider provider;

    public UserUpdater(SpringJdbcConnectionProvider provider) {
        this.provider = provider;
    }

    public void update(User resource) {
        String sql = "update tinder.tinder_users set sex = '%s', name = '%s', story = '%s', looking_for = '%s' where id = '%s'"
                .formatted(resource.getSex(), resource.getName(), resource.getStory(), resource.getLooking_for(), resource.getId());
        provider.putData(sql);
    }
}
