public abstract class GameObject{
	protected String name;
	protected int x, y;
	protected int z; // for render order (ps: -1 wont be rendered)
	protected String tag;

	public GameObject(String _name, int _x, int _y, int _z, String _tag)
	{
		name = _name;
		x = _x;
		y = _y;
		z = _z;
		tag = _tag;
	}
	public abstract void tick();

	public void setName(String s){name=s;}
	public String getName(){return name;}

	public void setX(int i){x=i;}
	public int getX(){return x;}

	public void setY(int i){y=i;}
	public int getY(){return y;}

	public void setZ(int i){z=i;}
	public int getZ(){return z;}

	public void setTag(String s){tag=s;}
	public String getTag(){return tag;}
}