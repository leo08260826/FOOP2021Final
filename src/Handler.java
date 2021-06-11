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

	public void tick()
	{
		balls.forEach(o -> o.tick());
		bricks.forEach(o -> o.tick());
		boards.forEach(o -> o.tick());
		walls.forEach(o -> o.tick());
		grounds.forEach(o -> o.tick());
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
		return bricks.size() == 0;
	}
	public boolean lose() {
		return balls.size() == 0;
	}

	public void arrange() {
		balls.clear();
		bricks.clear();
		boards.clear();
		// walls and grounds remain the same.

		boards.add(new Board("board", 290, 430, theSameZ, 10, 60, ""));
		balls.add(new Ball("ball", 315, 420, theSameZ, 10, 10, ""));
		List<Position> positions = BrickArranger.arrange(currentStage);
		positions.forEach((pos) -> {
			bricks.add(new Brick("brick", pos.x, pos.y, theSameZ, 20, 30, ""));
		});

		tick();
	}
}