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

public class Enemy4 extends Enemy {
	
	public Enemy4 () {
		init();
	}

	private void init () {

		regEnemy = Assets.instance.enemy4.enemy4;
		
		// init physics values
		
		terminalVelocity.x = 3;
		terminalVelocity.y = 3;
		friction.x = 0;
		friction.y = 0;
		accleration.x = 15;
		accleration.y = 15;
		velocity.x = 0;
		velocity.y = 0;
		
	}
	
	public void update (float deltaTime) {
		
		if (active) {
			
			
			// if velocity y equals zero; apply up or down velocity; if not zero don't apply velocity to continue on same path
			
			if (velocity.y == 0) {
				
				// if ship above enemy go up
				
				if (distanceYFromShip > 0) {
					
					velocity.y = 1.0f;
					
				}
				
				// if ship below enemy go down
			
				if (distanceYFromShip < 0) {
					
					velocity.y = -1.0f;
					
				}
				
			}
			
			// check to see if enemy is approaching bottom of game board, if so turn around
			
			if (position.y < - (Constants.GAMEBOARD_HEIGHT * .5f)) {
				
				velocity.y = 1.0f;
				
			}
			
			// check to see if enemy is approaching top of game board, if so turn around
			
			if (position.y + dimension.y > (Constants.GAMEBOARD_HEIGHT * .5f)) {
				
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

