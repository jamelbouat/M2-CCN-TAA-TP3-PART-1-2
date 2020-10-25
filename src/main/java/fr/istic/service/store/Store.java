package fr.istic.service.store;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.istic.service.bank.IBank;
import fr.istic.service.provider.IProvider;

@Component
public class Store implements IFastLane, ILane, IJustHaveALook, IStore {
	
	@Autowired
	IBank ibank;
	@Autowired
	IProvider iProvider;
	
	private Map<Integer, Integer> storeProducts;
	private Map<Integer, Double> storePrices;
	private Map<Integer, Integer> cart;
	
	public Store() {
		this.storePrices = new HashMap<Integer, Double>();
		this.storeProducts = new HashMap<Integer, Integer>();
		this.cart = new HashMap<Integer, Integer>();
		
		supplyStore();
	}
	
	private void supplyStore() {
		this.storePrices.put(0, 10.99);
		this.storePrices.put(1, 20.0);
		this.storeProducts.put(0, 50);
		this.storeProducts.put(1, 30);		
	}
	
	@Override
	public double getPrice(int productId) {
		return this.storePrices.get(productId);
	}
	
	@Override
	public boolean isAvailable(int productId, int quantity) {
		return this.storeProducts.get(productId) >= quantity;
	}
	
	@Override
	public void addItemToCart(int productId, int quantity, int clientAccountId) {
		if (!isAvailable(productId, quantity)) {
			return;
		}
		if (this.cart.containsKey(productId)) {
			this.cart.compute(productId, (key, value) -> value + cart.get(productId));
			return;
		}
		this.cart.put(productId, quantity);
	}
	
	@Override
	public void pay(int clientAccountId, int storeAccountId) {
		double  totalPrice = 0.0;
		
		for (int productId : this.cart.keySet()) {
			totalPrice += cart.get(productId) * getPrice(productId);
		}
		
		this.ibank.transferMoney(totalPrice, clientAccountId, storeAccountId);
		
		for (int productId : this.cart.keySet()) {
			this.storeProducts.compute(productId, (key, value) -> value - cart.get(productId));
			
			if (cart.get(productId) < 10) {
				this.iProvider.order(storeProducts, productId, 50);
			}
		}
		
		this.cart.clear();
	}
	
	@Override
	public void oneShotOrder(int productId, int quantity, int clientAccountId, int storeAccountId) {
		this.addItemToCart(productId, quantity, clientAccountId);
		this.pay(clientAccountId, storeAccountId);
	}

}
