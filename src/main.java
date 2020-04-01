import java.util.ArrayList;
import javax.swing.JOptionPane;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.util.Scanner;
import java.io.*;
import java.time.*;

public class Main 
{
	static Finch myfi = new Finch();

	//getting initial sensors
	public static int left_light = myfi.getLeftLightSensor()+25; 	//increase threshold because current light value is different from initial value
	public static int right_light = myfi.getRightLightSensor()+25;

	//initializing counters
	static int countforward = 0;
	static int countleft = 0;
	static int countright = 0;
	static int countstop = 0;

	static ArrayList<String> sequence = new ArrayList<String>();

	//getting current time and date
	static LocalDate today = LocalDate.now();
	static LocalTime time = LocalTime.now();

	public static void main(String[] args) throws IOException //sequence that it will run
	{		
		start();
		process();
		countandexit();
	}

	public static void start()
	{		
		System.out.println("\n++++++++++++++++++ Finch Light - "+today+" at "+time+" ++++++++++++++++++\n");

		System.out.println("Ready for initial light capturing...");
		System.out.println("Reading initial lighting: "+left_light+" "+right_light);
		JOptionPane.showMessageDialog(null,"Click OK to start running");

		System.out.println("\n<<< RUNNING >>>");
		System.out.println("START SHINING TORCH\n");
	}
	
	public static void process() throws IOException
	{
		//initializing object and obstacle array
		Movement go = new Movement();

		if(myfi.isObstacleLeftSide()==true || myfi.isObstacleRightSide()==true) //check if there is an obstacle (0 is left, 1 is right)
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
					//if both current left and right light sensor reading is more than atmosphere on both left and right sensor, finch moves forward
					countforward++;
					go.Forward();
					sequence.add("Forward");
				}
				else if (myfi.getRightLightSensor() > right_light)
				{
					//if the current right sensor is more than the initial right atmosphere sensor reading, finch turns right
					countright++;
					go.Right();
					sequence.add("Right");
				}
				else if (myfi.getLeftLightSensor()> left_light)
				{
					//if the current left sensor is more than the initial left atmosphere sensor reading, finch turns left
					countleft++;
					go.Left();
					sequence.add("Left");
				}
				else if (myfi.getLeftLightSensor() < left_light && myfi.getRightLightSensor() < right_light)
				{
					// if the current left and right light value is lesser than the initial reading value, finch stops moving
					countstop++;
					go.NoLight();
					sequence.add("Stop");
				}
			}
		}
	}
	
	public static void countandexit() throws IOException
	{
		System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");

		System.out.println("<<< SUMMARY >>>");
		System.out.println("Total Forward Move: "+countforward);
		System.out.println("Total Left Turn: "+countleft);
		System.out.println("Total Right Turn: "+countright);
		System.out.println("Total Stops: "+countstop);

		System.out.println("\n++++++++++++++++++ End Session - "+today+" at "+time+" ++++++++++++++++++\n");
        
		ExtraFeatures xtra = new ExtraFeatures();
		xtra.StoreinFile();
		
		System.exit(0);
	}
}




