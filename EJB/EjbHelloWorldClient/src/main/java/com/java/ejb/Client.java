package com.java.ejb;

import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client {

	public static void main(String[] args) throws InterruptedException {

		try {
			HelloWorldRemote service = (HelloWorldRemote) new InitialContext().lookup("HelloWorld/remote");
			System.out.println(service.sayHello());
			System.out.println(service.greeting(" Ansal"));
			Thread.sleep(10000000);

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
}
