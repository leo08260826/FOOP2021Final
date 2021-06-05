import java.util.*;
public class Handler{
	public List<GameObject> objs = new ArrayList<GameObject>();

	public void tick(long deltaTime)
	{
		for(int i=0; i<objs.size(); i++)
		{
			objs.get(i).tick(deltaTime);
		}
	}

	public void addObj(GameObject obj)
	{
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
	public void removeObj(GameObject obj)
	{
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
}