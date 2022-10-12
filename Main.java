package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static ArrayList<String> Lista_imion = new ArrayList<String>();
    static ArrayList<ArrayList<String>> Lista_imiona_na_litere = new ArrayList<ArrayList<String>>();
    static ArrayList<ArrayList<imie>> Lista_imiona_na_litere_2 = new ArrayList<ArrayList<imie>>();
    static String st;

    public static void main(String[] args) throws IOException {
        System.out.println("Autor: Szymon Borzdyński");

        ArrayList<String> lista1 = Odczytaj_plik("C:\\Users\\Dom\\Desktop\\pliki do JP\\Imiona1.txt");
        ArrayList<String> lista2 = Odczytaj_plik("C:\\Users\\Dom\\Desktop\\pliki do JP\\Imiona2.txt");
        ArrayList<String> lista3 = Odczytaj_plik("C:\\Users\\Dom\\Desktop\\pliki do JP\\Imiona3.txt");

        Lista_imion.addAll(lista1);
        Lista_imion.addAll(lista2);
        Lista_imion.addAll(lista3);

        Collections.sort(Lista_imion);
        System.out.println(Lista_imion);
        for(int i=0;i<26;i++){
            Lista_imiona_na_litere.add(new ArrayList<>());
        }
        //System.out.println(numer_litery("A"));
        zliczanie(Lista_imion);
        System.exit(69);
    }

    public static ArrayList<String> Odczytaj_plik(String dir) throws IOException {
        ArrayList<String> Lista = new ArrayList<String>();
        File plik = new File(dir);
        BufferedReader buf = new BufferedReader(new FileReader(plik));
        while ((st = buf.readLine()) != null){
            Lista.add(st);
        }
        return Lista;
    }
    public static int numer_litery(String litera) {
        char[] ch = litera.toCharArray();
        int numer = 0;
        for (char c : ch) {
            int temp_integer = 65;
            if ((int) c <= 90 & (int) c >= 65) {
                numer = (int) c - temp_integer;
            }
        }
        return numer;

    }
    public static void zliczanie(ArrayList<String> lista_imion){
        for(int i=0; i<lista_imion.size();i++){
            Lista_imiona_na_litere.get(numer_litery(lista_imion.get(i).substring(0, 1))).add(lista_imion.get(i));
        }
        System.out.println(Lista_imiona_na_litere);
    }


}
