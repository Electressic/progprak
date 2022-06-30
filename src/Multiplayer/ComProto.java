package progprak.src.Multiplayer;



public class ComProto
{
    private static final int MYTURN = 0;
    private static final int YOURTURN = 1;

    public String Communication(String INPUT)
    {
        String OUTPUT = null;
        String[] inputarray = INPUT.split(" ");
        if(inputarray[0].equalsIgnoreCase("size"))
        {
            int x = Integer.parseInt(inputarray[1]);
            //create Spielfeld here
        }
        if(inputarray[0].equalsIgnoreCase("ships"))
        {
            for(int i=1; i < inputarray.length; i++)
            {
                //create ships here
            }
        }
        if(inputarray[0].equalsIgnoreCase("done"))
        {
            //done
        }
        if(inputarray[0].equalsIgnoreCase("ready"))
        {
            //start game
        }
        if(inputarray[0].equalsIgnoreCase("answer"))
        {
            if(Integer.parseInt(inputarray[1]) == 0)
            {
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
            //shot
        }
        if(inputarray[0].equalsIgnoreCase("save"))
        {
            //save
        }
        if(inputarray[0].equalsIgnoreCase("load"))
        {
            //load
        }
        if(inputarray[0].equalsIgnoreCase("ok"))
        {
            //ok
        }
        return OUTPUT;
    }

}
