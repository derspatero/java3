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
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String birthName;
	
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
	 * @param birthName
	 */
	public Player(String id, String firstName, String lastName, String email, String birthName) {
		super();
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setBirthName(birthName);
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
	public String getBirthName() {
		return birthName;
	}

	/**
	 * @param birthName the birthName to set
	 */
	public void setBirthName(String birthName) {
		this.birthName = birthName;
	}
	
	

}
