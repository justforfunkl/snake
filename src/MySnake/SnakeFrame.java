package MySnake;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SnakeFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public SnakeFrame() {
		// TODO Auto-generated constructor stub
		
	}
	
	public static void main(String[] args) {
		SnakeFrame frame=new SnakeFrame();
		SnakePanel panel=new SnakePanel(frame);
		frame.setSize(1200, 1000);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(panel);//¼üÅÌ¼àÌý
		Thread thread=panel.new SnakeThread();//ÉßµÄÏß³Ì
		thread.start();
		frame.setVisible(true);
		
	}
}
