
package elements;


public class BuyingOrder extends Order implements Comparable<BuyingOrder> {
	

	public BuyingOrder(int traderID, double amount, double price) {
		super(traderID, amount, price);
	}

	@Override
	public int compareTo(BuyingOrder e) {
		if(price > e.price)
			return -1;
		if(price < e.price)
			return 1;
		if(amount > e.amount)
			return -1;
		if(amount < e.amount)
			return 1;
		if(traderID < e.traderID)
			return -1;
		if(traderID > e.traderID)
			return 1;
		return 0;
	}
	
}
