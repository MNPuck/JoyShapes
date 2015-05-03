/*****************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game.objects;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.util.Constants;
import com.nowhereinc.joyshapes.util.Xbox360Pad;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Vector2;

public class Border extends AbstractGameObject {

	private TextureRegion Border;

	public Border () {
		init();
	}

	private void init () {
		dimension.set(Constants.GAMEBOARD_WIDTH, Constants.GAMEBOARD_HEIGHT);
		
		Border = Assets.instance.border.border;

	}

	public void render (SpriteBatch batch) {
		
		TextureRegion reg = null;
		
		float newPositionx = position.x - (dimension.x * .5f);
		float newPositiony = position.y - (dimension.y * .5f);

		reg = Border;
		batch.draw(reg.getTexture(), newPositionx, newPositiony, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
			rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		
	}

}

