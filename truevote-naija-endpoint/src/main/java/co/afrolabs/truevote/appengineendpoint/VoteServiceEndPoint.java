package co.afrolabs.truevote.appengineendpoint;

import java.util.ArrayList;
import java.util.List;

import co.afrolabs.truevote.constant.ServerConstants;
import co.afrolabs.truevote.dao.PoliticalPartyDAO;
import co.afrolabs.truevote.dto.PoliticalParty;
import co.afrolabs.truevote.dto.UserData;
import co.afrolabs.truevote.service.PoliticalPartyService;
import co.afrolabs.truevote.service.UserDataService;
import co.afrolabs.truevote.vo.RequestObject;
import co.afrolabs.truevote.vo.VoteRequest;
import co.afrolabs.truevote.vo.VoteResponse;
import co.afrolabs.truevote.vo.VoteStatisticsResponse;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.users.User;

@Api(name = "trueVoteService", version = "v1", scopes = { ServerConstants.EMAIL_SCOPE }, clientIds = {
		ServerConstants.WEB_CLIENT_ID, ServerConstants.ANDROID_CLIENT_ID,
		ServerConstants.IOS_CLIENT_ID }, audiences = { ServerConstants.ANDROID_AUDIENCE })
public class VoteServiceEndPoint {

	@ApiMethod(name = "vote.authed", httpMethod = "post", path = "vote/authed")
	public VoteResponse authedVote(VoteRequest request, User user) {
		VoteResponse response = new VoteResponse();
		response.setMessage("failed");
		response.setResponseCode("404");
		return response;
	}

	@ApiMethod(name = "record.vote", httpMethod = "post", path = "vote")
	public VoteResponse recordVote(VoteRequest request) {
		VoteResponse response = new VoteResponse();
		UserDataService userService = new UserDataService();
		PoliticalPartyService partyService = new PoliticalPartyService();
		UserData user = new UserData();
		// vote only when imei number and party is present
		if (userService.findByIMEINumber(request.getImeiNumber()) != null
				&& request.getVotedPartyName() != null) {
			try {
				PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
				PoliticalParty politicalParty = partyService.findByName(request
						.getVotedPartyName());
				user = userService.findByIMEINumber(request.getImeiNumber());
				user.setVotedParty(politicalPartyDAO.getKey(politicalParty
						.getId()));
				System.out.println("log A >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				user.setVoted(true);
				userService.createUserData(user);
				System.out.println("log B >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {// create new user
			try {
				PoliticalPartyDAO politicalPartyDAO = new PoliticalPartyDAO();
				PoliticalParty politicalParty = partyService.findByName(request
						.getVotedPartyName());
				if(politicalParty!=null){
					user.setVotedParty(politicalPartyDAO.getKey(politicalParty
							.getId()));
					user.setVoted(true);
				}
				user.setImeiNumber(request.getImeiNumber());
				user.setPhoneNumber(request.getPhoneNumber());
				userService.createUserData(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.setMessage("Success");
		return response;
	}
	@ApiMethod(name = "Vote.Status", httpMethod = "post")
	public VoteResponse checkVoteStatus(VoteRequest request) {
		VoteResponse response = new VoteResponse();
		UserDataService userService = new UserDataService();
		if (userService.findByIMEINumber(request.getImeiNumber()).isVoted()) {
			response.setMessage("true");
		} else {
			response.setMessage("false");
		}
		response.setResponseCode("200");
		return response;
	}

	@ApiMethod(name = "vote.statistics", httpMethod = "post", path = "stat")
	public VoteStatisticsResponse getVoteStatistics(RequestObject request) {
		VoteStatisticsResponse response = new VoteStatisticsResponse();
		PoliticalPartyService partyService = new PoliticalPartyService();
		PoliticalPartyService userService = new PoliticalPartyService();
		List<PoliticalParty> parties = new ArrayList<PoliticalParty>();
		for (PoliticalParty p : partyService.getAllParties()) {
			try {
				p.setTotalVotes(userService.getPartyTotalVotes(p.getName()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			parties.add(p);
		}
		response.setResponseCode("400");
		response.setPoliticalParties(parties);
		return response;
	}

}
