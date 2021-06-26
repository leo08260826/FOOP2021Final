import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
	private int screenWidth;
	private int screenHeight;
	private JPanel mainPanel, selectionPanel, playPanel;
	private JPanel winPanel, losePanel;
	private static final int btnWidth = 80;
	private static final int btnHeight = 28;

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

	public void setKeyListener(Controller controller) {
		playPanel.addKeyListener(controller);
	}

	public void render() {
		((PlayPanel) playPanel).render();
	}

	public void renderEndGame(int state) {
		if (state == 1) {
			setContentPane(winPanel);
			winPanel.requestFocus();
			setVisible(true);
			((PlayPanel) winPanel).render();
		} else if (state == 2) {
			setContentPane(losePanel);
			losePanel.requestFocus();
			setVisible(true);
			((PlayPanel) losePanel).render();
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
		// btn.setBounds(0, 0, size.width, size.height);
		btn.setBounds(0, 0, btnWidth, btnHeight);

		btn.setLayout(null);
		btn.setLocation(x, y);
	}

	private void initPanel(Game game, Handler handler) {
		// main panel
		// https://opengameart.org/content/background-12
		Image img = Toolkit.getDefaultToolkit().createImage("./src/images/bg001.png");
		mainPanel = new MyJPanel(img, screenWidth, screenHeight);
		mainPanel.setFocusable(true);
		mainPanel.setLayout(null);

		JButton btnStart = new JButton("Start");
		// btnStart.setSize(30, 300);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.stageSelection();
			}
		});
		setButtonLocation(btnStart, screenWidth / 2 - 100, screenHeight / 2 - 50);
		mainPanel.add(btnStart);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.exit();
			}
		});
		setButtonLocation(btnExit, screenWidth / 2, screenHeight / 2 - 50);
		mainPanel.add(btnExit);

		// selection panel
		selectionPanel = new MyJPanel(img, screenWidth, screenHeight);
		selectionPanel.setFocusable(true);
		selectionPanel.setLayout(null);

		JButton btn1 = new JButton("Level 1");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(1);
			}
		});
		setButtonLocation(btn1, screenWidth / 2 - 140, screenHeight / 2 - 50);
		selectionPanel.add(btn1);
		JButton btn2 = new JButton("Level 2");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(2);
			}
		});
		setButtonLocation(btn2, screenWidth / 2 - 40, screenHeight / 2 - 50);
		selectionPanel.add(btn2);
		JButton btn3 = new JButton("Level 3");
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(3);
			}
		});
		setButtonLocation(btn3, screenWidth / 2 + 60, screenHeight / 2 - 50);
		selectionPanel.add(btn3);

		JButton btn4 = new JButton("Level 4");
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(4);
			}
		});
		setButtonLocation(btn4, screenWidth / 2 - 140, screenHeight / 2 - 10);
		selectionPanel.add(btn4);

		JButton btn5 = new JButton("Level 5");
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.play(5);
			}
		});
		setButtonLocation(btn5, screenWidth / 2 - 40, screenHeight / 2 - 10);
		selectionPanel.add(btn5);

		JButton btnMain = new JButton("main");
		btnMain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.main();
			}
		});
		setButtonLocation(btnMain, screenWidth / 2 - btnWidth/2, screenHeight / 2 - 100);
		selectionPanel.add(btnMain);

		// player panel
		// https://opengameart.org/content/large-nature-background
		Image img1 = Toolkit.getDefaultToolkit().createImage("./src/images/background.png");
		playPanel = new PlayPanel(handler, screenWidth, screenHeight, 0, img1);
		winPanel = new PlayPanel(handler, screenWidth, screenHeight, 1, img1);
		losePanel = new PlayPanel(handler, screenWidth, screenHeight, 2, img1);

	}
}
