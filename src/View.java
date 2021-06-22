import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
	private int screenWidth;
	private int screenHeight;
	private JPanel mainPanel, selectionPanel, playPanel;
	private JPanel winPanel, losePanel;

	public View(int width, int height, String title, Handler _handler, Game _game) {
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

	public void render() {
		((PlayPanel) playPanel).render();
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

	public void renderMain() {
		setContentPane(mainPanel);
		mainPanel.requestFocus();
		setVisible(true);
	}

	public void renderStageSelection() {
		setContentPane(selectionPanel);
		selectionPanel.requestFocus();
		setVisible(true);
	}

	public void renderPlay() {
		setContentPane(playPanel);
		playPanel.requestFocus();
		setVisible(true);
	}

	private void setButtonLocation(JButton btn, int x, int y) {
		Dimension size = btn.getPreferredSize();
		btn.setBounds(0, 0, size.width, size.height);
		btn.setLayout(null);
		btn.setLocation(x, y);
	}

	private void initPanel(Game game, Handler handler)
	{
		// main panel
		// https://opengameart.org/content/background-12
		Image img = Toolkit.getDefaultToolkit().createImage("./src/images/bg001.png");
		mainPanel = new MyJPanel(img, screenWidth, screenHeight);
		mainPanel.setFocusable(true);

		JButton btn1 = new JButton("Start Game");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.stageSelection();
			}
		});
		mainPanel.setLayout(null);
		setButtonLocation(btn1, screenWidth / 2 - 100, screenHeight / 2 - 50);
		mainPanel.add(btn1);

		JButton btn2 = new JButton("Exit");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.exit();
			}
		});
		setButtonLocation(btn2, screenWidth / 2, screenHeight / 2 - 50);
		mainPanel.add(btn2);

		// selection panel
		selectionPanel = new MyJPanel(img, screenWidth, screenHeight);
		selectionPanel.setFocusable(true);
		selectionPanel.setLayout(null);

		JButton btn3 = new JButton("Level 1");
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(1);
			}
		});
		setButtonLocation(btn3, screenWidth / 2 - 140, screenHeight / 2 - 50);
		selectionPanel.add(btn3);
		JButton btn4 = new JButton("Level 2");
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(2);
			}
		});
		setButtonLocation(btn4, screenWidth / 2 - 40, screenHeight / 2 - 50);
		selectionPanel.add(btn4);
		JButton btn5 = new JButton("Level 3");
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(3);
			}
		});
		setButtonLocation(btn5, screenWidth / 2 + 60, screenHeight / 2 - 50);
		selectionPanel.add(btn5);
		JButton btn6 = new JButton("main");
		btn6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.main();
			}
		});
		setButtonLocation(btn6, screenWidth / 2 - 35, screenHeight / 2 - 100);
		selectionPanel.add(btn6);

		// player panel
		// https://opengameart.org/content/large-nature-background
		Image img1 = Toolkit.getDefaultToolkit().createImage("./src/images/background.png");
	    playPanel = new PlayPanel(handler,screenWidth,screenHeight, 0, img1);
	    winPanel = new PlayPanel(handler,screenWidth,screenHeight, 1, img1);
	    losePanel = new PlayPanel(handler,screenWidth,screenHeight, 2, img1);

	}
}

