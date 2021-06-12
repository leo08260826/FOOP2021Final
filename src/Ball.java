public class Ball extends GameObject implements Collider{
    // velocity of x, y axis.
    private int vx;
    private int vy;
    
    public Ball(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, int _vx, int _vy)
	{
		super(_name, _x, _y, _z, _width, _height, _tag);
		vx = _vx;
		vy = _vy;
	}

	@Override
	public void tick()
	{
		x+=vx;
        y+=vy;
	}
    
    public void collide(){

    }
}
