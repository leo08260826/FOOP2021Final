public class Ball extends GameObject implements Collider{
    // velocity of x, y axis.
    private int vx;
    private int vy;
    
    public Ball(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, int _vx, int _vy)
	{
		super(_name, _x, _y, _z, _width, _height, _tag);
		vx = _vx;
		vy = _vy;
		isdead = false;
	}

	private void collidesVertical(){
		this.setVy(-vy);
		this.setY(this.getY() + vy);
	}
	private void collidesHorizontal(){
		this.setVx(-vx);
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
		if(object instanceof Board){
			vy = -vy;
			this.setY(this.getY() + vy);
			vx = vx + ((Board)object).getDirection()*(4*((Board)object).getDistance())/object.getWidth();
			if(vx > 2) vx = 2;
			else if(vx < -2) vx = -2;
			this.setX(this.getX() + vx);
		}
		else{
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

	public int getVx(){return vx;}
	public int getVy(){return vy;}
	public void setVx(int v){vx = v;}
	public void setVy(int v){vy = v;}

}
