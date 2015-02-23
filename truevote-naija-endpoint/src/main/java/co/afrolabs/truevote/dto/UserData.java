package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class UserData implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private Long id;
	private String phoneNumber;
	private String imeiNumber;
	private String userLongitude;
	private String userLatitude;
	private boolean voted = false;
	private String emailAddress;
	private String facebookId;
	private String googleId;
	private String others;
	private String emailSource;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	
	public String getEmailSource() {
		return emailSource;
	}
	public void setEmailSource(String emailSource) {
		this.emailSource = emailSource;
	}
	
}
