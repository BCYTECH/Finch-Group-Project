import java.io.*;

public class ExtraFeatures extends Main

{
	Main home = new Main(); //initializing object name

	public void Sound()
	{
		myfi.buzz(7500, 255);
		myfi.sleep(400);
		myfi.buzz(10000, 255);
		myfi.sleep(300);
	}

	public void BlueLED()
	{
		myfi.setLED(0,0,255,50); //led turns blue for 50ms
	}

	public void RedLED()
	{
		myfi.setLED(255,0,0);
	}

	public void CountdownLight() throws IOException
	{
		long beforetime = System.currentTimeMillis(); 											//begins start point for 5 second timer
		System.out.println("Finch will exit if there is no light for 5 seconds\n");
		while(System.currentTimeMillis() - beforetime <= 5000)									//after 5 seconds...the loop will finish, and then proceed to close the application
		{		
			//detect if light came back
			if (( (myfi.getLeftLightSensor() >= (left_light + 5)) | (myfi.getRightLightSensor() >= (right_light + 5 )) )) //added 5 to ambient as light must be a true source, not fluctuations in ambient
			{	
				home.process();
			}
		}
		Main.countandexit();
	}

	public void CountdownObstacle() throws IOException
	{
		long beforetime = System.currentTimeMillis(); 											//begins start point for 5 second timer
		System.out.println("Finch will exit if there is an obstacle for more than 5 seconds\n");
		while(System.currentTimeMillis() - beforetime <= 5000)									//after 5 seconds...the loop will finish, and then proceed to close the application
		{		
			//detect if light came back
			if (( (myfi.getLeftLightSensor() >= (left_light + 5)) | (myfi.getRightLightSensor() >= (right_light + 5 )) )) //added 5 to ambient as light must be a true source, not fluctuations in ambient
			{	
				home.process();
			}
		}
		Main.countandexit();
	}
	
	public void StoreinFile() throws IOException
	{
		FileWriter writehandle = new FileWriter("c:\\Users\\User\\Desktop\\recent.txt");
        BufferedWriter bw = new BufferedWriter(writehandle);
        
        for(int i = 1; i < sequence.size();i++)
    	{
			if (i == 1)
			{
				bw.write("Sequence: "+sequence.get(0));
			}
			else 
			{
				bw.write(","+ sequence.get(i));
			}	
    	}
        
        //bw.write(countforward+"\n"+countleft+"\n"+countright+"\n"+countstop);
        
        bw.write("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
		bw.write("\n\n<<< SUMMARY >>>\n");
		bw.write("Total Forward Move: "+countforward+"\n");
		bw.write("Total Left Turn: "+countleft+"\n");
		bw.write("Total Right Turn: "+countright+"\n");
		bw.write("Total Stops: "+countstop+"\n");
        bw.write("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        
        bw.close();
        writehandle.close();
	}
}
