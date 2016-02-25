/**
 * Project: A00892244Gis
 * File: GisReportEntries.java
 * Date: Feb 24, 2016
 * Time: 2:00:07 PM
 */

package a00892244.utils;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class LeaderBoardReportEntry {
	private String winLoss;
	private String gameName;
	private String gamerTag;
	private String platform;

	public LeaderBoardReportEntry() {

	}

	/**
	 * @param winLoss
	 * @param gameName
	 * @param gamerTag
	 * @param platform
	 * 
	 * 
	 */
	public LeaderBoardReportEntry(String winLoss, String gameName, String gamerTag, String platform) {
		super();
		setWinLoss(winLoss);
		setGameName(gameName);
		setGamerTag(gamerTag);
		setPlatform(platform);
	}

	/**
	 * @return the winLoss
	 */
	public String getWinLoss() {
		return winLoss;
	}

	/**
	 * @param winLoss
	 *            the winLoss to set
	 */
	public void setWinLoss(String winLoss) {
		this.winLoss = winLoss;
	}

	/**
	 * @return the gameName
	 */
	public String getGameName() {
		return gameName;
	}

	/**
	 * @param gameName
	 *            the gameName to set
	 */
	public void setGameName(String gameName) {
		this.gameName = gameName;
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

}
