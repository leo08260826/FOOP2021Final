import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
public class Model{
	private View view;
	private Handler handler;
	private CollisionHandler collisionHandler;

	private Boolean running = false;
	private int refreshRate;

	// GameObjs
	private Board board;

	public Model(View _view, Handler _handler, int fps)
	{
		view = _view;
		handler = _handler;
		refreshRate = (int)(1/(double)fps*1000);
		System.out.println("refreshRate: " + refreshRate);
		collisionHandler = new CollisionHandler(_handler);
	}

	public void init()
	{
		board = handler.arrange(0);

		Wall wall1 = new Wall("wallL", 50, 50, 10, 5, 400, "");
		handler.addObj(wall1);
		Wall wall2 = new Wall("wallR", 620, 50, 10, 5, 400, "");
		handler.addObj(wall2);
		Wall wall3 = new Wall("wallT", 50, 50, 10, 600, 5, "");
		handler.addObj(wall3);
		Ground ground = new Ground("ground", 50, 470, 10, 600, 5, "");
		handler.addObj(ground);
	}

	public void startLoop()
	{
		// game loop
		// ref: https://www.youtube.com/watch?v=1gir2R7G9ws
		running = true;
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(running)
		{
			long now = System.nanoTime();
			delta += (now-lastTime)/ns;
			lastTime = now;
			while(delta>=1)
			{
				tick();
				delta--;
			}
			if(running) render();
			
			try{TimeUnit.MILLISECONDS.sleep(refreshRate);}
			catch(InterruptedException e){}
		}
	}

	public void stopLoop()
	{
		running = false;
	}

	private void tick()
	{
		handler.tick();
		if(collisionHandler.detectCollison())
		{
			//TODO: check game state
			List<GameObject> newballs = new ArrayList<GameObject>();
			for(int i = 0; i < handler.balls.size(); i++){
				if(!handler.balls.get(i).getIsDead()){
					newballs.add(handler.balls.get(i));
				}
			}
			handler.balls = newballs;
			List<GameObject> newbricks = new ArrayList<GameObject>();
			for(int i = 0; i < handler.bricks.size(); i++){
				if(!handler.bricks.get(i).getIsDead()){
					newbricks.add(handler.bricks.get(i));
				}
			}
			handler.bricks = newbricks;
		}
	}
	private void render()
	{
		view.render();
	}

	public void boardMove(int direction, boolean isPress)
	{
		board.move(direction, isPress);
	}

}