package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.api.server.spi.config.ApiTransformer;
import com.googlecode.objectify.Key;

@Entity
public class UserData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String phoneNumber;
	private String imeiNumber;
	private String userLongitude;
	private String userLatitude;
	 //@ApiTransformer(value = null)
	private Key<PoliticalParty> votedParty;
	
	private boolean voted = false;
	
	private String others;
	
	
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
	public Key<PoliticalParty> getVotedParty() {
		return votedParty;
	}
	public void setVotedParty(Key<PoliticalParty> votedParty) {
		this.votedParty = votedParty;
	}
	
}
