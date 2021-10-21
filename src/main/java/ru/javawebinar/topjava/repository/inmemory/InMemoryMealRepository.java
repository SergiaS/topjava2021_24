package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.ADMINS_MEAL.forEach(meal -> save(meal,1));
        MealsUtil.USERS_MEAL.forEach(meal -> save(meal,2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        if (userMeals == null) {
            userMeals = new HashMap<>();
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
        }
        userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        repository.put(userId, userMeals);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.get(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDate).thenComparing(Meal::getTime).reversed())
                .collect(Collectors.toList());
    }
}

