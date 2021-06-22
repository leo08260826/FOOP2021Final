import java.util.*;

public class Handler{
	public List<GameObject> balls = new ArrayList<GameObject>();
	public List<GameObject> bricks = new ArrayList<GameObject>();
	public List<GameObject> boards = new ArrayList<GameObject>();
	public List<GameObject> walls = new ArrayList<GameObject>();
	public List<GameObject> grounds = new ArrayList<GameObject>();

	private int theSameZ;
	private int life;

	private int boardLeftLimit, boardRightLimit;

	public Handler() {
		theSameZ = 10;

		int leftWallX = 10;
		int rightWallX = 470;
		int thickness = 5;

		addObj(new Wall("wallL", leftWallX, 10, 10, thickness, 480, ""));
		addObj(new Wall("wallR", rightWallX, 10, 10, thickness, 480, ""));
		addObj(new Wall("wallT", 10, 10, 10, 460, thickness, ""));
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
		for(int i=0; i<objs.size(); i++)
		{
			if(objs.get(i).getZ()>obj.getZ())
			{
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

	private void removeObjHelper(GameObject obj, List<GameObject> objs) {
		for(int i=0; i<objs.size(); i++)
		{
			if(objs.get(i).equals(obj))
			{
				objs.remove(i);
				System.out.println("Remove!");
				return;
			}
		}
		System.out.println("Didn't remove anything!");
	}

	// TODO: check whether this is needed
	public void removeObj(GameObject obj) {
		if (obj instanceof Ball) {
			removeObjHelper(obj, balls);
			return;
		}
		else if (obj instanceof Board) {
			removeObjHelper(obj, boards);
			return;
		}
		else if (obj instanceof Brick) {
			removeObjHelper(obj, bricks);
			return;
		}
		else if (obj instanceof Wall) {
			removeObjHelper(obj, walls);
			return;
		}
		else if (obj instanceof Ground) {
			removeObjHelper(obj, grounds);
			return;
		}
		else System.out.println("Invalid object type.");
	}

	public boolean win() {
		// DONE: change logic due to new brick type BlockBrick
		for (GameObject brick: bricks) {
			if (!(brick instanceof BlockBrick)) {
				return false;
			}
		}
		return true;
	}
	public boolean noBall()
	{
		return balls.size() == 0;
	}
	public boolean lose() {
		return balls.size() == 0 && life <= 0;
	}

	public void arrange(int currentStage) {
		life = 2;

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
		Board board = new Board("board", 305, 430, theSameZ, 60, 10, "", boardLeftLimit, boardRightLimit);
		boards.add(board);
		balls.add(new Ball("ball", 328, 420, theSameZ, 10, 10, "", 0, -1));

		return board;
	}

	public int getLife(){return life;}
	public void setLife(int i){life=i;}
	public int getBallCount(){ return balls.size(); }
}