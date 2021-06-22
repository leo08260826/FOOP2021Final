import javax.swing.*;
import java.awt.*;
class PlayPanel extends JPanel
{
	private int screenWidth;
	private int screenHeight;
	private Handler handler;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Image resultImage;
	private Image image = toolkit.getImage("./src/images/bg001.png");

	public PlayPanel(Handler _handler, int width, int height, int state, Image image)
	{
		handler = _handler;
		screenWidth = width;
		screenHeight = height;
		setFocusable(true);
		this.image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
		
		if(state==0)
		{
			resultImage = toolkit.getImage("./src/images/none.PNG");
		}
		else if(state==1)
		{
			resultImage = toolkit.getImage("./src/images/win.jpg");
		}
		else if(state==2)
		{
			resultImage = toolkit.getImage("./src/images/lose.PNG");
		}
		
	}

	public void render()
	{
		repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		// draw background
		g.drawImage(image, 0, 0, this);
		// g.setColor(Color.white);
		// g.fillRect(0, 0, screenWidth, screenHeight);

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

		for(int i=0; i<handler.bricks.size(); i++)
		{
			GameObject tmpObj = handler.bricks.get(i);
			if(tmpObj.getZ()<0) continue;
			Image a =toolkit.getImage( ((Brick)tmpObj).imagePath() );  
			g.drawImage(a, tmpObj.getX(),tmpObj.getY(), tmpObj.getWidth(),tmpObj.getHeight(),this);
		}

		// draw UI

		// draw End Game
		g.drawImage(resultImage, 320-150, 240-100, 300, 200, this);
		
	}

}