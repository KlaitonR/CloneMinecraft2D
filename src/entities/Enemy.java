package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import world.FloorTile;
import world.Tile;
import world.WallTile;
import world.World;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public int vida = 3;
	
	public int dir = 1;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		
		if(World.isFree((int)x,(int)(y+1))) {
			y+=1;
		}
		
		if(dir == 1) {
			if(World.isFree((int)(x+speed), (int)y)) {
				x+=speed;
			}else {
				
				int xnext = (int)((x+speed) / 16) + 1;
				int ynext = (int)(y / 16);
				Tile tile = World.tiles[xnext+ynext*World.WIDTH];
				if(tile instanceof WallTile && !tile.solid) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile(xnext*16, ynext*16, Tile.TILE_AR);
				}
				dir = -1;
				right = true;
				left = false;
			}
		}else if (dir == -1){
			if(World.isFree((int)(x-speed), (int)y)) {
				x-=speed;
			}else {
				
				int xnext = (int)((x - speed) / 16);
				int ynext = (int)(y / 16);
				Tile tile = World.tiles[xnext+ynext*World.WIDTH];
				if(tile instanceof WallTile && !tile.solid) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile(xnext*16, ynext*16, Tile.TILE_AR);
				}
				
				dir = 1;
				right = false;
				left = true;
			}
		}
		
	}
	
	public void render(Graphics g) {
		if(right)
		sprite = Entity.ENEMY_RIGHT;
		else if(left)
		sprite = Entity.ENEMY_LEFT;
		
		super.render(g);
	}

}
