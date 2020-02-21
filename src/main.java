import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class main 
{
	static Finch myfi = new Finch();
	final private static int testtime = 10000;

	public static void main(String[] args)
	{
	int lileft = myfi.getLeftLightSensor();
	int liright = myfi.getRightLightSensor();	
		
		System.out.println("\n"+"Running: ");
		long before = System.currentTimeMillis();
		while(System.currentTimeMillis() - before < testtime)
		{
			System.out.println(myfi.getLeftLightSensor() + " " + myfi.getRightLightSensor());
		}
	}	
}
