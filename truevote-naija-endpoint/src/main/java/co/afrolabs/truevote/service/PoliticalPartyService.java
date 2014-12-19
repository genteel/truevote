package co.afrolabs.truevote.service;

import java.util.List;

import co.afrolabs.truevote.dao.PoliticalPartyDAO;
import co.afrolabs.truevote.dao.UserDataDAO;
import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.dto.UserData;


public class PoliticalPartyService {
	
	public void createParty(PoliticalParty politicalParty) throws Exception {
		if (findByName(politicalParty.getName()) != null) {
			throw new Exception("Political Party already exists with name :"
					+ politicalParty.getName());
		}
		PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
		 politicalPartyDAO.put(politicalParty);
		
		//ofy().save().entity(politicalParty).now();
	}

	@SuppressWarnings("unchecked")
	public List<PoliticalParty> getAllParties() {
	PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
		return politicalPartyDAO.listAll();
	}

	public PoliticalParty findByName(String partyName) throws Exception {
		PoliticalParty result;
		
		PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
		result = politicalPartyDAO.getByProperty("name", partyName);
		return result;
}
	public long getTotalVotes(){
		 UserDataDAO userDataDAO = new UserDataDAO();
		  
		 return userDataDAO.ofy().query(UserData.class).filter("voted", true).count();
	 }
	 
	 public long getPartyTotalVotes(String partyName) throws Exception{
		 PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
		
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("votedParty",politicalPartyDAO.getKey(findByName(partyName).getId())).filter("voted", true).count();
	 }
}
