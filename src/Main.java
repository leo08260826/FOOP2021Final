public class Main{
	public static final int WIDTH=640, HEIGHT=480;
	public static final String TITLE="Block Game";
	public static final int FPS=60;

	public static void main(String[] args)
	{
		Game game = new Game(WIDTH, HEIGHT, TITLE, FPS);
		game.main();
	}

}