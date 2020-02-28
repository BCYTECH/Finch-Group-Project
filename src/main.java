import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class main 
{
	static Finch myfi = new Finch();

	public static void main(String[] args)
	{
		int atmosphere = (myfi.getLeftLightSensor()+myfi.getRightLightSensor()/2);			//average atmosphere light reading
		System.out.println("Ready for room light capturing...");
		JOptionPane.showMessageDialog(null,"Click OK to start running");

		int left_light = myfi.getLeftLightSensor();
		int right_light = myfi.getRightLightSensor();

		//START
		System.out.println("Reading ambient lighting: "+left_light+" "+right_light);
		System.out.println("<<<RUNNING>>>");
	
		// 		add another method here

		{
			while(true) 											
			{	
				if(myfi.isObstacle() == true)
				{
					System.out.println("Obstacle detected!");
					myfi.setLED(255,0,0);																	//Red LED for stop
					myfi.stopWheels();
				}
				else 
				{
					if (myfi.getLeftLightSensor() + myfi.getRightLightSensor() > atmosphere)
					{
						System.out.println("Moving Forward");
						myfi.setLED(0,0,255,10);
						myfi.setWheelVelocities(150,150);
						//create new variable & if function for light sensor to keep it max 255
						//if light sensor detected reach 200, set light sensor value to 255
						//light = speed
						//room initial
						//torch initial
					}
					else if (myfi.getRightLightSensor() >= right_light)
					{
						System.out.println("Turning Right");
						myfi.setLED(0,0,255,10);
						myfi.setWheelVelocities(150,100);

					}
					else if (myfi.getLeftLightSensor()>= left_light)
					{
						System.out.println("Turning Left");
						myfi.setLED(0,0,255,10);
						myfi.setWheelVelocities(100,150);

					}
					else if (myfi.getLeftLightSensor()<= left_light && myfi.getRightLightSensor() <= right_light)
					{
						System.out.println("<NO LIGHT DETECTED!>");
						myfi.setLED(255,0,0);	
						myfi.stopWheels();

						long beforetime = System.currentTimeMillis(); 											//begins start point for 5 second timer
						System.out.println("Finch will exit if there is no light for 5 seconds");
						while(System.currentTimeMillis() - beforetime <= 5000)									//after 5 seconds...the loop will finish, and then proceed to close the application
						{		
							if (( (myfi.getLeftLightSensor() > (left_light + 5)) | (myfi.getRightLightSensor() > (right_light + 5 )) )) //added 5 to ambient as light must be a true source, not fluctuations in ambient
							{	
								main(args);
								
							}
						}
						System.out.println("<<<TERMINATED!>>>");
						System.exit(0);
					}
					else 
					{
						myfi.stopWheels(); 
						System.out.println("<<<TERMINATED!>>>");
						System.exit(0);
					}
				}
			}
		}
	}
}
//implement scanner to start the finch
//torch over the finch press 1
//store torch data in a variable
//if initial atmosphere light < torch, do nothing
//if initial = or > torch, move forward
//keep in while loop "while (true)"
//check if any left or right light is > torch
//if condition is false, stop moving


