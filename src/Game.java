public class Game {
	private Handler handler;
	private View view;
	private Model model;
	private Controller controller;

	public Game(int width, int height, String title, int fps)
	{
		handler = new Handler();
		view = new View(width, height, title, handler, this);
		model = new Model(view, handler, fps);
		controller = new Controller(model);
		view.setKeyListener(controller);
	}

	public void main()
	{
		System.out.println("Main scene.");
		view.renderMain();
	}

	public void stageSelection()
	{
		System.out.println("Stage selection scene.");
		view.renderStageSelection();
	}

	public void play(int stage)
	{
		System.out.println("Play scene: playing " + stage + " stage.");
		view.renderPlay();
		model.init(stage);
		// anthor thread for game loop
		(new GameThread(model, this)).start();
	}
	public void exit()
	{
		System.out.println("Exit game.");
		System.exit(0);
	}
}
