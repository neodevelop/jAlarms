package com.solab.alarms.aop;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import javax.annotation.*;
import com.solab.alarms.UnitTestChannel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-aop.xml")
public class TestAlarmAspect {

	@Resource
	private TestBean bean;
	@Resource
	private UnitTestChannel chan;

	@Test(expected=NullPointerException.class)
	public void testException1() {
		chan.setDelegate(new UnitTestChannel.ChanDelegate(){
			public void alarmReceived(String msg, long when) {
				int idx = msg.indexOf("MSG from ASPECT");
				assert idx >= 0 && msg.indexOf("NullPointerException") > idx;
			}
		});
		chan.prepare();
		bean.doSomethingStupid();
		chan.waitForSend();
	}

	@Test(expected=IllegalMonitorStateException.class)
	public void testException2() {
		chan.setDelegate(new UnitTestChannel.ChanDelegate(){
			public void alarmReceived(String msg, long when) {
				int idx = msg.indexOf("Multithreading is hard!");
				assert idx >= 0 && msg.indexOf("IllegalMonitorStateException") > idx;
			}
		});
		chan.prepare();
		bean.doSomethingStupider();
		chan.waitForSend();
	}

	@After
	public void cleanup() {
		chan.setDelegate(null);
	}

}
