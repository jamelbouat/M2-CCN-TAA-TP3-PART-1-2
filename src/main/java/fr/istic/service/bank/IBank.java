package fr.istic.service.bank;

public interface IBank {	
	
	void transferMoney(double amount, int clientAccountId, int storeAccountId);
}
