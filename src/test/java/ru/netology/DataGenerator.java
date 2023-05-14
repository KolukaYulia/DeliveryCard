package ru.netology;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private static Faker faker;
    private static final Random random = new Random();
    private DataGenerator() {
    }

    public static int randomPeriod(int begin, int end) {
        return random.nextInt(end + 1 - begin) + begin;
    }

    public static String generateDate(int plusdays, String pattern ) {
        return LocalDate.now().plusDays(plusdays).format(DateTimeFormatter.ofPattern(pattern));

        // TODO: добавить логику для объявления переменной date и задания её значения, для генерации строки с датой
        // Вы можете использовать класс LocalDate и его методы для получения и форматирования даты

    }

    public static String generateCity(String local) {
        Faker faker = new Faker(new Locale(local));

        // TODO: добавить логику для объявления переменной city и задания её значения, генерацию можно выполнить
        // с помощью Faker, либо используя массив валидных городов и класс Random
        return faker.address().city();
    }

    public static String generateName(String local) {
        Faker faker = new Faker(new Locale(local));
        // TODO: добавить логику для объявления переменной name и задания её значения, для генерации можно
        // использовать Faker
        return faker.name().fullName();
    }

    public static String generatePhone(String local) {
        Faker faker = new Faker(new Locale(local));

        // TODO: добавить логику для объявления переменной phone и задания её значения, для генерации можно
        // использовать Faker
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String local) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            return new UserInfo(
                    generateCity(local), generateName(local), generatePhone(local));
        };

    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
