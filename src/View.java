import javax.swing.*;
import java.awt.*;

public class View extends JFrame{
	private Handler handler;

	private int screenWidth;
	private int screenHeight;	
	
	public View(int width, int height, String title, Handler _handler)
	{
		super(title);
		screenWidth = width;
		screenHeight = height;
		handler = _handler;
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		setVisible(true);
	}

	@Override
	public void paint(Graphics g)
	{
		// draw background
		g.setColor(Color.white);
		g.fillRect(0, 0, screenWidth, screenHeight);

		// draw gameObject
		g.setColor(Color.black);
		for(int i=0; i<handler.objs.size(); i++)
		{
			GameObject tmpObj = handler.objs.get(i);
			if(tmpObj.getZ()<0) continue;

			// test
			g.drawOval(tmpObj.getX(), tmpObj.getY(), 10, 10);
		}

		// draw UI
		
	}
	public void render()
	{
		revalidate();
		repaint();
	}
}