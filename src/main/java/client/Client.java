package client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8989;
        try (
                Socket clientSocket = new Socket(host, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outFile = new PrintWriter("reply.json");
                BufferedReader inFile = new BufferedReader(new FileReader("request.json"))
        ) {

            out.println(inFile.readLine());

            outFile.println(in.readLine());
        }
    }
}
