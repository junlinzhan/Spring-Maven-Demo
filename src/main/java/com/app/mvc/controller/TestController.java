package com.app.mvc.controller;

import com.app.mvc.beans.JsonData;
import com.app.mvc.dao.AdminDao;
import com.app.mvc.domain.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    AdminDao adminDao;

    @Resource(name = "redisPool")
    private ShardedJedisPool redisPool;

    @ResponseBody
    @RequestMapping(value = "hello.json", method = RequestMethod.GET)
    public ModelAndView printWelcome(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("ret", true);
        Admin admin = adminDao.findById(1);
        mav.addObject("data", admin);
        log.info("xxx");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "hello2.json", method = RequestMethod.GET)
    public JsonData hello2(HttpServletRequest request) {
        return JsonData.success("xxx");
    }

    @RequestMapping(value = "test.do", method = RequestMethod.GET)
    public ModelAndView test(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("testMVC");
        mav.addObject("message", "test");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "setRedis.do", method = RequestMethod.GET)
    public JsonData saveRedis(@RequestParam("k") String k, @RequestParam("v") String v, @RequestParam("timeout") int timeout) {
        redisPool.getResource().setex(k, timeout, v);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping(value = "getRedis.do", method = RequestMethod.GET)
    public JsonData getRedis(@RequestParam("k") String k) {
        return JsonData.success(redisPool.getResource().get(k));
    }

}