package iface_forum.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactorySingleton {
	private static EntityManagerFactory factory;

	private FactorySingleton() {
	}

	public static EntityManager getManager() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("iface");
		return factory.createEntityManager();
	}
}
