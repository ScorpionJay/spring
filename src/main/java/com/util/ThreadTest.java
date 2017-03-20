package com.util;

public class ThreadTest extends Thread {

	private String name;
	
	
	
	public ThreadTest(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(this.name +  "-----" + i);
		}
		
	}

	public static void main(String[] args) {
		Thread t = new ThreadTest("线程1");
		Thread t1 = new ThreadTest("线程2");
		t.start();
		t1.start();

	}

}
