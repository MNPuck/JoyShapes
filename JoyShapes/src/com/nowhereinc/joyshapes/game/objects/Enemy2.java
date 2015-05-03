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

public class Enemy2 extends Enemy {

	public Enemy2 () {
		init();
	}

	private void init () {

		regEnemy = Assets.instance.enemy2.enemy2;
		
		// init physics values
		
		terminalVelocity.x = 1.5f;
		terminalVelocity.y = 1.5f;
		friction.x = 0;
		friction.y = 0;
		accleration.x = 2.5f;
		accleration.y = 2.5f;
		velocity.x = 0;
		velocity.y = 0;
		
	}

	public void update (float deltaTime) {
		
		if (active) {
			
			// apply x before assigning velocity 
			
			if (velocity.x != 0) {
				
				// Apply friction
				if (velocity.x > 0) {
					velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
				} else {
					velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
				}
			}			
			
			// assign velocity based on enemy location relative to ship
			
			if (distanceXFromShip > 0) {
				
				velocity.x += accleration.x * deltaTime * 1.0f;
				
			}
			
			if (distanceXFromShip < 0) {
				
				velocity.x += accleration.x * deltaTime * -1.0f;
				
			}
			
			if (distanceXFromShip == 0) {
				
				velocity.x = 0.0f;
				
			}
			
			// limit speed to terminal velocity
			
			velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
			
			// update position x
			
			position.x += velocity.x * deltaTime;
			
			// apply y before assigning velocity 
			
			if (velocity.y != 0) {
				
				// Apply friction
				if (velocity.y > 0) {
					velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
				} else {
					velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
				}
			}
			
			// assign velocity based on enemy location relative to ship
			
			if (distanceYFromShip > 0) {
				
				velocity.y += accleration.y * deltaTime * 1.0f;				
			}
			
			if (distanceYFromShip < 0) {
				
				velocity.y += accleration.y * deltaTime * -1.0f;		
				
			}
			
			if (distanceYFromShip == 0) {
				
				velocity.y = 0.0f;
				
			}
			
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