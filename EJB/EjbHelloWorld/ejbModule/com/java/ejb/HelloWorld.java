package com.java.ejb;

import java.util.Date;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloWorld
 */
@Stateless
@Remote(HelloWorldRemote.class)
public class HelloWorld implements HelloWorldRemote {

    /**
     * Default constructor. 
     */
    public HelloWorld() {
    }

	@Override
	public String sayHello() {
		return "Welcome to Infinite..EJB 3 going on...";
	}

	@Override
	public String greeting(String name) {
		Date obj=new Date();
		int hr=obj.getHours();
		if (hr<12) {
			return "Gooooooood Morning" +name;
		}
		return "Good Evening..."+name;
	}

}
