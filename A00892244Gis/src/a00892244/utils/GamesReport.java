/**
 * Project: A00892244Gis
 * File: GamesReport.java
 * Date: Feb 24, 2016
 * Time: 2:54:05 PM
 */

package a00892244.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import a00892244.data.Game;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class GamesReport {

		private List<Game> games;

		public GamesReport(Map<String, Game> games) {
			this.games = new ArrayList<Game>(games.values());
		}
		
		public String getReport(){
			StringBuilder report = new StringBuilder();
			Iterator<Game> iterator = games.iterator();
			while(iterator.hasNext()) {
				Game game = iterator.next();
				report.append(String.format("%-20s%s\n", game.getName(), game.getScores().size()));
			}
			report.append("----------------------------------------------------------\n");
			return report.toString();
		}
		
}
