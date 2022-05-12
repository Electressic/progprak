package test;
public class Benutzer implements InterfaceNutzer
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

	@Override
	public Zustand ZustandFeld(int x, int y) 
	{
		return null;
	}
}
