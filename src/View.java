import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JFrame{
	private Handler handler;

	private int screenWidth;
	private int screenHeight;
	
	private Toolkit toolkit =Toolkit.getDefaultToolkit();  

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

		for(int i=0; i<handler.boards.size(); i++)
		{
			GameObject tmpObj = handler.boards.get(i);
			if(tmpObj.getZ()<0) continue;
			g.fillRect(tmpObj.getX(),tmpObj.getY(),tmpObj.getWidth(),tmpObj.getHeight());
		}
		for(int i=0; i<handler.walls.size(); i++)
		{
			GameObject tmpObj = handler.walls.get(i);
			if(tmpObj.getZ()<0) continue;
			g.fillRect(tmpObj.getX(),tmpObj.getY(),tmpObj.getWidth(),tmpObj.getHeight());
		}
		g.setColor(Color.red);
		for(int i=0; i<handler.grounds.size(); i++)
		{
			GameObject tmpObj = handler.grounds.get(i);
			if(tmpObj.getZ()<0) continue;
			g.fillRect(tmpObj.getX(),tmpObj.getY(),tmpObj.getWidth(),tmpObj.getHeight());
		}
		for(int i=0; i<handler.balls.size(); i++)
		{
			GameObject tmpObj = handler.balls.get(i);
			if(tmpObj.getZ()<0) continue;
			g.fillRect(tmpObj.getX(),tmpObj.getY(),tmpObj.getWidth(),tmpObj.getHeight());
		}
		
		// g.setColor(Color.blue);
		
		for(int i=0; i<handler.bricks.size(); i++)
		{
			GameObject tmpObj = handler.bricks.get(i);
			if(tmpObj.getZ()<0) continue;
			Image a =toolkit.getImage( ((Brick)tmpObj).imagePath() );  
			g.drawImage(a, tmpObj.getX(),tmpObj.getY(), tmpObj.getWidth(),tmpObj.getHeight(),this);
		}

		// draw UI
		
	}
	public void render()
	{
		revalidate();
		repaint();
	}
}