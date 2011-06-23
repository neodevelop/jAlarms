package com.solab.alarms.aop;

/** This is just a bean to test the annotation.
 *
 * @author Enrique Zamudio
 */
public class TestBean {

	private Object nothing;

	@AlarmOnException
	public void doSomethingStupid() {
		nothing.toString();
	}

	@AlarmOnException(message="Multithreading is hard!", stack=5)
	public void doSomethingStupider() {
		notify();
	}

}

