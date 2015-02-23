package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class PoliticalParty implements Serializable{
		/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private String name;
	private int partyCode;
	private long totalVotes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPartyCode() {
		return partyCode;
	}
	public void setPartyCode(int partyCode) {
		this.partyCode = partyCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getTotalVotes() {
		return totalVotes;
	}
	public void setTotalVotes(long totalVotes) {
		this.totalVotes = totalVotes;
	}
	
}
