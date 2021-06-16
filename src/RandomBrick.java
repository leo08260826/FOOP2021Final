public class RandomBrick extends Brick  {
    public RandomBrick(String _name, int _x, int _y, int _z, int _width, int _height, String _tag)
    {
        super(_name, _x, _y, _z, _width, _height, _tag);
    }
    private int randomRange = 2;
    private int getRandom(){
        return ((int)(Math.random()*2*randomRange)+1)-randomRange;
    }
    public void collided(GameObject other, int collisionType)
    {
    	System.out.println("touch RandomBrick");
        isDead = true;
        if(other instanceof Ball){
            // [0,6] and -3 = [-3,3]
            ((Ball)other).setVx(getRandom());
            int vy_ = getRandom();
            // assert vy_ != 0
            while(vy_ == 0){
                vy_ = getRandom();
            }
            ((Ball)other).setVy(vy_);
        }
    }

    public String imagePath(){
        return "./src/images/PNG/05-Breakout-Tiles.png";
    }
}
