import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.NoSuchFileException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    static Pracownik[] lista = new Pracownik[100];

    static int ile=0;

    public static void main(String[] args) {
        lista[0] = new PracownikEtatowy("Dawid","Lebkowski","Programista",12345678910L, 1, 1, 21000);
        lista[1] = new PracownikEtatowy("Jan","Kowalski","Programista",23432142311L, 1, 1, 9000);
        czytajPlik();
        int i=0;
        boolean w=true;
        int l=9;
        boolean loop=true;
        while(loop) {
            if(l!=9)
                System.out.println("Aktualna liczba pracownikow " + ile);
            System.out.println("Aby wyswietlic liste pracownikow, wprowadz - 0");
            System.out.println("Aby dodac pracownika:");
            System.out.println("Na pelen etat, wprowadz - 1");
            System.out.println("Na trzy czwarte etatu, wprowadz - 2");
            System.out.println("Na pol etatu, wprowadz - 3");
            System.out.println("Godzinowego, wprowadz - 4");
            System.out.println("Zakoncz prace - 5");

            try{
                l=Integer.valueOf(scan.nextLine());
                if(l>5||l<0)
                    throw new NumberFormatException();
                if(l==5)
                {
                  loop=false;
                }
                else if(l!=0) {
                    dodajPracownika(l);
                    i++;
                }
                else {
                    l=0;
                    rysujTabele();
                }
                zapiszPlik();
            } catch (Exception e) {
                System.err.println("Wprowadzono nieprawidlowy znak.");
            }
        }

    }



    private static void zapiszPlik() {

        ile=0;
        for(Pracownik aa: lista) {
            if(aa!=null)
                ile++;
        }

        IteratorTablicy<Pracownik> it = new IteratorTablicy<Pracownik>(lista);

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("plik.txt"));
            while(it.hasNext())
                os.writeObject(it.next());
            os.write(ile);
            os.close();
        }catch (NoSuchElementException ae) {}
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void czytajPlik() {

        IteratorTablicy<Pracownik> it = new IteratorTablicy<Pracownik>(lista);

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("plik.txt"));
            while(true) {
                try {
                    Object a = is.readObject();
                    if(Integer.valueOf(String.valueOf(a)) instanceof Integer)
                        ile = Integer.valueOf(String.valueOf(a));
                    else
                        it.replace((Pracownik) a);
                }
                catch(Exception b) {break;}
            }
            is.close();
        }catch(NoSuchFileException a) {
            zapiszPlik();
        }catch(FileNotFoundException ad) {
            zapiszPlik();
        }catch(EOFException n) {}
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void dodajPracownika(int l) {
        String imie, nazwisko, stanowisko;
        long pesel = 0;
        int staz = 0;
        double stawka = 0;
        boolean c=true;
        System.out.println("Wprowadz nazwisko pracownika");
        nazwisko = scan.nextLine();
        System.out.println("Wprowadz imie pracownika");
        imie=scan.nextLine();
        System.out.println("Wprowadz stanowisko pracownika");
        stanowisko=scan.nextLine();
        while(c) {
            System.out.println("Wprowadz pesel pracownika");
            try{
                pesel=Long.valueOf(scan.nextLine());
                if(pesel<10000000000L||pesel>99999999999L)
                    throw new NumberFormatException();
                c=false;
            }catch (Exception e) {
                System.err.println("Nieprawidlowy pesel.");
            }
        }
        c=true;
        while(c) {
            System.out.println("Wprowadz staz pracownika");
            try{
                staz=Integer.valueOf(scan.nextLine());
                if(staz<0||staz>100)
                    throw new NumberFormatException();
                c=false;
            }catch (Exception e) {
                System.err.println("Nieprawidlowy staz.");
            }
        }
        c=true;
        while(c) {
            System.out.println("Wprowadz stawke pracownika");
            try{
                stawka=Double.valueOf(scan.nextLine());
                if(stawka<0)
                    throw new NumberFormatException();
                c=false;
            }catch (Exception e) {
                System.err.println("Nieprawidlowa stawka.");
            }
        }

        int ii=0;
        for(;ii<lista.length;ii++) {
            if(lista[ii]==null)
                break;
        }

        switch(l) {
            case 1:
                lista[ii]=new PracownikEtatowy(imie, nazwisko, stanowisko, pesel, staz, 1, stawka);
                break;
            case 2:
                lista[ii]=new PracownikEtatowy(imie, nazwisko, stanowisko, pesel, staz, 0.75, stawka);
                break;
            case 3:
                lista[ii]=new PracownikEtatowy(imie, nazwisko, stanowisko, pesel, staz, 0.5, stawka);
                break;
            case 4:
                while(true) {
                    System.out.println("Podaj liczbe godzin pracy");
                    try{
                        lista[ii]=new PracownikGodzinowy(imie, nazwisko, stanowisko, pesel, staz, stawka, Integer.valueOf(scan.nextLine()));
                        break;
                    } catch(Exception e) {System.err.println("Nieprawidlowa ilosc godzin");}
                }
                break;
        }
    }

    private static void rysujTabele() {
        IteratorTablicy<Pracownik> it = new IteratorTablicy<>(lista);
        System.out.println("------------------------------------------------------------------------");
        System.out.printf("|%-10s | %-10s | %-11s | %-12s | %-4s | %-9s|\n", "Nazwisko" , "Imie" , "Pesel" , "Stanowisko" , "Staz" , "Pensja");
        System.out.println("------------------------------------------------------------------------");
        Pracownik a;
        while(it.hasNext()) {
            a=it.next();
            System.out.println(a);
            //System.out.printf("|%-10s | %-10s | %11d | %-12s | %-4d | %-8.8s |\n", a.nazwisko , a.imie , a.pesel , a.stanowisko , a.staz , String.valueOf(a.pensja()));
        }
        it.moveTo(0);
        if(it.hasNext()==true)
            System.out.println("------------------------------------------------------------------------");


    }

}
