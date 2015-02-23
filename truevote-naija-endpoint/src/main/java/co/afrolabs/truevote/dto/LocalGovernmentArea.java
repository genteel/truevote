package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class LocalGovernmentArea implements Serializable {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	private long stateId;
	private String name;

	public LocalGovernmentArea() {
	}

	public LocalGovernmentArea(String name, long stateId) {
		this.name = name;
		this.stateId = stateId;
	}
	public LocalGovernmentArea(String name) {
		this.name = name;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStateId() {
		return stateId;
	}

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
