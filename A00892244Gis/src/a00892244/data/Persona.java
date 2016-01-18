/**
 * Project: A00892244Gis
 * File: Persona.java
 * Date: Jan 13, 2016
 * Time: 11:30:33 AM
 */

package a00892244.data;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Persona {
	private String id;
	private String playerId;
	private String gamerTag;
	private String platform;

	/**
	 * 
	 */
	public Persona() {
		
	}
	
	/**
	 * 
	 * @param id
	 * @param playerId
	 * @param gamerTag
	 * @param platform
	 */
	public Persona(String id, String playerId, String gamerTag, String platform) {
		setId(id);
		setPlayerId(playerId);
		setGamerTag(gamerTag);
		setPlatform(platform);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the playerId
	 */
	public String getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId the playerId to set
	 */
	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the gamerTag
	 */
	public String getGamerTag() {
		return gamerTag;
	}

	/**
	 * @param gamerTag the gamerTag to set
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
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
	
}
