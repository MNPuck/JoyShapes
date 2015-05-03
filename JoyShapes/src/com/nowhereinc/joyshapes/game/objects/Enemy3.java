/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.util.AudioManager;
import com.nowhereinc.joyshapes.util.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

public class Enemy3 extends Enemy {

	public Enemy3 () {
		init();
	}

	private void init () {

		regEnemy = Assets.instance.enemy3.enemy3;
		
		// init physics values
		
		terminalVelocity.x = 3.5f;
		terminalVelocity.y = 3.5f;
		friction.x = 0;
		friction.y = 0;
		accleration.x = 15;
		accleration.y = 15;
		velocity.x = 0;
		velocity.y = 0;
		
	}
	
	public void update (float deltaTime) {
		
		if (active) {
			
			// if velocity x equals zero; apply left or right velocity; if not zero don't apply velocity to continue on same path
			
			if (velocity.x == 0) {
				
				// if ship is to left of enemy go left
				
				if (distanceXFromShip < 0) {
					
					velocity.x = -1.0f;
					
				}
				
				// if ship is to right of enemy go right
				
				if (distanceXFromShip > 0) {
					
					velocity.x = 1.0f;
					
				}
				
			}
			
			// check to see if enemy is approaching left of gameboard, if so; slow down and turn around
			
			if (position.x < - Constants.GAMEBOARD_WIDTH * .5f) {
				
				velocity.x = 1.0f;
				
			}
			
			// check to see if enemy is approaching right of gameboard, if so; slow down and turn around
			
			if (position.x + dimension.x > Constants.GAMEBOARD_WIDTH * .5f) {
			
				velocity.x = -1.0f;
				
			}
					
			// get new velocity
			
			velocity.x += accleration.x * deltaTime * velocity.x;
			
			// limit speed to terminal velocity
			
			velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
				
			// update position x
				
			position.x += velocity.x * deltaTime;
			
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

