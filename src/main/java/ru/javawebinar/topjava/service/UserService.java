package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.utils.exeption.NotFoundExeption;

import java.util.List;

import static ru.javawebinar.topjava.utils.ValidationUtil.checkNotFound;
import static ru.javawebinar.topjava.utils.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(int id) throws NotFoundExeption {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) throws NotFoundExeption {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundExeption {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) throws NotFoundExeption {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
