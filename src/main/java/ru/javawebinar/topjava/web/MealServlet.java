package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Rivera on 18.12.2016.
 */
public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LOG.debug("redirect to MEAL_LIST");
        req.setAttribute("mealList", MealsUtil.getFilteredWithExceeded(MealsUtil.MEAL_LIST, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
