package com.nowhereinc.joyshapes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.nowhereinc.joyshapes.game.Level;
import com.nowhereinc.joyshapes.game.Level.BLOCK_TYPE;
import com.nowhereinc.joyshapes.game.objects.Enemy1;
import com.nowhereinc.joyshapes.util.Constants;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class LevelBuilder {
	
	public static final String TAG = Level.class.getName();
	
	// enemy position coordinates
	public Float[] enemy1CoordX;
	public Float[] enemy1CoordY;
	public Float[] enemy2CoordX;
	public Float[] enemy2CoordY;
	public Float[] enemy3CoordX;
	public Float[] enemy3CoordY;
	public Float[] enemy4CoordX;
	public Float[] enemy4CoordY;
	public Float[] enemy5CoordX;
	public Float[] enemy5CoordY;
	
	
	// enemy Indexes
	public int enemys1Index;
	public int enemys2Index;
	public int enemys3Index;
	public int enemys4Index;
	public int enemys5Index;
	
	public LevelBuilder (String filename) {
	
		init();
		load(filename);
		
	}
	
	private void init() {
		
		enemy1CoordX = new Float[20];
		enemy1CoordY = new Float[20];
		enemy2CoordX = new Float[20];
		enemy2CoordY = new Float[20];
		enemy3CoordX = new Float[20];
		enemy3CoordY = new Float[20];
		enemy4CoordX = new Float[20];
		enemy4CoordY = new Float[20];
		enemy5CoordX = new Float[20];
		enemy5CoordY = new Float[20];
		
	
		
		enemys1Index = 0;
		enemys2Index = 0;
		enemys3Index = 0;
		enemys4Index = 0;
		enemys5Index = 0;
		
	}
	
	public void load(String filename) {
		
		// load image file that represents the level data
		Pixmap pixmap = new Pixmap(Gdx.files.internal(filename));
		
		// scan pixels from top-left to bottom-right
		
		int lastPixel = -1;
		
		for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
			for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
						
				// get color of current pixel as 32-bit RGBA value
				int currentPixel = pixmap.getPixel(pixelX, pixelY);
				
				// change the pixel X to negative if it's under half of gameworld width
				
				int adjustedGameBoardWidth = ((int) Constants.GAMEBOARD_WIDTH / 2);
				int adjustedPixelX = pixelX;
				
				adjustedPixelX = adjustedPixelX - adjustedGameBoardWidth;
				
				// change the pixel Y to negative if it's under half of gameworld width
				
				int adjustedGameBoardHeight = ((int) Constants.GAMEBOARD_HEIGHT / 2);
				int adjustedPixelY = pixelY;
				
				adjustedPixelY = adjustedGameBoardHeight - adjustedPixelY;
				
				float adjustedPositionX = 0f;
				float adjustedPositionY = 0f;
				
				// find matching color value to identify block type at (x,y)
				// point and create the corresponding game object if there is
				// a match

				// empty space
				if (BLOCK_TYPE.EMPTY.sameColor(currentPixel)) {
				
				}
						
				// enemy 1
				else if (BLOCK_TYPE.ENEMY1.sameColor(currentPixel)) {
					
					if (adjustedPixelX < 0)
						adjustedPositionX = adjustedPixelX - (Constants.ENEMYSIZE);
					else
						adjustedPositionX = adjustedPixelX;
					
					if (adjustedPixelY < 0)
						adjustedPositionY = adjustedPixelY - (Constants.ENEMYSIZE);
					else
						adjustedPositionY = adjustedPixelY;
					
					enemy1CoordX[enemys1Index] = adjustedPositionX;
					enemy1CoordY[enemys1Index] = adjustedPositionY;
					enemys1Index++;
								
				}
				
				// enemy 2
				else if (BLOCK_TYPE.ENEMY2.sameColor(currentPixel)) {
					
					if (adjustedPixelX < 0)
						adjustedPositionX = adjustedPixelX - (Constants.ENEMYSIZE);
					else
						adjustedPositionX = adjustedPixelX;
					
					if (adjustedPixelY < 0)
						adjustedPositionY = adjustedPixelY - (Constants.ENEMYSIZE);
					else
						adjustedPositionY = adjustedPixelY;
								
					enemy2CoordX[enemys2Index] = adjustedPositionX;
					enemy2CoordY[enemys2Index] = adjustedPositionY;
					enemys2Index++;
									
				}
				
				// enemy 3
				else if (BLOCK_TYPE.ENEMY3.sameColor(currentPixel)) {
					
					if (adjustedPixelX < 0)
						adjustedPositionX = adjustedPixelX - (Constants.ENEMYSIZE);
					else
						adjustedPositionX = adjustedPixelX;
					
					if (adjustedPixelY < 0)
						adjustedPositionY = adjustedPixelY - (Constants.ENEMYSIZE);
					else
						adjustedPositionY = adjustedPixelY;
								
					enemy3CoordX[enemys3Index] = adjustedPositionX;
					enemy3CoordY[enemys3Index] = adjustedPositionY;
					enemys3Index++;
											
				}
				
				// enemy 4
				else if (BLOCK_TYPE.ENEMY4.sameColor(currentPixel)) {
					
					if (adjustedPixelX < 0)
						adjustedPositionX = adjustedPixelX - (Constants.ENEMYSIZE);
					else
						adjustedPositionX = adjustedPixelX;
					
					if (adjustedPixelY < 0)
						adjustedPositionY = adjustedPixelY - (Constants.ENEMYSIZE);
					else
						adjustedPositionY = adjustedPixelY;
					
					enemy4CoordX[enemys4Index] = adjustedPositionX;
					enemy4CoordY[enemys4Index] = adjustedPositionY;
					enemys4Index++;
											
				}
				
				// enemy 5
				else if (BLOCK_TYPE.ENEMY5.sameColor(currentPixel)) {
					
					if (adjustedPixelX < 0)
						adjustedPositionX = adjustedPixelX - (Constants.ENEMYSIZE);
					else
						adjustedPositionX = adjustedPixelX;
					
					if (adjustedPixelY < 0)
						adjustedPositionY = adjustedPixelY - (Constants.ENEMYSIZE);
					else
						adjustedPositionY = adjustedPixelY;
		
					enemy5CoordX[enemys5Index] = adjustedPositionX;
					enemy5CoordY[enemys5Index] = adjustedPositionY;
					enemys5Index++;
					
				}
				
				// unknown object/pixel color
				else {
					// red color channel
					int r = 0xff & (currentPixel >>> 24);
					// green color channel
					int g = 0xff & (currentPixel >>> 16);
					// blue color channel
					int b = 0xff & (currentPixel >>> 8);
					// alpha channel
					int a = 0xff & currentPixel;
					Gdx.app.error(TAG, "Unknown object at x<" + pixelX + "> y<" + pixelY + ">: r<" + r + "> g<" + g + "> b<" + b
									    + "> a<" + a + ">");
					}
					
				lastPixel = currentPixel;
					
				}
			}
				
	}

}
