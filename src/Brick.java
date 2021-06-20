public abstract class Brick extends GameObject implements Collidable, HasImageObject {
	public Brick(String _name, int _x, int _y, int _z, int _width, int _height, String _tag)
	{
		super(_name, _x, _y, _z, _width, _height, _tag);
	}

	@Override
	public void tick()
	{
		return;
	}

    public void collided(GameObject other)
    {
		this.setIsDead(true);
    	// System.out.println("touch brick");
    }
	abstract public String imagePath();
}