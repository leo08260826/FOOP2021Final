public class Main{
	public static final int WIDTH=640, HEIGHT=WIDTH/12*9;
	public static final String TITLE = "test";
	public static final int FPS=60;

	public static void main(String[] args)
	{
		Game game = new Game(WIDTH, HEIGHT, TITLE, FPS);
		game.start();
	}

}