import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

public class View extends JFrame{
	private int screenWidth;
	private int screenHeight;
	private JPanel mainPanel, selectionPanel, playPanel;  

	public View(int width, int height, String title, Handler _handler, Game _game)
	{
		super(title);
		screenWidth = width;
		screenHeight = height;

		init(_game, _handler);

		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	public void setKeyListener(Controller controller)
	{
		mainPanel.addKeyListener(controller);
		playPanel.addKeyListener(controller);
		selectionPanel.addKeyListener(controller);
	}

	public void render()
	{
		((PlayPanel)playPanel).render();
	}

	public void renderMain()
	{		
		setContentPane(mainPanel);
		mainPanel.requestFocus();
		setVisible(true);
	}
	public void renderStageSelection()
	{
	    setContentPane(selectionPanel);
	    selectionPanel.requestFocus();
		setVisible(true);
	}
	public void renderPlay()
	{
		setContentPane(playPanel);
		playPanel.requestFocus();
		setVisible(true);
	}

	private void init(Game game, Handler handler)
	{
		// main panel
		mainPanel = new JPanel();
		mainPanel.setFocusable(true);
		JButton btn1 = new JButton("Start Game");
    	btn1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.stageSelection();
	        }
	    });
	    mainPanel.add(btn1);
	    JButton btn2 = new JButton("Exit");
    	btn2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.exit();
	        }
	    });
	    mainPanel.add(btn2);

	    // selection panel
	    selectionPanel = new JPanel();
	    selectionPanel.setFocusable(true);

		JButton btn3 = new JButton("Level 1");
    	btn3.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.play(1);
	        }
	    });
	    selectionPanel.add(btn3);
	    JButton btn4 = new JButton("Level 2");
    	btn4.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.play(2);
	        }
	    });
	    selectionPanel.add(btn4);
	    JButton btn5 = new JButton("Level 3");
    	btn5.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.play(3);
	        }
	    });
	    selectionPanel.add(btn5);

	    JButton btn__ = new JButton("Level 4");
		btn__.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(4);
			}
		});
		selectionPanel.add(btn__);

		JButton btn___ = new JButton("Level 5");
		btn___.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(5);
			}
		});
		selectionPanel.add(btn___);

	    JButton btn6 = new JButton("main");
    	btn6.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.main();
	        }
	    });
	    selectionPanel.add(btn6);

	    // player panel
	    playPanel = new PlayPanel(handler,screenWidth,screenHeight); 
	    playPanel.setFocusable(true);
	}
}


class PlayPanel extends JPanel
{
	private int screenWidth;
	private int screenHeight;
	private Handler handler;
	private Toolkit toolkit =Toolkit.getDefaultToolkit();

	public PlayPanel(Handler _handler, int width, int height)
	{
		handler = _handler;
		screenWidth = width;
		screenHeight = height;
	}

	public void render()
	{
		repaint();
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

		for(int i=0; i<handler.bricks.size(); i++)
		{
			GameObject tmpObj = handler.bricks.get(i);
			if(tmpObj.getZ()<0) continue;
			Image a =toolkit.getImage( ((Brick)tmpObj).imagePath() );  
			g.drawImage(a, tmpObj.getX(),tmpObj.getY(), tmpObj.getWidth(),tmpObj.getHeight(),this);
		}

		// draw UI
		g.setColor(Color.BLACK);
		g.setFont(new Font("Ariel", Font.BOLD, 20));
		g.drawString("Life: " + handler.getLife(), 490, 30);
		g.drawString("Balls: " + handler.getBallCount(), 490, 60);
	}

}
