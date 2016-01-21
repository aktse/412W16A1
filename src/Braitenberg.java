import lejos.hardware.Button;
import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.EncoderMotor;

// Q5 - Braitenberg vehicle behavior
public class Braitenberg {

	NXTLightSensor leftL = new NXTLightSensor(SensorPort.S1);
	NXTLightSensor rightL = new NXTLightSensor(SensorPort.S4);
	EncoderMotor leftM = new NXTMotor(MotorPort.A);
	EncoderMotor rightM = new NXTMotor(MotorPort.C);
	
	public enum Profile {
		COWER, AGGRESSIVE, LOVE, EXPLORE
	}
	
	Profile currentProfile = Profile.COWER;
	
	public Braitenberg() {}
	
	public void run() {
		cower();
		Button.waitForAnyPress();
	}
	
	public void cower() {
		float[] leftIntensity = new float[1];
		float[] rightIntensity = new float[1];
		while (!Button.DOWN.isDown()) {
			leftL.getAmbientMode().fetchSample(leftIntensity, 0);
			rightL.getAmbientMode().fetchSample(rightIntensity, 0);
			leftM.setPower((int) (leftIntensity[0] * 50));
			rightM.setPower((int) (rightIntensity[0] * 50));
			leftM.forward();
			rightM.forward();
		}
		
		
		leftM.stop();
		rightM.stop();
	}
	
	public void aggressive()  {
		
	}
	
	public void love() {
		
	}
	
	public void explore() {
		
	}
	
}
