package fr.istic.service.bank;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Bank implements IBank {
		
	private Map<Integer, Double> accounts;
	
	public Bank() {
		this.accounts = new HashMap<Integer, Double>();
		supplyAccounts();
	}
	
	private void supplyAccounts() {
		this.accounts.put(0, 5000.0);
		this.accounts.put(1, 0.0);
	}	

	public void transferMoney(double amount, int clientAccountId, int storeAccountId) {
		this.accounts.compute(clientAccountId, (key, value) -> value - amount);
		this.accounts.compute(storeAccountId, (key, value) -> value + amount);
	}

}
 