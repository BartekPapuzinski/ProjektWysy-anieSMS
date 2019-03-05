package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Serwer implements Runnable{

    Socket connect;
    int id;
    SMSClass smsClass =new SMSClass();


    public Serwer(Socket connect1,int id1)
    {
        connect=connect1;
        id=id1;

    }

    public void start() {

        try {

            BufferedReader input = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            DataOutputStream output = new DataOutputStream(connect.getOutputStream());
            String clientSentence;

            UserList[] users = new UserList[10];
            int licznik = 0;

            while (true) {

                licznik++;
                try {
                    if ((clientSentence = input.readLine()) != null) {
                        System.out.println(clientSentence);




                        if ((clientSentence.charAt(0)) == 'F')
                        {
                            String[] User;
                            User = clientSentence.split(" ");
                            output.writeBytes(id + "\n");

                            users[id] = new UserList(id, User[1], User[2], 1, connect, input);
                            System.out.println(users[id].write());
                        }

                        if ((clientSentence.charAt(0)) == 'N')
                        {
                            String[] User;
                            User = clientSentence.split(" ");

                            users[Integer.parseInt(User[1])].live = 1;
                        }

                        if ((clientSentence.charAt(0)) == 'E')
                        {
                            String[] User;
                            User = clientSentence.split(" ");

                            users[Integer.parseInt(User[1])].live = 2;
                        }
                    }
                }
                catch (IOException e)
                {
                    //e.printStackTrace();
                }

                if (licznik == 1000000)
                {
                    int i=users[id].check();

                    if(i==0)
                    {
                        System.out.println("Komputer o nazwie " + users[id].Name + " przestał działać");
                        //sendSMS(users[id].Number, "Komputer o nazwie " + users[id].Name + " przestał działać");
                        smsClass.SendSms(users[id].Number,"Komputer o nazwie " + users[id].Name + " przestał działać");
                        break;
                    }
                    if(i==2)
                    {
                        System.out.println("odłączono");
                        break;
                    }


                    licznik = 0;
                }


            }
        }catch (IOException e) {
            //e.printStackTrace();
        }
    }
    /*void sendSMS(String numer1,String tresc) {
        try {
            String numer=numer1;
            SmsManager menager = SmsManager.getDefault();
            menager.sendTextMessage(numer, null, tresc, null, null);
            System.out.println("Wysłano");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nie wysłano");
        }

    }*/
    @Override
    public void run() {
        start();
    }

}
