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
		// test
        System.out.println("keyTyped");
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	// test
    	if(key==KeyEvent.VK_W)
    	{
    		System.out.println("keyPressed W");
    	}
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    	// test
        System.out.println("keyReleased");
    }

}