package fr.istic.service.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Provider implements IProvider {
	
	private Map<Integer, Integer> providerProducts;
	private Map<Integer, Double> providerPrices;
	
	public Provider() {
		this.providerPrices = new HashMap<Integer, Double>();
		this.providerProducts = new HashMap<Integer, Integer>();
		
		supplyProvider();
	}
		
	private void supplyProvider() {
		this.providerPrices.put(0, 5.99);
		this.providerPrices.put(1, 10.0);
		this.providerProducts.put(0, 5000);
		this.providerProducts.put(1, 3000);		
	}
	
	@Override
	public double getPrice(int productId) {
		return this.providerPrices.get(productId);
	}

	@Override
	public void order(Map<Integer, Integer> storeProducts, int productId, int productQuantity) {
		
		this.providerProducts.compute(productId, (key, value) -> value - productQuantity);
		storeProducts.compute(productId, (key, value) -> value + productQuantity);
	}
	
}
