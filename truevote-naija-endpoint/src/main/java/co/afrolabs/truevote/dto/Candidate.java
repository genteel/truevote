package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import co.afrolabs.truevote.constant.PoliticalPosition;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class Candidate implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private Long id;
	private String firstName;
	private String lastName;
	private String otherNames;
	private String honors;
	// key is equivalent to id
	private long partyKey;
	private String politicalPosition;
	private long stateKey;
	private long constituencyKey;
	private long localGovernmentKey;
	private long senatorialDistrictKey;
	private String ward;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOtherNames() {
		return otherNames;
	}
	public void setOtherNames(String otherNames) {
		this.otherNames = otherNames;
	}
	public String getHonors() {
		return honors;
	}
	public void setHonors(String honors) {
		this.honors = honors;
	}
	public long getPartyKey() {
		return partyKey;
	}
	public void setPartyKey(long partyKey) {
		this.partyKey = partyKey;
	}
	public String getPosition() {
		return politicalPosition;
	}
	public void setPosition(String position) {
		this.politicalPosition = position;
	}
	
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public long getStateKey() {
		return stateKey;
	}
	public void setStateKey(long stateKey) {
		this.stateKey = stateKey;
	}
	public long getConstituencyKey() {
		return constituencyKey;
	}
	public void setConstituencyKey(long constituencyKey) {
		this.constituencyKey = constituencyKey;
	}
	public long getLocalGovernmentKey() {
		return localGovernmentKey;
	}
	public void setLocalGovernmentKey(long localGovernmentKey) {
		this.localGovernmentKey = localGovernmentKey;
	}
	public long getSenatorialDistrictKey() {
		return senatorialDistrictKey;
	}
	public void setSenatorialDistrictKey(long senatorialDistrictKey) {
		this.senatorialDistrictKey = senatorialDistrictKey;
	}
}
