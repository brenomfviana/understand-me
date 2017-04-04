/*
 * GNU License.
 */
package canachatserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Cana chat server.
 *
 * @author Breno Viana
 */
public class CanaChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            int port = 25000;
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and listening to the port 25000");
            List<Socket> sockets = new ArrayList<>();

            //Server is running always. This is done using this while(true) loop
            while (true) {
                //Reading the message from the client
                sockets.add(serverSocket.accept());

                if (sockets.size() == 2) {
                    InputStream is = sockets.get(0).getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String message = br.readLine();
                    System.out.println("Message received from client is " + message);

                    //Sending the response back to the client.
                    OutputStream os = sockets.get(1).getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);
                    bw.write(message);
                    System.out.println("Message sent to the client is " + message);
                    bw.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // socket.close();
            } catch (Exception e) {
            }
        }
    }

}
