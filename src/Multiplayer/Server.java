package progprak.src.Multiplayer;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException
    {
        try (

            ServerSocket Server = new ServerSocket(111);
            Socket clientsocket = Server.accept();
            BufferedReader incoming = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            BufferedWriter outgoing = new BufferedWriter(new OutputStreamWriter(clientsocket.getOutputStream()));
        )
        {
            String input, output = null;
            ComProto CPS = new ComProto();
            CPS.MYTURN = true;
            while ((input = incoming.readLine()) != null) {
                if (!CPS.MYTURN) {
                    output = CPS.Communication(input);
                    CPS.MYTURN = true;
                }
                if (CPS.MYTURN)
                {
                    outgoing.write(output);
                    outgoing.newLine();
                    outgoing.flush();
                    CPS.MYTURN = false;
                }

            }
        }

        catch (Exception e) {
            System.out.println("Exception in Server main");
        }
    }
}