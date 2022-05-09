import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpielfeldImpl extends AbstractSpielfeld
{
	//Wie int zu behandeln
	Zustand[][] intSpielfeld;	
	BattleShip[] Ships = new BattleShip[5]; //muss angepasst werden
	int SpielfedSize;
	enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
	int[] Flotte;
	
	//Konstruktor
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
		this.SpielfedSize = size;
	}
	/*
	Funktioniert so nicht Aufgrund der Fehlermeldung, dass das erste der Konstruktoraufruf ist
	public SpielfeldImpl (String Input)
	{
		String[] Text = Input.split(" ");
		int size = Integer.parseInt(Text[1]);
		this(size);		
	}
	*/
	
	public String getFleet()
	{
		if(getSpielfeldSize() >= 20)
		{
			return "3 4 5 6";
		}
		return "2 3 4 5";
	}
	
	public int getSpielfeldSize()
	{
		return this.SpielfedSize;
	}
	//Es muss noch überprüft werden, mit welchem Objekt ich den Schuss überprüfen kann!!!
	public String PruefeSchuss(int x, int y)
	{
		if(intSpielfeld[x][y] == Zustand.Wasser)
		{
			return "answer 0";
		}
		else if(intSpielfeld[x][y] == Zustand.Schiff_Normal)
		{
			//Welches Schiff wurde getroffen??? Index anpassen!!!!!
			if(Ships[0].getroffen() == 1) 
			{
				return "answer 2";
			}
			this.intSpielfeld[x][y] = Zustand.Schiff_Getroffen;
			return "answer 1";			
		}
		else if(intSpielfeld[x][y] == Zustand.Schiff_Getroffen)
		{
			return "answer 0";
		}
		return null;
	}
	
	boolean setzeSchiff(int intStartPos, int intRichtung, int intGroesse)
	{
		if((intGroesse + intStartPos) > this.SpielfedSize)
		{
			return false;
		}
		
		BattleShip ship = new BattleShip(intGroesse);
		ship.setRichtung(intRichtung);
		ship.setStartposition(intStartPos);
		for (int i = 0; i < Ships.length; i++)
		{
			if(Ships[i] != null)
			{
				Ships[i] = ship;
			}
		}
		return true;
	}
	
	void speichern()
	{
		System.out.println("speichern....");
		
        PrintWriter pWriter = null;
        String s = "/Users/Admin/Downloads/Neues Textdokument.txt";
        try 
        {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println("Hallo Welt!");
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        finally 
        {
            if (pWriter != null) 
            {
                pWriter.flush();
                pWriter.close();
            }
        }
		System.out.println("Spiel gespeichert!");
	}
}
