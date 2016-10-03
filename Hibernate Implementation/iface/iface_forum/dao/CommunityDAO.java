package iface_forum.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import iface_forum.Community;

public class CommunityDAO extends GenericDAO<Community>{
	

	@SuppressWarnings("unchecked")
	public List<Community> returnCommunityListOfUser(Integer userId) {
		EntityManager entityManager = FactorySingleton.getManager();
		List<Community> lista = null;
		try {
			 Query query = entityManager.createQuery("select t from Community as t where t.idCreator = " + userId);
			 lista = query.getResultList();
		} finally {
			entityManager.close();
		}
		return lista;
	}
	
/*	@SuppressWarnings("unchecked")
	public List<Integer> returnCommunityMembers(Integer comId) {
		EntityManager entityManager = FactorySingleton.getManager();
		List<Community> lista = null;
		try {
			 Query query = entityManager.createQuery("select t from Community as t where t.idCreator = " + userId);
			 lista = query.getResultList();
		} finally {
			entityManager.close();
		}
		return lista;
	}
	*/

}
