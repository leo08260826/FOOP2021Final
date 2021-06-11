import java.util.concurrent.TimeUnit;
public class Model{
	private View view;
	private Handler handler;

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
	}

	public void init()
	{
		// test
		// PlayerTest player1 = new PlayerTest("player1", 50, 50, 10, 10, 1, "p");
		// handler.addObj(player1);
		board = handler.arrange();
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
	// public void collideDetection(GameObject object1, GameObject object2){
	// 	//object1 = ball
	// 	if(((object1.getX() >= object2.getX()) && (object1.getX() <= object2.getX() + object2.getWidth()))
	// 		&& ((object1.getY() >= object2.getY()) && (object1.getY() <= object2.getY() + object2.getLength()))){
	// 		//Top-left corner of the ball is collided with object2
	// 		//object1.collide();
	// 		//object2.collided();
	// 	}else if(((object1.getX() + object1.getWidth() >= object2.getX()) && (object1.getX() + object1.getWidth() <= object2.getX() + object2.getWidth()))
	// 	&& ((object1.getY() >= object2.getY()) && (object1.getY() <= object2.getY() + object2.getLength()))){
	// 		//Top-right corner of the ball is collided with object2
	// 		//object1.collide();
	// 		//object2.collided();
	// 	}else if(((object1.getX() >= object2.getX()) && (object1.getX() <= object2.getX() + object2.getWidth()))
	// 	&& ((object1.getY() + object1.getLength() >= object2.getY()) && (object1.getY() + object1.getLength() <= object2.getY() + object2.getLength()))){
	// 		//Bottom-left corner of the ball is collided with object2
	// 		//object1.collide();
	// 		//object2.collided();
	// 	}
	// 	else if(((object1.getX() + object1.getWidth() >= object2.getX()) && (object1.getX() + object1.getWidth() <= object2.getX() + object2.getWidth()))
	// 	&& ((object1.getY() + object1.getLength() >= object2.getY()) && (object1.getY() + object1.getLength() <= object2.getY() + object2.getLength()))){
	// 		//Bottom-right corner of the ball is collided with object2
	// 		//object1.collide();
	// 		//object2.collided();
	// 	}
	// }

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

	public void boardMove(int direction, boolean isPress)
	{
		board.move(direction, isPress);
	}

}