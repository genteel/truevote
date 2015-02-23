package co.afrolabs.truevote.service;

import java.util.List;
import java.util.Map;

import co.afrolabs.truevote.dao.ConstituencyDAO;
import co.afrolabs.truevote.dao.LocalGovernmentAreaDAO;
import co.afrolabs.truevote.dao.SenatorialDistrictDAO;
import co.afrolabs.truevote.dao.StateDAO;
import co.afrolabs.truevote.dto.Constituency;
import co.afrolabs.truevote.dto.LocalGovernmentArea;
import co.afrolabs.truevote.dto.SenatorialDistrict;
import co.afrolabs.truevote.dto.State;

public class StateService {
	private ConstituencyDAO constituencyDAO = new ConstituencyDAO();
	private LocalGovernmentAreaDAO LgaDAO = new LocalGovernmentAreaDAO();
	private SenatorialDistrictDAO senatorialDistrictDAO = new SenatorialDistrictDAO();
	private StateDAO stateDAO = new StateDAO();

	public void createState(State state) throws Exception {
		if (findStateByName(state.getName()) != null) {
			throw new Exception("State already exists with name: "
					+ state.getName());
		}
		stateDAO.put(state);
	}

	public List<State> findAllState() {
		return stateDAO.listAll();
	}

	public State findStateByName(String name) {
		return stateDAO.ofy().query(State.class).filter("name", name).get();
	}

	public List<LocalGovernmentArea> findAllLocalGovernmentArea(long stateId) {
		return LgaDAO.ofy().query(LocalGovernmentArea.class)
				.filter("stateId", stateId).list();
	}

	public List<Constituency> findAllConstituency(long stateId) {
		return constituencyDAO.ofy().query(Constituency.class)
				.filter("stateId", stateId).list();
	}

	
	public List<Constituency> findConstituencyByState(String stateName) {
		State state = findStateByName(stateName);
		return constituencyDAO.ofy().query(Constituency.class)
				.filter("stateId", state.getId()).list();
	}

	public void createConstituency(
			Map<String, List<Constituency>> statesAndConstituencies) {
		ConstituencyDAO ConstituencyService = new ConstituencyDAO();
		for (String stateName : statesAndConstituencies.keySet()) {
			State state = findStateByName(stateName);
			for (Constituency constituency : statesAndConstituencies
					.get(stateName)) {
				constituency.setStateId(state.getId());
				ConstituencyService.put(constituency);
			}
		}

	}

	public List<SenatorialDistrict> findAllSenatorialDistrict(long stateId) {
		return senatorialDistrictDAO.ofy().query(SenatorialDistrict.class)
				.filter("stateId", stateId).list();
	}

	public void createSenatorialDistrict(
			Map<String, List<SenatorialDistrict>> statesAndSenatorialDistricts) {
		SenatorialDistrictDAO SenatorialService = new SenatorialDistrictDAO();
		for (String stateName : statesAndSenatorialDistricts.keySet()) {
			State state = findStateByName(stateName);
			for (SenatorialDistrict senatorialZone : statesAndSenatorialDistricts
					.get(stateName)) {
				senatorialZone.setStateId(state.getId());
				SenatorialService.put(senatorialZone);
			}
		}

	}
}
