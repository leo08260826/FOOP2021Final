public class Brick extends GameObject implements Collidable {

	public Brick(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, boolean _isdead)
	{
		super(_name, _x, _y, _z, _width, _height, _tag, _isdead);
	}

	@Override
	public void tick()
	{
		return;
	}

    public void collided(GameObject other)
    {
		this.setIsDead(true);
    	System.out.println("touch brick");
    }
}