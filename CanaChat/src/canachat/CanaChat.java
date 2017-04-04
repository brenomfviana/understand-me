/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canachat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author breno
 */
public class CanaChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // 
            String host = args[0];
            // Port
            int port = 25000;
            // Get address
            InetAddress address = InetAddress.getByName(host);
            // Start connection with server
            Socket socket = new Socket(address, port);
            // Client indenfier
            try {
                int clientID = Integer.valueOf(args[1]);
                int friendID = Integer.valueOf(args[2]);
            } catch (Exception e) {
                System.exit(-1);
            }

            // 
            while (true) {
                // 
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                // 
                int c = Integer.valueOf(input.readLine());
                if (c == 1) {
                    // Send the message to the server
                    OutputStream os = socket.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os);
                    BufferedWriter bw = new BufferedWriter(osw);

                    String number = input.readLine();

                    String sendMessage = number + "\n";
                    bw.write(sendMessage);
                    bw.flush();
                    System.out.println("You: " + sendMessage);
                } else {
                    //Get the return message from the server
                    InputStream is = socket.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    String message = br.readLine();
                    System.err.println("Your Friend: " + message);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            //Closing the socket
            try {
                // socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
