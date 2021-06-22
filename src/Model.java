import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
public class Model{
	private View view;
	private Handler handler;
	private CollisionHandler collisionHandler;

	private Boolean running = false;
	private int refreshRate;
	private int gameState; // 0:playing, 1:win, 2:lose
	private int gameStage; // level
	private int endGameMessageDuration = 3;

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

	public void init(int stage)
	{
		gameStage = stage;
		handler.arrange(stage);
		board = handler.newBall();
	}

	public int	startLoop()
	{
		gameState = 0;
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
		
		if(gameState!=0)
		{
			renderEndGame();
			try{TimeUnit.SECONDS.sleep(endGameMessageDuration);}
			catch(InterruptedException e){}
		}

		return gameState;
	}

	public void stopLoop()
	{
		running = false;
	}

	private void render()
	{
		view.render();
	}

	private void renderEndGame()
	{
		view.renderEndGame(gameState);
	}

	private void tick()
	{
		handler.tick();
		if(collisionHandler.detectCollison())
		{
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

			// check game state
			if(handler.win())
			{
				gameState=1;
				stopLoop();
			}
			else if(handler.lose())
			{
				handler.setLife(handler.getLife()-1);
				gameState=2;
				stopLoop();		
			}
			else if(handler.noBall())
			{
				handler.setLife(handler.getLife()-1);
				board = handler.newBall();
			}

		}
	}
	public void boardMove(int direction, boolean isPress)
	{
		board.move(direction, isPress);
	}

	public int getGameStage(){return gameStage;}

}