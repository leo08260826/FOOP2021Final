
// test
public class PlayerTest extends GameObject{

	public PlayerTest(int _x, int _y, int _z, String _tag)
	{
		super(_x, _y, _z, _tag);
	}

	@Override
	public void tick(long deltaTime)
	{
		x+=1;
	}
}