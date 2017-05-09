package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        if (meal.isNew()) {
            meal.setUser(ref);
            em.persist(meal);
        }
        else {
            meal.setUser(ref);
            em.merge(meal);
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {

        Query query = em.createQuery("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:user_id");
        query.setParameter("user_id", userId);
        query.setParameter("id", id);
        return  query.executeUpdate()!=0;
    }

    @Override
    public Meal get(int id, int userId) {
        Query query = em.createQuery("SELECT m FROM Meal m WHERE  m.user.id=:user_id and m.id=:id");
//        Query query = em.createQuery("SELECT m FROM Meal m WHERE m.id=:id");
        query.setParameter("user_id", userId);
        query.setParameter("id", id);
        return (Meal) query.getSingleResult();
    }

    @Override
    public List<Meal> getAll(int userId) {
        Query query = em.createQuery("SELECT m FROM Meal m WHERE  m.user.id=:user_id");
//        Query query = em.createQuery("SELECT m FROM Meal m WHERE m.id=:id");
        query.setParameter("user_id", userId);
        return  query.getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}