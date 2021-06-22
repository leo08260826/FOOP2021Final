import java.awt.*;
import java.awt.event.*;

public class Controller extends KeyAdapter{
	private Model model;

	public Controller(Model _model)
	{
		model = _model;
	}

	@Override
	public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	if(key==KeyEvent.VK_A)
    	{
    		model.boardMove(-1, true);
    	}
    	else if(key==KeyEvent.VK_D)
    	{
    		model.boardMove(1, true);
    	}

    	if(key==KeyEvent.VK_ESCAPE)
		{
			model.stopLoop();
		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();
    	if(key==KeyEvent.VK_A)
    	{
    		model.boardMove(-1, false);
    	}
    	else if(key==KeyEvent.VK_D)
    	{
    		model.boardMove(1, false);
    	}
    }

}