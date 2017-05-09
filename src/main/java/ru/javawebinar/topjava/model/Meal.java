package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
//@NamedQueries({
////        @NamedQuery(name = Meal.DELETEMEAL, query = "DELETE m FROM Meal m WHERE m.id=:id and m.user_id=:user_id")
//        @NamedQuery(name = Meal.DELETEMEAL, query = "DELETE FROM Meal m WHERE m.id=:id")
//})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "user_id","date_time"}, name = "meals_unique_user_datetime_idx")})

public class Meal extends BaseEntity {

    @Column (name = "date_time", nullable = false, unique = true)
    private LocalDateTime dateTime;

    @Column(name = "description")
    @NotEmpty
    @Length(max = 500)
    private String description;

    @Column(name = "calories")
    private int calories;

//    @Column(name = "user_id", updatable = false)
//    private int user_id;

    public Meal() {
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
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

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }

/*    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }*/
}
