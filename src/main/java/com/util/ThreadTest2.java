package com.util;

public class ThreadTest2 implements Runnable {

	private String name;

	public ThreadTest2(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println(this.name + "----" + i);
			try {
				Thread.sleep(3*1000);//线程睡眠
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}


	public static void main(String[] args) {
		Thread t = new Thread(new ThreadTest2("线程1"));
		Thread t1 = new Thread(new ThreadTest2("线程2"));
		
		t.start();
		t1.start();

	}

	
}
