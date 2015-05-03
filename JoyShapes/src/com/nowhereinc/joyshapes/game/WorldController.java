/*******************************************************************************
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.nowhereinc.joyshapes.game.objects.Bullet;
import com.nowhereinc.joyshapes.game.objects.Enemy;
import com.nowhereinc.joyshapes.game.objects.Enemy1;
import com.nowhereinc.joyshapes.game.objects.Enemy2;
import com.nowhereinc.joyshapes.game.objects.Enemy3;
import com.nowhereinc.joyshapes.game.objects.Enemy4;
import com.nowhereinc.joyshapes.game.objects.Enemy5;
import com.nowhereinc.joyshapes.game.objects.Gem;
import com.nowhereinc.joyshapes.game.objects.Ship;
import com.nowhereinc.joyshapes.screens.DirectedGame;
import com.nowhereinc.joyshapes.util.AudioManager;
import com.nowhereinc.joyshapes.util.CameraHelper;
import com.nowhereinc.joyshapes.util.Constants;
import com.nowhereinc.joyshapes.util.Xbox360Pad;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.nowhereinc.joyshapes.screens.GameScreen;



public class WorldController extends InputAdapter  {

	private static final String TAG = WorldController.class.getName();
	
	private DirectedGame game;
	public Level level;
	public static int bombs;
	public static int lives;
	public static int score; 
	public int enemyScore;
	public static int multiplier;
	public static int levelNumber;
	public static boolean gameOver;
	public static boolean escPressed;
	public boolean endLevel;
	public boolean clearScreenSwitch;
	public boolean score10k;
	public boolean score100k;
	public boolean score1Mil;
	
	private float levelTime;
	
	private Controller player1Controller;
	
	private static boolean startPressed;
	private static boolean selectPressed;

	// public CameraHelper cameraHelper;
	
	// Rectangles for collision detection
	private Rectangle r1 = new Rectangle();
	private Rectangle r2 = new Rectangle();

	public WorldController (DirectedGame game) {
		this.game = game;
		init();
	}

	private void init () {
		
		Gdx.input.setInputProcessor(this);
		//cameraHelper = new CameraHelper();
		lives = Constants.LIVES_START;
		bombs = Constants.BOMBS_START;
		gameOver = false;
		escPressed = false;
		score10k = false;
		score100k = false;
		score1Mil = false;
		
		player1Controller = Controllers.getControllers().get(0);
		startPressed = false;
		selectPressed = false;
		
		initLevel();
		
	}
	
	public static boolean isGameOver () {
		return gameOver;
	}
	
	public static boolean isEscPressed () {
		return escPressed;
	}
	
	public static boolean isStartPressed() {
		return startPressed;
	}
	
	public static boolean isSelectPressed() {
		return selectPressed;
	}

	private void initLevel() {
		
		score = 0;
		multiplier = 1;
		endLevel = false;
		levelNumber = 1;
		level = new Level();
		//cameraHelper.setTarget(level.ship);
		levelTime = 0f;
		clearScreenSwitch = false;

	}

	public void update (float deltaTime) {
		
		if (!gameOver) {
			
			//handleDebugInput(deltaTime);
			//cameraHelper.update(deltaTime);
			readController();
			level.update(deltaTime);
			testCollisions();
			checkScores();
		
			if (clearScreenSwitch)
				clearScreen();
		
			if (lives == 0 &&
				!level.ship.explosion) {
			
				gameOver = true;

			}
		
			if (!gameOver && !level.ship.spawnShip && !level.ship.explosion) {
				endLevel = false;
				checkForEndLevelTime(deltaTime);
			}
		
			if (endLevel &&
				!gameOver) {
				
				levelNumber++;
				
				level.newLevel();
				
			}
			
		}
		
		else {
			
			checkEscKey();
			checkBackKey();
			
		}
		
	}
	
	private void readController() {
		
		//ship move 
		float leftXAxis = player1Controller.getAxis(Xbox360Pad.AXIS_LEFT_X);
		float leftYAxis = player1Controller.getAxis(Xbox360Pad.AXIS_LEFT_Y);
		level.ship.inputController(leftXAxis, leftYAxis);
		
		//bulletShot
		float rightXAxis = player1Controller.getAxis(Xbox360Pad.AXIS_RIGHT_X);
		float rightYAxis = player1Controller.getAxis(Xbox360Pad.AXIS_RIGHT_Y);
		level.bullets.get(level.bulletCounter).inputController(rightXAxis, rightYAxis);
		
		//bomb check
		float rightTrigger = player1Controller.getAxis(Xbox360Pad.AXIS_RIGHT_TRIGGER);
		float leftTrigger = player1Controller.getAxis(Xbox360Pad.AXIS_LEFT_TRIGGER);
		level.bomb.inputController(rightTrigger, leftTrigger);
		
		//pause check
		startPressed = player1Controller.getButton(Xbox360Pad.BUTTON_START);
			
	}
	
	private void testCollisions() {

		// Test collision: Bullets <-> Enemys
		
	    int enemyCount = 0;
		
		for (Bullet bullet : level.bullets) {
			 r1.set(bullet.position.x, bullet.position.y, bullet.bounds.width, bullet.bounds.height);
			
			 if (level.enemys1Counter > 0) {
				 
				enemyCount = 0;
				 
				 for (Enemy1 enemy1 : level.enemys1) {
					 r2.set(enemy1.position.x, enemy1.position.y, enemy1.bounds.width, enemy1.bounds.height);
					 enemyCount++;
					 
					 if (!enemy1.active)
						 break;
					 
					 if (!r1.overlaps(r2) || 
						 bullet.returnDirection() == Constants.NIL) continue;
					 	 
					 	onCollisionBulletWithEnemy1(enemy1, bullet, enemyCount);
					
				 }
				 
			 }
			 
			 if (level.enemys2Counter > 0) {
				 
				 enemyCount = 0;
			
				 for (Enemy2 enemy2 : level.enemys2) {
					 r2.set(enemy2.position.x, enemy2.position.y, enemy2.bounds.width, enemy2.bounds.height);
					 enemyCount++;
					 
					 if (!enemy2.active)
						 break;
					 
					 if (!r1.overlaps(r2) || 
						bullet.returnDirection() == Constants.NIL) continue;
				  
					 	onCollisionBulletWithEnemy2(enemy2, bullet, enemyCount);
				 }
			 
			 }
			 
			 if (level.enemys3Counter > 0) {
				 
				 enemyCount = 0;
			 
				 for (Enemy3 enemy3 : level.enemys3) {
					 r2.set(enemy3.position.x, enemy3.position.y, enemy3.bounds.width, enemy3.bounds.height);
					 enemyCount++;
					 
					 if (!enemy3.active)
						 break;
					 
					 if (!r1.overlaps(r2) ||
						bullet.returnDirection() == Constants.NIL) continue;
				  
					 onCollisionBulletWithEnemy3(enemy3, bullet, enemyCount);
				 }
				 
			 }
			 
			 if (level.enemys4Counter > 0) {
				 
				 enemyCount = 0;
			 
				 for (Enemy4 enemy4 : level.enemys4) {
					 r2.set(enemy4.position.x, enemy4.position.y, enemy4.bounds.width, enemy4.bounds.height);
					 enemyCount++;
					 
					 if (!enemy4.active)
						 break;
					 
					 if (!r1.overlaps(r2) ||
						bullet.returnDirection() == Constants.NIL) continue;
				  
					 	onCollisionBulletWithEnemy4(enemy4, bullet, enemyCount);
				 }
				 
			 }
			 
			 if (level.enemys5Counter > 0) {
				 
				 enemyCount = 0;
			 
				 for (Enemy5 enemy5 : level.enemys5) {
					 r2.set(enemy5.position.x, enemy5.position.y, enemy5.bounds.width, enemy5.bounds.height);
					 enemyCount++;
					 
					 if(!enemy5.active)
						 break;
					 
					 if (!r1.overlaps(r2) ||
						bullet.returnDirection() == Constants.NIL) continue;
				  
					 onCollisionBulletWithEnemy5(enemy5, bullet, enemyCount);
				 }
				 
			 }
												
		}
		
		// if bomb is active check for collisions with enemys
		
		if (level.bomb.active) {
			
			 r1.set(level.bomb.position.x, level.bomb.position.y,
					level.bomb.bounds.width, level.bomb.bounds.height);
			 
			 if (level.enemys1Counter > 0) {
				 
				 enemyCount = 0;
			 
				 for (Enemy1 enemy1 : level.enemys1) {
					 r2.set(enemy1.position.x, enemy1.position.y, enemy1.bounds.width, enemy1.bounds.height);
					 enemyCount++;
					 
					 if (!enemy1.active)
						 break;
					 
					 if (!r1.overlaps(r2))
						continue;
					 
					 onCollisionBombWithEnemy1(enemy1, enemyCount);
					 
				 }
			
			 }
			 
			 if (level.enemys2Counter > 0) {
				 
				 enemyCount = 0;
				 
				 for (Enemy2 enemy2 : level.enemys2) {
					 r2.set(enemy2.position.x, enemy2.position.y, enemy2.bounds.width, enemy2.bounds.height);
					 enemyCount++;
					 
					 if (!enemy2.active)
						 break;
					 
					 if (!r1.overlaps(r2)) 
						 continue;
				 
					 onCollisionBombWithEnemy2(enemy2, enemyCount);
					 
				 }
			
			 }
			 
			 
			 if (level.enemys3Counter > 0) {
				 
				 enemyCount = 0;
				 
				 for (Enemy3 enemy3 : level.enemys3) {
					 r2.set(enemy3.position.x, enemy3.position.y, enemy3.bounds.width, enemy3.bounds.height);
					 enemyCount++;
					 
					 if (!enemy3.active)
						 break;
					
					 if (!r1.overlaps(r2))
						 continue;
				 
					 onCollisionBombWithEnemy3(enemy3, enemyCount);
					 
				 }
			
			 }
			 
			 if (level.enemys4Counter > 0) {
				 
				 enemyCount = 0;
				 
				 for (Enemy4 enemy4 : level.enemys4) {
					 r2.set(enemy4.position.x, enemy4.position.y, enemy4.bounds.width, enemy4.bounds.height);
					 enemyCount++;
					 
					 if (!enemy4.active)
						 break;
					 
					 if (!r1.overlaps(r2))
						continue;
				 
					 onCollisionBombWithEnemy4(enemy4, enemyCount);
					 
				 }
			
			 }
			 
			 if (level.enemys5Counter > 0) {
				 
				 enemyCount = 0;
				 
				 for (Enemy5 enemy5 : level.enemys5) {
					 r2.set(enemy5.position.x, enemy5.position.y, enemy5.bounds.width, enemy5.bounds.height);
					 enemyCount++;
					 
					 if (!enemy5.active)
						 break;
					 
					 if (!r1.overlaps(r2))
						continue;
				 
					 onCollisionBombWithEnemy5(enemy5, enemyCount);
					 
				 }
			
			 }
			 
		}
		
		// Test collision: Ship <-> Gems (make ship hit box bigger)
		
		if (level.ship.active) {
			
			float biggerShipWidth = level.ship.bounds.width * Constants.GEMSHIPGATHERBOX;
			float biggerShipHeight = level.ship.bounds.height * Constants.GEMSHIPGATHERBOX;
			
			r1.set(level.ship.position.x, level.ship.position.y, biggerShipWidth, biggerShipHeight);
			
			for (Gem gem : level.gems) {
				r2.set(gem.position.x, gem.position.y, gem.bounds.width, gem.bounds.height);
				if (!r1.overlaps(r2) || !gem.active) continue;
				onCollisionShipWithGem(gem);
				
			}
			
		}
		
		boolean shipHit = false;
		
		if (level.ship.active) {
		
			r1.set(level.ship.position.x, level.ship.position.y, level.ship.bounds.width, level.ship.bounds.height);

			// Test collision: Ship <-> Enemy1
		
			if (level.enemys1Counter > 0) {
			
				for (Enemy1 enemy1 : level.enemys1) {
					r2.set(enemy1.position.x, enemy1.position.y, enemy1.bounds.width, enemy1.bounds.height);
					if (!enemy1.active)
						break;
					
					if (!r1.overlaps(r2)) continue;
					else {
						onCollisionShipWithEnemy();
						shipHit = true;
						break;
					}
				
				}
		
			}
		
			// Test collision: Ship <-> Enemy2
		
			if (level.enemys2Counter > 0 && !shipHit) {
		
				for (Enemy2 enemy2 : level.enemys2) {
					r2.set(enemy2.position.x, enemy2.position.y, enemy2.bounds.width, enemy2.bounds.height);
					if (!enemy2.active)
						break;
					
					if (!r1.overlaps(r2)) continue;
					else {
						onCollisionShipWithEnemy();
						shipHit = true;
						break;
					}
				 
				}
					
			}
		
			// Test collision: Ship <-> Enemy3
		 
			if (level.enemys3Counter > 0 && !shipHit) {
			
				for (Enemy3 enemy3 : level.enemys3) {
					r2.set(enemy3.position.x, enemy3.position.y, enemy3.bounds.width, enemy3.bounds.height);
					if (!enemy3.active)
						break;
					
					if (!r1.overlaps(r2)) continue;
					else {
						onCollisionShipWithEnemy();
						shipHit = true;
						break;
					}
				
				}
							
			}
		
			// Test collision: Ship <-> Enemy4
		
			if (level.enemys4Counter > 0 && !shipHit) {
			
				for (Enemy4 enemy4 : level.enemys4) {
					r2.set(enemy4.position.x, enemy4.position.y, enemy4.bounds.width, enemy4.bounds.height);
					if (!enemy4.active)
						break;
					
					if (!r1.overlaps(r2)) continue;
					else {
						onCollisionShipWithEnemy();
						shipHit = true;
						break;
					}
				
				}
									
			}
		
			// Test collision: Ship <-> Enemy5
		
			if (level.enemys5Counter > 0 && !shipHit) {
			
				for (Enemy5 enemy5 : level.enemys5) {
					r2.set(enemy5.position.x, enemy5.position.y, enemy5.bounds.width, enemy5.bounds.height);
					if (!enemy5.active)
						break;
					
					if (!r1.overlaps(r2)) continue;
					else {
						onCollisionShipWithEnemy();
						shipHit = true;
						break;
					}	
				
				}
										
			}
		
		}

	}
	
	private void onCollisionBulletWithEnemy1(Enemy1 enemy1, Bullet bullet, int enemyCount) {
		
		enemyDestroy(enemy1);
	
		level.enemy1Shot(enemy1, enemyCount);
		
		bullet.reset();
				
	}
	
	private void onCollisionBulletWithEnemy2(Enemy2 enemy2, Bullet bullet, int enemyCount) {
		
		enemyDestroy(enemy2);
		
		level.enemy2Shot(enemy2, enemyCount);
		
		bullet.reset();
	
	}
	
	private void onCollisionBulletWithEnemy3(Enemy3 enemy3, Bullet bullet, int enemyCount) {
		
		enemyDestroy(enemy3);
		
		level.enemy3Shot(enemy3, enemyCount);
		
		bullet.reset();
	
	}
	
	
	private void onCollisionBulletWithEnemy4(Enemy4 enemy4, Bullet bullet, int enemyCount) {
		
		enemyDestroy(enemy4);
		
		level.enemy4Shot(enemy4, enemyCount);
		
		bullet.reset();
	
	}
	
	private void onCollisionBulletWithEnemy5(Enemy5 enemy5, Bullet bullet, int enemyCount) {
		
		
		enemyDestroy(enemy5);
		
		level.enemy5Shot(enemy5, enemyCount);
		
		bullet.reset();
	
	}
	
	private void onCollisionBombWithEnemy1(Enemy1 enemy1, int enemyCount) {
		
		
		enemyDestroy(enemy1);
		
		level.enemy1Shot(enemy1, enemyCount);
	
	}
	
	
	private void onCollisionBombWithEnemy2(Enemy2 enemy2, int enemyCount) {
		
		
		enemyDestroy(enemy2);
		
		level.enemy2Shot(enemy2, enemyCount);
	
	}
	
	
	private void onCollisionBombWithEnemy3(Enemy3 enemy3, int enemyCount) {
		
		
		enemyDestroy(enemy3);
		
		level.enemy3Shot(enemy3, enemyCount);
	
	}
	
	
	private void onCollisionBombWithEnemy4(Enemy4 enemy4, int enemyCount) {
		
		
		enemyDestroy(enemy4);
		
		level.enemy4Shot(enemy4, enemyCount);
	
	}
	
	
	private void onCollisionBombWithEnemy5(Enemy5 enemy5, int enemyCount) {
		
		
		enemyDestroy(enemy5);
		
		level.enemy5Shot(enemy5, enemyCount);
	
	}
	
	private void enemyDestroy(Enemy enemy) {
		
		float enemyCenterX = enemy.returnCenterX();
		float enemyCenterY = enemy.returnCenterY();
		level.gems.get(level.gemsIndex).activate(enemyCenterX, enemyCenterY);
		level.gemsIndex++;
		
		if (level.gemsIndex > (Constants.MAXGEMS - 1))
			level.gemsIndex = 0;
		
		enemyScore = enemy.getScore() * multiplier;
		enemy.deactivate(enemyScore);
		score += enemyScore;
		
		AudioManager.instance.play(Assets.instance.sounds.enemyExplosion, .5f);
	
	}
	
	private void onCollisionShipWithEnemy() {
		
		level.ship.deactivate();
		clearScreenSwitch = true;
		
		AudioManager.instance.play(Assets.instance.sounds.shipExplosion, 1f, 1f, 1f);
		
		lives = lives - 1;
		
	}
	
	private void onCollisionShipWithGem(Gem gem) {
		
	
		multiplier++;
		gem.gathered(multiplier);
		AudioManager.instance.play(Assets.instance.sounds.gemPickup, .5f);
		
	}
	
	private void checkForEndLevelTime(float deltaTime) {
		
		levelTime = levelTime + deltaTime;
		
		float levelCheckNumber = Constants.LEVELTIMEAMOUNT - (levelNumber / Constants.NUMBEROFLEVELS);
		
		if (levelCheckNumber < 1.0f) {
			
			levelCheckNumber = 1.0f;
		}
		
		if (levelTime > levelCheckNumber) {
			
			endLevel = true;
			levelTime = 0f;
		
		}
		
	}
	
	private void clearScreen() {
		
		// See if any Enemy1 is active and clear if true
		
		for (Enemy1 enemy1 : level.enemys1) {
			
			if(enemy1.inactive)
				break;
			
			if(enemy1.active || enemy1.spawnEnemy)
				enemy1.clear();
			
		}
		
		level.enemys1Counter = 0;
		
		// See if any Enemy2 is active and clear if true
		
		for (Enemy2 enemy2 : level.enemys2) {
			
			if(enemy2.inactive)
				break;
					
			if(enemy2.active || enemy2.spawnEnemy)
				enemy2.clear();
			
		}
		
		level.enemys2Counter = 0;
			
		// See if any Enemy3 is active and clear if true
			
		for (Enemy3 enemy3 : level.enemys3) {
			
			if(enemy3.inactive)
				break;
					
			if(enemy3.active || enemy3.spawnEnemy) 
				enemy3.clear();
					
		}
		
		level.enemys3Counter = 0;
		
		// See if any Enemy4 is active and clear if true
			
		for (Enemy4 enemy4 : level.enemys4) {
			
			if(enemy4.inactive)
				break;
							
			if(enemy4.active || enemy4.spawnEnemy) 
				enemy4.clear();
							
		}
		
		level.enemys4Counter = 0;
		
		// See if any Enemy5 is active and clear if true
			
		for (Enemy5 enemy5 : level.enemys5) {
			
			if(enemy5.inactive)
				break;
									
			if(enemy5.active || enemy5.spawnEnemy) 
				enemy5.clear();
		
		}
		
		level.enemys5Counter = 0;
		
		// Clear gems
		
		for (Gem gem : level.gems) {
			
			if(gem.active) 
				gem.deactivate();
		
		}
		
		clearScreenSwitch = false;
		
		//set level time to constant so new level will trigger when ship spawn is complete
		
		levelTime = Constants.LEVELTIMEAMOUNT;
		
	}
	
	private void checkScores() {
		
		if (score > 10000 && !score10k) {
			
			lives++;
			bombs++;
			score10k = true;
			
		}
			
		if (score > 100000 && !score100k) {
			
			lives++;
			bombs++;
			score100k = true;
		
		}
		
		if (score > 1000000 && !score1Mil) {
			
			lives++;
			bombs++;
			score1Mil = true;
			
		}
				
	}
	
	private void checkEscKey() {
		
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
				escPressed = true;

	}
	
	private void checkBackKey() {
		
		//select check
		selectPressed =  player1Controller.getButton(Xbox360Pad.BUTTON_BACK);
		
	}
	
	/*
	
	private void handleDebugInput (float deltaTime) {
		if (Gdx.app.getType() != ApplicationType.Desktop) return;

		// Camera Controls (move)
		float camMoveSpeed = 5 * deltaTime;
		float camMoveSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) camMoveSpeed *= camMoveSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.LEFT)) moveCamera(-camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) moveCamera(camMoveSpeed, 0);
		if (Gdx.input.isKeyPressed(Keys.UP)) moveCamera(0, camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.DOWN)) moveCamera(0, -camMoveSpeed);
		if (Gdx.input.isKeyPressed(Keys.BACKSPACE)) cameraHelper.setPosition(0, 0);

		// Camera Controls (zoom)
		float camZoomSpeed = 1 * deltaTime;
		float camZoomSpeedAccelerationFactor = 5;
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) camZoomSpeed *= camZoomSpeedAccelerationFactor;
		if (Gdx.input.isKeyPressed(Keys.COMMA)) cameraHelper.addZoom(camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.PERIOD)) cameraHelper.addZoom(-camZoomSpeed);
		if (Gdx.input.isKeyPressed(Keys.SLASH)) cameraHelper.setZoom(1);
	}

	private void moveCamera (float x, float y) {
		x += cameraHelper.getPosition().x;
		y += cameraHelper.getPosition().y;
		cameraHelper.setPosition(x, y);
	}

	@Override
	public boolean keyUp (int keycode) {
		// Reset game world
		if (keycode == Keys.R) {
			init();
			Gdx.app.debug(TAG, "Game world resetted");
		}
		// Toggle camera follow
		else if (keycode == Keys.ENTER) {
			cameraHelper.setTarget(cameraHelper.hasTarget() ? null : level.ship);
			Gdx.app.debug(TAG, "Camera follow enabled: " + cameraHelper.hasTarget());
		}
		return false;
	}
	
	*/
}
