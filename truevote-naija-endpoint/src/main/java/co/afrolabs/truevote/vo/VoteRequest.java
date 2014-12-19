package co.afrolabs.truevote.vo;


public class VoteRequest {

	private String phoneNumber;
	private String imeiNumber;
	private String userLongitude;
	private String userLatitude;
	private String votedPartyName;
	private boolean voted = false;
	private String others;
	
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

}
