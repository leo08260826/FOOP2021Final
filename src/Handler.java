import java.util.*;

public class Handler{
	public List<GameObject> balls = new ArrayList<GameObject>();
	public List<GameObject> bricks = new ArrayList<GameObject>();
	public List<GameObject> boards = new ArrayList<GameObject>();
	public List<GameObject> walls = new ArrayList<GameObject>();
	public List<GameObject> grounds = new ArrayList<GameObject>();

	private int currentStage;
	private int theSameZ = 10;
	private int lifeNumber = 3;
	private int lifeNow;
	private int boardLeftLimit, boardRightLimit;

	public Handler() {
		// current playground:
		//     left limit:  x = 15
		//     right limit: x = 465	  (the center of the board/ball should be at x = 240)
		//     top limit:   y = 15
		int leftWallX = 5;
		int rightWallX = 465;
		int thickness = 10;

		addObj(new Wall("wallL", leftWallX, 5, 10, thickness, 480, ""));
		addObj(new Wall("wallR", rightWallX, 5, 10, thickness, 480, ""));
		addObj(new Wall("wallT", 5, 5, 10, 470, thickness, ""));
		addObj(new Ground("ground", 10, 470, 10, 600, thickness, ""));

		boardLeftLimit = leftWallX + thickness;
		boardRightLimit = rightWallX;
	}

	private void tickAndCheckForRemove(List<GameObject> list) {
		Stack<Integer> indicesToBeDeleted = new Stack<>();
		for (int i = 0; i < list.size(); i++) {
			GameObject o = list.get(i);
			o.tick();
			if (o.getIsDead()) {
				indicesToBeDeleted.push(i);
			}
		}
		while (!indicesToBeDeleted.empty()) {
			list.remove(indicesToBeDeleted.pop());
		}
	}
	public void tick() {
		tickAndCheckForRemove(balls);
		tickAndCheckForRemove(bricks);
		tickAndCheckForRemove(boards);
		tickAndCheckForRemove(walls);
		tickAndCheckForRemove(grounds);
	}

	private void addObjHelper(GameObject obj, List<GameObject> objs) {
		for(int i = 0; i < objs.size(); i++) {
			if(objs.get(i).getZ() > obj.getZ()) {
				objs.add(i, obj);
				return;
			}
		}
		objs.add(obj);
	}
	public void addObj(GameObject obj) {
		if (obj instanceof Ball) addObjHelper(obj, balls);
		else if (obj instanceof Board) addObjHelper(obj, boards);
		else if (obj instanceof Brick) addObjHelper(obj, bricks);
		else if (obj instanceof Wall) addObjHelper(obj, walls);
		else if (obj instanceof Ground) addObjHelper(obj, grounds);
		else System.out.println("Invalid object type.");
	}

	public boolean win() {
		for (GameObject brick: bricks) {
			if (!(brick instanceof BlockBrick)) {
				return false;
			}
		}
		return true;
	}
	public boolean noBall() {
		return balls.size() == 0;
	}
	public boolean lose() {
		return balls.size() == 0 && lifeNow <= 1;
	}

	public void arrange(int currentStage) {
		lifeNow = lifeNumber;
		this.currentStage = currentStage;

		balls.clear();
		bricks.clear();
		boards.clear();
		//walls.clear();
		//grounds.clear();

		BrickArranger.arrange(currentStage, bricks, theSameZ);
	}
	public Board newBall() {
		boards.clear();
		balls.clear();
		Board board = new Board("board", 210, 430, theSameZ, 60, 10, "", boardLeftLimit, boardRightLimit);
		boards.add(board);
		int vx = (int)(Math.random()*4 - 2);
		balls.add(new Ball("ball", 235, 420, theSameZ, 10, 10, "", vx, -3));

		return board;
	}

	public int getLife() { return lifeNow; }
	public void setLife(int i) { lifeNow = i; }
	public int getBallCount() { return balls.size(); }
}