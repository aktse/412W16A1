import lejos.hardware.Button;
import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.EncoderMotor;

// Q5 - Braitenberg vehicle behavior
public class Braitenberg {

	NXTLightSensor leftL;
	NXTLightSensor rightL;
	EncoderMotor leftM;
	EncoderMotor rightM;
	int high = 50, low = 25; 
	double forwardThreshold = 0.0025;
	double loveThreshold = 0.8;
	double exploreThreshold = 0.175;
	
	public enum Profile {
		COWER, AGGRESSIVE, LOVE, EXPLORE
	}
	
	Profile currentProfile = Profile.COWER;
	
	public Braitenberg() {}
	
	public void run(
			NXTLightSensor ll, 
			NXTLightSensor rl, 
			EncoderMotor lm,
			EncoderMotor rm
			) {
		leftL = ll; rightL = rl; leftM = lm; rightM = rm;
		
		explore();
	}
	
	public void cower() {
		float[] leftIntensity = new float[1];
		float[] rightIntensity = new float[1];
		while (!Button.DOWN.isDown()) {
			leftL.getAmbientMode().fetchSample(leftIntensity, 0);
			rightL.getAmbientMode().fetchSample(rightIntensity, 0);
			if (leftIntensity[0] > rightIntensity[0]) {
				leftM.setPower(high);
				rightM.setPower(low);
			} else if ((rightIntensity[0] - leftIntensity[0]) < forwardThreshold) {
				leftM.setPower(low);
				rightM.setPower(low);
			} else {
				leftM.setPower(low);
				rightM.setPower(high);
			}

			leftM.forward();
			rightM.forward();
		}
		
		leftM.stop();
		rightM.stop();
	}
	
	public void aggressive()  {
		float[] leftIntensity = new float[1];
		float[] rightIntensity = new float[1];
		while (!Button.DOWN.isDown()) {
			leftL.getAmbientMode().fetchSample(leftIntensity, 0);
			rightL.getAmbientMode().fetchSample(rightIntensity, 0);
			if (leftIntensity[0] > rightIntensity[0]) {
				leftM.setPower(low);
				rightM.setPower(high);
			} else if ((rightIntensity[0] - leftIntensity[0]) < forwardThreshold) {
				leftM.setPower(low);
				rightM.setPower(low);
			} else {
				leftM.setPower(high);
				rightM.setPower(low);
			}

			leftM.forward();
			rightM.forward();
		}
		
		leftM.stop();
		rightM.stop();
	}
	
	public void love() {
		float[] leftIntensity = new float[1];
		float[] rightIntensity = new float[1];
		while (!Button.DOWN.isDown()) {
			leftL.getAmbientMode().fetchSample(leftIntensity, 0);
			rightL.getAmbientMode().fetchSample(rightIntensity, 0);
			if (leftIntensity[0] > loveThreshold && rightIntensity[0] > loveThreshold) {
				leftM.setPower(0);
				rightM.setPower(0);
			} else if (leftIntensity[0] > rightIntensity[0]) {
				leftM.setPower(low);
				rightM.setPower(high);
			} else if ((rightIntensity[0] - leftIntensity[0]) < forwardThreshold) {
				leftM.setPower(low);
				rightM.setPower(low);
			} else {
				leftM.setPower(high);
				rightM.setPower(low);
			}

			leftM.forward();
			rightM.forward();
		}
		
		leftM.stop();
		rightM.stop();
	}
	
	public void explore() {
		float[] leftIntensity = new float[1];
		float[] rightIntensity = new float[1];
		while (!Button.DOWN.isDown()) {
			leftL.getAmbientMode().fetchSample(leftIntensity, 0);
			rightL.getAmbientMode().fetchSample(rightIntensity, 0);
			if (leftIntensity[0] < exploreThreshold && rightIntensity[0] < exploreThreshold) {
				leftM.setPower(0);
				rightM.setPower(0);
			} else if (leftIntensity[0] > rightIntensity[0]) {
				leftM.setPower(high);
				rightM.setPower(low);
			} else if ((rightIntensity[0] - leftIntensity[0]) < forwardThreshold) {
				leftM.setPower(low);
				rightM.setPower(low);
			} else {
				leftM.setPower(low);
				rightM.setPower(high);
			}

			leftM.forward();
			rightM.forward();
		}
		
		leftM.stop();
		rightM.stop();
	}
	
}
