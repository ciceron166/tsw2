package stan;

public class Stan {
	private String adresa = null;
	private int kvadratura = 0;
	private int cenaPoKvadratu = 0;

	public Stan(String adresa, int kvadratura, int cenaPoKvadratu) {
		super();
		this.adresa = adresa;
		this.kvadratura = kvadratura;
		this.cenaPoKvadratu = cenaPoKvadratu;
	}

	public int getKvdratura() {
		return kvadratura;
	}

	public void setKvadratura(int kvadratura) {
		if (kvadratura < 0)
			throw new RuntimeException("Kvadratura ne sme biti manja od 0");
		this.kvadratura = kvadratura;
	}

	public int getCenaPoKvadratu() {
		return cenaPoKvadratu;
	}

	public void setCenaPoKvadratu(int cenaPoKvadratu) {
		if (cenaPoKvadratu <= 0 || cenaPoKvadratu >= 5000)
			throw new RuntimeException("Cena mora biti veca od 0 i manja od 5000");
		this.cenaPoKvadratu = cenaPoKvadratu;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		if (adresa == null)
			throw new RuntimeException("Morate uneti adresu");
		this.adresa = adresa;
	}

	public double izracunajCenu() {
		return (this.kvadratura * this.cenaPoKvadratu);
	}

	public boolean povoljanStan() {
		if (this.izracunajCenu() <= 50000)
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		return "Stan [adresa=" + adresa + ", kvadratura=" + kvadratura + ", cena po kvadratu=" + cenaPoKvadratu + "]";
	}
	
	
	
}

