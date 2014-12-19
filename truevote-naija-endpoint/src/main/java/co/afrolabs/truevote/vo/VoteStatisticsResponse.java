package co.afrolabs.truevote.vo;

import java.util.List;

import co.afrolabs.truevote.dto.PoliticalParty;

public class VoteStatisticsResponse {
	private String responseCode;
	private List<PoliticalParty> PoliticalParties;

	public List<PoliticalParty> getPoliticalParties() {
		return PoliticalParties;
	}

	public void setPoliticalParties(List<PoliticalParty> politicalParties) {
		PoliticalParties = politicalParties;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

}
