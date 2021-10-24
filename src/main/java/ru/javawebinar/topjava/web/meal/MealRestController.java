package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal) {
        log.info("save {}", meal);
        return service.save(meal, authUserId());
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id, authUserId());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(id, authUserId());
    }

    public Collection<MealTo> getAll() {
        log.info("getAll");
        Collection<Meal> all = service.getAll(authUserId());
        return MealsUtil.getTos(all, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Collection<MealTo> filter(String startDate, String endDate, String startTime, String endTime) {
        LocalDate sDate = startDate.isEmpty() ? LocalDate.ofYearDay(1970, 1) : LocalDate.parse(startDate);
        LocalDate eDate = endDate.isEmpty() ? LocalDate.now() : LocalDate.parse(endDate);
        LocalTime sTime = startTime.isEmpty() ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime eTime = endTime.isEmpty() ? LocalTime.MAX : LocalTime.parse(endTime);

        return service.filter(sDate, eDate, sTime, eTime, authUserId());
    }
}
