package model;
/**
 * model class to represent a requirement. A student must fulfill
 * all requirements to graduate
 * @author Will Henry
 *
 */
public class Requirement {

	private int id;
	private String requirementArea;
	/**
	 * constructor for a Requirement object
	 * @param id - the id of the object
	 * @param requirementArea - the requirement Area
	 */
	public Requirement(int id, String requirementArea) {
		this.id = id;
		this.requirementArea = requirementArea;
	}
	/**
	 * getter for the id of a requirement
	 * @return id - the requirment's id
	 */
	public int getId() {
		return id;
	}
	/**
	 * getter for the requirment's area
	 * @return requirementArea - the 
	 */
	public String getRequirementArea() {
		return requirementArea;
	}
}
