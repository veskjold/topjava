package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {

    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Breakfas", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 11, 0), "Dinner", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Supper", 400),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Breakfast", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Dinner", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Supper", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceeded.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field


      /* way #1: cycles
        Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();

        for (UserMeal item : mealList) {

            LocalDate timeOfMeal = item.getDateTime().toLocalDate();

            caloriesSumByDate.put(timeOfMeal, caloriesSumByDate.getOrDefault(timeOfMeal, 0) + item.getCalories());
        }

        List<UserMealWithExceed> resultList = new ArrayList<>();

        for (UserMeal item : mealList) {

            LocalDateTime mealTime = item.getDateTime();

            if (TimeUtil.isBetween(mealTime.toLocalTime(), startTime, endTime)) {
                resultList.add(new UserMealWithExceed(item.getDateTime(), item.getDescription(), item.getCalories(), caloriesSumByDate.getOrDefault(mealTime.toLocalDate(), 0) > caloriesPerDay));
            }

        }*/

        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(), caloriesSumByDate.get(um.getDateTime().toLocalDate())>caloriesPerDay))
                .collect(Collectors.toList());


    }
}
