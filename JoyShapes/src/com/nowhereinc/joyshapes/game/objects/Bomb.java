/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.util.Constants;
import com.nowhereinc.joyshapes.util.Xbox360Pad;
import com.badlogic.gdx.math.Vector2;
import com.nowhereinc.joyshapes.game.objects.Ship;

public class Bomb extends AbstractGameObject {

	private TextureRegion Bomb;

	public boolean active;
	public boolean triggered;
	public boolean skipFirstRender;
	
	private float origCenterX;
	private float origCenterY;
	
	private float bombTime;
	
	private float rightTrigger;
	private float leftTrigger;

	public Bomb () {
		init();
	}

	private void init () {
		
		Bomb = Assets.instance.bomb.bomb;
		
		active = false;
		triggered = false;
		skipFirstRender = true;
		
		bombTime = 0f;
		
	}
	
	public void activate(float centerX, float centerY) {
		
		active = true;
		skipFirstRender = true;
		
		origCenterX = centerX;
		origCenterY = centerY;
		
		position.x = origCenterX - dimension.x * .5f;
		position.y = origCenterY - dimension.y * .5f;
		
		dimension.set(Constants.BOMBSTARTSIZE, Constants.BOMBSTARTSIZE);
		
		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
	
		bombTime = 0f;
		triggered = false;
		
	}
	
	public void deactivate() {
		
		active = false;
		
	}
	
	public void inputController(float rightTriggerIn, float leftTriggerIn) {
	
		rightTrigger = rightTriggerIn;
		leftTrigger = leftTriggerIn;
		
	}
	
	public void update (float deltaTime) {
		
		if (!active) {
				
			if (rightTrigger < Constants.RIGHTTRIGGERADJUSTMENT ||
				leftTrigger > Constants.LEFTTRIGGERADJUSTMENT) 
				triggered = true;
		
		}
		
		else {
			
			bombTime = bombTime + deltaTime;
			
			if (bombTime > Constants.BOMBTIME) {
				
				active = false;
				triggered = false;
				
			}
			
			else {
				
				skipFirstRender = false;
				
				float bombSizeRatio = bombTime / Constants.BOMBTIME;
				float bombCurrentSize = Constants.BOMBSIZE * bombSizeRatio + Constants.BOMBSTARTSIZE;
				
				
				dimension.set(bombCurrentSize, bombCurrentSize);
				// Set bounding box for collision detection
				bounds.set(0, 0, dimension.x, dimension.y);
				
				position.x = origCenterX - (bombCurrentSize * .5f);
				position.y = origCenterY - (bombCurrentSize * .5f);
			
			
			}
				
		}	
	
	}	

	public void render (SpriteBatch batch) {
		
		if (active && !skipFirstRender) {

			TextureRegion reg = null;

			reg = Bomb;
			batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
				rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			
			}
		
	}
	
}