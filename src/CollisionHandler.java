public class CollisionHandler{

	private Handler handler;
	public CollisionHandler(Handler _handler)
	{
		handler = _handler;
	}

	public void detectCollison()
	{
		for(int i=0; i<handler.balls.size(); i++)
		{
			for(int j=0; j<handler.boards.size(); j++)
			{
				collideDetection(handler.balls.get(i), handler.boards.get(j));
			}
			for(int j=0; j<handler.bricks.size(); j++)
			{
				collideDetection(handler.balls.get(i), handler.bricks.get(j));
			}
			for(int j=0; j<handler.walls.size(); j++)
			{
				collideDetection(handler.balls.get(i), handler.walls.get(j));
			}
		}
	}

	private void collideDetection(GameObject object1, GameObject object2){
		//object1 = ball

		if(((object1.getX() >= object2.getX()) && (object1.getX() <= object2.getX() + object2.getWidth()))
			&& ((object1.getY() >= object2.getY()) && (object1.getY() <= object2.getY() + object2.getHeight()))){
			//Top-left corner of the ball is collided with object2
			((Collidable)object2).collided(object1, 0);
		}
		else if(((object1.getX() + object1.getWidth() >= object2.getX()) && (object1.getX() + object1.getWidth() <= object2.getX() + object2.getWidth()))
		&& ((object1.getY() >= object2.getY()) && (object1.getY() <= object2.getY() + object2.getHeight()))){
			//Top-right corner of the ball is collided with object2
			((Collidable)object2).collided(object1, 1);
		}
		else if(((object1.getX() >= object2.getX()) && (object1.getX() <= object2.getX() + object2.getWidth()))
		&& ((object1.getY() + object1.getHeight() >= object2.getY()) && (object1.getY() + object1.getHeight() <= object2.getY() + object2.getHeight()))){
			//Bottom-left corner of the ball is collided with object2
			((Collidable)object2).collided(object1, 2);
		}
		else if(((object1.getX() + object1.getWidth() >= object2.getX()) && (object1.getX() + object1.getWidth() <= object2.getX() + object2.getWidth()))
		&& ((object1.getY() + object1.getHeight() >= object2.getY()) && (object1.getY() + object1.getHeight() <= object2.getY() + object2.getHeight()))){
			//Bottom-right corner of the ball is collided with object2
			((Collidable)object2).collided(object1, 3);
		}
	}	
}