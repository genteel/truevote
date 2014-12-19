package co.afrolabs.truevote.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.service.PoliticalPartyService;

public class WebContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		PoliticalPartyService partyService = new PoliticalPartyService();
		if (partyService.getAllParties() == null
				|| partyService.getAllParties().isEmpty()) {
			PoliticalParty party1 = new PoliticalParty();
			PoliticalParty party2 = new PoliticalParty();
			party1.setName("PDP");
			party1.setPartyCode(1);
			party2.setName("APC");
			party2.setPartyCode(2);
			try {
				partyService.createParty(party1);
				partyService.createParty(party2);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
