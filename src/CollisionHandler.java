public class CollisionHandler{

	private Handler handler;
	public CollisionHandler(Handler _handler)
	{
		handler = _handler;
	}

	public Boolean detectCollison()
	{
		Boolean res = false;
		for(int i=0; i<handler.balls.size(); i++)
		{
			for(int j=0; j<handler.boards.size(); j++)
			{
				if(collideDetection(handler.balls.get(i), handler.boards.get(j)))
					res = true;
			}
			for(int j=0; j<handler.bricks.size(); j++)
			{
				if(collideDetection(handler.balls.get(i), handler.bricks.get(j)))
					res = true;
			}
			for(int j=0; j<handler.walls.size(); j++)
			{
				if(collideDetection(handler.balls.get(i), handler.walls.get(j)))
					res = true;
			}
			for(int j=0; j<handler.grounds.size(); j++)
			{
				if(collideDetection(handler.balls.get(i), handler.grounds.get(j)))
					res = true;
			}
		}
		return res;
	}

	private Boolean collideDetection(GameObject object1, GameObject object2){
		//object1 = ball

		if(((object1.getX() >= object2.getX()) && (object1.getX() <= object2.getX() + object2.getWidth()))
			&& ((object1.getY() >= object2.getY()) && (object1.getY() <= object2.getY() + object2.getHeight())))
		{
			//Top-left corner of the ball is collided with object2
			((Collidable)object2).collided(object1);
			((Collider)object1).collide(object2, 0);
			return true;
		}
		else if(((object1.getX() + object1.getWidth() >= object2.getX()) && (object1.getX() + object1.getWidth() <= object2.getX() + object2.getWidth()))
		&& ((object1.getY() >= object2.getY()) && (object1.getY() <= object2.getY() + object2.getHeight())))
		{
			//Top-right corner of the ball is collided with object2
			((Collidable)object2).collided(object1);
			((Collider)object1).collide(object2, 1);
			return true;
		}
		else if(((object1.getX() >= object2.getX()) && (object1.getX() <= object2.getX() + object2.getWidth()))
		&& ((object1.getY() + object1.getHeight() >= object2.getY()) && (object1.getY() + object1.getHeight() <= object2.getY() + object2.getHeight())))
		{
			//Bottom-left corner of the ball is collided with object2
			((Collidable)object2).collided(object1);
			((Collider)object1).collide(object2, 2);
			return true;
		}
		else if(((object1.getX() + object1.getWidth() >= object2.getX()) && (object1.getX() + object1.getWidth() <= object2.getX() + object2.getWidth()))
		&& ((object1.getY() + object1.getHeight() >= object2.getY()) && (object1.getY() + object1.getHeight() <= object2.getY() + object2.getHeight())))
		{
			//Bottom-right corner of the ball is collided with object2
			((Collidable)object2).collided(object1);
			((Collider)object1).collide(object2, 3);
			return true;
		}
		return false;
	}	
}