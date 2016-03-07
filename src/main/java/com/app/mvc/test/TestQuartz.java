package com.app.mvc.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by jimin on 15/11/21.
 */
@Slf4j
@Component
public class TestQuartz {

    //@Scheduled(cron = "0 0/1 * * * ?")
    public void execute1() {
        log.info("quartz 1 min " + new Date().toString());
    }

    //@Scheduled(fixedDelay = 5000)
    public void execute2() {
        log.info("quartz delay 5000 " + new Date().toString());
    }
}
