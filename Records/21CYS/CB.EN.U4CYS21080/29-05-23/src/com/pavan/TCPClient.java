package com.pavan.amrita.cys.jpl.net;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The TCPClient class represents the client side of the TCP client-server application.
 * It connects to the server and sends requests.
 */
public class TCPClient {

    /**
     * The main method is the entry point of the client application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            // Connect to the server on localhost and port 4424
            Socket clientSocket = new Socket("localhost", 4424);

            // Create input and output streams for client communication
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            String request;
            Scanner scanner = new Scanner(System.in);

            do {
                // Send request to the server
                System.out.print("Enter your request (or 'exit' to quit): ");
                request = scanner.nextLine();

                outToServer.writeBytes(request + '\n');
                System.out.println("Sent to server: " + request);

                // Receive response from the server
                String response = inFromServer.readLine();
                System.out.println("Received from server: " + response);

            } while (!request.equalsIgnoreCase("exit"));
            // Close the client socket
            clientSocket.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
