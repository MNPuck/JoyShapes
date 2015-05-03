/*******************************************************************************
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.nowhereinc.joyshapes.game.Assets;
import com.nowhereinc.joyshapes.game.WorldController;
import com.nowhereinc.joyshapes.screens.DirectedGame;
import com.nowhereinc.joyshapes.screens.GameScreen;
import com.nowhereinc.joyshapes.util.GamePreferences;

public class JoyShapesMain extends DirectedGame {

	@Override
	public void create () {
		// Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_NONE);
		// Load assets
		Assets.instance.init(new AssetManager());
		// Initialize controller and renderer
		
		//ScreenTransition transition = ScreenTransitionSlice.init(2, ScreenTransitionSlice.UP_DOWN, 10, Interpolation.pow5Out);
		
		setScreen(new GameScreen(this));
		
	   // Assets.instance.dispose();

		
	}

}
