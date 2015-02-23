package co.afrolabs.truevote.service;

import java.util.List;

import co.afrolabs.truevote.constant.PoliticalPosition;
import co.afrolabs.truevote.dao.CandidateDAO;
import co.afrolabs.truevote.dao.PoliticalPartyDAO;
import co.afrolabs.truevote.dto.Candidate;
import co.afrolabs.truevote.dto.Constituency;
import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.dto.SenatorialDistrict;

import com.googlecode.objectify.Key;

public class CandidateService {
	private CandidateDAO candidateDAO = new CandidateDAO();

	public void createCandidate(Candidate candidate) throws Exception {
		// creates only one candidate per position per political party
		if (checkCandidateStatus(candidate)) {
			throw new Exception("The " + candidate.getPosition()
					+ " Candidate for political party with Key= "
					+ candidate.getPartyKey() + " Already exists");
		}
		candidateDAO.put(candidate);
	}

	public void editCandidate(Candidate candidate) throws Exception {
		Candidate persistCandidate = findCandidateByPosition(
				candidate.getPartyKey(), candidate.getPosition());
		if (persistCandidate != null) {
			candidate.setId(persistCandidate.getId());
			candidateDAO.put(candidate);
		}
	}

	public Candidate findCandidateByPosition(String partyName,
			String politicalPosition) throws Exception {
		Candidate result;

		CandidateDAO candidateDAO = new CandidateDAO();
		PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
		result = candidateDAO
				.ofy()
				.query(Candidate.class)
				.filter("partyKey",
						politicalPartyDAO.getKey(politicalPartyDAO
								.getByProperty("name", partyName).getId()))
				.filter("politicalPosition", politicalPosition).get();
		return result;
	}

	public Candidate findCandidateByPosition(long partyKey,
			String politicalPosition) throws Exception {
		Candidate result;
		CandidateDAO candidateDAO = new CandidateDAO();
		result = candidateDAO.ofy().query(Candidate.class)
				.filter("id", partyKey)
				.filter("politicalPosition", politicalPosition).get();
		return result;
	}

	public Candidate findPartySenateCandidate(Candidate candidate)
			throws Exception {
		Candidate result;
		CandidateDAO candidateDAO = new CandidateDAO();
		result = candidateDAO
				.ofy()
				.query(Candidate.class)
				.filter("partyKey", candidate.getPartyKey())
				.filter("stateKey", candidate.getStateKey())
				.filter("senatorialDistrictKey",
						candidate.getSenatorialDistrictKey()).get();
		return result;
	}

	public Candidate findPartyHouseofRepCandidate(Candidate candidate)
			throws Exception {
		Candidate result;
		CandidateDAO candidateDAO = new CandidateDAO();
		result = candidateDAO.ofy().query(Candidate.class)
				.filter("partyKey", candidate.getPartyKey())
				.filter("stateKey", candidate.getStateKey())
				.filter("constituencyKey", candidate.getConstituencyKey())
				.get();
		return result;
	}

	public Candidate findPartyGovernorshipCandidate(Candidate candidate) throws Exception {
		Candidate result;
		CandidateDAO candidateDAO = new CandidateDAO();
		result = candidateDAO.ofy().query(Candidate.class)
				.filter("partyKey", candidate.getPartyKey())
				.filter("stateKey", candidate.getStateKey()).get();
		return result;
	}

	public List<Candidate> findALlCandidates() {
		return candidateDAO.listAll();
	}
/** 
 * returns true if candidate already exists
 * **/
	private boolean checkCandidateStatus(Candidate candidate) throws Exception {
		boolean status = false;
		PoliticalPosition politicalPos = PoliticalPosition.valueOf(candidate
				.getPosition());
		switch (politicalPos) {
		case PRESIDENT:
			if (findCandidateByPosition(candidate.getPartyKey(),
					candidate.getPosition()) != null) {
				status = true;
			}
			break;
		case GOVERNOR:
			if (findPartyGovernorshipCandidate(candidate) != null) {
				status = true;
			}
			break;
		case SENATE:
			if (findPartySenateCandidate(candidate) != null) {
				status = true;
			}
			break;
		case HOUSE_OF_REPS:
			if (findPartyHouseofRepCandidate(candidate) != null) {
				status = true;
			}
			break;
		case CHAIRMAN:
			break;
		case COUNCILLOR:
			break;
		}
		return status;
	}
}
