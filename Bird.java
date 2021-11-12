
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bird extends Rectangle{
	
	
	private static final long serialVersionUID = 1L;
	
	private float spd = 4;
	public boolean isPressed = false;
	private BufferedImage sheet;
	private BufferedImage[] texture;
	private ArrayList<Rectangle> tubes;
	private int imageIndex = 0;
	private boolean isFalling = false;
	private int frames = 0;
	
	private float gravity = (float) 0.3;
	 
	public Bird(int x,int y,ArrayList<Rectangle> tubes) {
		setBounds(x,y,45,45);
		this.tubes = tubes;
		texture = new BufferedImage[3];
		try {
		sheet = ImageIO.read(getClass().getResource("/birdy.png"));
		
		texture[0] = sheet.getSubimage(0, 0,16,16);
		texture[1] = sheet.getSubimage(16, 0,16,16);
		texture[2] = sheet.getSubimage(32, 0,16,16);
		}catch(IOException e) {}
		}
		
public void  update() {
	isFalling = false;
	if(isPressed) {
		spd = 4;
		y-=(spd);
		imageIndex = 1 ;
		frames = 0;
	}else {
		isFalling = true;
		y+=spd;
		frames++;
		if(frames > 13) frames = 13;
	}
	
	if (isFalling) {
		spd+=gravity;
		if (spd > 8) spd = 8;
		if (frames >= 13) imageIndex = 2;
		else imageIndex = 0;
	}
	for (int i = 0; i < tubes.size(); i++) {
		if (this.intersects(tubes.get(i))) {
			//restart the game.
			Flappy.room = new Room(70);
			tubes = Flappy.room.tubes;
			y = Flappy.HEIGHT/2;
			Flappy.score = 0;
			spd = 4;
			break;
		}
			
	}
	if (y >= Flappy.HEIGHT) {
		//restart the game.
		Flappy.room = new Room(70);
		tubes = Flappy.room.tubes;
		y = Flappy.HEIGHT/2;
		Flappy.score = 0;
		spd = 4;
	}
	
}


public void render(Graphics g) {
	
	g.drawImage(texture[imageIndex], x, y, width, height, null);
}
}
