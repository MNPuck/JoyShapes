/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.math.MathUtils;
import com.nowhereinc.joyshapes.util.AudioManager;
import com.nowhereinc.joyshapes.game.objects.AbstractGameObject;
import com.nowhereinc.joyshapes.game.objects.Bomb;
import com.nowhereinc.joyshapes.game.objects.Border;
import com.nowhereinc.joyshapes.game.objects.Bullet;
import com.nowhereinc.joyshapes.game.objects.Enemy1;
import com.nowhereinc.joyshapes.game.objects.Enemy2;
import com.nowhereinc.joyshapes.game.objects.Enemy3;
import com.nowhereinc.joyshapes.game.objects.Enemy4;
import com.nowhereinc.joyshapes.game.objects.Enemy5;
import com.nowhereinc.joyshapes.game.objects.Gem;
import com.nowhereinc.joyshapes.game.objects.Ship;
import com.nowhereinc.joyshapes.util.Constants;
import com.badlogic.gdx.math.Vector2;

public class Level {

	public static final String TAG = Level.class.getName();
	
	public enum BLOCK_TYPE {
		EMPTY(0, 0, 0), // black
		ENEMY1(0, 146, 253), // light blue
		ENEMY2(244, 65, 254), // purple
		ENEMY3(253, 239, 21), // yellow
		ENEMY4(253, 0, 0), // red
		ENEMY5(255, 127, 35); // orange
		

		private int color;

		private BLOCK_TYPE (int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}

		public boolean sameColor (int color) {
			return this.color == color;
		}

		public int getColor () {
			return color;
		}
	} 
	
	// border
	public Border border;
	
	// player ship
	public Ship ship;
	
	// bullets
	public Array<Bullet> bullets;
	
	// bomb
	public Bomb bomb;
	
	// enemy objects
	public Array<Enemy1> enemys1;
	public Array<Enemy2> enemys2;
	public Array<Enemy3> enemys3;
	public Array<Enemy4> enemys4;
	public Array<Enemy5> enemys5;
	
	// gems
	public Array<Gem> gems;
	
	// enemy Counters
	public int enemys1Counter;
	public int enemys2Counter;
	public int enemys3Counter;
	public int enemys4Counter;
	public int enemys5Counter;
	
	//gems index
	public int gemsIndex;
	
	// variable to store level number
	public int levelNumber;
	
	// count bullets
	public int bulletCounter;
	
	// ship center
	private Vector2 shipCenter = new Vector2();
	
	// time added up
	private float levelTotalTime;
	
	// time since last bullet
	private float timeSinceLastBullet;
	
	// enemy spawned
	public boolean enemySpawned;
	
	// enemy 1 particle pool
	public ParticleEffectPool pool1;
	public Array<PooledEffect> activeEffects1;
	
	// enemy 2 particle pool
	public ParticleEffectPool pool2;
	public Array<PooledEffect> activeEffects2;
	
	// enemy 3 particle pool
	public ParticleEffectPool pool3;
	public Array<PooledEffect> activeEffects3;
	
	// enemy 4 particle pool
	public ParticleEffectPool pool4;
	public Array<PooledEffect> activeEffects4;
	
	// enemy 5 particle pool
	public ParticleEffectPool pool5;
	public Array<PooledEffect> activeEffects5;	
	
	// level objects
	public Array<LevelBuilder> levels;

	public Level () {

		init();
	}
		
