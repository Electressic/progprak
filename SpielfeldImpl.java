
public class SpielfeldImpl extends AbstractSpielfeld
{
	//Wie int zu behandeln
	static Zustand[][] intSpielfeld;	
	static int SpielfedSize;
	enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
		
	public SpielfeldImpl (int size)
	{
		intSpielfeld = new Zustand[size][size];		
		for(int i = 0; i < intSpielfeld[0].length; i++)
		{
			for(int y = 0; i < intSpielfeld[1].length; y++)
			{
				intSpielfeld[i][y] = Zustand.Wasser;
			}
		}
	}
}
