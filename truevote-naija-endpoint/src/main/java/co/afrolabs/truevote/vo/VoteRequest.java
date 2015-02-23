package co.afrolabs.truevote.vo;

import co.afrolabs.truevote.constant.PoliticalPosition;


public class VoteRequest {

	private String phoneNumber;
	private String imeiNumber;
	private String userLongitude;
	private String userLatitude;
	private String votedPartyName;
	private PoliticalPosition politicalPosition;
	private String emailAddress;
	private String facebookId;
	private String googleId;
	private boolean voted = false;
	private String others;
	private String clientID;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getImeiNumber() {
		return imeiNumber;
	}
	public void setImeiNumber(String imeiNumber) {
		this.imeiNumber = imeiNumber;
	}
	public String getUserLongitude() {
		return userLongitude;
	}
	public void setUserLongitude(String userLongitude) {
		this.userLongitude = userLongitude;
	}
	public String getUserLatitude() {
		return userLatitude;
	}
	public void setUserLatitude(String userLatitude) {
		this.userLatitude = userLatitude;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	
	public boolean isVoted() {
		return voted;
	}
	public void setVoted(boolean voted) {
		this.voted = voted;
	}
	public String getVotedPartyName() {
		return votedPartyName;
	}
	public void setVotedPartyName(String votedPartyName) {
		this.votedPartyName = votedPartyName;
	}
	public PoliticalPosition getPoliticalPosition() {
		return politicalPosition;
	}
	public void setPoliticalPosition(PoliticalPosition politicalPosition) {
		this.politicalPosition = politicalPosition;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getGoogleId() {
		return googleId;
	}
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	

}
