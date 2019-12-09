package com.anyuhang.test;


import java.util.HashMap;

import org.apache.commons.logging.impl.Jdk14Logger;
import org.junit.Test;
import org.junit.internal.builders.JUnit4Builder;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.anyuhang.StringUtils;
import com.anyuhang.domain.User;
import com.anyuhang.domain.UserUtils;

@RunWith(JUnit4.class)
@ContextConfiguration("classpath:redis.xml")
public class RedisTest {

	@Autowired
	RedisTemplate<String,User> redisTemplate;
	
	@Test
	//jdk方式
	public void getJdk() {
		
		User user=new User();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			user.setGender(UserUtils.getSex());
			user.setPhone("13"+StringUtils.getRandomNumber(9));
			user.setEmil(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			redisTemplate.opsForValue().set("user",user);
		}
		long end = System.currentTimeMillis();
		
		System.out.println("jdk方式");
		System.out.println("使用时间:"+(end - start) );
		System.out.println("保存5w条数据");
		
	}
	//json方式
public void getJson() {
		
		User user=new User();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			user.setId(i);
			user.setName(StringUtils.getRandomCn(3));
			user.setGender(UserUtils.getSex());
			user.setPhone("13"+StringUtils.getRandomNumber(9));
			user.setEmil(UserUtils.getMail());
			user.setBirthday(UserUtils.getBirthday());
			redisTemplate.opsForValue().set("user",user);
		}
		long end = System.currentTimeMillis();
		
		System.out.println("json方式");
		System.out.println("使用时间:"+(end - start) );
		System.out.println("保存5w条数据");
		
	}
//hash方式
public void gethash() {
	
	HashMap<Object,Object> map=new HashMap<>();
	User user=new User();
	long start = System.currentTimeMillis();
	for (int i = 0; i < 50000; i++) {
		user.setId(i);
		user.setName(StringUtils.getRandomCn(3));
		user.setGender(UserUtils.getSex());
		user.setPhone("13"+StringUtils.getRandomNumber(9));
		user.setEmil(UserUtils.getMail());
		user.setBirthday(UserUtils.getBirthday());
	    
		redisTemplate.opsForHash().putAll("users",map);
		
	}
	long end = System.currentTimeMillis();
	
	System.out.println("jdk方式");
	System.out.println("使用时间:"+(end - start) );
	System.out.println("保存5w条数据");
	
}
}
