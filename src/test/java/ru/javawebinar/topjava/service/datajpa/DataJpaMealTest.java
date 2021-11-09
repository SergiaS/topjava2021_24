package ru.javawebinar.topjava.service.datajpa;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles({Profiles.DATAJPA, Profiles.DB_IMPLEMENTATION})
@RunWith(SpringRunner.class)
public class DataJpaMealTest extends MealServiceTest {

    @Test
    @Transactional
    @Override
    public void delete() {
        super.delete();
    }

    @Test
    @Transactional
    @Override
    public void deleteNotFound() {
        super.deleteNotFound();
    }

    @Test
    @Transactional
    @Override
    public void deleteNotOwn() {
        super.deleteNotOwn();
    }

    @Test
    @Transactional
    @Override
    public void create() {
        super.create();
    }

    @Test
    @Override
    public void duplicateDateTimeCreate() {
        super.duplicateDateTimeCreate();
    }

    @Test
    @Override
    public void get() {
        super.get();
    }

    @Test
    @Override
    public void getNotFound() {
        super.getNotFound();
    }

    @Test
    @Override
    public void getNotOwn() {
        super.getNotOwn();
    }

    @Test
    @Transactional
    @Override
    public void update() {
        super.update();
    }

    @Test
    @Transactional
    @Override
    public void updateNotOwn() {
        super.updateNotOwn();
    }

    @Test
    @Override
    public void getAll() {
        super.getAll();
    }

    @Test
    @Override
    public void getBetweenInclusive() {
        super.getBetweenInclusive();
    }

    @Test
    @Override
    public void getBetweenWithNullDates() {
        super.getBetweenWithNullDates();
    }
}
