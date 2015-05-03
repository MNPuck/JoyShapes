/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.util.Constants;
import com.nowhereinc.joyshapes.util.Xbox360Pad;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends AbstractGameObject {

	private TextureRegion Bullet;

	public boolean active;
	
	private boolean moveSet;
	
	//set direction to NIL
	private int direction;
	
	private float rotation;
	
	private float rightXAxis;
	private float rightYAxis;

	public Bullet () {
		init();
	}

	private void init () {
		dimension.set(Constants.BULLETSIZE, Constants.BULLETSIZE);

		Bullet = Assets.instance.bullet.bullet;

		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
	    
		direction = Constants.NIL;
		
	}
	
	public int returnDirection() {
		
		return direction;
	}
	
	public void reset() {

		direction = Constants.NIL;
		rotation = 0f;
		position.x = 0;
		position.y = 0;
		
	}
	
	public int directionreturn () {
		
		return direction;
	}
	
	public void update (float deltaTime) {
		
		switch(direction) {
			
			case Constants.NW : {
					
				float bulletMoveSpeed = 5.0f * deltaTime;
				position.x = position.x - bulletMoveSpeed;
				position.y = position.y + bulletMoveSpeed;
				break;
					
			}
				
			case Constants.NE : {
					
				float bulletMoveSpeed = 5.0f * deltaTime;
				position.x = position.x + bulletMoveSpeed;
				position.y = position.y + bulletMoveSpeed;
				break;
					
			}
				
			case Constants.SE : {
					
				float bulletMoveSpeed = 5.0f * deltaTime;
				position.x = position.x + bulletMoveSpeed;
				position.y = position.y - bulletMoveSpeed;
				break;
					
			}
					
			case Constants.SW : {
					
				float bulletMoveSpeed = 5.0f * deltaTime;
				position.x = position.x - bulletMoveSpeed;
				position.y = position.y - bulletMoveSpeed;
				break;
					
			}
				
			case Constants.W : {
					
				float bulletMoveSpeed = 10.0f * deltaTime;
				position.x = position.x - bulletMoveSpeed;
				break;
					
			}
					
			case Constants.E : {
					
				float bulletMoveSpeed = 10.0f * deltaTime;
				position.x = position.x + bulletMoveSpeed;
				break;
					
			}
				
			case Constants.N : {
					
				float bulletMoveSpeed = 10.0f * deltaTime;
				position.y = position.y + bulletMoveSpeed;
				break;
					
			}
					
			case Constants.S : {
					
				float bulletMoveSpeed = 10.0f * deltaTime;
				position.y = position.y - bulletMoveSpeed;
				break;
					
			}
				
		}
		
		// check to see if bullet is on edges of screen
		
		if (position.x > (Constants.GAMEBOARD_WIDTH / 2) - dimension.x) {
						
			direction = Constants.NIL;
			
		}
					
		if (position.x < - Constants.GAMEBOARD_WIDTH / 2) {
						
			direction = Constants.NIL;
						
		}
					
		if (position.y > (Constants.GAMEBOARD_HEIGHT / 2) - dimension.y) {
						
			direction = Constants.NIL;
						
		}
					
		if (position.y < - Constants.GAMEBOARD_HEIGHT / 2) {
						
			direction = Constants.NIL;
						
		}
			
	}
	
	public void inputController (float rightXAxisIn, float rightYAxisIn) {
		
		rightXAxis = rightXAxisIn;
		rightYAxis = rightYAxisIn;
		
	}
	
	public void intshot (float deltaTime, Vector2 shipCenter) {
				
		// set moveSet to false, check on regular moves to make sure diagnol
		// has been set yet that will cause shape to double move
			
		moveSet = false;
		
		// move upper left
		
		if (rightXAxis < - Constants.RIGHTJOYADJUSTMENT && 
			rightYAxis < - Constants.RIGHTJOYADJUSTMENT) {

			position.x = shipCenter.x + (dimension.x / 4);
			position.y = shipCenter.y + (dimension.y / 4); 
				
			moveSet = true;
			direction = Constants.NW;
			rotation = 135f;
		
		}
		
		// move upper right
			
		if (rightXAxis > Constants.RIGHTJOYADJUSTMENT && 
			rightYAxis < - Constants.RIGHTJOYADJUSTMENT) {
			
			position.x = shipCenter.x + (dimension.x / 4);
			position.y = shipCenter.y - (dimension.y / 4); 

			moveSet = true;
			direction = Constants.NE;
			rotation = 45f;
				
		}
			
		// move lower left
			
		if (rightXAxis < - Constants.RIGHTJOYADJUSTMENT && 
			rightYAxis > Constants.RIGHTJOYADJUSTMENT) {
					
			position.x = shipCenter.x - (dimension.x / 4);
			position.y = shipCenter.y + (dimension.y / 4); 
				
			moveSet = true;
			direction = Constants.SW;
			rotation = 225f;
							
		}
			
		// move lower right
			
		if (rightXAxis > Constants.RIGHTJOYADJUSTMENT && 
			rightYAxis > Constants.RIGHTJOYADJUSTMENT) {
				
			position.x = shipCenter.x - (dimension.x / 4);
			position.y = shipCenter.y - (dimension.y / 4); 
					
			moveSet = true;
			direction = Constants.SE;	
			rotation = 315f;
		}
		
		// move left
			
		if (rightXAxis < - Constants.RIGHTJOYADJUSTMENT &&
			!moveSet)  {
				
			position.x = shipCenter.x + (dimension.x / 2);
			position.y = shipCenter.y + (dimension.y / 2);
			
			direction = Constants.W;
			rotation = 180f;
			
		}
			
		// move right
			
		if (rightXAxis > Constants.RIGHTJOYADJUSTMENT &&
			!moveSet) {
			
			position.x = shipCenter.x - (dimension.x / 2);
			position.y = shipCenter.y - (dimension.y / 2);
		
			direction = Constants.E;
				
		}
			
		// move up
		
		if (rightYAxis < - Constants.RIGHTJOYADJUSTMENT &&
			!moveSet) {
				
			position.x = shipCenter.x + (dimension.x / 2);
			position.y = shipCenter.y - (dimension.y / 2);
				
			direction = Constants.N;
			rotation = 90f;
			
		}
			
		// move down
			
		if (rightYAxis > Constants.RIGHTJOYADJUSTMENT &&
			!moveSet) {
			
			position.x = shipCenter.x - (dimension.x / 2);
			position.y = shipCenter.y + (dimension.y / 2);
			
			direction = Constants.S;
			rotation = 270f;
			
		}
		
	}
	
	public void intshotL (float deltaTime, Vector2 shipCenter, int pDirection) {	
	
		// move upper left
		
		if (pDirection == Constants.NW) {

			position.x = shipCenter.x + (dimension.x / 2) - Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + (dimension.y / 2) - Constants.SIDEBULLETADJUSTMENT; 
			
			moveSet = true;
			direction = Constants.NW;
			rotation = 135f;
	
		}
	
		// move upper right
			
		if (pDirection == Constants.NE) {
				
			position.x = shipCenter.x + (dimension.x / 2) - Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + (dimension.y / 2); 

			moveSet = true;
			direction = Constants.NE;
			rotation = 45f;
				
		}
			
		// move lower left
			
		if (pDirection == Constants.SW) {
			
			position.x = shipCenter.x - (dimension.x / 2) + Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y - (dimension.y / 2); 
				
			moveSet = true;
			direction = Constants.SW;
			rotation = 225f;
							
		}
			
		// move lower right
			
		if (pDirection == Constants.SE) {
				
			position.x = shipCenter.x - (dimension.x / 2) + Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y - (dimension.y / 2) + Constants.SIDEBULLETADJUSTMENT; 
						

			moveSet = true;
			direction = Constants.SE;	
			rotation = 315f;
		}
			
		// move left
			
		if (pDirection == Constants.W) {
			
			position.x = shipCenter.x + (dimension.x / 4);
			position.y = shipCenter.y + (dimension.y / 4) - Constants.SIDEBULLETADJUSTMENT; 
				
			direction = Constants.W;
			rotation = 180f;
			
		}
			
		// move right
		
		if (pDirection == Constants.E) {
			
			position.x = shipCenter.x + (dimension.x / 4);
			position.y = shipCenter.y - (dimension.y / 2) + Constants.SIDEBULLETADJUSTMENT; 
				
			direction = Constants.E;
			
		}

		// move up
		
		if (pDirection == Constants.N) {
			
			position.x = shipCenter.x + (dimension.x / 4) - Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + (dimension.y / 4);
				
			direction = Constants.N;
			rotation = 90f;
			
		}
		
		// move down
		
		if (pDirection == Constants.S) {
			
			position.x = shipCenter.x - (dimension.x / 4) + Constants.SIDEBULLETADJUSTMENT; 
			position.y = shipCenter.y + (dimension.y / 4);
				
			direction = Constants.S;
			rotation = 270f;
			
		}
		
	}
	
	public void intshotR (float deltaTime, Vector2 shipCenter, int pDirection) {	
		
		// move upper left
		
		if (pDirection == Constants.NW) {

			// position.x = shipCenter.x - (dimension.x / 4) + Constants.SIDEBULLETADJUSTMENT;
			// position.y = shipCenter.y - (dimension.y / 4) + Constants.SIDEBULLETADJUSTMENT;
			
			position.x = shipCenter.x + Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + Constants.SIDEBULLETADJUSTMENT; 
			
			moveSet = true;
			direction = Constants.NW;
			rotation = 135f;
	
		}
	
		// move upper right
			
		if (pDirection == Constants.NE) {
				
			position.x = shipCenter.x + (dimension.x / 4) + Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + (dimension.y / 4) - Constants.SIDEBULLETADJUSTMENT; 

			moveSet = true;
			direction = Constants.NE;
			rotation = 45f;
				
		}
			
		// move lower left
			
		if (pDirection == Constants.SW) {
			
			position.x = shipCenter.x + (dimension.x / 4) - Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + (dimension.y / 4) + Constants.SIDEBULLETADJUSTMENT; 
				
			moveSet = true;
			direction = Constants.SW;
			rotation = 225f;
							
		}
			
		// move lower right
			
		if (pDirection == Constants.SE) {
				
			// position.x = shipCenter.x + (dimension.x / 4) - Constants.SIDEBULLETADJUSTMENT;
			// position.y = shipCenter.y + (dimension.y / 4) - Constants.SIDEBULLETADJUSTMENT;
			
			position.x = shipCenter.x - Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y - Constants.SIDEBULLETADJUSTMENT; 
						

			moveSet = true;
			direction = Constants.SE;	
			rotation = 315f;
		}
			
		// move left
			
		if (pDirection == Constants.W) {
			
			position.x = shipCenter.x + (dimension.x / 4);
			position.y = shipCenter.y + (dimension.y / 2) + Constants.SIDEBULLETADJUSTMENT; 
				
			direction = Constants.W;
			rotation = 180f;
			
		}
			
		// move right
		
		if (pDirection == Constants.E) {
			
			position.x = shipCenter.x + (dimension.x / 4);
			position.y = shipCenter.y - (dimension.y / 2) - Constants.SIDEBULLETADJUSTMENT; 
				
			direction = Constants.E;
			
		}

		// move up
		
		if (pDirection == Constants.N) {
			
			position.x = shipCenter.x + (dimension.x / 2) + Constants.SIDEBULLETADJUSTMENT;
			position.y = shipCenter.y + (dimension.y / 4);
				
			direction = Constants.N;
			rotation = 90f;
			
		}
		
		// move down
		
		if (pDirection == Constants.S) {
			
			position.x = shipCenter.x - (dimension.x / 2) - Constants.SIDEBULLETADJUSTMENT; 
			position.y = shipCenter.y + (dimension.y / 4);
				
			direction = Constants.S;
			rotation = 270f;
			
		}
		
	}
	
	public void render (SpriteBatch batch) {
		if (direction != Constants.NIL) {

		TextureRegion reg = null;

		reg = Bullet;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
			rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		
		}
	}

	public int getScore () {
		return 100;
	}

}

