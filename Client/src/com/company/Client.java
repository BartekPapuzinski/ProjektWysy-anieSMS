package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable{
    String name;
    String number;
    String ipaddress;
    int id;
    DataOutputStream out;
    Socket socket;

    public Client(){}

    public Client(String name1,String number1,String ipaddress1)
    {
        name=name1;
        number=number1;
        ipaddress=ipaddress1;


    }


    public void start(String name, String number)throws IOException
    {
        socket = new Socket(ipaddress, 6789);
        out = new DataOutputStream(socket.getOutputStream());
        BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        id=Login(number,name,serverAnswer,out);
        System.out.println(id);
        Live(id,out);
    }

    public static int Login(String Number, String SysName, BufferedReader serverAnswer, DataOutputStream out)
    {
        try
        {

            out.writeBytes("F" +" "+ Number+" " + SysName + '\n');
            return Integer.parseInt(serverAnswer.readLine());
        }
        catch (IOException e)
        {
            System.out.println("błąd");
        }
        return 0;
    }

    public  void Live(int id,DataOutputStream out) {
        try {
            for (;; ) {
                out.writeBytes("N" + " " + id + " " + "Live" + '\n');
                System.out.println("Live");
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void End()
    {
        try {
            out.writeBytes("E" + " " + id + '\n');
            socket.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void run() {
        try {
            start(name,number);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
