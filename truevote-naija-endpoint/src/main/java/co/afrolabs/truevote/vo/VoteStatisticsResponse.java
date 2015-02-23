package co.afrolabs.truevote.vo;

import java.util.List;

import co.afrolabs.truevote.constant.ServerConstants;
import co.afrolabs.truevote.dto.PoliticalParty;

public class VoteStatisticsResponse {
	private String responseCode=ServerConstants.RESPONSE_RESQUEST_ERROR;;
	private String politicalPosition;
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

	public String getPoliticalPosition() {
		return politicalPosition;
	}

	public void setPoliticalPosition(String politicalPosition) {
		this.politicalPosition = politicalPosition;
	}

}
