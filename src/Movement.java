import java.io.IOException;

public class Movement extends Main{

	public ExtraFeatures features = new ExtraFeatures(); //initializing object name
	
	public void Forward()
	{
		int left = myfi.getRightLightSensor()-20;
		int right = myfi.getLeftLightSensor()-20;
		
		//if both left and right current light sensor reading is more than atmosphere on both left and right sensor, finch moves forward
		System.out.println("Moving Forward");
		myfi.setWheelVelocities(left,right);
		System.out.println("Speed: (L) "+left+" (R) "+right+"\n");
		//ExtraFeatures.bw.write("Speed: (L) "+left+" (R) "+right+"\n");

		//calling sound method
		features.Sound();

		//calling led method
		features.BlueLED();
	}

	public void Left()
	{
		int left = myfi.getRightLightSensor()-30;
		int right = myfi.getLeftLightSensor()-5;
		
		//if the current left sensor is more than the initial left atmosphere sensor reading, finch turns left
		System.out.println("Turning Left");
		myfi.setWheelVelocities(left,right);
		System.out.println("Speed: (L) "+left+" (R) "+right+"\n");
		
		//calling sound method
		features.Sound();

		//calling led method
		features.BlueLED();
	}

	public void Right()
	{
		int left = myfi.getRightLightSensor()-5;
		int right = myfi.getLeftLightSensor()-30;
		
		//if the current right sensor is more than the initial right atmosphere sensor reading, finch turns right
		System.out.println("Turning Right");
		myfi.setWheelVelocities(left,right);
		System.out.println("Speed: (L) "+left+" (R) "+right+"\n");
		

		//calling sound method
		features.Sound();

		//calling led method
		features.BlueLED();
	}

	public void Obstacle() throws IOException
	{
		//checks if there is an obstacle infront
		System.out.println("\n<OBSTACLE DETECTED>");	
		myfi.stopWheels();

		//calling led method
		features.RedLED();

		//calling timer method
		features.CountdownObstacle();
	}

	public void NoLight() throws IOException
	{
		//otherwise, if there both current sensor is less than the initial atmosphere sensor reading, finch will stop moving
		System.out.println("\n<NO LIGHT DETECTED>");
		myfi.stopWheels();

		//calling led method
		features.RedLED();

		//calling timer method
		features.CountdownLight();
	}
}
