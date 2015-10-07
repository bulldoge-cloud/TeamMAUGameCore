import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.Rectangle; 

public class Tower extends Entity{

	private int startX;
	int life = 1;
	
	public Tower(int x, int y){
	
		super(x,y);
		startX = x;
	}
	
	public void update(){
	
		x += 0;
		checkCollisions();
		//checkOffScreen();
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics2D g2d){
	
		g2d.drawImage(getTowerImg(), x, y, null);
		//g2d.draw(getBounds());
	
	}

	public Image getTowerImg(){
	
		ImageIcon ic = new ImageIcon("tower.png");
		return ic.getImage();
	
	}
	
	public void checkCollisions(){
	
		for(int i=0; i<GameFrame.getUnitList().size(); i++){
		
			Unit u = GameFrame.getUnitList().get(i);
		
			if(getBounds().intersects(u.getBounds())){

				//GameFrame.removeFireBall(f);
				//life = life - 1;
				u.update(0);
				
				if(life == 0){
				
					GameFrame.removeTower(this);
					
				}
			}
		
		}
	
	}
	
	public void checkOffScreen(){
	
		if(x >= 700){
		
			x = startX;
		
		}
	
	}
	
	public Rectangle getBounds(){
	
		return new Rectangle(x, y, getTowerImg().getWidth(null), getTowerImg().getHeight(null));
	
	}
	
}