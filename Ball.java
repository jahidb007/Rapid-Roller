import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;


public class Ball implements Runnable, ImageObserver{
	 
	SaveScore	sc= new SaveScore();
	Image ballImage;
	ImageIcon bi,brokenImage;
	Font font = new Font("Meiryo", Font.BOLD, 14);
		int x=150,y=70;
		int leftmovex,rightmovex,count,score,hscore;
		double s,ds,v,u,g=1.8;
		boolean moveBall=false,gameStart=false;
		Rectangle ball = new Rectangle(x,y,10,10);
		Rectangle soil = new Rectangle(0,355,300,10);
		Rectangle sky = new Rectangle(0,5,355,30);
		Paddle p1 = new Paddle(400,1);
		Paddle p2 = new Paddle(500,2);
		Paddle p3 = new Paddle(600,3);
		Paddle p4 = new Paddle(700,4);
		
		public Ball(){
			
			
			bi = new ImageIcon(this.getClass().getResource("ball.png"));
			ballImage = bi.getImage();
			brokenImage = new ImageIcon(this.getClass().getResource("ballBroken.png"));
		}
		public void ballDown(){
			
			if (moveBall==true) {
				ballImage = bi.getImage();
				u = v;
				v = u + (g * .005);
				ds = (((v * v) - (u * u)) / (2 * g));
				s = s + ds;
				ball.y = ball.y + (int) s;
				count++;
				if (count == 10) {
					s = 1;
				}
			}
			
		}
		int asd;
		public void scoreUpdate(){
			if (moveBall) {
				double sc = s;
				score += sc;
				asd = score / 10;
				if (asd > hscore)
					hscore = asd;
			}
		}
		public void collision(){
			if (moveBall) {
				if (ball.intersects(p1.paddle)) {
					ball.y = p1.paddle.y - 10;
					reVelocity();
					
				}
				if (ball.intersects(p2.paddle)) {
					ball.y = p2.paddle.y - 10;
					reVelocity();
				}
				if (ball.intersects(p3.paddle)) {
					ball.y = p3.paddle.y - 10;
					reVelocity();
				}
				if (ball.intersects(p4.paddle)) {
					ball.y = p4.paddle.y - 10;
					reVelocity();
				}
			}
		}
		
		
		public void reVelocity() {
			s = 0;ds = 0;v = 0;u = 0;count = 0;
		}
		public void ballMove(){
			if(ball.x>0)
			ball.x+=leftmovex;
			if(ball.x<290)
			ball.x+=rightmovex;
		}
		
		public void run() {
		try {
			while(true){
				fallDown();
				collision();
				ballDown();
				ballMove();
				scoreUpdate();
				Thread.sleep(5);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		public void fallDown() {
			if(ball.intersects(soil)||ball.intersects(sky)){
				moveBall=false;
				ballImage = brokenImage.getImage();
			}
		}
		void draw(Graphics g){
		g.drawImage(ballImage,ball.x,ball.y,this);
		g.setFont(font);
		g.setColor(Color.darkGray);
		g.fillRect(300, 0, 135, 400);
		g.setColor(Color.WHITE);
		g.drawString("score = "+asd, 310,90);
		g.drawString("High score = "+hscore, 300,70);
		if(moveBall==false && gameStart==true){
			g.setColor(Color.LIGHT_GRAY);
			g.drawString("PRESS 'R' TO RESTART THE GAME",25,140);
		}
	if (gameStart==false) {
		g.drawString("Press LEFT or Right Arrow", 100, 200);
		g.drawString("To Start the Game !!", 100, 220);
	}
		}
		public void keyPressed(KeyEvent e) {
			int keycode = e.getKeyCode();
			if(keycode==KeyEvent.VK_LEFT){
				leftmovex=-1;
				gameStart=true;
				moveBall=true;
			}
			if(keycode==KeyEvent.VK_RIGHT){
				rightmovex=1;
				gameStart=true;
				moveBall=true;
			}
			if(keycode==KeyEvent.VK_R){
				ball.y=40;
				moveBall=true;
				score=0;
				reVelocity();
			}
		}
		public void keyReleased(KeyEvent e) {
			int keycode = e.getKeyCode();
			if(keycode==KeyEvent.VK_LEFT){
				leftmovex=0;
			}
			if(keycode==e.VK_RIGHT){
				rightmovex=0;
			}
			
		}
		public void mouseMoved(MouseEvent e) {
			
			
		}
		@Override
		public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
				int arg4, int arg5) {
			// TODO Auto-generated method stub
			return false;
		}

}
