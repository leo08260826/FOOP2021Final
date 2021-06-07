public class Wall extends GameObject implements Collidable {

	public Wall(String _name, int _x, int _y, int _z, int _length, int _width, String _tag)
	{
		super(_name, _x, _y, _z, _length, _width, _tag);
	}

	@Override
	public void tick()
	{
		return;
	}

    public void collided(){
        return;
    }
}