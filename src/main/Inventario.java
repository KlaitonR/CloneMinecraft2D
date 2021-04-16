package main;

import java.awt.Color;
import java.awt.Graphics;

import world.Camera;
import world.FloorTile;
import world.Tile;
import world.WallTile;
import world.World;

public class Inventario {
	
	public int inventoryBoxSize = 40;
	public String[] itens = {"grama", "terra", "neve", "areia", "argila", "ar"};
	public int initialPosition = ((Game.WIDTH*Game.SCALE) / 2) - ((itens.length*inventoryBoxSize)/2);
	public int selected;
	public boolean isPress;
	public boolean isPlaceItem;
	public int mx;
	public int my;
	
	public void tick() {
		
		if(isPress) {
			isPress = false;
			
			if(mx >= initialPosition && mx < initialPosition + (inventoryBoxSize*itens.length)) {
				if(my >= (Game.WIDTH*Game.SCALE/2) + inventoryBoxSize + 35 && my < ((Game.WIDTH*Game.SCALE/2) + inventoryBoxSize + 35)*inventoryBoxSize){
					selected = (int)(mx-initialPosition)/inventoryBoxSize;
				}
			}
		}
		
		if(isPlaceItem) {
			
			isPlaceItem = false;
			mx = (int)mx/3 + Camera.x;
			my = (int)my/3 + Camera.y;
			
			int tilex = mx/16;
			int tiley = my/16;
			
			if(!World.tiles[tilex+tiley*World.WIDTH].solid) {
				if(itens[selected].equals("grama")){
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_GRAMA);
				}else if (itens[selected].equals("terra")) {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_TERRA);
				}else if (itens[selected].equals("ar")) {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16, tiley*16, Tile.TILE_AR);
				}else if (itens[selected].equals("areia")) {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_AREIA);
				}else if (itens[selected].equals("neve")) {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_NEVE);
				}
				
				if(!World.isFree(Game.player.getX(), Game.player.getY())) {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16, tiley*16, Tile.TILE_AR);
				}
				
			}
			
		}
		
		
	}
	
	public void render(Graphics g) {
		
		for(int i=0; i<itens.length; i++) {
			g.setColor(Color.black);
			g.fillRect(initialPosition + (i*inventoryBoxSize), (Game.WIDTH*Game.SCALE/2) + inventoryBoxSize + 35, inventoryBoxSize, inventoryBoxSize);
			g.setColor(Color.gray);
			g.drawRect(initialPosition + (i*inventoryBoxSize), (Game.WIDTH*Game.SCALE/2) + inventoryBoxSize + 35, inventoryBoxSize, inventoryBoxSize);
			
			if(itens[i].equals("grama")) {
				g.drawImage(Tile.TILE_GRAMA, initialPosition + (i*inventoryBoxSize) + 8, ((Game.HEIGHT*Game.SCALE)/2) + inventoryBoxSize + 160, 28, 28, null);
			}else if(itens[i].equals("ar")) {
				g.drawImage(Tile.TILE_AR, initialPosition + (i*inventoryBoxSize) + 8, ((Game.HEIGHT*Game.SCALE)/2) + inventoryBoxSize + 160, 28, 28, null);
			}else if(itens[i].equals("areia")) {
				g.drawImage(Tile.TILE_AREIA, initialPosition + (i*inventoryBoxSize) + 8, ((Game.HEIGHT*Game.SCALE)/2) + inventoryBoxSize + 160, 28, 28, null);
			}else if(itens[i].equals("neve")) {
				g.drawImage(Tile.TILE_NEVE, initialPosition + (i*inventoryBoxSize) + 8, ((Game.HEIGHT*Game.SCALE)/2) + inventoryBoxSize + 160, 28, 28, null);
			}
			
			if(itens[i].equals("terra")) {
				g.drawImage(Tile.TILE_TERRA, initialPosition + (i*inventoryBoxSize) + 8, ((Game.HEIGHT*Game.SCALE)/2) + inventoryBoxSize + 160, 28, 28, null);
			}
			
			if(selected == i) {
				g.setColor(Color.red);
				g.drawRect( initialPosition + (i*inventoryBoxSize) + 8, ((Game.HEIGHT*Game.SCALE)/2) + inventoryBoxSize + 160, 28, 28);
			}
		}
	}

}
