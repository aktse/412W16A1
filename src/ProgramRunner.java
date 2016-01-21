import lejos.hardware.motor.NXTMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTLightSensor;
import lejos.robotics.EncoderMotor;

public class ProgramRunner {
	public static BasicMovement bm = new BasicMovement();
	public static DeadReckoning dr = new DeadReckoning();
	public static Braitenberg bv = new Braitenberg();
	public static Brick_TO bto = new Brick_TO();
	
	static NXTLightSensor leftL = new NXTLightSensor(SensorPort.S1);
	static NXTLightSensor rightL = new NXTLightSensor(SensorPort.S4);
	static EncoderMotor leftM = new NXTMotor(MotorPort.A);
	static EncoderMotor rightM = new NXTMotor(MotorPort.C);
	
	public static void main(String[] args) {
		bm.run(leftM, rightM);
//		dr.run(leftM, rightM);
//		bv.run(leftL, rightL, leftM, rightM);
//		bto.run(leftM, rightM);
	}
}
