
public class PracownikEtatowy extends Pracownik{

    double etat, stawka;

    PracownikEtatowy(){
        super();
        etat=1;
        stawka=6677.88;
    }

    PracownikEtatowy(String imie, String nazwisko, String stanowisko, long pesel, int staz, double etat, double stawka){
        super(imie, nazwisko, stanowisko, pesel, staz);
        this.etat=etat;
        this.stawka=stawka;
    }

    @Override
    double pensja() {
        return etat*stawka;
    }
	
/*	@Override
	public String toString() {
		return String.format("%s, %f, %f, %f", super.toString(), etat, stawka, pensja());
}*/
}