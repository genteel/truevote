package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class MyChoice implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private long id;
	 private long userId=0l;
	 private long presidentialCanId;
	 private long governorshipCanId;
	 private long senatorialCanid;
	 private long houseOfRepCanId;
	 private long chairmanshipCanId;
	 private long counselorshipCanId;
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPresidentialCanId() {
		return presidentialCanId;
	}
	public void setPresidentialCanId(long presidentialCanId) {
		this.presidentialCanId = presidentialCanId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getGovernorshipCanId() {
		return governorshipCanId;
	}
	public void setGovernorshipCanId(long governorshipCanId) {
		this.governorshipCanId = governorshipCanId;
	}
	public long getSenatorialCanid() {
		return senatorialCanid;
	}
	public void setSenatorialCanid(long senatorialCanid) {
		this.senatorialCanid = senatorialCanid;
	}
	public long getHouseOfRepCanId() {
		return houseOfRepCanId;
	}
	public void setHouseOfRepCanId(long houseOfRepCanId) {
		this.houseOfRepCanId = houseOfRepCanId;
	}
	public long getChairmanshipCanId() {
		return chairmanshipCanId;
	}
	public void setChairmanshipCanId(long chairmanshipCanId) {
		this.chairmanshipCanId = chairmanshipCanId;
	}
	public long getCounselorshipCanId() {
		return counselorshipCanId;
	}
	public void setCounselorshipCanId(long counselorshipCanId) {
		this.counselorshipCanId = counselorshipCanId;
	}
}
