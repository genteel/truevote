package co.afrolabs.truevote.dto;

import java.io.Serializable;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;
import com.googlecode.objectify.annotation.Entity;

@Entity
public class CandidateManifesto implements Serializable{

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	@Id
    private Long id;
	private Blob file;
	private long candidateId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	

}
