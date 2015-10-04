package elevator;

public class Request
{

	private int personID;

	private int startFloor;

	private int endFloor;

	public Request(int personID, int startFloor, int endFloor)
	{
		super();
		this.personID = personID;
		this.startFloor = startFloor;
		this.endFloor = endFloor;
	}

	/**
	 * @return the personID
	 */
	public int getPersonID()
	{
		return personID;
	}

	/**
	 * @param personID
	 *            the personID to set
	 */
	public void setPersonID(int personID)
	{
		this.personID = personID;
	}

	/**
	 * @return the startFloor
	 */
	public int getStartFloor()
	{
		return startFloor;
	}

	/**
	 * @param startFloor
	 *            the startFloor to set
	 */
	public void setStartFloor(int startFloor)
	{
		this.startFloor = startFloor;
	}

	/**
	 * @return the endFloor
	 */
	public int getEndFloor()
	{
		return endFloor;
	}

	/**
	 * @param endFloor
	 *            the endFloor to set
	 */
	public void setEndFloor(int endFloor)
	{
		this.endFloor = endFloor;
	}

}
