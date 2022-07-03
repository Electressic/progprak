package progprak.src.Multiplayer;


import jdk.jfr.Timespan;
import java.time.*;
import progprak.src.Logik.Spielfeld;

public class ComProto
{
    private static final int NEW = 0;
    private static final int SENTFIELD = 1;
    private static final int SENTSHIPS = 2;

    public boolean MYTURN;

    public int[] coord = new int[2];
    public int size;
    public int[] ships;

    public String shipstate;

    public Spielfeld Feld;
    private int progress = NEW;

    public String Communication(String INPUT) throws Exception {
        String OUTPUT = null;
        String[] inputarray = INPUT.split(" ");
        if(inputarray[0].equalsIgnoreCase("size"))
        {
            this.setSize(Integer.parseInt(inputarray[1]));
            //create Spielfeld here
            OUTPUT = "done";
            progress = SENTFIELD;
        }
        if(inputarray[0].equalsIgnoreCase("ships") && progress == 1)
        {
                //create ships here
                this.setShips(inputarray);
                progress = SENTSHIPS;
        }
        if(inputarray[0].equalsIgnoreCase("done"))
        {
            //done
        }
        if(inputarray[0].equalsIgnoreCase("ready") && progress == 2)
        {
            //start game
        }
        if(inputarray[0].equalsIgnoreCase("answer"))
        {
            if(Integer.parseInt(inputarray[1]) == 0)
            {
                OUTPUT = "pass";
                //update Spielfeld
            }
            if(Integer.parseInt(inputarray[1]) == 1)
            {
                setShipstate("shiphit");
                //update Spielfeld
            }
            if(Integer.parseInt(inputarray[1]) == 2)
            {
                setShipstate("shipsunk");
                //update Spielfeld
            }
        }
        if(inputarray[0].equalsIgnoreCase("pass"))
        {
            //pass
        }
        if(inputarray[0].equalsIgnoreCase("shot"))
        {
            int row = Integer.parseInt(inputarray[1]);
            int col = Integer.parseInt(inputarray[2]);
            this.setCoord(row,col);
            //shot
        }
        if(inputarray[0].equalsIgnoreCase("save"))
        {
            long id = Integer.parseInt(inputarray[1]);
            Feld.speichern();
            //save
        }
        if(inputarray[0].equalsIgnoreCase("load"))
        {
            String id = inputarray[1];
            Feld.SpielLaden(id);
            //load
        }
        if(inputarray[0].equalsIgnoreCase("ok"))
        {
            //ok
        }
        return OUTPUT;
    }

    public void setCoord(int row, int col)
    {
        this.coord[0] = row;
        this.coord[1] = col;
    }

    public int[] getCoord()
    {
        return this.coord;
    }

    public void setSize(int s)
    {
        this.size = s;
    }

    public int getSize()
    {
        return this.size;
    }

    public void setShips(String[] sh)
    {
        for(int i = 0; i < sh.length - 1; i++)
        {
            this.ships[i] = Integer.parseInt(sh[i+1]);
        }
    }

    public int[] setships()
    {
        return this.ships;
    }

    public void setShipstate(String shipstate)
    {
        this.shipstate = shipstate;
    }

    public String getShipstate()
    {
        return shipstate;
    }
}