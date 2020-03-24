import java.util.ArrayList;
import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Scanner;
import java.time.*;

public class Main 
{
	static Finch myfi = new Finch();

	//getting initial sensor
	public static int left_light = myfi.getLeftLightSensor()+20; 	//increase threshold because current light value is different from initial value
	public static int right_light = myfi.getRightLightSensor()+20;

	//initializing counters
	static int countforward = 0;
	static int countleft = 0;
	static int countright = 0;
	static int countstop = 0;

//	static ArrayList<String> sequence = new ArrayList<String>();

	static LocalDate today = LocalDate.now();
	static LocalTime time = LocalTime.now();

	public static void main(String[] args)
	{		
		start();
		process();
		countandexit();
	}

	public static void start()
	{		
		System.out.println("\n++++++++++++++++++Finch Light - "+today+" at "+time+"++++++++++++++++++\n");

		System.out.println("Ready for initial light capturing...");
		System.out.println("Reading initial lighting: "+left_light+" "+right_light);
		JOptionPane.showMessageDialog(null,"Click OK to start running");

		System.out.println("\n<<< RUNNING >>>");
		System.out.println("START SHINING TORCH\n");
	}
	
	public static void process()
	{
		Movement go = new Movement();

		if(myfi.isObstacle() == true)
		{
			countstop++;
			go.Obstacle();
		}
		else
		{
			while(true) 	
			{
				if (myfi.getLeftLightSensor() > left_light && myfi.getRightLightSensor() > right_light)
				{
					go.Forward();
					countforward++;
					//sequence.add("Forward");
				}
				else if (myfi.getRightLightSensor() > right_light)
				{
					//if the current right sensor is more than the initial right atmosphere sensor reading, finch turns right
					go.Right();
					countright++;
					//sequence.add("Right");
				}
				else if (myfi.getLeftLightSensor()> left_light)
				{
					go.Left();
					countleft++;
					//sequence.add("Left");
				}
				else if (myfi.getLeftLightSensor() < left_light && myfi.getRightLightSensor() < right_light)
				{
					go.NoLight();
					countstop++;
					//sequence.add("Stop");
				}
			}
		}
	}
	
	public static void countandexit()
	{
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
//		for(int i = 1; i < sequence.size();i++)
//		{
//			if (i == 1)
//			{
//				System.out.print("Sequence: "+sequence.get(0));
//			}
//			else 
//			{
//				System.out.print(","+ sequence.get(i));
//			}	
//		}
		System.out.println("<<< SUMMARY >>>");
		System.out.println("Total Forward Move: "+countforward);
		System.out.println("Total Left Turn: "+countleft);
		System.out.println("Total Right Turn: "+countright);
		System.out.println("Total Stops: "+countstop);

		System.out.println("\n++++++++++++++++++End Session - "+today+" at "+time+"++++++++++++++++++\n");

		System.exit(0);
	}
}




