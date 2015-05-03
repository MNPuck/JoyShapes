/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.nowhereinc.joyshapes.JoyShapesMain;
import com.badlogic.gdx.backends.lwjgl.LwjglFrame;
import com.badlogic.gdx.Application.ApplicationType;

public class Main {
	private static boolean rebuildAtlas = false;
	private static boolean drawDebugOutline = false;

	public static void main (String[] args) {
		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.duplicatePadding = false;
			settings.debug = drawDebugOutline;
			TexturePacker.process(settings, "assets-raw/images", "../JoyShapes-android/assets/images", "joyshapes.pack");
		}
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Joy Shapes";
		cfg.width = 1920;
		cfg.height = 1080;
		cfg.fullscreen = true;
		cfg.vSyncEnabled = true;
		
		new LwjglApplication(new JoyShapesMain(), cfg);
		
	}
	
}
		

