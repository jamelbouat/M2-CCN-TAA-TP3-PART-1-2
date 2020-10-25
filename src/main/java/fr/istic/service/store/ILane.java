package fr.istic.service.store;

public interface ILane {
	
	void addItemToCart(int productId, int quantity, int clientAccountId);
	void pay(int clientAccountId, int storeAccountId);
}
