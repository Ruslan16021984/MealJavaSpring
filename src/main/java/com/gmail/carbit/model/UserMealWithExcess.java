package com.gmail.carbit.model;

import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;
    private final int calories;
    private final boolean exess;

    public UserMealWithExcess(LocalDateTime dateTime, int calories, boolean exess) {
        this.dateTime = dateTime;
        this.calories = calories;
        this.exess = exess;
    }

    @Override
    public String toString() {
        return "com.gmail.carbit.model.UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", calories=" + calories +
                ", exess=" + exess +
                '}';
    }
}
