package com.java.ejb;

import java.util.Properties;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RemoteEJBClient {


	public static void main(String[] args) {
		try {
			invokeStatelessBean();
			Thread.sleep(10000000);

		} catch (javax.naming.NoInitialContextException e) {
			System.out.println("Error: Failed to initialize JNDI context. Please check configuration.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Context createInitialContext() throws NamingException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		return new InitialContext(jndiProperties);
	}

	private static ArithmeticBeanRemote lookupRemoteStatelessCalculator() throws NamingException {
		Context ctx = createInitialContext();

		final String distinctName = "";

		final String beanName = "ArithmeticBean";
		System.out.println(beanName);

		final String viewClassName = ArithmeticBeanRemote.class.getName();

		String appName = "";
		String moduleName = "ArithmeticServer";
		return (ArithmeticBeanRemote) ctx.lookup(
				"ejb:" + appName + "/" + moduleName + "/" + distinctName + "/" + beanName + "!" + viewClassName);
	}

	private static void invokeStatelessBean() throws NamingException {
		// Let's lookup the remote stateless calculator
		final ArithmeticBeanRemote statelessRemoteCalculator = lookupRemoteStatelessCalculator();
		System.out.println("Obtained a remote stateless calculator for invocation");
		
		Scanner sc=new Scanner(System.in);
		// invoke on the remote calculator
		System.out.println("Enter a: ");
		int a = sc.nextInt();
		System.out.println("Enter b: ");
		int b = sc.nextInt();
		System.out.println("Adding " + a + " and " + b + " via the remote stateless calculator deployed on the server");
		int sum = statelessRemoteCalculator.add(a, b);
		System.out.println("Remote calculator returned sum = " + sum);
		sc.close();
		if (sum != a + b) {
			throw new RuntimeException(
					"Remote stateless calculator returned an incorrect sum " + sum + " ,expected sum was " + (a + b));
		}
	}
}