public class Ground extends GameObject implements Collidable{

	public Ground(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, Boolean _isdead)
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

		other.setIsDead(true);
		System.out.println("touch ground");
	}
}