package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles({Profiles.DATAJPA, Profiles.DB_IMPLEMENTATION})
@RunWith(SpringRunner.class)
public class DataJpaUserTest extends UserServiceTest {

    @Test
    @Override
    public void setup() {
        super.setup();
    }

    @Test
    @Transactional
    @Override
    public void create() {
        super.create();
    }

    @Test
    @Override
    public void duplicateMailCreate() {
        super.duplicateMailCreate();
    }

    @Test
    @Transactional
    @Override
    public void delete() {
        super.delete();
    }

    @Test
    @Transactional
    @Override
    public void deletedNotFound() {
        super.deletedNotFound();
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
    public void getByEmail() {
        super.getByEmail();
    }

    @Test
    @Transactional
    @Override
    public void update() {
        super.update();
    }

    @Test
    @Override
    public void getAll() {
        super.getAll();
    }
}
