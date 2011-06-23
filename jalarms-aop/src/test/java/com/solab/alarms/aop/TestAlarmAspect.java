package com.solab.alarms.aop;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import javax.annotation.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-aop.xml")
public class TestAlarmAspect {

	@Resource
	private TestBean bean;

	@Test(expected=NullPointerException.class)
	public void testException1() {
		bean.doSomethingStupid();
	}

	@Test(expected=IllegalMonitorStateException.class)
	public void testException2() {
		bean.doSomethingStupider();
	}

}
