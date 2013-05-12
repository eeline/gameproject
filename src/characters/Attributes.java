package characters;

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
	private int current_speed;
	private int max_speed;

	/**
	 * attribute keys
	 */
	protected static final int CURRENT_HEALTH_KEY = 0;
	protected static final int MAX_HEALTH_KEY = 1;
	protected static final int CURRENT_POWER_KEY = 2;
	protected static final int MAX_POWER_KEY = 3;
	protected static final int CURRENT_SPEED_KEY = 4;
	protected static final int MAX_SPEED_KEY = 5;

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
			int maxPower, int currentSpeed, int maxSpeed) {
		this.current_health = currentHealth;
		this.max_health = maxHealth;
		this.current_power = currentPower;
		this.max_power = maxPower;
		this.current_speed = currentSpeed;
		this.max_speed = maxSpeed;
	}

	/**
	 * max and current are set to the same value
	 * 
	 * @param maxHealth
	 * @param maxPower
	 * @param maxSpeed
	 */
	public Attributes(int maxHealth, int maxPower, int maxSpeed) {
		this(maxHealth, maxHealth, maxPower, maxPower, maxSpeed, maxSpeed);
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

		case CURRENT_SPEED_KEY:
			if (this.willCauseNegativeResult(this.current_speed, increment))
				this.current_speed = 0;
			else
				this.current_speed += increment;
		case MAX_SPEED_KEY:
			if (this.willCauseNegativeResult(this.max_speed, increment))
				this.max_speed = 0;
			else
				this.max_speed += increment;
		}
	}

	/**
	 * 
	 * @param key
	 * @return returns the attribute (use for checks, exterior modification will
	 *         NOT be accepted) -1 indicates an invalid value
	 */
	protected int getAttribute(final int key) {
		switch (key) {
		case CURRENT_HEALTH_KEY:
			return this.current_health;
		case MAX_HEALTH_KEY:
			return this.max_health;
		case CURRENT_POWER_KEY:
			return this.current_power;
		case MAX_POWER_KEY:
			return this.max_power;
		case CURRENT_SPEED_KEY:
			return this.current_speed;
		case MAX_SPEED_KEY:
			return this.max_speed;
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
