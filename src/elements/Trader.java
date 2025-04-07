
package elements;

public class Trader {

	private int id;

	private Wallet wallet;

	public static int numberOfUsers = 0;

	public Trader(double dollars, double coins) {
		id = numberOfUsers;
		numberOfUsers++;
		wallet = new Wallet(dollars, coins);
	}

	public int sell(double amount, double price, Market market) {
		final boolean flag = wallet.checkBlockedCoins(amount) || id == 0;
		
		if(flag) {
			wallet.payFromBlockedCoins(amount);
			wallet.depositDollars(amount * price * (1.0 - (double)market.getFee() / 1000.0));
		}
		
		return flag ? 1 : 0;
	}

	public int buy(double amount, double price, Market market) {
		final double dollars = amount * price;
		final boolean flag = wallet.checkBlockedDollars(dollars) || id == 0;
		
		if(flag) {
			wallet.payFromBlockedDollars(dollars);
			wallet.depositCoins(amount);
		}
		
		return flag ? 1 : 0;
	}

	public Wallet getWallet() {
		return wallet;
	}

	@Override
	public String toString() {
		return String.format("Trader %d: %s", id, wallet.toString());
	}
	
}
