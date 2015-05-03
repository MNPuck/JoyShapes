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
import com.badlogic.gdx.math.Vector2;

public class Gem extends AbstractGameObject {

	private TextureRegion Gem;
	
	private TextureRegion regScore1;
	private TextureRegion regScore2;
	private TextureRegion regScore3;
	private TextureRegion regScore4;
	private TextureRegion regScore5;
	private TextureRegion regScore6;
	private TextureRegion regScore7;
	private TextureRegion regScore8;
	private TextureRegion regScore9;
	private TextureRegion regScore0;
	private TextureRegion regScoreX;

	public boolean active;
	
	private float rotation;
	
	private float gemTime;
	
	private boolean gemBlinkToggle;
	private float gemTimeSinceLastBlink;
	
	private String stringGuiScore;
	private char[] scoreArray;
	
	private boolean pickedUp;
	private float pickedUpTime;

	public Gem () {
		init();
	}

	private void init () {
		dimension.set(Constants.GEMSIZE, Constants.GEMSIZE);

		Gem = Assets.instance.gem.gem;
		
		regScore1 = Assets.instance.number1g.number1g;
		regScore2 = Assets.instance.number2g.number2g;
		regScore3 = Assets.instance.number3g.number3g;
		regScore4 = Assets.instance.number4g.number4g;
		regScore5 = Assets.instance.number5g.number5g;
		regScore6 = Assets.instance.number6g.number6g;
		regScore7 = Assets.instance.number7g.number7g;
		regScore8 = Assets.instance.number8g.number8g;
		regScore9 = Assets.instance.number9g.number9g;
		regScore0 = Assets.instance.number0g.number0g;
		regScoreX = Assets.instance.numberXg.numberXg;
		
		active = false;
		gemBlinkToggle = true;
		gemTimeSinceLastBlink = 0f;

		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		
		scoreArray = new char[7];
		pickedUp = false;
		pickedUpTime = 0f;
		
	}
	
	public void activate(float centerX, float centerY) {
		
		active = true;
		position.x = centerX;
		position.y = centerY;
		
		gemTime = 0f;
		gemBlinkToggle = true;
		gemTimeSinceLastBlink = 0f;
		
		pickedUp = false;
		pickedUpTime = 0f;
				
	}
	
	public void deactivate() {
		
		active = false;
		
		pickedUp = false;
		
	}
	
	public void gathered(int multiplier) {
		
		active = false;
		
		pickedUp = true;
		
		stringGuiScore = Integer.toString(multiplier);
		
		assignStringtoScoreArray(stringGuiScore);
		
	}
	
	
	private void assignStringtoScoreArray(String stringGuiScore) {
		
		for (int i=0; i < stringGuiScore.length(); i++) {
			
			scoreArray[i] = stringGuiScore.charAt(i);
			
		}
		
	}
	
	public void update (float deltaTime) {
		
		gemTime = gemTime + deltaTime;
		
		if (Constants.GEMLIFETIME - gemTime < Constants.GEMSTARTBLINK) {
			
			gemTimeSinceLastBlink = gemTimeSinceLastBlink + deltaTime;
			
			if (gemTimeSinceLastBlink > Constants.GEMBLINKFLIP) {
				
				gemBlinkToggle = !gemBlinkToggle;
				gemTimeSinceLastBlink = 0f;
				
			}
			
			
		}
			
		if (gemTime > Constants.GEMLIFETIME)	
			active = false;
		
		if (pickedUp) {
			
			pickedUpTime += deltaTime;
			
		}
		
		else {
			
			pickedUp = false;
		}
			
			
			
	}	

	public void render (SpriteBatch batch) {
		
		if (
			pickedUp &&
			pickedUpTime < Constants.GEMSCORETIME) {
			
			TextureRegion reg = null;
			
			reg = regScoreX;
			
			batch.draw(reg.getTexture(), position.x - .05f, position.y, origin.x, origin.y, 
					Constants.SCORESIZE, Constants.SCORESIZE, scale.x, scale.y,
					rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
				
			for (int i = 0; i < scoreArray.length; i++) {
					
				reg = null;
					
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
					
					batch.draw(reg.getTexture(), position.x + i * .05f, position.y, origin.x, origin.y, 
							Constants.SCORESIZE, Constants.SCORESIZE, scale.x, scale.y,
							rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
				}
					
			}
				
		}
					
		if (active && gemBlinkToggle) {

		TextureRegion reg = null;

		reg = Gem;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
			rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		
		}
		
	}

}
