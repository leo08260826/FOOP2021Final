public abstract class GameObject{
	protected String name;
	protected int x, y; // top-left of the obj
	protected int z; // for render order (ps: -1 wont be rendered)
	protected String tag;
	protected int width, height;
	protected boolean isdead;

	public GameObject(String _name, int _x, int _y, int _z, int _width, int _height, String _tag)
	{
		name = _name;
		x = _x;
		y = _y;
		z = _z;
		width = _width;
		height = _height;
		tag = _tag;
		isdead = false;
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

	public int getWidth(){return width;}
	public int getHeight(){return height;}

	public void setIsDead(boolean i){isdead = i;}
	public boolean getIsDead(){return isdead;}
}