package fr.istic.service.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import fr.istic.service.store.IFastLane;
import fr.istic.service.store.IJustHaveALook;
import fr.istic.service.store.ILane;

@Component
public class Client implements IRun, IClient {
	
	@Autowired
	IFastLane ifastlane;
	@Autowired
	ILane ilane;
	@Autowired
	IJustHaveALook justHaveALook;

	@Value("${clientAddress}")
	private String clientAddress;
	
	@Value("${storeAccountId}")
	private Integer storeAccountId;
	
	@Value("${clientAccountId}")
	private Integer clientAccountId;
	
	public void run() {
		// referenceAProduct(productId, quantity)
		referenceAProduct(0, 1);
		
		// this.ifastlane.oneShotOrder(productId, quantity, clientAccountId)
		this.ifastlane.oneShotOrder(0, 5, clientAccountId, storeAccountId);

		// this.ilane.addItemToCart(productId, quantity, clientAccountId);
		this.ilane.addItemToCart(0, 10, clientAccountId);
		this.ilane.addItemToCart(1, 10, clientAccountId);
		this.ilane.pay(clientAccountId, storeAccountId);
		
	}

	public void referenceAProduct(int productId, int quantity) {
		if (this.justHaveALook.isAvailable(productId, quantity)) {
			this.justHaveALook.getPrice(productId);
		}
			
	}

}
