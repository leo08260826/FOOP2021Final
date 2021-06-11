import java.awt.*;
import java.awt.event.*;

public class Board extends GameObject implements Collidable {
    
    private static int boardSpeed = 1;
    private Boolean aIsPressed = false;
    private Boolean dIsPressed = false;

	public Board(String _name, int _x, int _y, int _z, int _width, int _height, String _tag)
	{
		super(_name, _x, _y, _z, _width, _height, _tag);
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
		if(aIsPressed && !dIsPressed) x -= boardSpeed;
		if(!aIsPressed && dIsPressed) x += boardSpeed;
	}

    public void collided(){
        return;
    }

}
