package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    public Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        //qwestion 3 - sorting
        UserUtil.USERS.forEach(this::save);
    }

    //qwestion 1 - delete in one step
    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        if (repository.get(id) == null) {
            return false;
        } else {

            repository.remove(id);
            return true;
        }
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);

        if (user.isNew())
            user.setId(counter.incrementAndGet());

        repository.put(user.getId(), user);

        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return new ArrayList<>(repository.values());
    }

    //qwestion 2 - finding in one field
    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        for (User user : repository.values())
            if (user.getEmail().equals(email))
                return user;
        return null;
    }
}
