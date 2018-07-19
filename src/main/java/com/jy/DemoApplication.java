package com.jy;

import com.jy.annotation.Demo.AutowireDemo;
import com.jy.annotation.Demo.AutowireDemo2;
import com.jy.utils.ApplicationContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


/**
 * rabbitMq springboot
 * RabbitAutoConfiguration 自动配置
 * 连接工厂CachingConnectionFactory
 * RabbitProperties封装了springmq的所有配置 spring.rabbitmq
 * RabbitTemplate 用于给rabbitmq发送接收消息
 * AmqpAdmin RabbitMQ系统管理工具 用于创建对列
 */
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class//禁用springboot自动配置单数据源
})
@MapperScan("com.jy.mapper")
@EnableTransactionManagement
@ComponentScan({"com.jy.controller","com.jy.service","com.jy.config",/*"com.jy.security",*/
				"com.jy.annotation","com.jy.scheduled","com.jy.websocket"})
@PropertySource("classpath:/test.properties")
@Import({ApplicationContextUtil.class})
@EnableScheduling
@EnableAsync
@EnableEurekaClient
@EnableFeignClients
public class DemoApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean("au2")
	public AutowireDemo2 getAu(@Autowired  AutowireDemo autowireDemo){
		AutowireDemo2 autowireDemo2 = new AutowireDemo2();
		autowireDemo2.setAutowireDemo(autowireDemo);
		return autowireDemo2;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
