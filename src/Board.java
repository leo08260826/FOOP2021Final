import java.awt.*;
import java.awt.event.*;

public class Board extends GameObject implements Collidable {
    private int ballSpeed = 1;
	public Board(String _name, int _x, int _y, int _z, int _length, int _width, String _tag)
	{
		super(_name, _x, _y, _z, _length, _width, _tag);
	}

    // key event 架構可能需要討論
    private int getDirection(){
        return main.game.controller.key == KeyEvent.KEY_LOCATION_LEFT? -1: 1;
    }

	@Override
	public void tick()
	{
		x += ballSpeed*getDirection();
	}

    public void collided(){
        return;
    }

}
