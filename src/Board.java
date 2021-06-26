import java.awt.*;
import java.awt.event.*;
import java.lang.Math;

public class Board extends GameObject implements Collidable {
    
    private static int boardSpeed = 3;
    private Boolean aIsPressed = false;
    private Boolean dIsPressed = false;
	private int distance = 0;
	private int direction = 1;
    private int leftLimit;
    private int rightLimit;
	private int lastx = 210;
	private int cnt = 0;

	public Board(String _name, int _x, int _y, int _z, int _width, int _height, String _tag, int lLim, int rLim)
	{
		super(_name, _x, _y, _z, _width, _height, _tag);
		leftLimit = lLim;
		rightLimit = rLim;
	}

    public void move(int direction, boolean isPress)
    {
    	if(direction==-1 && isPress) aIsPressed=true;
    	else if(direction==-1 && !isPress) aIsPressed=false;
    	else if(direction==1 && isPress) dIsPressed=true;
    	else dIsPressed=false;
    }

	@Override
	public void tick()
	{
		// if(aIsPressed && !dIsPressed){
		// 	direction = -1;
		// 	 x -= boardSpeed;
		// }
		// if(!aIsPressed && dIsPressed){
		// 	direction = 1;
		// 	x += boardSpeed;
		// }
		if(aIsPressed && !dIsPressed) x = Math.max(x - boardSpeed, leftLimit);
		if(!aIsPressed && dIsPressed) x = Math.min(x + boardSpeed, rightLimit - width);
		if(cnt == 10){
			lastx = x;
			cnt = 0;
		}
		cnt++;
	}

    public void collided(GameObject other)
    {
		if(lastx == x){
			distance = 0;
		}
		else{
			distance = 2*other.getX() + other.getWidth() - 2*this.getX() - this.getWidth();
		}
    	// System.out.println("touch board");
    }

	public int getDirection(){return Math.abs(direction);}
	public int getDistance(){return distance;}
}
