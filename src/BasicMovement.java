// Q2k - Basic movement: circles, lines, figure eight

import lejos.hardware.Button;
import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.utility.Delay;
public class BasicMovement {

	int aPower = 72;
	int cPower = 70;
	EncoderMotor leftM;
	EncoderMotor rightM;
	
	public BasicMovement() {}
	
	public void run(EncoderMotor left, EncoderMotor right) {
		leftM = left; rightM = right;
		
		leftM.setPower(aPower);
		rightM.setPower(cPower);
		moveSquare();
		moveCircle(true);
		moveEight();
		Button.waitForAnyPress();
	}
	
	public void moveForward() {
		leftM.forward();
		rightM.forward();
		Delay.msDelay(2000);
		leftM.stop();
		rightM.stop();
	}
	
	// Use left wheel to turn right (MotorA)
	public void turnRight(float degrees) {
		int tcount = leftM.getTachoCount();
		leftM.forward();
		rightM.backward();
		
		while(leftM.getTachoCount() <= 240 + tcount) {
			continue;
		}
		leftM.stop();
		rightM.stop();
	}
	
	// Use right wheel to turn left (MotorC)
	public void turnLeft(float degrees) {
		int tcount = rightM.getTachoCount();
		rightM.forward();
		leftM.backward();
		while(rightM.getTachoCount() <= 240 + tcount) {
			continue;
		}
		leftM.stop();
		rightM.stop();
	}
	
	public void moveSquare() {
		moveForward();
		turnRight(90);
		moveForward();
		turnRight(90);
		moveForward();
		turnRight(90);
		moveForward();
		turnRight(90);
	}
	
	// false for right true for left
	public void moveCircle(Boolean dir) {
		if (dir) {
			rightM.setPower(cPower);
			leftM.setPower(cPower-28);
		} else {
			leftM.setPower(aPower);
			rightM.setPower(aPower-30);
		}
		leftM.forward();
		rightM.forward();
		Delay.msDelay(7500);
		leftM.stop();
		rightM.stop();
	}
	
	public void moveEight() {
		moveCircle(false);
		moveCircle(true);
	}
	
}
