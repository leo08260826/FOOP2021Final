public abstract class GameObject{
	protected int x, y;
	protected int z; // for render order (ps: -1 wont be rendered)
	protected String tag;

	public GameObject(int _x, int _y, int _z, String _tag)
	{
		x = _x;
		y = _y;
		z = _z;
		tag = _tag;
	}
	public abstract void tick(long deltaTime);

	public void setX(int i){x=i;}
	public int getX(){return x;}

	public void setY(int i){y=i;}
	public int getY(){return y;}

	public void setZ(int i){z=i;}
	public int getZ(){return z;}

	public void setTag(String s){tag=s;}
	public String getTag(){return tag;}
}