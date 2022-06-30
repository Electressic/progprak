package progprak;
import progprak.src.ComProto;

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
            String input, output;
            ComProto CP = new ComProto();
            while ((input = incoming.readLine()) != null)
            {
                output = CP.Communication(input);
                outgoing.write(output);
                outgoing.newLine();
                outgoing.flush();
            }
        }

        catch (IOException e) {
            System.out.println("Exception in Server main");
        }
    }
}
