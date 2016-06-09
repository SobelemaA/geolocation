package org.alancesar.geolocation.test;

import org.alancesar.geolocation.address.Address;
import org.junit.Test;

public class DistanceTests {

	@Test
	public void distanceTo() {
		Address origin = Address.get("São Paulo SP Brasil");
		Address dest = Address.get("Campinas SP Brasil");
		
		System.out.println(origin.distanceTo(dest));
	}
}
