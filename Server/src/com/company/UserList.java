package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class UserList {
    int ID;
    String Number;
    String Name;
    Socket connect;
    BufferedReader input;
    int live;



    public UserList(int id1, String number1,String name1,int live1,Socket connect1 ,BufferedReader input1)
    {

        ID=id1;
        Number=number1;
        Name=name1;
        connect=connect1;
        input=input1;
        live=live1;
    }
    public String write()
    {
        return ID+Number+Name;
    }

    public int check()
    {
        if(live==2)
        {
            try
            {
                connect.close();
                System.out.println("Wyłączono"+ID);
                return 2;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(live==1)
        {
            live=0;
            return 1;
        }
        if(live==0)
        {
            System.out.println("przestał działać"+ID);
            try {
                connect.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }
        return 3;

    }
    //@Override
    /*public void run()
    {
        while(true) {
            try
            {
                System.out.println("watek");
                String clientSentence2;
                clientSentence2 = input.readLine();
                if (clientSentence2.charAt(0) == 'N') {
                    System.out.println("xd");
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
                break;
            }
        }

    }*/
}
