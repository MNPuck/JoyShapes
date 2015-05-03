/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game.objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.game.WorldController;
import com.nowhereinc.joyshapes.util.Constants;
import com.nowhereinc.joyshapes.util.Xbox360Pad;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ship extends AbstractGameObject {

	private TextureRegion Ship;
	
	public boolean spawnShip;
	
	private float spawnTime;
	private float spawnFlip;
	private boolean spawnToggle;
	
	private float leftXAxis;
	private float leftYAxis;
	
	private boolean moveSet;
	
	public boolean active;
	
	public ParticleEffect explosionParticles = new ParticleEffect();
	public ParticleEffect smokeParticles = new ParticleEffect();
	
	public boolean explosion;

	public Ship () {
		init();
	}

	private void init () {
		
		dimension.set(Constants.SHIPSIZE, Constants.SHIPSIZE);

		Ship = Assets.instance.ship.ship;

		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);

		active = true;
		spawnShip = false;
		
		explosionParticles.load(Gdx.files.internal("../JoyShapes-android/assets/particles/shipExp.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		smokeParticles.load(Gdx.files.internal("../JoyShapes-android/assets/particles/shipSmoke.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		// Set up particle scale
		
		float pScale = 0.005f;

	    float scaling = smokeParticles.getEmitters().get(0).getScale().getHighMax();
	    smokeParticles.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = smokeParticles.getEmitters().get(0).getScale().getLowMax();
	    smokeParticles.getEmitters().get(0).getScale().setLow(scaling * pScale);

	    scaling = smokeParticles.getEmitters().get(0).getVelocity().getHighMax();
	    smokeParticles.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = smokeParticles.getEmitters().get(0).getVelocity().getLowMax();
	    smokeParticles.getEmitters().get(0).getVelocity().setLow(scaling * pScale);

		explosion = false;
		
		// init physics values
		
		terminalVelocity.x = 3.5f;
		terminalVelocity.y = 3.5f;
		friction.x = 15;
		friction.y = 15;
		accleration.x = 30;
		accleration.y = 30;
		velocity.x = 0;
		velocity.y = 0;
		
	}
	
	public void spawn() {
		
		spawnShip = true;
		spawnTime = 0f;
		spawnFlip = 0f;
		spawnToggle = true;
		
		explosionParticles.load(Gdx.files.internal("../JoyShapes-android/assets/particles/shipExp.pfx"),
								Gdx.files.internal("../JoyShapes-android/assets/particles"));

		
		explosion = false;
		active = false;
		
		velocity.x = 0;
		velocity.y = 0;
	
	}
	
	public void inputController (float leftXAxisIn, float leftYAxisIn) {
		
		leftXAxis = leftXAxisIn;
		leftYAxis = leftYAxisIn;
	
	}
	
	public void update (float deltaTime) {
	
			
		// new ship movement
			
		if (velocity.x != 0) {
				
			// Apply friction
			if (velocity.x > 0) {
				velocity.x = Math.max(velocity.x - friction.x * deltaTime, 0);
			} else {
				velocity.x = Math.min(velocity.x + friction.x * deltaTime, 0);
			}
		}
			
		velocity.x += accleration.x * deltaTime * leftXAxis;
		velocity.x = MathUtils.clamp(velocity.x, -terminalVelocity.x, terminalVelocity.x);
		
		position.x += velocity.x * deltaTime;
		
		if (velocity.y != 0) {
				
			// Apply friction
			if (velocity.y > 0) {
				velocity.y = Math.max(velocity.y - friction.y * deltaTime, 0);
			} else {
				velocity.y = Math.min(velocity.y + friction.y * deltaTime, 0);
			}
		}
			
		velocity.y += accleration.y * deltaTime * (leftYAxis * -1);
		velocity.y = MathUtils.clamp(velocity.y, -terminalVelocity.y, terminalVelocity.y);
			
		position.y += velocity.y * deltaTime;		
			
		// check to see if ship is on edges of screen
			
		if (position.x > (Constants.GAMEBOARD_WIDTH * .5f) - dimension.x) {
				
			position.x = (Constants.GAMEBOARD_WIDTH * .5f) - dimension.x;
	
		}
			
		if (position.x < - Constants.GAMEBOARD_WIDTH * .5f) {
				
			position.x = - Constants.GAMEBOARD_WIDTH * .5f;
				
		}
			
		if (position.y > (Constants.GAMEBOARD_HEIGHT * .5f) - dimension.y) {
				
			position.y = (Constants.GAMEBOARD_HEIGHT * .5f) - dimension.y;
				
		}
			
		if (position.y < - Constants.GAMEBOARD_HEIGHT * .5f) {
				
			position.y =  - Constants.GAMEBOARD_HEIGHT * .5f;
				
		}
			
		// apply smoke particles if ship moving
			
		if ((velocity.x > 0.05f || velocity.x < -0.05f) &&
		    (velocity.y > 0.05f || velocity.y < -0.05f)) {
				
			smokeParticles.setPosition(position.x + dimension.x * .5f, position.y + dimension.y * .5f);
			smokeParticles.start();
			
			smokeParticles.update(deltaTime);
		}
			
		
		// spawn ship after loses life
		
		if (!active && spawnShip) {
			
			spawnTime = spawnTime + deltaTime;
			spawnFlip = spawnFlip + deltaTime;

			if (spawnTime > Constants.SHIPSPAWNTIME) {
			
				active = true;
				spawnShip = false;
				spawnFlip = 0f;
			
			}
		
			if (spawnFlip > Constants.SHIPSPAWNFLIP) {
				
				spawnFlip = 0f;
				spawnToggle = !spawnToggle;				
				
			}
		
		}
		
		// animate explosion if ship in explosion state.
		
		if (explosion) {
			
			if (explosionParticles.isComplete()) {
				explosion = false;
				spawn();
			}
			
		else
			explosionParticles.update(deltaTime);
			
		}

	}
	
	public void activate() {
		
		active = true;
				
	}
	
	public void deactivate() {
		
		active = false;
		spawnToggle = false;
		
		explosion = true;
		
		float pScale = 0.01f;

	    float scaling = explosionParticles.getEmitters().get(0).getScale().getHighMax();
	    explosionParticles.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = explosionParticles.getEmitters().get(0).getScale().getLowMax();
	    explosionParticles.getEmitters().get(0).getScale().setLow(scaling * pScale);

	    scaling = explosionParticles.getEmitters().get(0).getVelocity().getHighMax();
	    explosionParticles.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = explosionParticles.getEmitters().get(0).getVelocity().getLowMax();
	    explosionParticles.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
		
	    explosionParticles.setPosition(position.x + dimension.x * .5f, position.y + dimension.y * .5f);
		
		explosionParticles.start();
		
	}

	public float returnCenterX() {
		
		return position.x + dimension.x * .5f;
		
	}
	
	public float returnCenterY() {
		
		return position.y + dimension.y * .5f;
		
	}
	
	public void render (SpriteBatch batch) {
		
		if (explosion) {
			
			explosionParticles.draw(batch);
			
		}
		
		if (active || spawnToggle) {

			if (!WorldController.isGameOver()) {
		
				TextureRegion reg = null;
				
				if ((velocity.x > 0.05f || velocity.x < -0.05f) &&
				    (velocity.y > 0.05f || velocity.y < -0.05f)) {

					smokeParticles.draw(batch);
					
				}
				
				else {
					
					smokeParticles.reset();
					
				}
				
				reg = Ship;
				batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
						rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
				
				
			}
		
		}
	}

	public int getScore () {
		return 100;
	}

}
