package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.utils.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class SecurityUtil {
    public static int authUserId(){
        return 1;
    }
    public static int authUserCaloriesPerDay(){
        return DEFAULT_CALORIES_PER_DAY;
    }
}
