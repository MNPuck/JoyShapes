/*******************************************************************************
 * 
 ******************************************************************************/


package com.nowhereinc.joyshapes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;
import com.nowhereinc.joyshapes.util.Constants;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets implements Disposable, AssetErrorListener {

	public static final String TAG = Assets.class.getName();

	public static final Assets instance = new Assets();

	private AssetManager assetManager;
	
	public AssetFonts fonts;
	public AssetBullet bullet;
	public AssetEnemy1 enemy1;
	public AssetEnemy2 enemy2;
	public AssetEnemy3 enemy3;
	public AssetEnemy4 enemy4;
	public AssetEnemy5 enemy5;
	public AssetShip ship;
	public AssetBorder border;
	public AssetGem gem;
	public AssetBomb bomb;
	
	public AssetNumber1 number1;
	public AssetNumber2 number2;
	public AssetNumber3 number3;
	public AssetNumber4 number4;
	public AssetNumber5 number5;
	public AssetNumber6 number6;
	public AssetNumber7 number7;
	public AssetNumber8 number8;
	public AssetNumber9 number9;
	public AssetNumber0 number0;
	
	public AssetNumber1g number1g;
	public AssetNumber2g number2g;
	public AssetNumber3g number3g;
	public AssetNumber4g number4g;
	public AssetNumber5g number5g;
	public AssetNumber6g number6g;
	public AssetNumber7g number7g;
	public AssetNumber8g number8g;
	public AssetNumber9g number9g;
	public AssetNumber0g number0g;
	public AssetNumberXg numberXg;
	
	public AssetSounds sounds;
	

	// singleton: prevent instantiation from other classes
	private Assets () {
	}
	
	public class AssetFonts {
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;

		public AssetFonts () {

			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("../JoyShapes-android/assets/images/arial-15.fnt"), true);
			defaultNormal = new BitmapFont(Gdx.files.internal("../JoyShapes-android/assets/images/arial-15.fnt"), true);
			defaultBig = new BitmapFont(Gdx.files.internal("../JoyShapes-android/assets/images/arial-15.fnt"), true);
			// set font sizes
			defaultSmall.setScale(0.75f);
			defaultNormal.setScale(1.0f);
			defaultBig.setScale(2.0f);
			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
	
	public class AssetNumber1 {
		public final AtlasRegion number1;

		public AssetNumber1 (TextureAtlas atlas) {
			number1 = atlas.findRegion("Text1");
		
			if (number1 == null) {
			
			Gdx.app.debug(TAG,"Number 1 is null");
			
			}

		}
	
	}
	
	public class AssetNumber2 {
		public final AtlasRegion number2;

		public AssetNumber2 (TextureAtlas atlas) {
			number2 = atlas.findRegion("Text2");
		
			if (number2 == null) {
			
			Gdx.app.debug(TAG,"Number 2 is null");
			
			}

		}
	
	}
	
	public class AssetNumber3 {
		public final AtlasRegion number3;

		public AssetNumber3 (TextureAtlas atlas) {
			number3 = atlas.findRegion("Text3");
		
			if (number3 == null) {
			
			Gdx.app.debug(TAG,"Number 3 is null");
			
			}

		}
	
	}
	
	public class AssetNumber4 {
		public final AtlasRegion number4;

		public AssetNumber4 (TextureAtlas atlas) {
			number4 = atlas.findRegion("Text4");
		
			if (number4 == null) {
			
			Gdx.app.debug(TAG,"Number 4 is null");
			
			}

		}
	
	}
	
	public class AssetNumber5 {
		public final AtlasRegion number5;

		public AssetNumber5 (TextureAtlas atlas) {
			number5 = atlas.findRegion("Text5");
		
			if (number5 == null) {
			
			Gdx.app.debug(TAG,"Number 5 is null");
			
			}

		}
	
	}
	
	public class AssetNumber6 {
		public final AtlasRegion number6;

		public AssetNumber6 (TextureAtlas atlas) {
			number6 = atlas.findRegion("Text6");
		
			if (number6 == null) {
			
			Gdx.app.debug(TAG,"Number 6 is null");
			
			}

		}
	
	}
	
	public class AssetNumber7 {
		public final AtlasRegion number7;

		public AssetNumber7 (TextureAtlas atlas) {
			number7 = atlas.findRegion("Text7");
		
			if (number7 == null) {
			
			Gdx.app.debug(TAG,"Number 7 is null");
			
			}

		}
	
	}
	
	public class AssetNumber8 {
		public final AtlasRegion number8;

		public AssetNumber8 (TextureAtlas atlas) {
			number8 = atlas.findRegion("Text8");
		
			if (number8 == null) {
			
			Gdx.app.debug(TAG,"Number 8 is null");
			
			}

		}
	
	}
	
	public class AssetNumber9 {
		public final AtlasRegion number9;

		public AssetNumber9 (TextureAtlas atlas) {
			number9 = atlas.findRegion("Text9");
		
			if (number9 == null) {
			
			Gdx.app.debug(TAG,"Number 9 is null");
			
			}

		}
	
	}
	
	public class AssetNumber0 {
		public final AtlasRegion number0;

		public AssetNumber0 (TextureAtlas atlas) {
			number0 = atlas.findRegion("Text0");
		
			if (number0 == null) {
			
			Gdx.app.debug(TAG,"Number 0 is null");
			
			}

		}
	
	}
	
	public class AssetNumber1g {
		public final AtlasRegion number1g;

		public AssetNumber1g (TextureAtlas atlas) {
			number1g = atlas.findRegion("Text1g");
		
			if (number1g == null) {
			
			Gdx.app.debug(TAG,"Number 1g is null");
			
			}

		}
	
	}
	
	public class AssetNumber2g {
		public final AtlasRegion number2g;

		public AssetNumber2g (TextureAtlas atlas) {
			number2g = atlas.findRegion("Text2g");
		
			if (number2g == null) {
			
			Gdx.app.debug(TAG,"Number 2g is null");
			
			}

		}
	
	}
	
	public class AssetNumber3g {
		public final AtlasRegion number3g;

		public AssetNumber3g (TextureAtlas atlas) {
			number3g = atlas.findRegion("Text3g");
		
			if (number3g == null) {
			
			Gdx.app.debug(TAG,"Number 3g is null");
			
			}

		}
	
	}
	
	public class AssetNumber4g {
		public final AtlasRegion number4g;

		public AssetNumber4g (TextureAtlas atlas) {
			number4g = atlas.findRegion("Text4g");
		
			if (number4g == null) {
			
			Gdx.app.debug(TAG,"Number 4g is null");
			
			}

		}
	
	}
	
	public class AssetNumber5g {
		public final AtlasRegion number5g;

		public AssetNumber5g (TextureAtlas atlas) {
			number5g = atlas.findRegion("Text5g");
		
			if (number5g == null) {
			
			Gdx.app.debug(TAG,"Number 5g is null");
			
			}

		}
	
	}
	
	public class AssetNumber6g {
		public final AtlasRegion number6g;

		public AssetNumber6g (TextureAtlas atlas) {
			number6g = atlas.findRegion("Text6g");
		
			if (number6g == null) {
			
			Gdx.app.debug(TAG,"Number 6g is null");
			
			}

		}
	
	}
	
	public class AssetNumber7g {
		public final AtlasRegion number7g;

		public AssetNumber7g (TextureAtlas atlas) {
			number7g = atlas.findRegion("Text7g");
		
			if (number7g == null) {
			
			Gdx.app.debug(TAG,"Number 7g is null");
			
			}

		}
	
	}
	
	public class AssetNumber8g {
		public final AtlasRegion number8g;

		public AssetNumber8g (TextureAtlas atlas) {
			number8g = atlas.findRegion("Text8g");
		
			if (number8g == null) {
			
			Gdx.app.debug(TAG,"Number 8g is null");
			
			}

		}
	
	}
	
	public class AssetNumber9g {
		public final AtlasRegion number9g;

		public AssetNumber9g (TextureAtlas atlas) {
			number9g = atlas.findRegion("Text9g");
		
			if (number9g == null) {
			
			Gdx.app.debug(TAG,"Number 9g is null");
			
			}

		}
	
	}
	
	public class AssetNumber0g {
		public final AtlasRegion number0g;

		public AssetNumber0g (TextureAtlas atlas) {
			number0g = atlas.findRegion("Text0g");
		
			if (number0g == null) {
			
			Gdx.app.debug(TAG,"Number 0g is null");
			
			}

		}
	
	}
	
	public class AssetNumberXg {
		public final AtlasRegion numberXg;

		public AssetNumberXg (TextureAtlas atlas) {
			numberXg = atlas.findRegion("TextXg");
		
			if (numberXg == null) {
			
			Gdx.app.debug(TAG,"Number Xg is null");
			
			}

		}
	
	}
	
	public class AssetBorder {
		public final AtlasRegion border;

		public AssetBorder (TextureAtlas atlas) {
			border = atlas.findRegion("border");
			
			if (border == null) {
				
				Gdx.app.debug(TAG,"Border is null");
			}

		}
		
	}

	public class AssetBullet {
		public final AtlasRegion bullet;

		public AssetBullet (TextureAtlas atlas) {
			bullet = atlas.findRegion("bullet");
			
			if (bullet == null) {
				
				Gdx.app.debug(TAG,"Bullet is null");
			}

		}
		
	}

	public class AssetEnemy1 {
		public final AtlasRegion enemy1;

		public AssetEnemy1 (TextureAtlas atlas) {
			enemy1 = atlas.findRegion("enemy1");
			
			if (enemy1 == null) {
				
				Gdx.app.debug(TAG,"Enemy1 is null");
			}
			
		}
	}
	
	public class AssetEnemy2 {
		public final AtlasRegion enemy2;

		public AssetEnemy2 (TextureAtlas atlas) {
			enemy2 = atlas.findRegion("enemy2");
			
			if (enemy2 == null) {
				
				Gdx.app.debug(TAG,"Enemy2 is null");
			}
		}
	}

	public class AssetEnemy3 {
		public final AtlasRegion enemy3;

		public AssetEnemy3 (TextureAtlas atlas) {
			enemy3 = atlas.findRegion("enemy3");
			
			if (enemy3 == null) {
				
				Gdx.app.debug(TAG,"Enemy3 is null");
			}
		}
	}
	
	public class AssetEnemy4 {
		public final AtlasRegion enemy4;

		public AssetEnemy4 (TextureAtlas atlas) {
			enemy4 = atlas.findRegion("enemy4");
			
			if (enemy4 == null) {
				
				Gdx.app.debug(TAG,"Enemy4 is null");
			}
		}
	}
	
	public class AssetEnemy5 {
		public final AtlasRegion enemy5;

		public AssetEnemy5 (TextureAtlas atlas) {
			enemy5 = atlas.findRegion("enemy5");
			
			if (enemy5 == null) {
				
				Gdx.app.debug(TAG,"Enemy5 is null");
			}
		}
	}
	
	public class AssetShip {
		public final AtlasRegion ship;

		public AssetShip (TextureAtlas atlas) {
			ship = atlas.findRegion("ship");
			
			if (ship == null) {
				
				Gdx.app.debug(TAG,"Ship is null");
			}		
			
		}
		
	}
	
	public class AssetGem {
		public final AtlasRegion gem;

		public AssetGem (TextureAtlas atlas) {
			gem = atlas.findRegion("gem");
			
			if (gem == null) {
				
				Gdx.app.debug(TAG,"Gem is null");
			}		
			
		}
		
	}
	
	public class AssetBomb {
		public final AtlasRegion bomb;

		public AssetBomb (TextureAtlas atlas) {
			bomb = atlas.findRegion("bomb");
			
			if (bomb == null) {
				
				Gdx.app.debug(TAG,"Bomb is null");
			}		
			
		}
		
	}
	
	public class AssetSounds {
		
		public final Sound shipShot;
		public final Sound shipExplosion;
		public final Sound gemPickup;
		public final Sound enemyExplosion;
		public final Sound enemySpawn;
		public final Sound bombExplosion;
		
		public AssetSounds (AssetManager am) {
			
			shipShot = am.get("../JoyShapes-android/assets/sounds/ship_shot.wav", Sound.class);
			shipExplosion = am.get("../JoyShapes-android/assets/sounds/ship_explosion.wav", Sound.class);
			gemPickup = am.get("../JoyShapes-android/assets/sounds/gem_pickup.wav", Sound.class);
			enemyExplosion = am.get("../JoyShapes-android/assets/sounds/enemy_explosion.wav", Sound.class);
			enemySpawn = am.get("../JoyShapes-android/assets/sounds/enemy_spawn.wav", Sound.class);
			bombExplosion = am.get("../JoyShapes-android/assets/sounds/bomb_explosion.wav", Sound.class);
			
		}
		
		
	}

	public void init (AssetManager assetManager) {
		this.assetManager = assetManager;
		// set asset manager error handler
		assetManager.setErrorListener(this);
		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		// load sounds
		assetManager.load("../JoyShapes-android/assets/sounds/ship_shot.wav", Sound.class);
		assetManager.load("../JoyShapes-android/assets/sounds/ship_explosion.wav", Sound.class);
		assetManager.load("../JoyShapes-android/assets/sounds/gem_pickup.wav", Sound.class);
		assetManager.load("../JoyShapes-android/assets/sounds/enemy_explosion.wav", Sound.class);
		assetManager.load("../JoyShapes-android/assets/sounds/enemy_spawn.wav", Sound.class);
		assetManager.load("../JoyShapes-android/assets/sounds/bomb_explosion.wav", Sound.class);
		// start loading assets and wait until finished
		assetManager.finishLoading();

		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()) {
			Gdx.app.debug(TAG, "asset: " + a);
		}

		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures()) {
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}

		// create game resource objects
		fonts = new AssetFonts();
		border = new AssetBorder(atlas);
		bullet = new AssetBullet(atlas);
		enemy1 = new AssetEnemy1(atlas);
		enemy2 = new AssetEnemy2(atlas);
		enemy3 = new AssetEnemy3(atlas);
		enemy4 = new AssetEnemy4(atlas);
		enemy5 = new AssetEnemy5(atlas);
		ship = new AssetShip(atlas);
		gem = new AssetGem(atlas);
		bomb = new AssetBomb(atlas);
		
		number1 = new AssetNumber1(atlas);
		number2 = new AssetNumber2(atlas);
		number3 = new AssetNumber3(atlas);
		number4 = new AssetNumber4(atlas);
		number5 = new AssetNumber5(atlas);
		number6 = new AssetNumber6(atlas);
		number7 = new AssetNumber7(atlas);
		number8 = new AssetNumber8(atlas);
		number9 = new AssetNumber9(atlas);
		number0 = new AssetNumber0(atlas);
		
		number1g = new AssetNumber1g(atlas);
		number2g = new AssetNumber2g(atlas);
		number3g = new AssetNumber3g(atlas);
		number4g = new AssetNumber4g(atlas);
		number5g = new AssetNumber5g(atlas);
		number6g = new AssetNumber6g(atlas);
		number7g = new AssetNumber7g(atlas);
		number8g = new AssetNumber8g(atlas);
		number9g = new AssetNumber9g(atlas);
		number0g = new AssetNumber0g(atlas);
		numberXg = new AssetNumberXg(atlas);
		
		sounds = new AssetSounds(assetManager);
		
	}

	@Override
	public void dispose () {
		assetManager.dispose();
		fonts.defaultSmall.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultBig.dispose();
	}
	
	@Override
	public void error(@SuppressWarnings("rawtypes") AssetDescriptor asset, Throwable throwable) {
	   Gdx.app.error(getClass().getSimpleName(), "Couldn't load asset '" + asset + "'", throwable);
   }

}

