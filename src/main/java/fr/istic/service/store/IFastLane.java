package fr.istic.service.store;

public interface IFastLane {

	void oneShotOrder(int productId, int quantity, int clientAccountId, int storeAccountId);
}
