package iface_forum.dao;

import javax.persistence.EntityManager;

public class GenericDAO<T extends BaseEntity> {

	public  void salvar(T t) {
		EntityManager entityManager = FactorySingleton.getManager();
		try {
			entityManager.getTransaction().begin();
			if (t.getId() != null) {
				entityManager.merge(t);
			} else {
				entityManager.persist(t);
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public  T retornar(int id, Class<T> clazz) {
		EntityManager entityManager = FactorySingleton.getManager();
		T t = null;
		try {
			t = entityManager.find( clazz, id);
		} finally {
			entityManager.close();
		}
		return t;
	}

	public  void remover(int id, Class<T> clazz) {
		EntityManager entityManager = FactorySingleton.getManager();
		T t = retornar(id, clazz);
		try {
			entityManager.getTransaction().begin();
			t = entityManager.merge(t);
			entityManager.remove(t);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}

}
