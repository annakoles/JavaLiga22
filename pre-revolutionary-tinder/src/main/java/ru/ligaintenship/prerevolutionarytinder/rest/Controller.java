package ru.ligaintenship.prerevolutionarytinder.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.ligaintenship.prerevolutionarytinder.domain.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class Controller {
    private final UserFinder finder;
    private final UserCreator creator;
    private final UserUpdater updater;
    private final UserDeleter deleter;

    public Controller(UserFinder finder, UserCreator creator, UserUpdater updater, UserDeleter deleter) {
        this.finder = finder;
        this.creator = creator;
        this.updater = updater;
        this.deleter = deleter;
    }

    @GetMapping
    public List<User> findAll() {
        return finder.findAll();
    }

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable("id") Long id) {
        return finder.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int create(@RequestBody User resource) {
        return creator.create(resource);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody User resource) {
        updater.update(resource);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        deleter.deleteById(id);
    }
}
