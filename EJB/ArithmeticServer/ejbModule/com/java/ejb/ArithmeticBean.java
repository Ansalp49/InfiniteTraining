package com.java.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(ArithmeticBeanRemote.class)
public class ArithmeticBean implements ArithmeticBeanRemote {

	public ArithmeticBean() {
	}

	@Override
	public int add(int a, int b) {
		return a + b;
	}

	@Override
	public int sub(int a, int b) {
		return a - b;
	}

	@Override
	public int mul(int a, int b) {
		return a * b;
	}

	@Override
	public int div(int a, int b) {
		return a / b;
	}

}
