package logika;

/**
 * Možni igralci.
 */

public enum Igralec {
	PRVI, DRUGI;

	public Igralec nasprotnik() {
		return (this == PRVI ? DRUGI : PRVI);
	}
	
	public Polje getPolje() {
		return this == PRVI ? Polje.PRVI : Polje.DRUGI;
	}
}
