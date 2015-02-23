package co.afrolabs.truevote.vo;

import java.util.List;

import co.afrolabs.truevote.constant.ServerConstants;
import co.afrolabs.truevote.dto.Candidate;

public class VoteCandidates {
	private String responseCode= ServerConstants.RESPONSE_RESQUEST_ERROR;
	private List<Candidate> candidates;

	

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

}
