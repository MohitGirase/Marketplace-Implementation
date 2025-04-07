
package elements;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Market {
	

	private PriorityQueue<SellingOrder> sellingOrders;

	private PriorityQueue<BuyingOrder> buyingOrders;

	private ArrayList<Transaction> transactions;

	private int fee;

	private int no_of_successful_transactions;

	private ArrayList<Trader> traders;

	public Market(int fee) {
		sellingOrders = new PriorityQueue<SellingOrder>();
		buyingOrders = new PriorityQueue<BuyingOrder>();
		transactions = new ArrayList<Transaction>();
		
		this.fee = fee;
		no_of_successful_transactions = 0;
		traders = new ArrayList<Trader>();
	}
	

	public void giveSellOrder(SellingOrder order) {
		sellingOrders.add(order);
	}

	public void giveBuyOrder(BuyingOrder order) {
		buyingOrders.add(order);
	}

	public void makeOpenMarketOperation(double price) {
		while(buyingOrderPrice() >= price && !buyingOrders.isEmpty()) {
			BuyingOrder b_order = buyingOrders.peek();
			giveSellOrder(new SellingOrder(0, b_order.getAmount(), b_order.getPrice()));
			checkTransactions(traders);
		}
		while(sellingOrderPrice() <= price && !sellingOrders.isEmpty()) {
			SellingOrder s_order = sellingOrders.peek();
			giveBuyOrder(new BuyingOrder(0, s_order.getAmount(), s_order.getPrice()));
			checkTransactions(traders);
		}
	}

	public void checkTransactions(ArrayList<Trader> traders) {
		while(buyingOrderPrice() >= sellingOrderPrice() && !buyingOrders.isEmpty() && !sellingOrders.isEmpty()) {
			SellingOrder s_order = sellingOrders.poll();
			BuyingOrder b_order = buyingOrders.poll();
			
			double traded_amount;
			if(s_order.amount > b_order.amount) {
				traded_amount = b_order.amount;
				sellingOrders.add(new SellingOrder(s_order.traderID, s_order.price, s_order.amount - traded_amount));
			}
			else if(s_order.amount < b_order.amount) {
				traded_amount = s_order.amount;
				buyingOrders.add(new BuyingOrder(b_order.traderID, b_order.price, b_order.amount - traded_amount));
			}
			else
				traded_amount = b_order.amount;
			
			int sell_flag = traders.get(s_order.traderID).sell(traded_amount, s_order.price, this);
			int buy_flag = traders.get(b_order.traderID).buy(traded_amount, s_order.price, this);
			
			if(sell_flag == 1 && buy_flag == 1)
				no_of_successful_transactions++;
			
			if(b_order.price > s_order.price)
				traders.get(b_order.traderID).getWallet().returnDollars((b_order.price - s_order.price) * traded_amount);
			
			transactions.add(new Transaction(s_order, b_order));
		}
		
	}

	public PriorityQueue<SellingOrder> getSellingOrders() {
		return sellingOrders;
	}

	public PriorityQueue<BuyingOrder> getBuyingOrders() {
		return buyingOrders;
	}

	public int getFee() {
		return fee;
	}

	public void setTraders(ArrayList<Trader> traders) {
		this.traders = traders;
	}

	public double buyingOrderPrice() {
		return buyingOrders.isEmpty() ? 0.0 : buyingOrders.peek().getPrice();
	}

	public double sellingOrderPrice() {
		return sellingOrders.isEmpty() ? 0.0 : sellingOrders.peek().getPrice();
	}

	public String marketSizeInfo() {
		double dollars_sum = 0.0, coins_sum = 0.0;
		for(BuyingOrder b_order : buyingOrders)
			dollars_sum += b_order.getDollars();
		for(SellingOrder s_order : sellingOrders)
			coins_sum += s_order.getAmount();
		return String.format("Current market size: %.5f %.5f", dollars_sum, coins_sum);
	}

	public String currentPriceInfo() {
		final double buying_order_price = buyingOrderPrice();
		final double selling_order_price = sellingOrderPrice();
		double average_price;
		
		boolean flag1 = buyingOrders.isEmpty();
		boolean flag2 = sellingOrders.isEmpty();
		
		if(flag1 && flag2)
			average_price = 0.0;
		else if(!flag1 && flag2)
			average_price = buying_order_price;
		else if(flag1 && !flag2)
			average_price = selling_order_price;
		else
			average_price = (buying_order_price + selling_order_price) / 2.0;
		
		return String.format("Current prices: %.5f %.5f %.5f", buying_order_price, selling_order_price, average_price);
	}

	public String transactionNumberInfo() {
		return "Number of successful transactions: " + no_of_successful_transactions;
	}
	
}
