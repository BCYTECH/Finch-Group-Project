import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class Main 
{
	static Finch myfi = new Finch();
	
	//getting initial sensor
	public static int left_light = myfi.getLeftLightSensor();
	public static int right_light = myfi.getRightLightSensor();

	public static void main(String[] args)
	{		
		start();
		process();
	}

	public static void start()
	{
		System.out.println("Ready for initial light capturing...");
		System.out.println("Reading initial lighting: "+left_light+" "+right_light);
		JOptionPane.showMessageDialog(null,"Click OK to start running");
		
		System.out.println("<<<RUNNING>>>");
		System.out.println("START SHINING TORCH");
	}
	public static void process()
	{
		Movement go = new Movement();

		while(true) 											
		{	
			if(myfi.isObstacle() == true)
			{
				go.Obstacle();
			}
			else 
			{
				//add counter and list to show at the end of the program which way its going
				//countdown timer in new method 

				if (myfi.getLeftLightSensor() >= left_light && myfi.getRightLightSensor() >= right_light)
				{
					go.Forward();
				}

				else if (myfi.getRightLightSensor() >= right_light)
				{
					//if the current right sensor is more than the initial right atmosphere sensor reading, finch turns right
					go.Right();
				}
				else if (myfi.getLeftLightSensor()>= left_light)
				{
					go.Left();
				}
				else if (myfi.getLeftLightSensor()<= left_light && myfi.getRightLightSensor() <= right_light)
				{
					go.NoLight();
				}
			}
		}
	}
}




