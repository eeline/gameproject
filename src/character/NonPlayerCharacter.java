package character;





public abstract class NonPlayerCharacter extends Position {
	protected final Attributes attributes;
	
	public NonPlayerCharacter(int health, int power) {
		attributes = new Attributes(health, power);
	}
	
	public NonPlayerCharacter(int health, int power, int x, int y, int speedX, int speedY){
		super(x,y, speedX, speedY);
		this.attributes = new Attributes(health, power);
	}

	public abstract void update();

	public abstract void die();

	public abstract void attack();
	
	public abstract void paint();
}
