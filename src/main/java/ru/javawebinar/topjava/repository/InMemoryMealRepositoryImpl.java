package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealRepositoryImpl implements MealRepository {

    private final AtomicInteger COUNTER;
    private final Map<Integer, Meal> REPOSITORY;

    public InMemoryMealRepositoryImpl() {
        this.COUNTER = new AtomicInteger(0);
        this.REPOSITORY = new ConcurrentHashMap<>();

        MealsUtil.meals.forEach(meal -> REPOSITORY.put(COUNTER.incrementAndGet(), meal));
    }

    @Override
    public Meal addOrUpdate(Meal meal) {
        if (meal.getId() == null) {
            meal.setId(COUNTER.incrementAndGet());
        }
        return REPOSITORY.put(meal.getId(), meal);
    }

    @Override
    public Meal get(Integer id) {
        return REPOSITORY.get(id);
    }

    @Override
    public boolean delete(Integer id) {
        return REPOSITORY.remove(id) != null;
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(REPOSITORY.values());
    }
}
