package ru.javawebinar.topjava.repository.jdbc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealTestData;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.service.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcMealRepositoryTest {

    @Autowired
    private JdbcMealRepository repository;

    @Test
    public void save() {
        Meal created = repository.save(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(repository.get(newId, USER_ID), newMeal);
    }

    @Test
    public void delete() {
        Meal created = repository.save(getNew(), USER_ID);
        Integer id = created.getId();

        repository.delete(id, USER_ID);
        assertThrows(NotFoundException.class, () -> {
            if (repository.get(id, USER_ID) == null) {
                throw new NotFoundException("error");
            }
        });
    }

    @Test
    public void get() {
        Meal meal = repository.getAll(USER_ID).get(0);
        assertMatch(meal, meal3, "id");
    }

    @Test
    public void getAll() {
        List<Meal> all = repository.getAll(USER_ID);
        MealTestData.assertMatch(all, Arrays.asList(meal3, meal2, meal1), "id");
    }

    @Test
    public void getBetweenHalfOpen() {
        Meal meal = repository.getAll(USER_ID).get(0);

        List<Meal> all = repository.getAll(USER_ID).stream()
                .filter(dt -> dt.getDate().isEqual(meal.getDate()))
                .collect(Collectors.toList());

        assertMatch(all, Arrays.asList(meal3, meal2, meal1), "id");
    }

    @Test
    public void getNotOwn() {
        Meal userMeal = repository.getAll(USER_ID).get(0);
        assertThrows(NotFoundException.class, () -> {
            if (repository.get(userMeal.getId(), ADMIN_ID) == null) {
                throw new NotFoundException("error");
            }
        });
    }

    @Test
    public void deleteNotOwn() {
        Meal userMeal = repository.getAll(USER_ID).get(0);
        assertThrows(NotFoundException.class, () -> {
            if (!repository.delete(userMeal.getId(), ADMIN_ID)) {
                throw new NotFoundException("error");
            }
        });
    }

    @Test
    public void updateNotOwn() {
        Meal userMeal = repository.getAll(USER_ID).get(0);
        assertThrows(NotFoundException.class, () -> {
            if (repository.save(userMeal, ADMIN_ID) == null) {
                throw new NotFoundException("error");
            }
        });
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                repository.save(getDuplicateByDate(), USER_ID));
    }
}
