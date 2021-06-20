import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

public class View extends JFrame {
	private int screenWidth;
	private int screenHeight;
	private JPanel mainPanel, selectionPanel, playPanel;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();

	public View(int width, int height, String title, Handler _handler, Game _game) {
		super(title);
		screenWidth = width;
		screenHeight = height;

		init(_game, _handler);

		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	public void setKeyListener(Controller controller) {
		mainPanel.addKeyListener(controller);
		playPanel.addKeyListener(controller);
		selectionPanel.addKeyListener(controller);
	}

	public void render() {
		((PlayPanel) playPanel).render();
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

	private void init(Game game, Handler handler) {
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
		// selectionPanel = new JPanel();
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

		playPanel = new PlayPanel(handler, screenWidth, screenHeight, img1);
		playPanel.setFocusable(true);
	}
}

class PlayPanel extends JPanel {
	private int screenWidth;
	private int screenHeight;
	private Handler handler;
	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private Image image = toolkit.getImage("./src/images/bg001.png");

	public PlayPanel(Handler _handler, int width, int height, Image image) {
		handler = _handler;
		screenWidth = width;
		screenHeight = height;
		this.image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
	}

	public void render() {
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		// draw background
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
		// g.setColor(Color.white);
		// g.fillRect(0, 0, screenWidth, screenHeight);

		// draw gameObject
		g.setColor(Color.black);
		for (int i = 0; i < handler.boards.size(); i++) {
			GameObject tmpObj = handler.boards.get(i);
			if (tmpObj.getZ() < 0)
				continue;
			g.fillRect(tmpObj.getX(), tmpObj.getY(), tmpObj.getWidth(), tmpObj.getHeight());
		}
		for (int i = 0; i < handler.walls.size(); i++) {
			GameObject tmpObj = handler.walls.get(i);
			if (tmpObj.getZ() < 0)
				continue;
			g.fillRect(tmpObj.getX(), tmpObj.getY(), tmpObj.getWidth(), tmpObj.getHeight());
		}
		g.setColor(Color.red);
		for (int i = 0; i < handler.grounds.size(); i++) {
			GameObject tmpObj = handler.grounds.get(i);
			if (tmpObj.getZ() < 0)
				continue;
			g.fillRect(tmpObj.getX(), tmpObj.getY(), tmpObj.getWidth(), tmpObj.getHeight());
		}
		for (int i = 0; i < handler.balls.size(); i++) {
			GameObject tmpObj = handler.balls.get(i);
			if (tmpObj.getZ() < 0)
				continue;
			g.fillRect(tmpObj.getX(), tmpObj.getY(), tmpObj.getWidth(), tmpObj.getHeight());
		}

		for (int i = 0; i < handler.bricks.size(); i++) {
			GameObject tmpObj = handler.bricks.get(i);
			if (tmpObj.getZ() < 0)
				continue;
			Image a = toolkit.getImage(((Brick) tmpObj).imagePath());
			g.drawImage(a, tmpObj.getX(), tmpObj.getY(), tmpObj.getWidth(), tmpObj.getHeight(), this);
		}

		// draw UI

	}

}
