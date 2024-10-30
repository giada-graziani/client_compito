package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client partito...");
        Scanner scanner= new Scanner(System.in);
        Socket s= new Socket("localhost", 3000);

        BufferedReader in= new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        String username;
        String scelta;
        String messaggio;
        String nBiglietti;

        do{
            System.out.println("Inserisci il tuo username:");
            username= scanner.nextLine();
            out.writeBytes(username + "\n");
            messaggio=in.readLine();

            if(messaggio.equals("no")){
                System.out.println("username già esistente");
            }
            else{
                System.out.println("benvenuto!!");
                System.out.println("MENU");
                System.out.println("1)richiedere la disponibilità");
                System.out.println("2)acquistare biglietti");
                System.out.println("3)Uscire");
                do{
                    System.out.println("Scegli una delle opzioni");
                    scelta=scanner.nextLine();
                    out.writeBytes(scelta + "\n");
        
                    messaggio=in.readLine();
                    if(messaggio.equals("quit")){
                        System.out.println("Ok, alla prossima");
                    }
                    else if(messaggio.equals("buy")){
                        System.out.println("Quanti biglietti vuoi acquistare?");
                        nBiglietti= scanner.nextLine();
        
                        out.writeBytes(nBiglietti +"\n");
        
                        messaggio=in.readLine();
                        if(messaggio.equals("ok")){
                            System.out.println("acquisto venuto con successo");
                        }else{
                            System.out.println("biglietti non disponibili");
                        }
                    }
                    else if(messaggio.equals("n")){
                        nBiglietti=in.readLine();
                        System.out.println("biglietti disponibili: "+ nBiglietti);
                    }
                    else if(messaggio.equals("e")){
                        System.out.println("ERRORE");
                    }
        
        
                }while(!messaggio.equals("quit"));

            }

        }while(!messaggio.equals("no"));



    }
}