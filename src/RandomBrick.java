public class RandomBrick extends Brick {
    public RandomBrick(String _name, int _x, int _y, int _z, int _width, int _height, String _tag) {
        super(_name, _x, _y, _z, _width, _height, _tag);
    }

    private int randomRange = 2;

    private int getRandom() {
        return ((int) (Math.random() * 2 * randomRange) + 1) - randomRange;
    }

    @Override
    public void collided(GameObject other) {
        // System.out.println("touch RandomBrick");
        this.setIsDead(true);
        if (other instanceof Ball) {
            // [0,6] and -3 = [-3,3]
            // assert vx_ != 0
            int vx_ = getRandom();
            while (Math.abs(vx_) <= 1) {
                vx_ = getRandom();
            }
            ((Ball) other).setVx(vx_);
        }
    }

    public String imagePath() {
        return "./src/images/PNG/05-Breakout-Tiles.png";
    }
}
