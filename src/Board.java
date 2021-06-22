import java.awt.*;
import java.awt.event.*;

public class Board extends GameObject implements Collidable {
    
    private static int boardSpeed = 1;
    private Boolean aIsPressed = false;
    private Boolean dIsPressed = false;
    private int leftLimit;
    private int rightLimit;

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
		if(aIsPressed && !dIsPressed) x = Math.max(x - boardSpeed, leftLimit);
		if(!aIsPressed && dIsPressed) x = Math.min(x + boardSpeed, rightLimit - width);
	}

    public void collided(GameObject other)
    {
    	System.out.println("touch board");
    }

}
