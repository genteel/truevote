package co.afrolabs.truevote.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import co.afrolabs.truevote.constant.StateEnum;
import co.afrolabs.truevote.dao.StateDAO;
import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.dto.State;
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
		createStates();
	}

	private void createStates() {
		StateDAO stateService = new StateDAO();
		State newState;
		if (stateService.listAll() == null || stateService.listAll().isEmpty()) {
			System.out.println(">>>>>>>>> "+StateEnum.values().length);
			for (StateEnum state : StateEnum.values()) {
				newState  = new State();
				newState.setName(state.toString().toUpperCase().replaceAll("_", " "));
				stateService.put(newState);
			}
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}

}
