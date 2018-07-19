package com.jy.controller.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈〉
 *
 * @author jianglei
 * @create 2018/6/29
 * @since 1.0.0
 */
@RestController
public class JobTest {

    private Logger logger = LoggerFactory.getLogger(JobTest.class);

    @Value("${job-server}")
    private String jobUrl;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/jobTest",method = RequestMethod.GET)
    public String simpleJob(){
        logger.info("{} job start",new Exception().getStackTrace()[0].getMethodName());
        logger.info("job start time :{}" ,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return "ok";
    }

    @RequestMapping(value = "/job/add",method = RequestMethod.GET)
    public String addJob(){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("jobName","http://DEMO-CLOUD/jobTest");
        paramMap.put("cronExpression","0/1 * * * * ? *");
        paramMap.put("jobGroup","msTask");
        paramMap.put("type","sync");
        restTemplate.postForEntity(jobUrl+"/qy/api/task/save",paramMap,String.class);
        return "ok";
    }

}