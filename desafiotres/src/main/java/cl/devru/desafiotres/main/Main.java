package cl.devru.desafiotres.main;

import cl.devru.desafiotres.business.BusinessLogic;

public class Main {

	public static void main(String[] args) {
		BusinessLogic business = new BusinessLogic();
		System.out.println("********************************* Init **********************************");
		if(business.initData()) {
			System.out.println("Data init Ok");
			if(business.formatedData()) {
				System.out.println("Data Formatted OK");
				if(business.completeData()) {
					System.out.println("Data Complete OK");
				}
			}
		}
		System.out.println("********************************* Fin ***********************************");		

	}

}
