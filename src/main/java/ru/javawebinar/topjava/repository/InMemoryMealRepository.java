package ru.javawebinar.topjava.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.javawebinar.topjava.utils.Util;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.utils.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer,Meal>> userMealMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    {
        MealsUtil.MEALS.forEach(meal -> save(meal, InMemoryUserRepository.USER_ID));
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0),
                "Admin lanch", 510), InMemoryUserRepository.ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0),
                "Admin lanch", 1500), InMemoryUserRepository.ADMIN_ID);
    }
    @Override
    public Meal save(Meal meal, int userId) {
        Map <Integer, Meal>meals = userMealMap.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()){
            meal.setId(counter.incrementAndGet());
            meals.put(meal.getId(), meal);
            return meal;
        }
        return meals.computeIfPresent(meal.getId(), (id, oldMeal)-> meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal>meals = userMealMap.get(userId);
        return meals != null && meals.remove(id)!=null;
    }

    @Override
    public Meal get(int id,  int userId) {
        Map<Integer, Meal>meals = userMealMap.get(userId);
        return meals== null? null: meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllFiltered(userId, meal-> true);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startTime, LocalDateTime endDate, int userId) {
        return getAllFiltered(userId, meal -> Util.isBetweenInclusive(meal.getDateTime(), startTime, endDate));
    }
    private List<Meal>getAllFiltered(int userId, Predicate<Meal>filter){
        Map<Integer, Meal>meals = userMealMap.get(userId);
        return CollectionUtils.isEmpty(meals)? Collections.emptyList():
                meals.values().stream()
                .filter(filter)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }
}
