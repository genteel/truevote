package co.afrolabs.truevote.service;

import co.afrolabs.truevote.dao.UserDataDAO;
import co.afrolabs.truevote.dto.UserData;

public class UserDataService  {

	
	public void createUserData(UserData userData) throws Exception{
		UserDataDAO userDataDAO = new UserDataDAO();
		userDataDAO.put(userData);
	}
	
	public void editUserData(UserData userData) throws Exception{
		UserDataDAO userDataDAO = new UserDataDAO();
		UserData persistUserData =	userDataDAO.getByProperty("imeiNumber", userData.getImeiNumber());
		if(persistUserData!=null){
			 userData.setId(persistUserData.getId());
			 userDataDAO.put(userData);
		}
		
	}
	
	
	public UserData findByPhoneNumber(String phoneNumber){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("phoneNumber",phoneNumber).get();
	 }
	public UserData findByFacebookId(String facebookId){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("facebookId",facebookId).get();
	 }
	public UserData findByEmail(String email){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("email",email).get();
	 }
	public UserData findByGoogleId(String googleId){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("googleId",googleId).get();
	 }
	public UserData findByIMEINumber(String emeiNumber){
		 UserDataDAO userDataDAO = new UserDataDAO();
		 return  userDataDAO.ofy().query(UserData.class).filter("imeiNumber",emeiNumber).get();
	 }
	
	 
	 
	

}
