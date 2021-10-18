package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class Meal extends AbstractBaseMeal {

    public Meal(LocalDateTime dateTime, String description, int calories) {
        super(dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id, dateTime, description, calories);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
