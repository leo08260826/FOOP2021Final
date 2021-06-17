public class Game {
	private Handler handler;
	private View view;
	private Model model;
	private Controller controller;

	private int state=0; //0: main scene, 1: stage selection, 2: in-game scene

	public Game(int width, int height, String title, int fps)
	{
		handler = new Handler();
		view = new View(width, height, title, handler, this);
		model = new Model(view, handler, fps);
		controller = new Controller(model);
		view.setKeyListener(controller);
		// view.addKeyListener(controller);
	}

	public void main()
	{
		System.out.println("main");
		view.renderMain();
	}

	public void stageSelection()
	{
		System.out.println("stage selection");
		view.renderStageSelection();
	}

	public void play(int stage)
	{
		System.out.println("playing " + stage + " stage.");
		view.renderPlay();
		model.init(stage);
		// model.startLoop();
		Thread loop = new Thread(model::startLoop);
		loop.start();
		// try {
  //         loop.join();
  //       } catch (InterruptedException e) {
  //         e.printStackTrace();
  //       }
	}
	public void exit()
	{
		System.exit(0);
	}
}