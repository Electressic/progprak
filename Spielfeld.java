import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


enum Zustand {Wasser, Schiff_Normal, Schiff_Getroffen};
public class Spielfeld extends AbstractNutzer
{
	protected Zustand[][] zustandSpielfeldGegner;
	protected Zustand[][] zustandSpielfeld;
	protected BattleShip[][] Ships = new BattleShip[0][0];
	protected BattleShip[] vorhandeneSchiffe = new BattleShip[0];
	protected int SpielfeldSize;
	
	protected Spielfeld(int size)
	{
		this.SpielfeldSize = size;
		this.zustandSpielfeld = new Zustand[size][size];
		this.zustandSpielfeldGegner = new Zustand[size][size];
		for(int i = 0; i < zustandSpielfeld[0].length; i++)
		{
			for(int y = 0; i < zustandSpielfeld[1].length; y++)
			{
				zustandSpielfeld[i][y] = Zustand.Wasser;
				zustandSpielfeldGegner[i][y] = Zustand.Wasser;
			}
		}
	}

	public String getFleet()
	{
		if(getSpielfeldSize() >= 20)
		{
			return "ships 3 4 5 6";
		}
		return "ships 2 3 4 5";
	}
	public boolean setzeSchiff(int intStartPosX, int intStartPosY, int intRichtung, int intGroesse)
	{
		int Pruefwert = -1;
		if(intRichtung == 0)
		{
			Pruefwert = intStartPosX + intGroesse;
		}
		else if (intRichtung == 1)
		{
			Pruefwert = intStartPosY - intGroesse;
		}
		else if (intRichtung == 2)
		{
			Pruefwert = intStartPosX - intGroesse;
		}
		else if (intRichtung == 3)
		{
			Pruefwert = intStartPosY + intGroesse;
		}
		if(Pruefwert > getSpielfeldSize()  || Pruefwert < 0)
		{
			return false;
		}
		
		BattleShip ship = new BattleShip(intGroesse, getSpielfeldSize());
		int PosX = intStartPosX;
		int PosY = intStartPosY;
		//(0, 0) liegt unten Links
		for(int i = 0; i < intGroesse; i++)
		{
			Ships[PosX][PosY] = ship;
			if(intRichtung == 0)
			{
				PosX++;
			}
			else if(intRichtung == 1)
			{
				PosY--;
			}
			else if(intRichtung == 2)
			{
				PosX--;
			}
			else if(intRichtung == 3)
			{
				PosY++;
			}
		}
		BattleShip[] temp = new BattleShip[vorhandeneSchiffe.length + 1];
		for (int i = 0; i < vorhandeneSchiffe.length; i++)
		{
			temp[i] = vorhandeneSchiffe[i];
		}
		temp[temp.length - 1] = ship;
		vorhandeneSchiffe = temp;
		return true;
	}
	public Zustand getZustandPos(int x, int y)
	{
		return zustandSpielfeld[x][y];
	}
	public String PruefeSchuss(int x, int y)
	{
		if(zustandSpielfeld[x][y] == Zustand.Wasser)
		{
			return "answer 0";
		}
		else if(zustandSpielfeld[x][y] == Zustand.Schiff_Normal)
		{
			if(WelchesSchiff(x, y).getroffen() == 1) 
			{
				return "answer 2";
			}
			this.zustandSpielfeld[x][y] = Zustand.Schiff_Getroffen;
			return "answer 1";			
		}
		else if(zustandSpielfeld[x][y] == Zustand.Schiff_Getroffen)
		{
			return "answer 0";
		}
		return null;
	}
	public BattleShip WelchesSchiff(int x, int y)
	{
		if(getZustandPos(x, y) == Zustand.Wasser)
		{
			return null;
		}	
		return Ships[x][y];
	}
	public void speichern()
	{
		System.out.println("speichern....");
		
        PrintWriter pWriter = null;
        String s = "/Users/Admin/Downloads/Neues Textdokument.txt";
        try 
        {
            pWriter = new PrintWriter(new FileWriter(s));
            pWriter.println("Hallo Welt!");
            pWriter.flush();
            pWriter.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
        } 
        finally 
        {
        	
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
    public boolean habeVerloren()
    {
    	int counter = 0;
    	for(int i = 0; i < vorhandeneSchiffe.length; i++)
    	{
    		if(vorhandeneSchiffe[i].istVersenkt())
    		{
    			counter++;
    		}
    	}
    	if(counter == vorhandeneSchiffe.length - 1)
    	{
    		return true;
    	}
    	return false;
    }

	@Override
	protected int getSpielfeldSize() 
	{
		return this.SpielfeldSize;
	}
}
