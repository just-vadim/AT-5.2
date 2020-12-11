package ru.netology.util;

import com.github.javafaker.Faker;

import java.util.Locale;


public class DataGenerator {
    private DataGenerator(){}

    public static User generateByNamePasswordStatus() {
        Faker faker = new Faker(new Locale("en"));
        return new User(
                faker.name().firstName().toLowerCase() + faker.numerify("###"),
                faker.regexify("[a-z1-9]{10}"));
    }
}
