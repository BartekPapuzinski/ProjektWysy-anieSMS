package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

public class Main /*implements Runnable*/{


    public static void main(String[] args) throws IOException {
        int id=0;
        ServerSocket socket = new ServerSocket(6789);
        while(true)
        {
            System.out.println("Serwer Started");
            Socket connect = socket.accept();
            Runnable serwer = new Serwer(connect, id);
            Thread t1 = new Thread(serwer);
            t1.start();
            id++;
        }


    }
    /*@Override
    public void run() {
        try {
            main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
