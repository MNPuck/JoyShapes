/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.util.AudioManager;
import com.nowhereinc.joyshapes.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;

public class Enemy1 extends Enemy {

	public Enemy1 () {
		init();
	}
	
	private void init () {

		
		regEnemy = Assets.instance.enemy1.enemy1;
		
		// init physics values
		
		terminalVelocity.x = 2;
		terminalVelocity.y = 2;
		friction.x = 0;
		friction.y = 0;
		accleration.x = 5;
		accleration.y = 5;
		velocity.x = 0;
		velocity.y = 0;
		
	}
	
	public void update (float deltaTime) {
		
		if (active) {
			
			// if velocity x equals zero; apply left or right velocity; if not zero don't apply velocity to continue on same path
			
			if (velocity.x == 0) {
				
				// if ship is to left of enemy go left
				
				if (distanceXFromShip <= 0) {
					
					velocity.x = -1.0f;
					
				}
				
				// if ship is to right of enemy go right
				
				if (distanceXFromShip > 0) {
					
					velocity.x = 1.0f;
					
				}
				
			}
			
			// if velocity y equals zero; apply up or down velocity; if not zero don't apply velocity to continue on same path
			
			if (velocity.y == 0) {
				
				// if ship above enemy go up
				
				if (distanceYFromShip >= 0) {
					
					velocity.y = 1.0f;
					
				}
				
				// if ship below enemy go down
				
				if (distanceYFromShip < 0) {
					
					velocity.y = -1.0f;
					
				}
				
			}
			
			// check to see if enemy is to the left of gameboard, if so; place left side of gameboard and change velocity to +1;
			
			if (position.x < - Constants.GAMEBOARD_WIDTH * .5f) {
				
				velocity.x = 1.0f;
				
			}
			
			// check to see if enemy is to the right of gameboard, if so; place left side of gameboard and change velocity to +1;
			
			if (position.x + dimension.x > Constants.GAMEBOARD_WIDTH * .5f) {
				
				velocity.x = -1.0f;
				
			}
			
			// get new velocity
			
			velocity.x += accleration.x * deltaTime * velocity.x;
			
			// limit speed to terminal velocity
			
			velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
			
			// update position x
			
			position.x += velocity.x * deltaTime;
			
			
			// if velocity y equals zero; apply up or down velocity; if not zero don't apply velocity to continue on same path
			
			if (velocity.y == 0) {
			
				int randomDirectionY = (int) (Math.random() * 2 + 1);
				
				// if random direction is 1 go down
				
				if (randomDirectionY == 1) {
					
					velocity.y = -1.0f;
					
				}
				
				// if random direction is 2 go up
				
				if (randomDirectionY == 2) {
					
					velocity.y = 1.0f;
					
				}
				
			}
			
			// check to see if enemy is below bottom of game board, if so; place bottom of board and change velocity to +1
			
			if (position.y < - Constants.GAMEBOARD_HEIGHT * .5f) {
				
				velocity.y = 1.0f;
				
			}
			
			// check to see if enemy is above top of game board, if so; place top of board and change velocity to -1
			
			if (position.y + dimension.y > Constants.GAMEBOARD_HEIGHT * .5f) {
				
				velocity.y = -1.0f;
				
			}
			
			// get new velocity
			
			velocity.y += accleration.y * deltaTime * velocity.y;
			
			// limit speed to terminal velocity
			
			velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
			
			//update position y
			
			position.y += velocity.y * deltaTime;
					
		}
		
		if (!active && spawnEnemy) {
		
			spawnTime = spawnTime + deltaTime;
			spawnFlip = spawnFlip + deltaTime;

			if (spawnTime > Constants.ENEMYSPAWNTIME) {
			
			active = true;
			spawnEnemy = false;
			spawnFlip = 0f;
			
			}
			
			if (spawnFlip > Constants.ENEMYSPAWNFLIP) {
				
				spawnFlip = 0f;
				spawnToggle = !spawnToggle;	
				
			}
		
		}
		
		
		if (explosion) {
			
			explosionTime += deltaTime;
			
			if (explosionTime > Constants.EXPLOSIONDURATION) {
				
				explosion = false;
				inactive = true;
				
			}
		
		}
		
	}
	
}
