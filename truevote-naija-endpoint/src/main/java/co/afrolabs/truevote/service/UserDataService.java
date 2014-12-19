package co.afrolabs.truevote.service;

import co.afrolabs.truevote.dao.UserDataDAO;
import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.dto.UserData;

public class UserDataService  {

	
	public void createUserData(UserData userData) throws Exception{
		UserDataDAO userDataDAO = new UserDataDAO();
		userDataDAO.put(userData);
	}
	
	public void editUserData(UserData userData) throws Exception{
		UserDataDAO userDataDAO = new UserDataDAO();
		UserData persistUserData =	userDataDAO.getByProperty("phoneNumber", userData.getPhoneNumber());
		if(persistUserData!=null && userData.getPhoneNumber()!=null){
			
			 userData.setId(persistUserData.getId());
			 userDataDAO.put(userData);
		}
		
	}
	
	
	public UserData findByPhoneNumber(String phoneNumber){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("phoneNumber",phoneNumber).get();
	 }
	public UserData findByIMEINumber(String emeiNumber){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("imeiNumber",emeiNumber).get();
	 }
	
	 
	 
	

}
