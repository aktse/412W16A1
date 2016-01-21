// Q3 - Dead reckoning position controller

import lejos.hardware.Button;
import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.EncoderMotor;
import lejos.utility.Delay;

public class DeadReckoning {

	int[][] commands = {
		      { 80, 60, 2},
		      { 60, 60, 1},
		      {-50, 80, 2}
		    };
	
	EncoderMotor leftM;
	EncoderMotor rightM;
	
	// Dimensions in cm
	double wheelDiameter = 5.6;
	double trackWidth = 11.9;
	double distPerTick = (wheelDiameter * Math.PI) / 360.0;
	double tickPerRot = (Math.PI * trackWidth) / distPerTick;
	double radPerTick = (2 * Math.PI) / tickPerRot;
	
	// Tick info
	int leftTicks = 0;
	int rightTicks = 0;
	int leftDeltaTick;
	int rightDeltaTick;
	
	double x = 0.0, y = 0.0, heading = 1.5708;
	
	public DeadReckoning() {}
	
	public void run(EncoderMotor left, EncoderMotor right) {
		leftM = left; rightM = right;
		
		execute(commands[0]);
		execute(commands[1]);
		execute(commands[2]);
		printLocation();
		Button.waitForAnyPress();
	}
	
	
	// X(K+1) = X(K) + delta(X)
	// Y(K+1) = Y(K) + delta(y)
	// Heading(K+1) = Heading(K) + delta(heading)
	
	public void execute(int[] command) {
		leftM.setPower(command[0]);
		rightM.setPower(command[1]);
		leftM.forward();
		rightM.forward();
		float curTime = 0;
		float goalTime = command[2] * 1000;
		while (curTime < goalTime) {
			estimate();
			curTime += 20;
			Delay.msDelay(20);
		}
		leftM.stop();
		rightM.stop();
	}
	
	public void estimate() {
		leftDeltaTick = leftM.getTachoCount() - leftTicks;
		rightDeltaTick = rightM.getTachoCount() - rightTicks;
		leftTicks = leftM.getTachoCount();
		rightTicks = rightM.getTachoCount();
		
		double deltaDist = (leftDeltaTick + rightDeltaTick)/2 * distPerTick;
		double deltaHead = (rightDeltaTick - leftDeltaTick) * radPerTick/2;
		
		heading += deltaHead;
		x += deltaDist * Math.cos(heading);
		y += deltaDist * Math.sin(heading);
		
	}
	
	public void printLocation() {
		System.out.format("x: %.2f\ny: %.2f\nheading: %.2f", x, y, heading);
	}
	
}
