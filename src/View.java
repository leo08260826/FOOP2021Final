import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame{
	private int screenWidth;
	private int screenHeight;
	private JPanel mainPanel, selectionPanel, playPanel;
	private JPanel winPanel, losePanel;

	public View(int width, int height, String title, Handler _handler, Game _game)
	{
		super(title);
		screenWidth = width;
		screenHeight = height;

		initPanel(_game, _handler);

		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
	public void setKeyListener(Controller controller)
	{
		playPanel.addKeyListener(controller);
	}

	public void render()
	{
		((PlayPanel)playPanel).render();
	}
	public void renderEndGame(int state)
	{
		if(state==1)
		{
			setContentPane(winPanel);
			winPanel.requestFocus();
			setVisible(true);
			((PlayPanel)winPanel).render();
		}
		else
		{
			setContentPane(losePanel);
			losePanel.requestFocus();
			setVisible(true);
			((PlayPanel)losePanel).render();
		}
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

	private void initPanel(Game game, Handler handler)
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

	    JButton btn6 = new JButton("main");
    	btn6.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	game.main();
	        }
	    });
	    selectionPanel.add(btn6);

	    // player panel
	    playPanel = new PlayPanel(handler,screenWidth,screenHeight, 0);
	    winPanel = new PlayPanel(handler,screenWidth,screenHeight, 1);
	    losePanel = new PlayPanel(handler,screenWidth,screenHeight, 2);

	}

}

