package com.java.ejb;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(RestaurantBeanRemote.class)
public class RestaurantBean implements RestaurantBeanRemote {


	public RestaurantBean() {
	}

}
