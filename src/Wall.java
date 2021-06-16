public class Wall extends GameObject implements Collidable {

	public Wall(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, boolean _isdead)
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
    	System.out.println("touch wall");
    }
}