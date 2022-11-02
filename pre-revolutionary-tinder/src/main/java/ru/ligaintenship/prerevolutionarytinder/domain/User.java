package ru.ligaintenship.prerevolutionarytinder.domain;

import lombok.Data;

@Data
public class User {
    private final Long id;
    private final String sex;
    private final String name;
    private final String story;
    private final String looking_for;
}
