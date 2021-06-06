import java.util.concurrent.TimeUnit;
public class Model{
	private View view;
	private Handler handler;

	private Boolean running = false;
	private int refreshRate;

	public Model(View _view, Handler _handler, int fps)
	{
		view = _view;
		handler = _handler;
		refreshRate = (int)(1/(double)fps*1000);
		System.out.println("refreshRate: " + refreshRate);
	}

	public void init()
	{
		// test
		PlayerTest player1 = new PlayerTest("player1", 50, 50, 10, 10, 1, "p");
		PlayerTest player2 = new PlayerTest("player2", 100, 50, 10, 10, 0, "p");
		handler.addObj(player1);
		handler.addObj(player2);
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
	}
	private void render()
	{
		view.render();
	}

}