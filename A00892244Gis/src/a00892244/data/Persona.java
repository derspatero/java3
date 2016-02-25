/**
 * Project: A00892244Gis
 * File: Persona.java
 * Date: Jan 13, 2016
 * Time: 11:30:33 AM
 */

package a00892244.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Persona {
	private int id;
	private int playerId;
	private String gamerTag;
	private String platform;
	private Map<String, Game> games;

	/**
	 * 
	 */
	public Persona() {
		games = new HashMap<String, Game>();
	}

	/**
	 * 
	 * @param id
	 * @param playerId
	 * @param gamerTag
	 * @param platform
	 */
	public Persona(int id, int playerId, String gamerTag, String platform) {
		setId(id);
		setPlayerId(playerId);
		setGamerTag(gamerTag);
		setPlatform(platform);
		games = new HashMap<String, Game>();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the gamerTag
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param gamerTag
	 *            the gamerTag to set
	 */
	public void setGamerTag(String gamerTag) {
		this.gamerTag = gamerTag;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * 
	 * @param game
	 */
	public void addGame(Game game) {
		games.put(game.getId(), game);
	}

	/**
	 * 
	 * @return
	 */
	public Map<String, Game> getGames() {
		return games;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Persona [id=" + id + ", playerId=" + playerId + ", gamerTag=" + gamerTag + ", platform=" + platform + ", games=" + games + "]";
	}

}
