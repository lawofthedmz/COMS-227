package hw1;

/**
 * Some simple tests that may help you to get started implementing the
 * Balloon class. Warning: these tests do not cover all of the
 * specifications. Perform your own testing in addition to using the
 * specchecker.
 */
public class SimpleTests {
	public static void main(String args[]) {
		Balloon b = new Balloon(20, 90);
		
//		System.out.println("Test: Murphy");
//		b.setFuelRemaining(5);
//		b.setBalloonMass(1);
//		b.setFuelBurnRate(1);
//		b.setTetherLength(1);
//		
//		System.out.println("Fuel Rem" + b.getFuelRemaining() + "  Mass:" +  b.getBalloonMass() + "  BurnRate:" + b.getFuelBurnRate() + "  Tether Leng:" + b.getTetherLength() + "  Tether Rem:"+ b.getTetherRemaining() + " AirTemp:" + b.getOutsideAirTemp() + "  Balloon Temp:" + b.getBalloonTemp() + "  WindDirec:" + b.getWindDirection() + "  Altit:" + b.getAltitude() +   " Velocity:" + b.getVelocity() + " Secs:" + b.getSeconds() + " Mins:" + b.getMinutes());
//		b.reset();
//		b.setFuelRemaning(2);
//		b.setTetherLength(100);
//		b.setFuelBurnRate(5);
//		b.setBalloonTemp(70);
//		b.setOutsideAirTemp(20);
//		b.setBalloonMass(100);
//		System.out.println(b.getFuelRemaining());
//		System.out.println(b.getTetherLength());
//		System.out.println(b.getFuelBurnRate());
//		System.out.println(b.getBalloonTemp());
//		System.out.println(b.getOutsideAirTemp());
//		System.out.println(b.getBalloonMass());
//		System.out.println(b.getVelocity());
//		System.out.println(b.getAltitude());
//		System.out.println();
//		b.update();
//		System.out.println(b.getFuelRemaining());
//		System.out.println(b.getTetherLength());
//		System.out.println(b.getFuelBurnRate());
//		System.out.println(b.getBalloonTemp());
//		System.out.println(b.getOutsideAirTemp());
//		System.out.println(b.getBalloonMass());
//		System.out.println(b.getVelocity());
//		System.out.println(b.getAltitude());

	
		System.out.println("Test 1:");
		System.out.println("Fuel remaining is " + b.getFuelRemaining() + " expected 0.");
		System.out.println("Adding 1000 to fuel.");
		b.setFuelRemaning(1000);
		System.out.println("Fuel remaining is " + b.getFuelRemaining() + " expected 1000.");
		
		System.out.println("Test 2:");
		System.out.println("Air temp is " + b.getOutsideAirTemp() + " expected 20.");
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 20.");
		System.out.println("Fuel burn rate is " + b.getFuelBurnRate() + " expected 0.");
		System.out.println("Tether length is " + b.getTetherLength() + " expected 0.");
		System.out.println("Balloon mass is " + b.getBalloonMass() + " expected 0.");
		
		b.setOutsideAirTemp(18);
		b.setBalloonTemp(25);
		b.setFuelBurnRate(5);
		b.setTetherLength(100);
		b.setBalloonMass(110);

		System.out.println("Air temp is " + b.getOutsideAirTemp() + " expected 18.");
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 25.");
		System.out.println("Fuel burn rate is " + b.getFuelBurnRate() + " expected 5.");
		System.out.println("Tether length is " + b.getTetherLength() + " expected 100.");
		System.out.println("Balloon mass is " + b.getBalloonMass() + " expected 110.");
		
		System.out.println("Test 3:");
		System.out.println("Minutes is " + b.getMinutes() + " expected 0.");
		System.out.println("Seconds is " + b.getSeconds() + " expected 0.");
		b.update();
		b.update();
		b.update();
		System.out.println("Minutes is " + b.getMinutes() + " expected 0.");
		System.out.println("Seconds is " + b.getSeconds() + " expected 3.");
		
		System.out.println("Test 4:");
		System.out.println("Wind direction is " + b.getWindDirection() + " expected 90.");
		b.changeWindDirection(180);
		System.out.println("Wind direction is " + b.getWindDirection() + " expected 270.");
		b.changeWindDirection(90);
		System.out.println("Wind direction is " + b.getWindDirection() + " expected 0.");
		b.changeWindDirection(-90);
		System.out.println("Wind direction is " + b.getWindDirection() + " expected 270.");
		
		System.out.println("Test 5:");
		System.out.println(b.getBalloonTemp());
		b.reset();
		System.out.println(b.getBalloonTemp());
		System.out.println("Air temp is " + b.getOutsideAirTemp() + " expected 20.");
		System.out.println("Wind direction is " + b.getWindDirection() + " expected 90.");
		System.out.println("Fuel remaining is " + b.getFuelRemaining() + " expected 0.");
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 20.");
		System.out.println("Fuel burn rate is " + b.getFuelBurnRate() + " expected 0.");
		System.out.println("Tether length is " + b.getTetherLength() + " expected 0.");
		System.out.println("Balloon mass is " + b.getBalloonMass() + " expected 0.");
		
		System.out.println("Test 6:");
		b.setBalloonMass(100);
		b.setBalloonTemp(70);
		b.setFuelBurnRate(5);
		b.setFuelRemaning(10);
		b.setTetherLength(100);
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 70.");
		System.out.println("Balloon velocity is " + b.getVelocity() + " expected 0.");
		System.out.println("Altitude is " + b.getAltitude() + " expected 0.");
		b.update();
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 70.");
		System.out.println("Balloon velocity is " + b.getVelocity() + " expected 0.72...");
		System.out.println("Altitude is " + b.getAltitude() + " expected 0.72...");
		b.update();
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 70.");
		System.out.println("Balloon velocity is " + b.getVelocity() + " expected 1.45... DeltaT");
		System.out.println("Altitude is " + b.getAltitude() + " expected 2.18...");
		// note: at time point fuel has run out
		b.update();
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 65.");
		System.out.println("Balloon velocity is " + b.getVelocity() + " expected 1.27...");
		System.out.println("Altitude is " + b.getAltitude() + " expected 3.46...");
		b.update();
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 60.5.");
		System.out.println("Balloon velocity is " + b.getVelocity() + " expected 0.24...");
		System.out.println("Altitude is " + b.getAltitude() + " expected 3.70...");
		b.update();
		System.out.println("Balloon temperature is " + b.getBalloonTemp() + " expected 56.45.");
		System.out.println("Balloon velocity is " + b.getVelocity() + " expected -1.56...");
		System.out.println("Altitude is " + b.getAltitude() + " expected 2.14...");
	}
}
