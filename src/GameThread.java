class GameThread extends Thread
{
	private Model model;
	private Game game;

	private int gameStateTotal=5;
	
	public GameThread(Model _model, Game _game)
	{
		model = _model;
		game = _game;
	}
	@Override
	public void run()
	{
		int gameState = model.startLoop();
		if(gameState==1) // win
		{
			System.out.println("win");
			if(model.getGameStage()==gameStateTotal) // last stage
			{
				game.main();
			}
			else
			{
				game.play(model.getGameStage()+1);
			}
		}
		else if(gameState==2) // lose
		{
			System.out.println("lose");
			game.main();
		}
		else // esc
		{
			System.out.println("escape");
			game.main();
		}
	} 

}