package com.ajou.team3.junit2.restaurantKiosk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Junit2ApplicationTests {

	@Test
	public void contextLoads() {
		assertTrue(1==1);
	}
  

}
