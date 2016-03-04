/**
 * Project: A00892244Gis
 * File: Game.java
 * Date: Jan 13, 2016
 * Time: 11:30:49 AM
 */

package a00892244.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Game {
	private String id;
	private String name;
	private String producer;

	/**
	 * 
	 */
	public Game() {
	}

	/**
	 * 
	 * @param sourceGame
	 */
	public Game(Game sourceGame) {
		setId(sourceGame.getId());
		setName(sourceGame.getName());
		setProducer(sourceGame.getProducer());
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param producer
	 */
	public Game(String id, String name, String producer) {
		setId(id);
		setName(name);
		setProducer(producer);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the producer
	 */
	public String getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(String producer) {
		this.producer = producer;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Game [id=" + id + ", name=" + name + ", producer=" + producer + "]";
	}




}
