package character;

/**
 * attributes MUST be set individually before using them or you will get a LOT
 * of zeros
 * 
 * @author ee
 * 
 */
public class Attributes {
	private int current_health;
	private int max_health;
	private int current_power;
	private int max_power;

	/**
	 * attribute keys
	 */
	protected static final int CURRENT_HEALTH_KEY = 0;
	protected static final int MAX_HEALTH_KEY = 1;
	protected static final int CURRENT_POWER_KEY = 2;
	protected static final int MAX_POWER_KEY = 3;

	/**
	 * allows setting of each attribute individually
	 * 
	 * @param currentHealth
	 * @param maxHealth
	 * @param currentPower
	 * @param maxPower
	 * @param currentSpeed
	 * @param maxSpeed
	 */
	public Attributes(int currentHealth, int maxHealth, int currentPower,
			int maxPower) {
		this.current_health = currentHealth;
		this.max_health = maxHealth;
		this.current_power = currentPower;
		this.max_power = maxPower;
	}

	/**
	 * max and current are set to the same value
	 * 
	 * @param maxHealth
	 * @param maxPower
	 * @param maxSpeed
	 */
	public Attributes(int maxHealth, int maxPower) {
		this(maxHealth, maxHealth, maxPower, maxPower);
	}

	/**
	 * increments or decrements attributes
	 * 
	 * @param key
	 *            use to select the attribute
	 * @param increment
	 *            amount to modify (use whole numbers, can be negative)
	 * 
	 *            values below zero are set to zero
	 */
	protected void modifyAttribute(final int key, final int increment) {
		switch (key) {
		case CURRENT_HEALTH_KEY:
			if (this.willCauseNegativeResult(this.current_health, increment))
				this.current_health = 0;
			else
				this.current_health += increment;
			break;
		case MAX_HEALTH_KEY:
			if (this.willCauseNegativeResult(this.max_health, increment))
				this.max_health = 0;
			else
				this.max_health += increment;
		case CURRENT_POWER_KEY:
			if (this.willCauseNegativeResult(this.current_power, increment))
				this.current_power = 0;
			else
				this.current_power += increment;
		case MAX_POWER_KEY:
			if (this.willCauseNegativeResult(this.max_power, increment))
				this.max_power = 0;
			else
				this.max_power += increment;
		}
	}

	/**
	 * 
	 * @param key
	 * @return returns the attribute (use for checks, exterior modification will
	 *         NOT be accepted) -1 indicates an invalid value
	 */
	public int getAttribute(final int key) {
		switch (key) {
		case CURRENT_HEALTH_KEY:
			return this.current_health;
		case MAX_HEALTH_KEY:
			return this.max_health;
		case CURRENT_POWER_KEY:
			return this.current_power;
		case MAX_POWER_KEY:
			return this.max_power;
		default:
			return -1;
		}
	}

	private boolean willCauseNegativeResult(int attribute, int increment) {
		if ((attribute + increment) < 0)
			return true;
		return false;
	}
}
