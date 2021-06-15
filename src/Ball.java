import java.lang.Math;
public class Ball extends GameObject implements Collider{
    // velocity of x, y axis.
    private int vx;
    private int vy;
	private boolean isdead;
    
    public Ball(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, int _vx, int _vy, boolean _isdead)
	{
		super(_name, _x, _y, _z, _width, _height, _tag, _isdead);
		vx = _vx;
		vy = _vy;
		isdead = false;
	}

	private void collidesVertical(){
		vy = -vy;
		this.setY(this.getY() + vy);
	}
	private void collidesHorizontal(){
		vx = -vx;
		this.setX(this.getX() + vx);
	}

	@Override
	public void tick()
	{
		x+=vx;
        y+=vy;
	}
    
    public void collide(GameObject object, int collisionType)
	{
		if(collisionType == 0){
			if(vy > 0){
				//collides from right
				collidesHorizontal();
			}
			else if(vx > 0){
				//colides from bottom
				collidesVertical();
			}
			else{
				if((this.getX() - object.getX() - object.getWidth()) * ((float)object.getHeight() / (float)object.getWidth()) < this.getY() - object.getY() - object.getHeight()){
					//collides from bottom
					collidesVertical();
				}
				else{
					//collides from right
					collidesHorizontal();
				}
			}
		}
		else if(collisionType == 1){
			if(vx < 0){
				//colides from botttom
				collidesVertical();
			}
			else if(vy > 0){
				//collides from left
				collidesHorizontal();
			}
			else{
				if(-(this.getX() + this.getWidth() - object.getX()) * ((float)object.getHeight() / (float)object.getWidth()) < this.getY() - object.getY() - object.getHeight()){
					//collides from bottom
					collidesVertical();
				}
				else{
					//collides from left
					collidesHorizontal();
				}
			}
		}
		else if(collisionType == 2){
			if(vy < 0){
				//collides from right
				collidesHorizontal();
			}
			else if(vx > 0){
				//collides from top
				collidesVertical();
			}
			else{
				if(-(this.getX() - object.getX() - object.getWidth()) * ((float)object.getHeight() / (float)object.getWidth()) > this.getY() + this.getHeight() - object.getY()){
					//collides from top
					collidesVertical();
				}
				else{
					//collides from left
					collidesHorizontal();
				}
			}
		}
		else if(collisionType == 3){
			if(vx < 0){
				//colides from top
				collidesVertical();
			}
			else if(vy < 0){
				//collides from left
				collidesHorizontal();
			}
			else{
				if((this.getX() + this.getWidth() - object.getX()) * ((float)object.getHeight() / (float)object.getWidth()) < this.getY() + this.getHeight() - object.getY()){
					//collides from top
					collidesVertical();
				}
				else{
					//collides from left
					collidesHorizontal();
				}
			}
		}
    }
}
