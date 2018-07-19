package com.jy.controller.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jainglei
 * @create 2018/7/6
 * @since 1.0.0
 */
@FeignClient("ID-GENERATOR-API")
public interface FeiginUrlController {

    @RequestMapping(value = "/")
    String idGenerator();

}