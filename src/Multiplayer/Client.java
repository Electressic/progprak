package progprak.src.Multiplayer;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException
    {
        try (

            Socket Client = new Socket("localhost", 1111);
            BufferedReader inc = new BufferedReader(new InputStreamReader(Client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
            )
        {
            String fromServer, fromUser = null;
            ComProto CPC = new ComProto();
            CPC.MYTURN = false;
            while ((fromServer = inc.readLine()) != null)
            {
                if (!CPC.MYTURN)
                {
                    fromUser = CPC.Communication(fromServer);
                    CPC.MYTURN = true;
                }
                if (CPC.MYTURN)
                {
                    out.write(fromUser);
                    out.newLine();
                    out.flush();
                    CPC.MYTURN = false;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Exception in Client Main");
        }
    }
}
