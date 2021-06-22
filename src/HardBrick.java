public class HardBrick extends Brick  {
    public HardBrick(String _name, int _x, int _y, int _z, int _width, int _height, String _tag)
    {
        super(_name, _x, _y, _z, _width, _height, _tag);
    }
    private int life = 2;

    @Override
    public void collided(GameObject other)
    {
    	// System.out.println("touch HardBrick");
        if(life > 1){
            life--;
        }else{
            this.setIsDead(true);
        }
    }

    public String imagePath(){
        if(life == 2){
            return "./src/images/PNG/19-Breakout-Tiles.png";
        }
        else if(life == 1){
            return "./src/images/PNG/20-Breakout-Tiles.png";
        }
        return "";
    }
}
