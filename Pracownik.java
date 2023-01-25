import java.io.Serializable;

public abstract class Pracownik implements Serializable{


    String imie, nazwisko, stanowisko;
    long pesel;
    int staz;

    Pracownik(){
        imie="Jan";
        nazwisko="Kowalski";
        stanowisko="sprzatacz";
        pesel=98321327911L;
        staz=5;
    }

    Pracownik(String imie, String nazwisko, String stanowisko, long pesel, int staz){
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.stanowisko=stanowisko;
        this.pesel=pesel;
        this.staz=staz;
    }

    @Override
    public String toString() {
        return String.format("|%-10s | %-10s | %11d | %-12s | %-4d | %8.8s |", nazwisko, imie, pesel, stanowisko, staz, String.valueOf(pensja()));
    }

    abstract double pensja();

    public void wyswietl() {
        System.out.print(imie+" "+nazwisko+" "+stanowisko+" ");
        System.out.printf("%11d %2d", pesel+staz);
    }
}
