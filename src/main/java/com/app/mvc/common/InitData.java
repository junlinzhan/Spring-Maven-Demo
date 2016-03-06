package com.app.mvc.common;

import com.app.mvc.captcha.CaptchaImgFont;
import com.app.mvc.configuration.DatabaseConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jimin on 15/11/21.
 */
@Slf4j
public class InitData {

    public static void start() {

        log.info("init data start");

        DatabaseConfig.loadAllConfig();
        CaptchaImgFont.initImgFont();

        log.info("init data finish");

    }
}
