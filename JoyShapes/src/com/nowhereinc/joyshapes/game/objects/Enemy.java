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
import com.badlogic.gdx.utils.Array;

public class Enemy extends AbstractGameObject {

	public TextureRegion regEnemy;
	
	public TextureRegion regScore1;
	public TextureRegion regScore2;
	public TextureRegion regScore3;
	public TextureRegion regScore4;
	public TextureRegion regScore5;
	public TextureRegion regScore6;
	public TextureRegion regScore7;
	public TextureRegion regScore8;
	public TextureRegion regScore9;
	public TextureRegion regScore0;
	
	
	public boolean spawnEnemy;
	
	public float spawnTime;
	public float spawnFlip;
	public boolean spawnToggle;

	public boolean active;
	
	public int currentDirection;
	
	public boolean explosion;
	
	private String stringGuiScore;
	
	public char[] scoreArray;
	
	public float distanceXFromShip;
	public float distanceYFromShip;
	public float distanceFromShip;
	
	public boolean inactive;
	
	public float explosionTime;

	public Enemy () {
		init();
	}
	
	private void init () {
		
		dimension.set(Constants.ENEMYSIZE, Constants.ENEMYSIZE);
		
		regScore1 = Assets.instance.number1.number1;
		regScore2 = Assets.instance.number2.number2;
		regScore3 = Assets.instance.number3.number3;
		regScore4 = Assets.instance.number4.number4;
		regScore5 = Assets.instance.number5.number5;
		regScore6 = Assets.instance.number6.number6;
		regScore7 = Assets.instance.number7.number7;
		regScore8 = Assets.instance.number8.number8;
		regScore9 = Assets.instance.number9.number9;
		regScore0 = Assets.instance.number0.number0;

		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);

		active = false;
		spawnEnemy = false;
		
		currentDirection = Constants.NIL;

		explosion = false;
		
		scoreArray = new char[7];
	
		// init distance values
		
		distanceXFromShip = 0f;
		distanceYFromShip = 0f;
		distanceFromShip = 0f;
		
		inactive = true;
		
		explosionTime = 0f;
		
	}

	public void spawn() {
		
		spawnEnemy = true;
		spawnTime = 0f;
		spawnFlip = 0f;
		spawnToggle = true;

		explosion = false;
		
		// init distance values
		
		distanceXFromShip = 0f;
		distanceYFromShip = 0f;
		distanceFromShip = 0f;
		
		velocity.x = 0f;
		velocity.y = 0f;
		
		AudioManager.instance.play(Assets.instance.sounds.enemySpawn, .3f);
		
		inactive = false;
		
		explosionTime = 0f;

	}
	
	public void calculateDistanceFromShip (float shipCenterX, float shipCenterY) {
		
		if (active) {
		
			// calculate distance X from ship, if value negative enemy is to left of ship, if positive enemy to right of ship
			
			distanceXFromShip = shipCenterX - position.x;
			
			// calculate distance y from ship, if value negative enemy is above the ship, if negative enemy is above the ship
			
			distanceYFromShip = shipCenterY - position.y;
			
			// calculate the distance from ship which is the hypotenuse (diagnol)
			
			float distanceXSqrd = distanceXFromShip * distanceXFromShip;
			float distanceYSqrd = distanceYFromShip * distanceYFromShip;
			
			distanceFromShip = (float) Math.sqrt(distanceXSqrd + distanceYSqrd);

		}
		
		else
			
		{
			
			distanceXFromShip = 0f;
			distanceYFromShip = 0f;
			distanceFromShip = 0f;
			
		}
	
	}
	
	public void update (float deltaTime) {
		
	}
	
	public float returnCenterX() {
		
		return position.x + dimension.x * .5f;
		
	}
	
	public float returnCenterY() {
		
		return position.y + dimension.y * .5f;
		
	}
	
	public void clear() {
		
		active = false;
		spawnToggle = false;
		inactive = true;
		
	}
	
	public void deactivate(int enemyScore) {
		
		active = false;
		spawnToggle = false;
		
		explosion = true;
	
		// Move score to string to loop thru and assign to correct images
		
		stringGuiScore = Integer.toString(enemyScore);
		
		assignStringtoScoreArray(stringGuiScore);
		
	}
	
	private void assignStringtoScoreArray(String stringGuiScore) {
		
		for (int i = 0; i < stringGuiScore.length(); i++) {
			
			scoreArray[i] = stringGuiScore.charAt(i);
			
		}
		
	}

	public void render (SpriteBatch batch) {
		
		if (explosion) {
			
			for (int i = 0; i < scoreArray.length; i++) {
				
				TextureRegion reg = null;
				
				switch (scoreArray[i]) {
					
					case '1':
					
						reg = regScore1;
						break;		
					
					case '2':
					
						reg = regScore2;
						break;
						
					case '3':
						
						reg = regScore3;
						break;		
						
					case '4':
						
						reg = regScore4;
						break;		
					
					case '5':
						
						reg = regScore5;
						break;		
						
					case '6':
						
						reg = regScore6;
						break;		
						
					case '7':
						
						reg = regScore7;
						break;		
						
					case '8':
						
						reg = regScore8;
						break;		
						
					case '9':
						
						reg = regScore9;
						break;		
						
					case '0':
						
						reg = regScore0;
						break;
						
				}
				
				if (reg != null) {	
				
				batch.draw(reg.getTexture(), position.x + i * .05f, position.y + dimension.y, 
						origin.x, origin.y, Constants.SCORESIZE, Constants.SCORESIZE, scale.x, scale.y,
						rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(),
						false, false);
				}
				
			}
			
		}
		
		if (active || spawnToggle){

		TextureRegion reg = null;

		reg = regEnemy;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
			rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		
		}
	}

	public int getScore () {
	
		return 10;
		
	}
	
}
