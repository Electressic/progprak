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
            }


            return OUTPUT;
        }

    }
