package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private final int CALORIES_PER_DAY = 2000;
    private final MealRepository repository = new InMemoryMealRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);

        switch (action){
            case "/new":
                log.debug("{} - redirect to new", action);
                showNewPage(request, response);
                break;
            case "/update":
                log.debug("{} - redirect to update", action);
                showUpdatePage(request, response);
                break;
            case "/delete":
                log.debug("{} - redirect to meals", action);
                delete(request, response);
                break;
            default:
                log.debug("{} - redirect to meals", action);
                showAll(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to doPost method by MealServlet");
        String action = getAction(request);

        Integer id = getId(request);
        LocalDateTime ldt = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = null;
        if (action.equals("/new")) {
            meal = new Meal(ldt, description, calories);
        } else if (action.equals("/update")) {
            meal = repository.get(id);
            meal.setDateTime(ldt);
            meal.setDescription(description);
            meal.setCalories(calories);
        }
        repository.addOrUpdate(meal);
        response.sendRedirect(request.getContextPath() + request.getServletPath());
    }


    private void showNewPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/newOrUpdate.jsp").forward(request, response);
    }

    private void showUpdatePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = getId(request);
        Meal meal = repository.get(id);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/newOrUpdate.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        repository.delete(getId(request));
        response.sendRedirect(request.getContextPath() + request.getServletPath());
    }

    private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Meal> meals = repository.getAll();
        request.setAttribute("mealsTo", MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }


    private Integer getId(HttpServletRequest request) {
        String id = request.getParameter("id");
        return Objects.equals(id, "") ? null : Integer.valueOf(id);
    }

    private String getAction(HttpServletRequest request) {
        String action = request.getPathInfo();
        return action == null ? "/meals" : action;
    }
}
