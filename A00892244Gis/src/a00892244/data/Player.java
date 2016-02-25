/**
 * Project: A00892244Gis
 * File: Player.java
 * Date: Feb 16, 2016
 * Time: 10:33:00 AM
 */

package a00892244.data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Player {
	private int identifier;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private LocalDate birthdate;
	private Map<Integer, Persona> personas;

	/**
	 * default constructor
	 */
	public Player() {
		personas = new HashMap<Integer, Persona>();
	}

	/**
	 * @param identifier
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param gamerTag
	 */
	public Player(int identifier, String firstName, String lastName, String emailAddress, LocalDate birthdate) {
		setIdentifier(identifier);
		setFirstName(firstName);
		setLastName(lastName);
		setEmailAddress(emailAddress);
		setBirthdate(birthdate);
		personas = new HashMap<Integer, Persona>();
	}

	/**
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	@Deprecated
	public String getFormatedIdentifier() {
		String id = identifier + "";
		StringBuilder formatedId = new StringBuilder();
		for (int i = 6; i > id.length(); --i) {
			formatedId.append("0");
		}
		formatedId.append(id);
		return formatedId.toString();
	}

	/**
	 * @param identifier
	 *            the identifier to set
	 */
	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * @param emailAddress
	 *            the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * @return the birthdate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * @param birthdate
	 *            the birthdate to set
	 */
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * 
	 * @param persona
	 */
	public void addPersona(Persona persona) {
		personas.put(persona.getId(), persona);
	}

	/**
	 * 
	 * @param personaId
	 * @return
	 */
	public Persona getPersona(Integer personaId) {
		return personas.get(personaId);
	}

	public Map<Integer, Persona> getPersonas() {
		return personas;
	}
	
	public int getTotalGamesPlayed() {
		int total = 0;
		for (int personaKey : personas.keySet()){
			Persona persona = personas.get(personaKey);
			for (String gameKey : persona.getGames().keySet()){
				total += persona.getGames().get(gameKey).getScores().size();
			}
		}		
		return total;
	}
	
	public int getTotalWins() {
		int total = 0;
		for (int personaKey : personas.keySet()){
			Persona persona = personas.get(personaKey);
			for (String gameKey : persona.getGames().keySet()){
				Iterator<Score> iterator = persona.getGames().get(gameKey).getScores().iterator();
				while (iterator.hasNext()){
					Score score = iterator.next();
					if (score.getWin().equals("WIN")){
						total++;
					}
				}
			}
		}		
		return total;
	}
	
	public long getAge() {
		LocalDate now = LocalDate.now();
		return ChronoUnit.YEARS.between(birthdate, now);

		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [identifier=" + identifier + ", firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", birthdate=" + birthdate
				+ ", personas=" + personas + "]";
	}

}
