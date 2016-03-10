package com.app.mvc.business.controller;

import com.app.mvc.beans.JsonData;
import com.app.mvc.http.HttpClients;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource(name = "redisPool")
    private ShardedJedisPool redisPool;

    @ResponseBody
    @RequestMapping(value = "test.do", method = RequestMethod.GET)
    public ModelAndView test() throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("sync", HttpClients.syncClient().get("http://www.test.com:8080/product/page.json").getContent());
        mav.addObject("async", HttpClients.asyncClient().asyncGet("http://www.test.com:8080/product/page.json").get().getContent());
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