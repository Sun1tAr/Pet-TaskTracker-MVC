package my.learn.PetTaskTrackerMVC.util;

import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class DateUtils {
    public static long daysUntil(LocalDate date) {
        if (date == null) return 0;
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }



}
