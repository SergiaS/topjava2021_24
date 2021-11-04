package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = getUserReferenceById(userId);
        if (meal.isNew()) {
            meal.setUser(ref);
            em.persist(meal);
            return meal;
        } else {
            Meal updMeal = em.find(Meal.class, meal.getId());
            if (userId != updMeal.getUser().getId()) {
                throw new NotFoundException("Not own meal");
            }
            meal.setUser(updMeal.getUser());
            return em.merge(meal);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter(1, id)
                .setParameter(2, getUserReferenceById(userId))
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        if (meal == null) {
            throw new NotFoundException("Meal is null");
        }

        User owner = meal.getUser();
        if (owner != null && owner.getId() != userId) {
            throw new NotFoundException("Not own meal");
        }
        return meal;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter(1, getUserReferenceById(userId))
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.DATE_TIME_FILTER, Meal.class)
                .setParameter(1, startDateTime)
                .setParameter(2, endDateTime)
                .setParameter(3, getUserReferenceById(userId))
                .getResultList();
    }

    private User getUserReferenceById(int userId) {
        return em.getReference(User.class, userId);
    }
}
