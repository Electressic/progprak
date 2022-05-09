
public class Benutzer extends AbstractNutzer
{
	//Unterscheidung zwischen Server und Client
	static int intServer;
	static int intClient;
	
	Benutzer(int Server)
	{
		intServer = Server;
		if(intServer == 1)
		{
			intClient = 0;
		}
		else
		{
			intClient = 1;
		}
	}
	
	public int getStatus()
	{
		return intServer;
	}
}
