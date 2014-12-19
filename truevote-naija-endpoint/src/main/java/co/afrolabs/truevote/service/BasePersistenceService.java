package co.afrolabs.truevote.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public class BasePersistenceService {

	private EntityManager manager = EMF.get().createEntityManager();

	public EntityManager getEntityManager() {
		return this.manager;
	}

	protected Serializable createEntity(Serializable data) throws Exception {
		Serializable pData;
		try {
			getEntityManager().getTransaction().begin();
			pData = getEntityManager().merge(data);
			getEntityManager().getTransaction().commit();
		} finally {
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}
		return pData;
	}

	protected Serializable modifyEntity(Serializable data) throws Exception {
		Serializable pData;
		try {
			getEntityManager().getTransaction().begin();
			pData = getEntityManager().merge(data);
			getEntityManager().getTransaction().commit();
		} finally {
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}
		return pData;
	}

	protected void removeEntity(Serializable data) throws Exception {
		try {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(data);
			getEntityManager().getTransaction().commit();
		} finally {
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}

	}

	public Serializable findByPrimaryKey(Long pk, Class<?> clazz)
			throws Exception {
		Serializable pData;
		try {
			pData = (Serializable) getEntityManager().find(clazz, pk);
		} finally {
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}
		return pData;
	}

	protected List<?> findAll(Query query) throws Exception {
		List<?> results = query.getResultList();
		return results;
	}

	protected Object findEntity(Query query) {
		Object object = null;
		boolean status = false;
		try {
			object = query.getSingleResult();
		} catch (Exception ex) {
			status = true;
		}
		if (status) {
			return null;
		}
		return object;
	}

	protected void setParameterValues(Query q, Map<String, Object> map) {
		if (!map.isEmpty()) {
			Iterator<String> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				q.setParameter(key, map.get(key));
			}
		}
	}

	public List<? extends Serializable> findEntities(
			Class<? extends Object> clas) {
		return findEntities(true, -1, -1, clas);
	}

	public List<? extends Serializable> findEntities(int maxResults,
			int firstResult, Class clas) {
		return findEntities(false, maxResults, firstResult, clas);
	}

	private List<? extends Serializable> findEntities(boolean all,
			int maxResults, int firstResult, Class clas) {
		CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder()
				.createQuery();
		cq.select(cq.from(clas));
		Query q = getEntityManager().createQuery(cq);
		if (!all) {
			q.setMaxResults(maxResults);
			q.setFirstResult(firstResult);
		}
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<Object[]> findAllObjects(Query query) throws Exception {
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected List<Object> findAllObjects(String queryString) throws Exception {
		List<Object> results;
		try {
			getEntityManager().getTransaction().begin();
			Query query = getEntityManager().createQuery(queryString);
			results = query.getResultList();
		} finally {
			getEntityManager().getTransaction().commit();
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}
		return results;
	}

	protected Object findObject(String queryString) throws Exception {
		Object result;
		try {
			getEntityManager().getTransaction().begin();
			Query query = getEntityManager().createQuery(queryString);
			result = query.getSingleResult();
		} finally {
			getEntityManager().getTransaction().commit();
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}
		return result;
	}

	public long getCount(String queryString) {
		long totalVotes;
		try {
			getEntityManager().getTransaction().begin();
			Query query = getEntityManager().createQuery(queryString);
			totalVotes = (long) query.getSingleResult();
		} finally {
			getEntityManager().getTransaction().commit();
			if (getEntityManager() != null) {
				getEntityManager().close();
			}
		}
		return totalVotes;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}
}
