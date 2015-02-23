package co.afrolabs.truevote.vo;

import co.afrolabs.truevote.constant.PoliticalPosition;

public class RequestObject {

	private String phoneNumber;
	private String imeiNumber;
	private String userLongitude;
	private String userLatitude;
	private String emailAddress;
	private String facebookId;
	private String googleId;
	private String requestType;
	private String clientID;
	private String message;
	private String votedPartyName;
	private PoliticalPosition politicalPosition;
	private String others;
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String requestSource) {
		this.clientID = requestSource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

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

	public String getGoogleId() {
		return googleId;
	}

	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

}
