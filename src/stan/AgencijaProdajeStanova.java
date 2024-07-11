package stan;

import java.util.LinkedList;

public class AgencijaProdajeStanova {
	public static LinkedList<Stan> stan = new LinkedList<Stan>();

	public static void dodajStan(Stan a) {
		if (a == null)
			throw new NullPointerException("Stan ne sme biti null");
		if (stan.contains(a))
			throw new RuntimeException("Stan vec postoji");
		stan.add(a);
	}
	
	public static LinkedList<Stan> pronadjiStan(String adresa) {
		if (adresa == null)
			return null;
		LinkedList<Stan> novaLista = new LinkedList<Stan>();
		for (int i = 0; i < stan.size(); i++)
			if (stan.get(i).getAdresa().equals(adresa))
				novaLista.add(stan.get(i));
		return novaLista;
	}
}


