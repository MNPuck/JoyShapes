/*******************************************************************************
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.util;

public class Constants {

	// Visible game world is 5 meters wide
	public static final float VIEWPORT_WIDTH = 9.0f;

	// Visible game world is 5 meters tall
	public static final float VIEWPORT_HEIGHT = 5.0f;
	
	// GameWorld Border
	
	public static final float GAMEBOARD_WIDTH = 9.0f;
	public static final float GAMEBOARD_HEIGHT = 5.0f;
	

	// GUI Width
	public static final float VIEWPORT_GUI_WIDTH = 1920.0f;

	// GUI Height
	public static final float VIEWPORT_GUI_HEIGHT = 1080.0f;

	// Location of description file for texture atlas
	public static final String TEXTURE_ATLAS_OBJECTS = 
	"../JoyShapes-android/assets/images/joyshapes.pack.atlas";
	
	// Number of Levels
	public static final int NUMBEROFLEVELS = 49;
	
	// Amount of extra lives at level start
	public static final int LIVES_START = 3;
	
	// Amount of bombs at level start
	public static final int BOMBS_START = 3;

	// Delay after game over
	public static final float TIME_DELAY_GAME_OVER = 3;
	
	//used for joystick adjustments
	
	public static final float SQUAREJOYADJUSTMENT = 0.5f;
	public static final float DIAGNOLJOYADJUSTMENT = 0.5f;
	
	//used for right joystick adjustments
	
	public static final float RIGHTJOYADJUSTMENT = 0.2f;
	
	//used for controller right trigger adjustment
	
	public static final float RIGHTTRIGGERADJUSTMENT = -0.5f;
	
	//used for controller left trigger adjustment
	
	public static final float LEFTTRIGGERADJUSTMENT = 0.5f;
	
	// directions used for movement
	
	public static final int NUM_DIRS = 9;
	public static final int N = 0;  // north, etc going clockwise
	public static final int NE = 1;
	public static final int E = 2;
	public static final int SE = 3;
	public static final int S = 4;
	public static final int SW = 5;
	public static final int W = 6;
	public static final int NW = 7;
	public static final int NIL = 8;
	
	// max bullets
	
	public static final int MAXBULLETS = 30;
	
	// time to spawn bullets
	
	public static final float BULLETSPAWNTIME = .10f;
	
	// max number of enemyS
	
	public static final int MAXENEMY1 = 24;
	public static final int MAXENEMY2 = 24;
	public static final int MAXENEMY3 = 24;
	public static final int MAXENEMY4 = 24;
	public static final int MAXENEMY5 = 24;
	
	// enemy spawn settings
	
	public static final float ENEMYSPAWNTIME = 1.0f;
	public static final float ENEMYSPAWNFLIP = .25f;
	
	// ship spawn settings
	public static final float SHIPSPAWNTIME = 1.0f;
	public static final float SHIPSPAWNFLIP = .25f;
	
	// Time to spend on level until next level is triggered
	public static final float LEVELTIMEAMOUNT = 5.0f;
	
	// Time for explosions, used to determine how long to show score
	public static final float EXPLOSIONDURATION = 0.7f;
	
	// Size of ship
	public static final float SHIPSIZE = .33f;
	
	// Size of enemies
	public static final float ENEMYSIZE = .33f;
			
	// Size of bullets
	public static final float BULLETSIZE = .075f;
	
	// Size of gems
	public static final float GEMSIZE = .10f;
	
	// Max gems
	public static final int MAXGEMS = 30;
	
	// Gem Life Time
	public static final float GEMLIFETIME = 5.0f;
	
	// Gem blink 
	public static final float GEMSTARTBLINK = 1.0f;
	public static final float GEMBLINKFLIP = .25f;
	
	// Adjust ship box hit size when gathering gems
	public static final float GEMSHIPGATHERBOX = 2.0f;
	
	// Size of Bomb
	public static final float BOMBSIZE = 5.0f;
	
	// Bomb Start Size
	public static final float BOMBSTARTSIZE = .33f;
	
	// Time of Bomb
	public static final float BOMBTIME = .5f;
	
	// Particle effect scale
	public static final float PARTICLESCALE = 0.0060f;
	
	// Enemys text score max
	public static final float EMEMYSTEXTSCORETIME = 3.0f;
	
	// Score Size
	public static final float SCORESIZE = .05f;
	
	// Time for Gem Score to display
	public static final float GEMSCORETIME = .50f;
	
	// Box size for ships that circle
	public static final float EDGEZONESIZE = .50f;
	
	// Game preferences file
	public static final String PREFERENCES = "joyshapes.prefs";
	
	// Side Bullet Adjustments
	public static final float SIDEBULLETADJUSTMENT = .075f;

}
