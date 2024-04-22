package com.java.ejb;

import javax.ejb.Remote;

@Remote
public interface ArithmeticBeanRemote {

	int add(int a, int b);

	int sub(int a, int b);

	int mul(int a, int b);

	int div(int a, int b);
}
