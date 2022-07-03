package progprak.src.Multiplayer;


import progprak.src.Logik.Spielfeld;

public class ComProto
{
    private static final int NEW = 0;
    private static final int SENTFIELD = 1;
    private static final int SENTSHIPS = 2;

    public Spielfeld Feld;
    private int progress = NEW;

    public String Communication(String INPUT) throws Exception {
        String OUTPUT = null;
        String[] inputarray = INPUT.split(" ");
        if(inputarray[0].equalsIgnoreCase("size"))
        {
            int x = Integer.parseInt(inputarray[1]);
            //create Spielfeld here
            Spielfeld Feld = new Spielfeld(x);
            OUTPUT = "done";
            progress = SENTFIELD;
        }
        if(inputarray[0].equalsIgnoreCase("ships"))
        {
            for(int i=1; i < inputarray.length; i++)
            {
                //create ships here


                progress = SENTSHIPS;
            }
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
                //update Spielfeld
            }
            if(Integer.parseInt(inputarray[1]) == 2)
            {
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
            Feld.shoot(row,col);
            //shot
        }
        if(inputarray[0].equalsIgnoreCase("save"))
        {
            int id = Integer.parseInt(inputarray[1]);
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

}
