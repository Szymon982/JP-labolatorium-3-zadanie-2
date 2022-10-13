package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<String> Lista_imion = new ArrayList<>();
    static ArrayList<String> Lista_imion_bez_duplikatow = new ArrayList<>();
    static ArrayList<String> Duplikaty = new ArrayList<>();
    static ArrayList<ArrayList<imie>> Lista_koncowa = new ArrayList<>();
    static ArrayList<ArrayList<imie>> Lista_imiona_na_litere_2_bez_duplkatow = new ArrayList<>();
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
        for(int i=0;i<26;i++){
            Lista_koncowa.add(new ArrayList<>());
        }
        for(int i=0;i<26;i++){
            Lista_imiona_na_litere_2_bez_duplkatow.add(new ArrayList<>());
        }
        usun_duplikaty(Lista_imion,Lista_imion_bez_duplikatow);
        zlicz_wystapienia(Lista_imion);
        wypisz_ile_wystapien("Wiktor");
        System.out.println("LISTA: ");
        System.out.print("[ ");
        for (int i = 0; i< Lista_koncowa.size(); i++){
            System.out.print("[ ");
            for (int j = 0; j< Lista_koncowa.get(i).size(); j++){
                System.out.print(Lista_koncowa.get(i).get(j).imie);
                System.out.print(",");
            }
            System.out.print(" ]");
        }
        System.out.print(" ]");

        System.exit(69);
    }

    public static ArrayList<String> Odczytaj_plik(String dir) throws IOException {
        ArrayList<String> Lista = new ArrayList<>();
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

    public static void usun_duplikaty(ArrayList<String> lista,ArrayList<String> lista_nowa) {
        for (int i=0;i<lista.size();i++) {
            if(!lista_nowa.contains(lista.get(i))){
                lista_nowa.add(lista.get(i));
            } else {
                Duplikaty.add(lista.get(i));
            }
        }
    }

    public static void zlicz_wystapienia(ArrayList<String> lista_imion){
        try {
            int licznik = 0;
            for(int i=0; i<Lista_imion.size();i++){
                if (licznik != 0 && Duplikaty.contains(lista_imion.get(i))){
                    licznik = 0;
                    continue;
                }
                int liczba_wystapien = Collections.frequency(Lista_imion,lista_imion.get(i));
                Lista_koncowa.get(numer_litery(lista_imion.get(i).substring(0, 1))).add(new imie(lista_imion.get(i),liczba_wystapien));
                licznik++;
            }
        }
        catch(Exception InvalidParameterException) {
            System.out.println("Niepoprawny parametr");
        }
    }

    public static void wypisz_ile_wystapien(String imie){
        for(int i=0; i<Lista_koncowa.size();i++){
            for(int j=0; j<Lista_koncowa.get(i).size();j++){
                if(Objects.equals(Lista_koncowa.get(i).get(j).imie, imie)){
                    System.out.println("Liczba wystapien imienia "+imie+": "+Lista_koncowa.get(i).get(j).liczba_wystapien);
                }
            }
        }
    }
}
