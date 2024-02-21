package org.fasttrackit.country.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.HOURS;

@Slf4j
@Component
public class ScreamSchedular {

    //    @Scheduled(cron = "7 * * * * *")
    @Scheduled(fixedRate = 1, timeUnit = HOURS)
    public void scream() {
        log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
    }
}
