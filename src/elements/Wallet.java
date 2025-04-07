
package elements;

public class Wallet {

	private double dollars;

	private double coins;

	private double blockedDollars;

	private double blockedCoins;

	public Wallet(double dollars, double coins) {
		this.dollars = dollars;
		this.coins = coins;
		blockedDollars = 0.0;
		blockedCoins = 0.0;
	}

	public void depositDollars(double dollars) {
		this.dollars += dollars;
	}

	public void depositCoins(double coins) {
		this.coins += coins;
	}

	public void withdrawDollars(double dollars) {
		this.dollars -= dollars;
	}

	public void blockDollars(double dollars) {
		this.dollars -= dollars;
		blockedDollars += dollars;
	}

	public void blockCoins(double coins) {
		this.coins -= coins;
		blockedCoins += coins;
	}

	public void returnDollars(double dollars) {
		this.dollars += dollars;
		blockedDollars -= dollars;
	}

	public void payFromBlockedDollars(double dollars) {
		blockedDollars -= dollars;
	}

	public void payFromBlockedCoins(double coins) {
		blockedCoins -= coins;
	}

	public boolean checkWithdraw(double dollars) {
		return dollars <= this.dollars;
	}

	public boolean checkSelling(double coins) {
		return coins <= this.coins;
	}

	public boolean checkBlockedDollars(double dollars) {
		return dollars <= blockedDollars;
	}

	public boolean checkBlockedCoins(double coins) {
		return coins <= blockedCoins;
	}

	@Override
	public String toString() {
		return String.format("%.5f$ %.5fPQ", dollars + blockedDollars, coins + blockedCoins);
	}
	
}
