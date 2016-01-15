/*
*Version 0.8.1-beta
*/

import lejos.hardware.Button;
import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.utility.Delay;
public class Q2 {

	static int aPower = 72;
	static int cPower = 70;
	static EncoderMotor motorA = new NXTMotor (MotorPort.A);
	static EncoderMotor motorC = new NXTMotor (MotorPort.C);
	
	public static void main(String[] args) {
		
		motorA.setPower(aPower);
		motorC.setPower(cPower);
		moveSquare();
		moveCircle(true);
		moveEight();
		Button.waitForAnyPress();
	}
	
	public static void moveForward() {
		motorA.forward();
		motorC.forward();
		Delay.msDelay(2000);
		motorA.stop();
		motorC.stop();
	}
	
	// Use left wheel to turn right (MotorA)
	public static void turnRight(float degrees) {
		int tcount = motorA.getTachoCount();
		motorA.forward();
		motorC.backward();
		
		while(motorA.getTachoCount() <= 240 + tcount) {
			continue;
		}
		motorA.stop();
		motorC.stop();
	}
	
	// Use right wheel to turn left (MotorC)
	public static void turnLeft(float degrees) {
		int tcount = motorC.getTachoCount();
		motorC.forward();
		motorA.backward();
		while(motorC.getTachoCount() <= 240 + tcount) {
			continue;
		}
		motorA.stop();
		motorC.stop();
	}
	
	public static void moveSquare() {
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
	public static void moveCircle(Boolean dir) {
		if (dir) {
			motorC.setPower(cPower);
			motorA.setPower(cPower-28);
		} else {
			motorA.setPower(aPower);
			motorC.setPower(aPower-30);
		}
		motorA.forward();
		motorC.forward();
		Delay.msDelay(7500);
		motorA.stop();
		motorC.stop();
	}
	
	public static void moveEight() {
		moveCircle(false);
		moveCircle(true);
	}
	
}
