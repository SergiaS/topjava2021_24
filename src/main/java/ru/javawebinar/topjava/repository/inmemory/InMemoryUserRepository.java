package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);

    private final Map<Integer, User> repository;
    private final AtomicInteger counter;

    public InMemoryUserRepository() {
        this.repository = new ConcurrentHashMap<>();
        this.counter = new AtomicInteger(0);

        repository.put(counter.incrementAndGet(), new User(1, "ADMIN", "admin@google.com", "admin123", 2000, true, EnumSet.of(Role.ADMIN, Role.USER)));
        repository.put(counter.incrementAndGet(), new User(2, "User", "user@google.com", "user123", 2000, true, EnumSet.of(Role.USER)));
        repository.put(counter.incrementAndGet(), new User(3, "Bob", "bob@google.com", "b0bc0m", 2100, true, EnumSet.of(Role.USER)));
        repository.put(counter.incrementAndGet(), new User(4, "Katy", "katy@google.com", "kitykaty", 2000, true, EnumSet.of(Role.USER)));
    }


    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return repository.put(counter.incrementAndGet(), user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream()
                .sorted(Comparator.comparing(User::getName).thenComparing(User::getEmail))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst().orElse(null);
    }
}
