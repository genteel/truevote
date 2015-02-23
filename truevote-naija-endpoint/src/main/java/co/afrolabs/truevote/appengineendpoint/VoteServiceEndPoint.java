package co.afrolabs.truevote.appengineendpoint;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import co.afrolabs.truevote.constant.PoliticalPosition;
import co.afrolabs.truevote.constant.ServerConstants;
import co.afrolabs.truevote.dto.Candidate;
import co.afrolabs.truevote.dto.MyChoice;
import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.dto.UserData;
import co.afrolabs.truevote.service.CandidateService;
import co.afrolabs.truevote.service.MyChoiceService;
import co.afrolabs.truevote.service.PoliticalPartyService;
import co.afrolabs.truevote.service.UserDataService;
import co.afrolabs.truevote.util.LocalResourceService;
import co.afrolabs.truevote.vo.RequestObject;
import co.afrolabs.truevote.vo.ResponseObject;
import co.afrolabs.truevote.vo.VoteCandidates;
import co.afrolabs.truevote.vo.VoteRequest;
import co.afrolabs.truevote.vo.VoteResponse;
import co.afrolabs.truevote.vo.VoteStatisticsResponse;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.users.User;

@Api(name = "trueVoteService", version = "v3", scopes = { ServerConstants.EMAIL_SCOPE }, clientIds = {
		ServerConstants.WEB_CLIENT_ID, ServerConstants.ANDROID_CLIENT_ID,
		ServerConstants.IOS_CLIENT_ID }, audiences = { ServerConstants.ANDROID_AUDIENCE })
public class VoteServiceEndPoint {

	UserDataService userService = new UserDataService();
	CandidateService canService = new CandidateService();
	MyChoiceService choiceService = new MyChoiceService();

	@ApiMethod(name = "admin.login", httpMethod = "post", path = "login/authed")
	public ResponseObject AdminLogin(User user) {
		ResponseObject response = new ResponseObject();
		System.out.println(">>> User= "+user);
		if(user!=null){
			if(authAdmin( user.getEmail())){
				response.setResponseCode(ServerConstants.RESPONSE_SUCCESS);	
				response.setMessage("login authentication succesful");
			}else{
				response.setMessage("Unknown User");
				response.setResponseCode(ServerConstants.RESPONSE_LOGIN_FAILED);
			}	
		}else{
			response.setMessage("Unknown User");
			response.setResponseCode(ServerConstants.RESPONSE_LOGIN_FAILED);
		}
		return response;
	}

