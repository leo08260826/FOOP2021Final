public class BasicBrick extends Brick  {
    public BasicBrick(String _name, int _x, int _y, int _z, int _width, int _height, String _tag)
    {
        super(_name, _x, _y, _z, _width, _height, _tag);
    }

    @Override
    public void collided(GameObject other)
    {
    	// System.out.println("touch BasicBrick");
        this.setIsDead(true);
    }

    public String imagePath(){
        return "./src/images/PNG/11-Breakout-Tiles.png";
    }
}
