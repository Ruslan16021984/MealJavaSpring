package com.gmail.carbit.model;

import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;
    private final String description;
    private final int calories;
    private final boolean exess;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean exess) {
        this.dateTime = dateTime;
        this.calories = calories;
        this.exess = exess;
        this.description= description;
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exess=" + exess +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExess() {
        return exess;
    }
}