	private void init() {
		
		// level time
		levelTotalTime = 0f;
		timeSinceLastBullet = 1f;
		
		// level number
		levelNumber = 1;
			
		// player ship
		ship = null;
	
		// init ship center
		shipCenter.x = 0f;
		shipCenter.y = 0f;
		
		// bullets
		bullets = new Array<Bullet>();
		
		// enemy objects
		enemys1 = new Array<Enemy1>();
		enemys2 = new Array<Enemy2>();
		enemys3 = new Array<Enemy3>();
		enemys4 = new Array<Enemy4>();
		enemys5 = new Array<Enemy5>();
		
		// init counters
		enemys1Counter = 0;
		enemys2Counter = 0;
		enemys3Counter = 0;
		enemys4Counter = 0;
		enemys5Counter = 0;
		
		// gem objects
		gems = new Array<Gem>();
		
		// init index
		gemsIndex = 0;
		
		// create border
		AbstractGameObject obj = null;
		obj = new Border();
		obj.position.set(0,0);
		border = (Border)obj;

		// place ship in middle of screen
		obj = null;
		obj = new Ship();
		obj.position.set(0,0);
		ship = (Ship)obj;
	
		// create a bullets
	
		for (int i = 0; i < Constants.MAXBULLETS; i++) {
		
			obj = null;
			obj = new Bullet();
			obj.position.set(0,0);
			bullets.add((Bullet)obj);
		
	 	}
	
		bulletCounter = 0;
		
		// create bomb
		obj = null;
		obj = new Bomb();
		obj.position.set(0,0);
		bomb = (Bomb)obj;
		
		// create enemy 1
		
		for (int i = 0; i < Constants.MAXENEMY1; i++) {
			
			obj = null;
			obj = new Enemy1();
			obj.position.set(0,0);
			enemys1.add((Enemy1)obj);

		}
		
		// create enemy 2
		
		for (int i = 0; i < Constants.MAXENEMY2; i++) {
			
			obj = null;
			obj = new Enemy2();
			obj.position.set(0,0);
			enemys2.add((Enemy2)obj);

		}
		
		// create enemy 3
		
		for (int i = 0; i < Constants.MAXENEMY3; i++) {
			
			obj = null;
			obj = new Enemy3();
			obj.position.set(0,0);
			enemys3.add((Enemy3)obj);

		}
		
		// create enemy 4
		
		for (int i = 0; i < Constants.MAXENEMY4; i++) {
			
			obj = null;
			obj = new Enemy4();
			obj.position.set(0,0);
			enemys4.add((Enemy4)obj);

		}
		
		// create enemy 5
		
		for (int i = 0; i < Constants.MAXENEMY5; i++) {
			
			obj = null;
			obj = new Enemy5();
			obj.position.set(0,0);
			enemys5.add((Enemy5)obj);

		}
		
		// create gems
		
		for (int i = 0; i < Constants.MAXGEMS; i++) {
			
			obj = null;
			obj = new Gem();
			obj.position.set(0,0);
			gems.add((Gem)obj);

		}
		
		// create enemy 1 Particle Pool
		
		ParticleEffect explosionEffect = new ParticleEffect();
		
		explosionEffect.load(Gdx.files.internal("../JoyShapes-android/assets/particles/Enemy1Exp.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		float pScale = Constants.PARTICLESCALE;

	    float scaling = explosionEffect.getEmitters().get(0).getScale().getHighMax();
	    explosionEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getScale().getLowMax();
	    explosionEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);
	    
	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getHighMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getLowMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
	    
	    pool1 = new ParticleEffectPool(explosionEffect, 10 , 30);
	    activeEffects1 = new Array<PooledEffect>();
	    
		// create enemy 2 Particle Pool
		
		explosionEffect = new ParticleEffect();
		
		explosionEffect.load(Gdx.files.internal("../JoyShapes-android/assets/particles/Enemy2Exp.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		pScale = Constants.PARTICLESCALE;

	    scaling = explosionEffect.getEmitters().get(0).getScale().getHighMax();
	    explosionEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getScale().getLowMax();
	    explosionEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);
	    
	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getHighMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getLowMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
	    
	    pool2 = new ParticleEffectPool(explosionEffect, 10 , 30);
	    activeEffects2 = new Array<PooledEffect>();
	    
		// create enemy 3 Particle Pool
		
		explosionEffect = new ParticleEffect();
		
		explosionEffect.load(Gdx.files.internal("../JoyShapes-android/assets/particles/Enemy3Exp.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		pScale = Constants.PARTICLESCALE;

	    scaling = explosionEffect.getEmitters().get(0).getScale().getHighMax();
	    explosionEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getScale().getLowMax();
	    explosionEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);
	    
	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getHighMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getLowMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
	    
	    pool3 = new ParticleEffectPool(explosionEffect, 10 , 30);
	    activeEffects3 = new Array<PooledEffect>();
	    
		// create enemy 4 Particle Pool
		
		explosionEffect = new ParticleEffect();
		
		explosionEffect.load(Gdx.files.internal("../JoyShapes-android/assets/particles/Enemy4Exp.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		pScale = Constants.PARTICLESCALE;

	    scaling = explosionEffect.getEmitters().get(0).getScale().getHighMax();
	    explosionEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getScale().getLowMax();
	    explosionEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);
	    
	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getHighMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getLowMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
	    
	    pool4 = new ParticleEffectPool(explosionEffect, 10 , 30);
	    activeEffects4 = new Array<PooledEffect>();
	    
		// create enemy 5 Particle Pool
		
		explosionEffect = new ParticleEffect();
		
		explosionEffect.load(Gdx.files.internal("../JoyShapes-android/assets/particles/Enemy5Exp.pfx"),
				Gdx.files.internal("../JoyShapes-android/assets/particles"));
		
		pScale = Constants.PARTICLESCALE;

	    scaling = explosionEffect.getEmitters().get(0).getScale().getHighMax();
	    explosionEffect.getEmitters().get(0).getScale().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getScale().getLowMax();
	    explosionEffect.getEmitters().get(0).getScale().setLow(scaling * pScale);
	    
	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getHighMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setHigh(scaling * pScale);

	    scaling = explosionEffect.getEmitters().get(0).getVelocity().getLowMax();
	    explosionEffect.getEmitters().get(0).getVelocity().setLow(scaling * pScale);
	    
	    pool5 = new ParticleEffectPool(explosionEffect, 10 , 30);
	    activeEffects5 = new Array<PooledEffect>();
	    
	    // end pool effects
	    
		// level objects
		levels = new Array<LevelBuilder>();
		
		// create level
		
		
		for (int i = 0; i < Constants.NUMBEROFLEVELS; i++) {
			
			LevelBuilder lvls = null;
			lvls = new LevelBuilder(("../JoyShapes-android/assets/levels/level-") + (i + 1) + (".png"));
			
			levels.add((LevelBuilder)lvls);

		}
		
		enemySpawned = false;
		
		loadLevel();
		
	}
	
	public void update (float deltaTime) {
	
		levelTotalTime = levelTotalTime + deltaTime;
		
		// ship
		
		ship.update(deltaTime);
		
		// bullets	
		
		for (Bullet bullet : bullets) {
			
			if (bullet.directionreturn() != Constants.NIL)
			
				bullet.update(deltaTime);
		}
		
		timeSinceLastBullet = timeSinceLastBullet + deltaTime;
		
		if (timeSinceLastBullet > Constants.BULLETSPAWNTIME && ship.active) {
			
			shipCenter.x = ship.position.x + (ship.dimension.x * .5f);
			shipCenter.y = ship.position.y + (ship.dimension.y * .5f);
			
			bullets.get(bulletCounter).intshot(deltaTime, shipCenter);
			
			if (bullets.get(bulletCounter).directionreturn() != Constants.NIL) {
				
				int saveDirection = bullets.get(bulletCounter).directionreturn();
				
				// fire middle shot
				
				timeSinceLastBullet = 0f;
					
				bulletCounter += 1;
				
				if (bulletCounter == Constants.MAXBULLETS) {
				
					bulletCounter = 0;		
				}
				
				bullets.get(bulletCounter).reset();
				
				// fire left shot
				
				bullets.get(bulletCounter).intshotL(deltaTime, shipCenter, saveDirection);
				
				bulletCounter += 1;
				
				if (bulletCounter == Constants.MAXBULLETS) {
				
					bulletCounter = 0;		
				}
				
				bullets.get(bulletCounter).reset();
				
				// fire right shot
				
				bullets.get(bulletCounter).intshotR(deltaTime, shipCenter, saveDirection);
				
				bulletCounter += 1;
				
				if (bulletCounter == Constants.MAXBULLETS) {
				
					bulletCounter = 0;		
				}
				
				bullets.get(bulletCounter).reset();
				
				AudioManager.instance.play(Assets.instance.sounds.shipShot, .25f);
			
			}

		
		}	
		
		// bomb
		
		if (ship.active)
			bomb.update(deltaTime);
		
		if (bomb.triggered &&
			WorldController.bombs > 0) {
		
			shipCenter.x = ship.position.x + (ship.dimension.x * .5f);
			shipCenter.y = ship.position.y + (ship.dimension.y * .5f);
			
			AudioManager.instance.play(Assets.instance.sounds.bombExplosion, 1f);
			bomb.activate(shipCenter.x, shipCenter.y);
			WorldController.bombs--;
		
		}
		
		// get ship coordinates to pass to enemys
		
		float shipCenterX = ship.returnCenterX();
		float shipCenterY = ship.returnCenterY();
		
		// calculate distance from ship for each enemy
		
		for (Enemy1 enemy1 : enemys1) {
			
			if (enemy1.inactive)
				break;
			
			enemy1.calculateDistanceFromShip(shipCenterX, shipCenterY);
			
		}
	
		for (Enemy2 enemy2 : enemys2) {
			
			if (enemy2.inactive)
				break;
			
			enemy2.calculateDistanceFromShip(shipCenterX, shipCenterY);
			
		}
		
		for (Enemy3 enemy3 : enemys3) {
			
			if (enemy3.inactive)
				break;
			
			enemy3.calculateDistanceFromShip(shipCenterX, shipCenterY);
			
		}
		
		for (Enemy4 enemy4 : enemys4) {
			
			if (enemy4.inactive)
				break;
			
			enemy4.calculateDistanceFromShip(shipCenterX, shipCenterY);
			
		}
		
		for (Enemy5 enemy5 : enemys5) {
			
			if (enemy5.inactive)
				break;
			
			enemy5.calculateDistanceFromShip(shipCenterX, shipCenterY);
			
		}
		
		// update enemy positions
		
		// enemys1
		
		for (Enemy1 enemy1 : enemys1) {
			
			if (enemy1.inactive)
					break;
			
			enemy1.update(deltaTime);
			
		}
		
		// enemys2
		
		for (Enemy2 enemy2 : enemys2) {
			
			if (enemy2.inactive)
					break;

			enemy2.update(deltaTime);
			
		}
			
		// enemys3
		
		for (Enemy3 enemy3 : enemys3) {
			
			if (enemy3.inactive)
					break;
			
			enemy3.update(deltaTime);
			
		}

		// enemys4
		
		for (Enemy4 enemy4 : enemys4) {
			
			if (enemy4.inactive)
					break;

			enemy4.update(deltaTime);
			
		}
		
		// enemys5
		
		for (Enemy5 enemy5 : enemys5) {
			
			if (enemy5.inactive)
					break;
			
			enemy5.update(deltaTime);
			
		}
		
		// gems
		
		for (Gem gem : gems)
			gem.update(deltaTime);
		
	}
	
	public void enemy1Shot(Enemy1 enemy1, int enemyNumber) {  
		
		PooledEffect effect = pool1.obtain();
		
		if (effect != null) {
			activeEffects1.add(effect);
			effect.setPosition(enemy1.returnCenterX(), enemy1.returnCenterY());
		}
		
		Enemy1 temp = enemys1.get(enemys1Counter - 1);
		enemys1.set(enemys1Counter - 1, enemy1);
		enemys1.set(enemyNumber - 1, temp);
		enemys1Counter--;
		
	}
	
	public void enemy2Shot(Enemy2 enemy2, int enemyNumber) {  
		
		PooledEffect effect = pool2.obtain();
		
		if (effect != null) {
			activeEffects2.add(effect);
			effect.setPosition(enemy2.returnCenterX(), enemy2.returnCenterY());
		}
		
		Enemy2 temp = enemys2.get(enemys2Counter - 1);
		enemys2.set(enemys2Counter - 1, enemy2);
		enemys2.set(enemyNumber - 1, temp);
		enemys2Counter--;
		
	}
	
	public void enemy3Shot(Enemy3 enemy3, int enemyNumber) {
		
		PooledEffect effect = pool3.obtain();
		
		if (effect != null) {
			activeEffects3.add(effect);
			effect.setPosition(enemy3.returnCenterX(), enemy3.returnCenterY());
		}
		
		Enemy3 temp = enemys3.get(enemys3Counter - 1);
		enemys3.set(enemys3Counter - 1, enemy3);
		enemys3.set(enemyNumber - 1, temp);
		enemys3Counter--;
		
	}
	
	public void enemy4Shot(Enemy4 enemy4, int enemyNumber) { 
		
		PooledEffect effect = pool4.obtain();
		
		if (effect != null) {
			activeEffects4.add(effect);
			effect.setPosition(enemy4.returnCenterX(), enemy4.returnCenterY());
		}
		
		Enemy4 temp = enemys4.get(enemys4Counter - 1);
		enemys4.set(enemys4Counter - 1, enemy4);
		enemys4.set(enemyNumber - 1, temp);
		enemys4Counter--;
		
	}
	
	public void enemy5Shot(Enemy5 enemy5, int enemyNumber) {  
		
		PooledEffect effect = pool5.obtain();
		
		if (effect != null) {
			activeEffects5.add(effect);
			effect.setPosition(enemy5.returnCenterX(), enemy5.returnCenterY());
		}
		
		Enemy5 temp = enemys5.get(enemys5Counter - 1);
		enemys5.set(enemys5Counter - 1, enemy5);
		enemys5.set(enemyNumber - 1, temp);
		enemys5Counter--;
		
	}
	
	public void newLevel() {
		
		// inc level number 
		levelNumber++;
		
		// if level greater then max level go back to level 1
		
		if (levelNumber > Constants.NUMBEROFLEVELS)
			WorldController.gameOver = true;
		
		loadLevel();
		
	}
	
	public void loadLevel() {
		
		int lvlIdx = levelNumber - 1;
		
		// enemy 1 load
		
		for (int i = 0; i < levels.get(lvlIdx).enemys1Index; i++) {
			
			enemys1.get(enemys1Counter).position.x = levels.get(lvlIdx).enemy1CoordX[i];
			enemys1.get(enemys1Counter).position.y = levels.get(lvlIdx).enemy1CoordY[i];
			enemys1.get(enemys1Counter).spawn();
			enemys1Counter++;
			
		}
		
		// enemy 2 load
		
		for (int i = 0; i < levels.get(lvlIdx).enemys2Index; i++) {
			
			enemys2.get(enemys2Counter).position.x = levels.get(lvlIdx).enemy2CoordX[i];
			enemys2.get(enemys2Counter).position.y = levels.get(lvlIdx).enemy2CoordY[i];
			enemys2.get(enemys2Counter).spawn();
			enemys2Counter++;
			
		}

		// enemy 3 load
		
		for (int i = 0; i < levels.get(lvlIdx).enemys3Index; i++) {
			
			enemys3.get(enemys3Counter).position.x = levels.get(lvlIdx).enemy3CoordX[i];
			enemys3.get(enemys3Counter).position.y = levels.get(lvlIdx).enemy3CoordY[i];
			enemys3.get(enemys3Counter).spawn();
			enemys3Counter++;
			
		}
		
		// enemy 4 load
		
		for (int i = 0; i < levels.get(lvlIdx).enemys4Index; i++) {
			
			enemys4.get(enemys4Counter).position.x = levels.get(lvlIdx).enemy4CoordX[i];
			enemys4.get(enemys4Counter).position.y = levels.get(lvlIdx).enemy4CoordY[i];
			enemys4.get(enemys4Counter).spawn();
			enemys4Counter++;
			
		}
		
		// enemy 5 load
		
		for (int i = 0; i < levels.get(lvlIdx).enemys5Index; i++) {
		
			enemys5.get(enemys5Counter).position.x = levels.get(lvlIdx).enemy5CoordX[i];
			enemys5.get(enemys5Counter).position.y = levels.get(lvlIdx).enemy5CoordY[i];
			enemys5.get(enemys5Counter).spawn();
			enemys5Counter++;
			
		}
		
	}

	public void render (SpriteBatch batch, float deltaTime) {
		
		// draw border
		border.render(batch);
		
		// draw ship
		ship.render(batch);

		// draw bullets
		for (Bullet bullet : bullets)
			bullet.render(batch);
		
		// draw enemys1 
		
		for (Enemy1 enemy1 : enemys1) {
			
			if (!enemy1.active && 
				!enemy1.spawnEnemy && 
				!enemy1.explosion)
				break;
				
			enemy1.render(batch);
			
		}
		
		// draw enemys2
			
		for (Enemy2 enemy2 : enemys2) {
			
			if (!enemy2.active && 
				!enemy2.spawnEnemy && 
				!enemy2.explosion)
				break;
			
			enemy2.render(batch);
			
		}
		
		// draw enemys3
		
		for (Enemy3 enemy3 : enemys3) {
			
			if (!enemy3.active &&
				!enemy3.spawnEnemy &&
				!enemy3.explosion)
				break;
			
			enemy3.render(batch);
			
		}
		
		// draw enemys4
			
		for (Enemy4 enemy4 : enemys4) {
		
			if (!enemy4.active && 
				!enemy4.spawnEnemy &&
				!enemy4.explosion)
				break;
		
			enemy4.render(batch);
			
		}

		// draw enemys5
		
		for (Enemy5 enemy5 : enemys5) {
			
			if (!enemy5.active && 
				!enemy5.spawnEnemy &&
				!enemy5.explosion)
				break;
			
			enemy5.render(batch);
			
		}
		
		// draw gems
		
		for (Gem gem : gems)
			gem.render(batch);
		
		// draw bomb
		bomb.render(batch);
		
		// draw enemy 1 pooled particle effect
		
		for (int i = 0; i < activeEffects1.size; ) {
			
			PooledEffect effect = activeEffects1.get(i);
			
			if (effect.isComplete()) {
				pool1.free(effect);
				activeEffects1.removeIndex(i);
			}
			
			else {
				effect.draw(batch, deltaTime);
				i++;
			}
			
		}
		
		// draw enemy 2 pooled particle effect
		
		for (int i = 0; i < activeEffects2.size; ) {
			
			PooledEffect effect = activeEffects2.get(i);
			
			if (effect.isComplete()) {
				pool2.free(effect);
				activeEffects2.removeIndex(i);
			}
			
			else {
				effect.draw(batch, deltaTime);
				i++;
			}
			
		}
		
		// draw enemy 3 pooled particle effect
		
		for (int i = 0; i < activeEffects3.size; ) {
			
			PooledEffect effect = activeEffects3.get(i);
			
			if (effect.isComplete()) {
				pool3.free(effect);
				activeEffects3.removeIndex(i);
			}
			
			else {
				effect.draw(batch, deltaTime);
				i++;
			}
			
		}
		
		// draw enemy 4 pooled particle effect
		
		for (int i = 0; i < activeEffects4.size; ) {
			
			PooledEffect effect = activeEffects4.get(i);
			
			if (effect.isComplete()) {
				pool4.free(effect);
				activeEffects4.removeIndex(i);
			}
			
			else {
				effect.draw(batch, deltaTime);
				i++;
			}
			
		}
		
		// draw enemy 5 pooled particle effect
		
		for (int i = 0; i < activeEffects5.size; ) {
			
			PooledEffect effect = activeEffects5.get(i);
			
			if (effect.isComplete()) {
				pool5.free(effect);
				activeEffects5.removeIndex(i);
			}
			
			else {
				effect.draw(batch, deltaTime);
				i++;
			}
			
		}
		
		// end particle
		
	}

}
