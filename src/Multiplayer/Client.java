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
            String fromServer, fromUser;
            ComProto CP = new ComProto();
            while ((fromServer = inc.readLine()) != null)
            {

                fromUser = CP.Communication(fromServer);
                out.write(fromUser);
                out.newLine();
                out.flush();
            }
        }
        catch (Exception e) {
            System.out.println("Exception in Client Main");
        }
    }
}
