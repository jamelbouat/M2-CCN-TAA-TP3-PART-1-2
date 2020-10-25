package fr.istic.service.store;

public interface IJustHaveALook {
	
	double getPrice(int productId);
	boolean isAvailable(int productId, int quantity);
}
