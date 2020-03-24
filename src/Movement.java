
public class Movement extends Main{

	public ExtraFeatures features = new ExtraFeatures();

	public void Forward()
	{
		//if both left and right current light sensor reading is more than atmosphere on both left and right sensor, finch moves forward
		System.out.println("Moving Forward");
		myfi.setWheelVelocities(myfi.getRightLightSensor()-20,myfi.getLeftLightSensor()-20);

		//calling sound method
		features.Sound();

		//calling led method
		features.BlueLED();
	}

	public void Left()
	{
		//if the current left sensor is more than the initial left atmosphere sensor reading, finch turns left
		System.out.println("Turning Left");
		myfi.setWheelVelocities(myfi.getRightLightSensor()-50,myfi.getLeftLightSensor()-10);

		//calling sound method
		features.Sound();

		//calling led method
		features.BlueLED();
	}

	public void Right()
	{
		//if the current right sensor is more than the initial right atmosphere sensor reading, finch turns right
		System.out.println("Turning Right");
		myfi.setWheelVelocities(myfi.getRightLightSensor()-10,myfi.getLeftLightSensor()-50);

		//calling sound method
		features.Sound();

		//calling led method
		features.BlueLED();
	}

	public void Obstacle()
	{
		//checks if there is an obstacle infront
		System.out.println("\n<OBSTACLE DETECTED>");	
		myfi.stopWheels();

		//calling led method
		features.RedLED();

		//calling timer method
		features.CountdownObstacle();
	}

	public void NoLight()
	{
		//otherwise, if there both current sensor is less than the initial atmosphere sensor reading, finch will stop moving
		System.out.println("\n<NO LIGHT DETECTED>");
		myfi.stopWheels();

		//calling led method
		features.YellowLED();

		//calling timer method
		features.CountdownLight();
	}
}
