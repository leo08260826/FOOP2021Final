public class Game {
	private Handler handler;
	private View view;
	private Model model;
	private Controller controller;

	public Game(int width, int height, String title, int fps)
	{
		handler = new Handler();
		view = new View(width, height, title, handler);
		model = new Model(view, handler, fps);
		controller = new Controller(model);
		view.addKeyListener(controller);
	}

	public void start()
	{
		model.init();
		model.startLoop();
	}
}