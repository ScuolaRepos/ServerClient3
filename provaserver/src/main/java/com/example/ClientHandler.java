package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket s;
    private PrintWriter pr = null;
    private BufferedReader br = null;
    private int contatore;

    public ClientHandler(Socket s, int c) {
        this.s = s;
        contatore = c;
        try {
            // per parlare
            pr = new PrintWriter(s.getOutputStream(), true);
            // per ascoltare
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            System.out.println(br.readLine());
            pr.println("Ciao come ti chiami?"); // invio messaggio
            String nome = br.readLine(); // ricevo: il nome
            String nomeUpper = nome.toUpperCase(); // nome in maiuscolo
            System.out.println("Utente di nome " + nomeUpper + " connesso"); // console: nome ricevuto
            pr.println("Salve " + nomeUpper + " sei l'utente connesso numero " + contatore); // invio messaggio

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
