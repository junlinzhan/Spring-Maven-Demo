package com.app.mvc.business.controller;

import com.app.mvc.beans.JsonData;
import com.app.mvc.business.service.TestDataSourceService;
import com.app.mvc.business.service.TestService;
import com.app.mvc.http.HttpClients;
import com.app.mvc.redis.RedisPool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestDataSourceService testDataSourceService;
    @Resource
    private TestService testService;

    // @Resource(name = "redisPool")
    private RedisPool redisPool;
    //    @Resource
    //    private MessageProduceService messageProduceService;

    @ResponseBody
    @RequestMapping(value = "testMq.json", method = RequestMethod.GET)
    public JsonData testRabbitMQ(@RequestParam(value = "msg", defaultValue = "test") String msg) throws Exception {
        try {
            //            messageProduceService.pushToMessageQueue(msg);
            //            messageProduceService.pushToMessageQueue("testQ", "q_" + msg);
        } catch (Throwable t) {
            log.error("添加消息到rabbitmq出错", t);
            return JsonData.error(t.getMessage());
        }
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping(value = "testHttp.json", method = RequestMethod.GET)
    public ModelAndView testHttp() throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        mav.addObject("sync", HttpClients.syncClient().get("http://www.test.com:8080/product/page.json").getContent());
        mav.addObject("async", HttpClients.asyncClient().asyncGet("http://www.test.com:8080/product/page.json").get().getContent());
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "setRedis.do", method = RequestMethod.GET)
    public JsonData saveRedis(@RequestParam("k") String k, @RequestParam("v") String v, @RequestParam("timeout") int timeout) {
        redisPool.instance().setex(k, timeout, v);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping(value = "getRedis.do", method = RequestMethod.GET)
    public JsonData getRedis(@RequestParam("k") String k) {
        return JsonData.success(redisPool.instance().get(k));
        //return JsonData.success();
    }

    @ResponseBody
    @RequestMapping(value = "thread.json")
    public JsonData threadGroup() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();
        ThreadGroup topGroup = group;
        // 遍历线程组树，获取根线程组
        while (group != null) {
            topGroup = group;
            group = group.getParent();
        }
        // 激活的线程数加倍
        int estimatedSize = topGroup.activeCount() * 2;
        Thread[] slackList = new Thread[estimatedSize];
        // 获取根线程组的所有线程
        int actualSize = topGroup.enumerate(slackList);
        // copy into a list that is the exact size
        Thread[] list = new Thread[actualSize];
        System.arraycopy(slackList, 0, list, 0, actualSize);
        log.info("Thread list size == " + list.length);
        for (Thread thread : list) {
            log.info(thread.getName());
        }
        return JsonData.success(list);
    }

    @ResponseBody
    @RequestMapping(value = "switch.json")
    public JsonData datasourceSwitch(@RequestParam("msg") String msg) {
        testDataSourceService.save(msg);
        return JsonData.success();
    }

    @ResponseBody
    @RequestMapping(value = "test.json")
    public JsonData test() {
        testService.start();
        return JsonData.success();
    }
}