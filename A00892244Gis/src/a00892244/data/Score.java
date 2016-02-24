/**
 * Project: A00892244Gis
 * File: Score.java
 * Date: Jan 13, 2016
 * Time: 11:29:01 AM
 */

package a00892244.data;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Score {

	private int personaId;
	private String gameId;
	private String win;

	
	/**
	 * 
	 */
	public Score() {
		
	}
	
	/**
	 * @param personaId
	 * @param gameId
	 * @param win
	 */
	public Score(int personaId, String gameId, String win) {
		super();
		setPersonaId(personaId);
		setGameId(gameId);
		setWin(win);
	}

	/**
	 * @return the personaId
	 */
	public int getPersonaId() {
		return personaId;
	}

	/**
	 * @param personaId the personaId to set
	 */
	public void setPersonaId(int personaId) {
		this.personaId = personaId;
	}

	/**
	 * @return the gameId
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @param gameId the gameId to set
	 */
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	/**
	 * @return the win
	 */
	public String getWin() {
		return win;
	}

	/**
	 * @param win the win to set
	 */
	public void setWin(String win) {
		this.win = win;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Score [personaId=" + personaId + ", gameId=" + gameId + ", win=" + win + "]";
	}


	
	
	
	
	
}
