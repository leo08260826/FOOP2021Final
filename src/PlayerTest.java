// test
public class PlayerTest extends GameObject{

	public PlayerTest(String _name, int _x, int _y, int _z, String _tag)
	{
		super(_name, _x, _y, _z, _tag);
	}

	@Override
	public void tick()
	{
		x+=1;
	}
}