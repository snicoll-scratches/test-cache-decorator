package com.example.cache;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private AcmeCacheManager cacheManager;

	@Test
	public void contextLoads() {
		assertThat(cacheManager.getCacheNames()).containsExactly("one", "two");
		Cache one = cacheManager.getCache("one");
		one.put("key", "test");
		cacheManager.disableCache("one");
		assertThat(cacheManager.getCache("one").get("key")).isNull();
		cacheManager.enableCache("one");
		assertThat(cacheManager.getCache("one").get("key")).isNotNull();
	}

}
