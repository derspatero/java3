/**
 * Project: A00892244Gis
 * File: Player.java
 * Date: Jan 13, 2016
 * Time: 11:30:10 AM
 */

package a00892244.data;

/**
 * @author Edward Lambke, A00892244
 *
 */

public class Player {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String birthDate;
	
	/**
	 * 
	 */
	public Player() {
		
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param birthDate
	 */
	public Player(int id, String firstName, String lastName, String email, String birthDate) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setBirthDate(birthDate);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id2 the id to set
	 */
	public void setId(int id2) {
		this.id = id2;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
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
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the birthName
	 */
	public String getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthName the birthName to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}
