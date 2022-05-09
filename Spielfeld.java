import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
public class Spielfeld implements InterfaceSpielfeld
{

	Zustand[][] intSpielfeld;
	int SpielfedSize;
	BattleShip[] Ships = new BattleShip[0];
	protected Spielfeld(int size)
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
	int getSpielfeldSize()
	{
		return this.SpielfedSize;
	}
	public String getFleet()
	{
		if(getSpielfeldSize() >= 20)
		{
			return "3 4 5 6";
		}
		return "2 3 4 5";
	}
	boolean setzeSchiff(int intStartPosX, int intStartPosY, int intRichtung, int intGroesse)
	{
		if((intGroesse + intStartPosX) > this.SpielfedSize)
		{
			return false;
		}
		
		BattleShip ship = new BattleShip(intGroesse, getSpielfeldSize());
		
		BattleShip[] temp = new BattleShip[Ships.length + 1];
		ship.setRichtung(intRichtung);
		ship.setStartposition(intStartPosX, intStartPosY);
		for (int i = 0; i < Ships.length; i++)
		{
			temp[i] = Ships[i];
		}
		temp[temp.length-1] = ship;
		Ships = temp;
		return true;
	}
	Zustand getZustandPos(int x, int y)
	{
		return intSpielfeld[x][y];
	}
	public String PruefeSchuss(int x, int y)
	{
		if(intSpielfeld[x][y] == Zustand.Wasser)
		{
			return "answer 0";
		}
		else if(intSpielfeld[x][y] == Zustand.Schiff_Normal)
		{
			if(WelchesSchiff(x, y).getroffen() == 1) 
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
	BattleShip WelchesSchiff(int x, int y)
	{
		int[] Pos;
		for(int i = 0; i <Ships.length; i++)
		{
			Pos = Ships[i].getStartPos();
			if(Pos[0] == x && Pos[1] == y)
			{
				return Ships[i];
			}
		}
		return null;
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
    public boolean SchiffNormal(Zustand zustand) 
    {        
        if(zustand == Zustand.Schiff_Normal) {
            return true;            
        }    
        return false;
    }
    public boolean SchiffGetroffen(Zustand zustand) 
    {        
        if(zustand == Zustand.Schiff_Getroffen) 
        {
            return true;            
        }    
        return false;
    }
    public boolean Wasser(Zustand zustand) 
    {        
        if(zustand == Zustand.Wasser) 
        {
            return true;            
        }    
        return false;
    }
}
