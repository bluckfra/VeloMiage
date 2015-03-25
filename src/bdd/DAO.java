package bdd;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import bdd.connexionJDBC.ConnectionBDD;
import bdd.objetsbdd.StationBD;
import bdd.objetsbdd.Velo;

public abstract class DAO<T> {

	/**
	 * Link to the database connection
	 */
	public Connection connect = ConnectionBDD.getInstance();
	
	public abstract ArrayList<T> getInstances();
	
	/**
	 * gets object via ID
	 * @param id
	 * @return desired object
	 */
	public abstract T find(int id);
	
	/**
	 * creates object in the database
	 * 	 * @param obj
	 */
	public abstract T create(T obj);
	
	/**
	 * saves changes to a modified existent object in the DB 
	 * @param obj
	 */
	public abstract T update(T obj);
	
	/**
	 * delete object from the database
	 * @param obj
	 */
	public abstract void delete(T obj);
}