	@ApiMethod(name = "user.vote", httpMethod = "post", path = "vote")
	public VoteResponse recordVote(RequestObject request) {
		VoteResponse response = new VoteResponse();
		if (request.getRequestType().equalsIgnoreCase(
				ServerConstants.REQUEST_VOTE_STATISTICS)
				&& authClient(request.getClientID())) {

			UserData user = checkUserStatus(request);
			if (user.getId() > 0) {
				if (request.getVotedPartyName() != null
						&& request.getPoliticalPosition() != null) {
					try {
						setChoiceCandidate(request, user.getId());
						user.setVoted(true);
						user.setEmailAddress(request.getEmailAddress());
						user.setPhoneNumber(request.getPhoneNumber());
						user.setOthers(request.getOthers());
						if (request.getGoogleId() != null) {
							user.setGoogleId(request.getGoogleId());
							user.setEmailSource("GOOGLE");
						} else {
							user.setFacebookId(request.getFacebookId());
							user.setEmailSource("FACEBOOK");
						}
						userService.editUserData(user);
						response.setMessage("success");
						response.setResponseCode(ServerConstants.RESPONSE_SUCCESS);
						System.out
								.println("log B >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

					} catch (Exception e) {
						response.setResponseCode(ServerConstants.RESPONSE_FAILED);
						e.printStackTrace();
					}
					
				}
			} else {// create new user
				try {
					user.setImeiNumber(request.getImeiNumber());
					user.setPhoneNumber(request.getPhoneNumber());
					user.setOthers(request.getOthers());
					if (request.getGoogleId() != null) {
						user.setGoogleId(request.getGoogleId());
						user.setEmailSource("GOOGLE");
					} else {
						user.setFacebookId(request.getFacebookId());
						user.setEmailSource("FACEBOOK");
					}
					userService.createUserData(user);
					response.setResponseCode(ServerConstants.RESPONSE_SUCCESS);
					if (request.getVotedPartyName() != null
							&& request.getPoliticalPosition() != null) {
						setChoiceCandidate(request,
								userService.findByEmail(user.getEmailAddress())
										.getId());
						user.setVoted(true);
						userService.editUserData(user);

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					response.setResponseCode(ServerConstants.RESPONSE_FAILED);
					e.printStackTrace();
				}
			}
			response.setMessage("success");
		}
		return response;
	}

	private void setChoiceCandidate(RequestObject request, long userId) {

		Candidate candidate = null;
		try {
			candidate = canService.findCandidateByPosition(request
					.getVotedPartyName(), request.getPoliticalPosition()
					.toString());

			MyChoice userChoice = choiceService.findMyChoice(userId,
					candidate.getId());
			if (userChoice == null) {
				userChoice = new MyChoice();
			}
			userChoice.setUserId(userId);

			switch (PoliticalPosition.valueOf(PoliticalPosition.class,
					candidate.getPosition())) {
			case PRESIDENT:
				userChoice.setPresidentialCanId(candidate.getId());
				break;
			case SENATE:
				userChoice.setSenatorialCanid(candidate.getId());
				break;
			case HOUSE_OF_REPS:
				userChoice.setHouseOfRepCanId(candidate.getId());
				break;
			case GOVERNOR:
				userChoice.setGovernorshipCanId(candidate.getId());
				break;
			case CHAIRMAN:
				userChoice.setChairmanshipCanId(candidate.getId());
				break;
			case COUNCILLOR:
				userChoice.setCounselorshipCanId(candidate.getId());
				break;
			}
			choiceService.createUserChoice(userChoice);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private UserData checkUserStatus(VoteRequest request) {
		UserData user = new UserData();
		if (request.getEmailAddress() != null) {
			user = userService.findByEmail(request.getEmailAddress());
		}
		return user;
	}

	private UserData checkUserStatus(RequestObject request) {
		UserData user = new UserData();
		if (request.getEmailAddress() != null) {
			user = userService.findByEmail(request.getEmailAddress());
		}
		return user;
	}

	@ApiMethod(name = "user.Status", httpMethod = "post")
	public VoteResponse CheckVoteStatus(VoteRequest request) {
		VoteResponse response = new VoteResponse();
		response.setMessage("false");
		UserData user = checkUserStatus(request);
		if (user != null) {
			if (user.isVoted()) {
				response.setMessage("true");
			}
		}

		response.setResponseCode("200");
		return response;
	}

	@ApiMethod(name = "user.vote.statistics", httpMethod = "post", path = "stat")
	public VoteStatisticsResponse getVoteStatistics(RequestObject request) {
		VoteStatisticsResponse response = new VoteStatisticsResponse();
		if (request.getRequestType().equalsIgnoreCase(
				ServerConstants.REQUEST_VOTE_STATISTICS)
				&& authClient(request.getClientID())) {
			PoliticalPartyService partyService = new PoliticalPartyService();
			MyChoiceService choiceService = new MyChoiceService();
			List<PoliticalParty> parties = new ArrayList<PoliticalParty>();
			try {
				for (PoliticalParty p : partyService.getAllParties()) {
					p.setTotalVotes(choiceService
							.getPartyTotalVotesByPoliticalPositon(p.getName(),
									request.getMessage()));
					parties.add(p);
				}
				response.setResponseCode(ServerConstants.RESPONSE_SUCCESS);
			} catch (Exception e) {
				response.setResponseCode(ServerConstants.RESPONSE_FAILED);
				e.printStackTrace();
			}

			response.setPoliticalParties(parties);
		}
		return response;
	}

	@ApiMethod(name = "admin.candidates", httpMethod = "post", path = "cand")
	public VoteCandidates getCandidates(RequestObject request) {
		VoteCandidates candidates = new VoteCandidates();
		if (request.getRequestType().equals(
				ServerConstants.REQUEST_GET_CANDIDATES)
				&& authClient(request.getClientID())) {
			candidates.setCandidates(canService.findALlCandidates());
			candidates.setResponseCode(ServerConstants.RESPONSE_SUCCESS);
		}
		return candidates;
	}

	@ApiMethod(name = "user.choice", httpMethod = "post")
	public VoteCandidates getUserChoices(RequestObject request) {
		UserData user = checkUserStatus(request);
		VoteCandidates candidates = new VoteCandidates();
		if (request.getRequestType().equals(
				ServerConstants.REQUEST_GET_USER_CHOICE)
				&& authClient(request.getClientID())) {
			// user must be registered before checking for candidates
			if (recordVote(request).getResponseCode().equalsIgnoreCase(
					ServerConstants.RESPONSE_SUCCESS)) {
				candidates.setCandidates(choiceService
						.findMyChoiceCandidatesByEmailAddress(request
								.getEmailAddress()));
				candidates.setResponseCode(ServerConstants.RESPONSE_SUCCESS);

			}

		}
		return candidates;
	}

	@ApiMethod(name = "user.manifesto", httpMethod = "post", path = "mani")
	public VoteCandidates getCandidatManifesto(RequestObject request) {
		VoteCandidates candidates = new VoteCandidates();
		if (request.getRequestType().equals(
				ServerConstants.REQUEST_GET_USER_CHOICE)
				&& authClient(request.getClientID())) {
			// user must be registered before checking for candidates
			if (recordVote(request).getResponseCode().equalsIgnoreCase(
					ServerConstants.RESPONSE_SUCCESS)) {

				candidates.setCandidates(canService.findALlCandidates());

				candidates.setResponseCode(ServerConstants.RESPONSE_SUCCESS);
			}

		}

		return candidates;
	}

	@ApiMethod(name = "user.iReport", httpMethod = "post", path = "irep")
	public VoteCandidates sendIreport(RequestObject request) {
		VoteCandidates candidates = new VoteCandidates();
		if (request.getRequestType().equals(
				ServerConstants.REQUEST_GET_USER_CHOICE)
				&& authClient(request.getClientID())) {
			// user must be registered before checking for candidates
			if (recordVote(request).getResponseCode().equalsIgnoreCase(
					ServerConstants.RESPONSE_SUCCESS)) {
			//new 	StringInputStream()
			//	FileUtils.readFileToByteArray(new File(uri));
				candidates.setCandidates(canService.findALlCandidates());

				candidates.setResponseCode(ServerConstants.RESPONSE_SUCCESS);
			}

		}
		return candidates;
	}

	private boolean authClient(String clientId) {
		return authClientId(clientId);
	}
	
	private boolean authAdmin(String emailAddress){
		String emailAdds=LocalResourceService.getResource("admin.emails");
	List<String> emailAddresses = Arrays.asList(emailAdds.split(","));
		return emailAddresses.contains(emailAddress);	
	}
	private boolean authClientId(String clientId){
		String emailAdds=LocalResourceService.getResource("clientIds");
	List<String> emailAddresses = Arrays.asList(emailAdds.split(","));
		return emailAddresses.contains(clientId);	
	}

}
