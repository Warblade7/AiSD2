
public class PracownikGodzinowy extends Pracownik{

    double stawka;
    int liczba_godz;

    PracownikGodzinowy(){
        super();
        stawka=21.7;
        liczba_godz=34;
    }

    PracownikGodzinowy(String imie, String nazwisko, String stanowisko, long pesel, int staz,double stawka, int liczba_godz){
        super(imie, nazwisko, stanowisko, pesel, staz);
        this.stawka=stawka;
        this.liczba_godz=liczba_godz;
    }

    @Override
    double pensja() {
        return stawka*liczba_godz;
    }

/*	@Override
	public String toString() {
		return String.format("%s, %d, %f, %f", super.toString(), liczba_godz, stawka, pensja());
}*/
}
