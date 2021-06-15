import java.util.*;

public class Handler{
	public List<GameObject> balls = new ArrayList<GameObject>();
	public List<GameObject> bricks = new ArrayList<GameObject>();
	public List<GameObject> boards = new ArrayList<GameObject>();
	public List<GameObject> walls = new ArrayList<GameObject>();
	public List<GameObject> grounds = new ArrayList<GameObject>();

	private int currentStage;
	private int theSameZ;

	public Handler() {
		currentStage = 0;
		theSameZ = 10;
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
		// TODO: need to change logic due to new brick type HardBrick
		return bricks.size() == 0;
	}
	public boolean lose() {
		return balls.size() == 0;
	}

	public Board arrange() {
		balls.clear();
		bricks.clear();
		boards.clear();
		// walls and grounds remain the same.

		Board board = new Board("board", 305, 430, theSameZ, 60, 10, "");
		boards.add(board);
		balls.add(new Ball("ball", 328, 420, theSameZ, 10, 10, "", 0, -1));
		List<Position> positions = BrickArranger.arrange(currentStage);
		positions.forEach((pos) -> {
			bricks.add(new Brick("brick", pos.x, pos.y, theSameZ, 50, 20, ""));
		});

		return board;
	}
	public Board arrange(int currentStage) {
		// TODO: combine this and above after external calls are determined.
		this.currentStage = currentStage;
		return arrange();
	}
}