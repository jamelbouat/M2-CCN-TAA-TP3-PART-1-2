package fr.istic.service.provider;

import java.util.Map;

public interface IProvider {
	
	double getPrice(int productId);
	void order(Map<Integer, Integer> storeProducts, int productId, int productQuantity);
}
