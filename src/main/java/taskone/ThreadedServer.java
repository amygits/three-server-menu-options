
/**
  File: ThreadedServer.java
  Author: Amy Ma
  Description: Threaded Server class in package taskone.
*/

package taskone;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

class ThreadedServer {
    public static void main(String[] args) throws Exception {
        int port;
        StringList strings = new StringList();

        if (args.length != 1) {
            // gradle runServer -Pport=9099 -q --console=plain
            System.out.println("Usage: gradle runTask1 -Pport=9099 -q --console=plain");
            System.exit(1);
        }
        port = -1;
        try {
            port = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be an integer");
            System.exit(2);
        }

        ServerSocket server = new ServerSocket(port);
        System.out.println("Server Started...");
        while (true) {
            System.out.println("Accepting Requests...");
            clientThread t = new clientThread(server, strings);
            t.start();

        }
    }
}

class clientThread extends Thread {
    Socket sock;
    StringList strings;

    public clientThread(ServerSocket serv, StringList strings) {
        try {
            this.sock = serv.accept();
            this.strings = strings;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Connected to client " + sock.getInetAddress() 
        + " @ port " + sock.getPort());
        Performer performer = new Performer(sock, strings);
        performer.doPerform();
        try {
            System.out.println("close socket of client ");
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}