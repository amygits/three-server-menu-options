/**
  File: ThreadPoolServer.java
  Author: Amy Ma
  Description: Thread pool server class in package taskone.
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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.json.JSONObject;

class clientWorker implements Runnable {
    StringList strlist;
    Socket sock;

    public clientWorker(ServerSocket serv, StringList stringlist, int i) {
        try {
            System.out.println("Starting thread-" + i);
            sock = serv.accept();
            strlist = stringlist;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Connected to client " + sock.getInetAddress() + " @ port " + sock.getPort());
        Performer performer = new Performer(sock, strlist);
        performer.doPerform();
        try {
            System.out.println("close socket of client ");
            sock.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ThreadPoolServer {
    static int port = 9099;
    static int threads = 3;

    public static void main(String[] args) throws Exception {

        StringList strings = new StringList();

        if (args.length != 2) {
            System.out.println("Usage: gradle runTask3 -Pport=9099 -Pthreads=3 -q --console=plain");
            System.exit(1);
        }
        System.out.println(args[0]+ ", " + args[1]);        
        
        try {
            port = Integer.parseInt(args[0]);
            threads = Integer.parseInt(args[1]);
            System.out.println("port: " + port);
            System.out.println("threads: " + threads);
        } catch (NumberFormatException nfe) {
            System.out.println("[Port] must be an integer");
            System.exit(2);
        }

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server Started...");
        System.out.println("Room for " + threads + " requests...");

        Executor pool = Executors.newFixedThreadPool(threads);

        for (int i = 0; i < threads; i++) {
            ServerSocket finalServ = server;
            pool.execute(new clientWorker(finalServ, strings, i));
        }

        System.out.println("Too many requests");
        // clientThread t = new clientThread(server, strings);
        // t.start();

    }
}