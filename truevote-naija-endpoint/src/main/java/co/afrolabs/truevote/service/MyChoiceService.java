package co.afrolabs.truevote.service;

import java.util.ArrayList;
import java.util.List;

import co.afrolabs.truevote.constant.PoliticalPosition;
import co.afrolabs.truevote.dao.CandidateDAO;
import co.afrolabs.truevote.dao.MyChoiceDAO;
import co.afrolabs.truevote.dto.Candidate;
import co.afrolabs.truevote.dto.MyChoice;

public class MyChoiceService {
	private MyChoiceDAO myChoiceDAO = new MyChoiceDAO();
	private CandidateService canService = new CandidateService();

	public void createUserChoice(MyChoice myChoice) throws Exception {
		if (myChoice.getUserId() <= 0) {
			throw new Exception("UserId is = " + myChoice.getUserId()
					+ ". A choice must belong to a User");
		}
		myChoiceDAO.put(myChoice);
	}

	public void editUserChoice(MyChoice myChoice) throws Exception {
		MyChoice persistUserChoice = myChoiceDAO.getByProperty("userId",
				myChoice.getUserId());
		if (persistUserChoice != null && myChoice.getUserId() > 0) {
			myChoice.setId(persistUserChoice.getId());
			myChoiceDAO.put(myChoice);
		}
	}

	public MyChoice findMyChoice(long userId, long candidateId) {
		return myChoiceDAO
				.ofy()
				.query(MyChoice.class)
				.filter(getMyChoiceFieldByCandidateId(candidateId), candidateId)
				.filter("userId", userId).get();
	}

	public List<Candidate> findMyChoiceCandidatesByEmailAddress(
			String emailAddress) {
		UserDataService userService = new UserDataService();
		return findMyChoiceCandidates(userService.findByEmail(emailAddress)
				.getId());
	}

	public List<Candidate> findMyChoiceCandidates(long userId) {

		List<Candidate> choiceCandidates = new ArrayList<Candidate>();
		CandidateDAO service = new CandidateDAO();
		MyChoice choice = myChoiceDAO.ofy().query(MyChoice.class)
				.filter("userId", userId).get();
		choiceCandidates.add(service.get(choice.getPresidentialCanId()));
		choiceCandidates.add(service.get(choice.getGovernorshipCanId()));
		choiceCandidates.add(service.get(choice.getSenatorialCanid()));
		choiceCandidates.add(service.get(choice.getChairmanshipCanId()));
		choiceCandidates.add(service.get(choice.getHouseOfRepCanId()));
		choiceCandidates.add(service.get(choice.getCounselorshipCanId()));
		return choiceCandidates;
	}

	public long getPartyTotalVotesByPoliticalPositon(String partyName,
			String politicalPosition) throws Exception {

		return myChoiceDAO
				.ofy()
				.query(MyChoice.class)
				.filter(getMyChoiceFieldByPosition(politicalPosition),
						canService.findCandidateByPosition(partyName,
								politicalPosition).getId()).count();
	}

	private String getMyChoiceFieldByCandidateId(long canId) {
		CandidateDAO cand = new CandidateDAO();
		return getMyChoiceFieldByPosition(cand.get(canId).getPosition());
	}

	private String getMyChoiceFieldByPosition(String politicalPosition) {
		PoliticalPosition politicalPos = PoliticalPosition
				.valueOf(politicalPosition);
		switch (politicalPos) {
		case PRESIDENT:
			return "presidentialCanId";
		case GOVERNOR:
			return "governorshipCanId";
		case SENATE:
			return "senatorialCanid";
		case HOUSE_OF_REPS:
			return "houseOfRepCanId";
		case CHAIRMAN:
			return "chairmanshipCanId";
		case COUNCILLOR:
			return "counselorshipCanId";

		default:
			return null;
		}
	}
}
