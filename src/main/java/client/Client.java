package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8989;
        try (
                Socket clientSocket = new Socket(host, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {

            out.println("{\"title\": \"мыло\", \"date\": \"2022.04.09\", \"sum\": 1000}");

            System.out.println(in.readLine());
        }
    }
}
