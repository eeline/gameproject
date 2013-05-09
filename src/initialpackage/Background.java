package initialpackage;

public class Background {
	private int backgroundX;
	private final int backgroundY;
	private int backgroundSpeedX;
	
	public static final int BACKGROUND_LENGTH_X = -2160;
	
	public Background(int x, int y){
		this.backgroundX = x;
		this.backgroundY =y;
		this.backgroundSpeedX =0;
	}
	
	public void update(){
		this.backgroundX += this.backgroundSpeedX;
		
		if(this.backgroundX <= BACKGROUND_LENGTH_X){
			this.backgroundX += (BACKGROUND_LENGTH_X * -2);
		}
	}

	/**
	 * @return the backgroundX
	 */
	public int getBackgroundX() {
		return backgroundX;
	}

	/**
	 * @return the backgroundY
	 */
	public int getBackgroundY() {
		return backgroundY;
	}

	/**
	 * @return the backgroundSpeedX
	 */
	public int getBackgroundSpeedX() {
		return backgroundSpeedX;
	}

	/**
	 * @param backgroundSpeedX the backgroundSpeedX to set
	 */
	public void setBackgroundSpeedX(int backgroundSpeedX) {
		this.backgroundSpeedX = backgroundSpeedX;
	}
}
