public class Ball extends GameObject implements Collider{
    // velocity of x, y axis.
    private int vx;
    private int vy;
    
    public Ball(String _name, int _x, int _y, int _z, int _length, int _width, String _tag)
	{
		super(_name, _x, _y, _z, _length, _width, _tag);
	}

	@Override
	public void tick()
	{
		x+=vx;
        y+=vy;
	}
    
    public void collide(){
        // TODO
        // vx = -vx; or vy = -vy;
    }
}
