package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal addOrUpdate(Meal meal);

    Meal get(Integer id);

    boolean delete(Integer id);

    List<Meal> getAll();

}
