
public class ExtraFeatures extends Main{

	public void Sound()
	{
		myfi.buzz(7500, 255);
		myfi.sleep(400);
		myfi.buzz(10000, 255);
		myfi.sleep(300);
	}
	
	public void BlueLED()
	{
		myfi.setLED(0,0,255,50);
	}
	
	public void RedLED()
	{
		myfi.setLED(255,0,0);
	}
	
	public void YellowLED()
	{
		myfi.setLED(255,255,0);	
	}
	
	public void Countdown()
	{
		Main home = new Main();
		
		long beforetime = System.currentTimeMillis(); 											//begins start point for 5 second timer
		System.out.println("Finch will exit if there is no light for 5 seconds");
		while(System.currentTimeMillis() - beforetime <= 5000)									//after 5 seconds...the loop will finish, and then proceed to close the application
		{		
			//detect if light came back
			if (( (myfi.getLeftLightSensor() >= (left_light + 5)) | (myfi.getRightLightSensor() >= (right_light + 5 )) )) //added 5 to ambient as light must be a true source, not fluctuations in ambient
			{	
				home.process();
			}
		}
	}
}
