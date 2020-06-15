package MySnake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

public class SnakePanel extends JPanel implements KeyListener,ActionListener{
	JButton start_button,end_button,restart_button;
	JFrame frame;
//	private int x,y;
//	private int width,height;
	private int score=0;
	private int speed=20;
	boolean start=false,live=true;
	enum Direction{UP,DOWN,LEFT,RIGHT};
	Direction direction=Direction.UP;
	List<Snake> list=new ArrayList<Snake>();
	Snake snake=new Snake(400,400);
	Food food=new Food(400,300);
	//SnakeThread thread=new SnakeThread();
	public SnakePanel(JFrame frame) {
		this.frame=frame;
		start_button=new JButton("��ʼ");
		start_button.setBounds(80, 80, 100, 80);
		end_button=new JButton("��ͣ");
		end_button.setBounds(200, 80, 100, 80);
		restart_button=new JButton("����Ϸ");
		restart_button.setBounds(20, 20, 100, 80);
		start_button.setEnabled(true);
		end_button.setEnabled(false);
		start_button.addActionListener(this);
		end_button.addActionListener(this);
		restart_button.addActionListener(this);
		list.add(snake);
		this.add(restart_button);
		this.add(start_button);
		this.add(end_button);
		
		this.setSize(1000, 800);
		this.setLocation(100, 100);
		this.setBackground(Color.white);
		this.setVisible(true);
		this.requestFocus(true);
		
//		this.addKeyListener(this);
//		thread.start();
	}
	public void paint(Graphics g) {
		super.paint(g);
		//eat();
		Font font=new Font(Font.SANS_SERIF,50,50);
		g.setFont(font);
		g.drawString("�÷�Ϊ:  "+score, 700, 80);
		g.drawString("����Ϊ:  "+list.size(), 700, 130);
		g.setColor(Color.GREEN);
		g.fillRect(food.getX(), food.getY(),20, 20);
		
		g.setColor(Color.red);
		list.get(0).draw(g);
		//��������
		g.setColor(Color.blue);
		for(int i=1;i<list.size();i++) {
			list.get(i).draw(g);
		}
		
		if(!live&&start) {//���������
			g.setColor(Color.RED);
			font=new Font(Font.MONOSPACED,100,100);
			g.setFont(font);
			g.drawString("die", 500, 500);
			
			restart_button.setEnabled(true);
			start_button.setEnabled(false);
			end_button.setEnabled(false);
		}

	}
	
	//�Ե�ʳ���ͷ��ʳ���Ƿ�������
	public int eat() {
		if(Math.abs(list.get(0).getX()-food.getX())<20&&Math.abs(list.get(0).getY()-food.getY())<20) {
			score+=10;
			int i=(int) (Math.random()*10000)%1000;
			int j=(int) (Math.random()*10000)%800;
			 boolean f=false;//�ж��²�����ʳ���Ƿ�������ظ���
			for(int k=0;k<list.size();k++) {
				if(i==list.get(k).getX()&&j==list.get(k).getY()) {
					f=true;
				}
			}
			if(f)//û�ظ�
					food=new Food(i,j);
			else {
				i=(int) (Math.random()*10000)%1000;
				j= j=(int) (Math.random()*10000)%800;
				food=new Food(i,j);
			}
			return 1;
		}
		return 0;
	}
	//�ƶ�����Ӧ����������ƶ���
	public void move(Direction d) {
		int firstX=list.get(0).getX();//��һ����������
		int firstY=list.get(0).getY();
		int oldX=list.get(list.size()-1).getX();//���һ����������
		int oldY=list.get(list.size()-1).getY();
		Snake s;
		
		
		switch(d) {
		case UP:
			for(int i=list.size()-1;i>0;i--) {
				
				list.set(i, list.get(i-1));//����һ������⣬ÿ���ڵ㶼��ǰһ���Ĵ���	�������ƶ���	
			}
			if(firstY<0) {//�����ͷԽ���ϱ߽磬����һ��������õ����±߽�����������ƶ�
				list.set(0, new Snake(firstX,960));
			}
			else	
				list.set(0, new Snake(firstX,firstY-20));//����һ��������õ��ڶ���֮ǰ
			//�Ƿ�Ե�ʳ��
			if(eat()==1) {
				s=new Snake(oldX,oldY+20);
				   list.add(s);
			}
			break;
		case DOWN:
			for(int i=list.size()-1;i>0;i--) {
				
				list.set(i, list.get(i-1));//����һ������⣬ÿ���ڵ㶼��ǰһ���Ĵ���		�������ƶ���
			}
			if(firstY>1000) {//�����ͷԽ���±߽磬����һ��������õ����ϱ߽�����������ƶ�
				list.set(0, new Snake(firstX,30));
			}
			else
				list.set(0, new Snake(firstX,firstY+20));
			
			if(eat()==1) {
				s=new Snake(oldX,oldY-20);
				   list.add(s);
			}
			break;
		case LEFT:
			for(int i=list.size()-1;i>0;i--) {
				
				list.set(i, list.get(i-1));//����һ������⣬ÿ���ڵ㶼��ǰһ���Ĵ���		�������ƶ���
			}
			if(firstX<0) {//�����ͷԽ����߽磬����һ��������õ����ұ߽�����������ƶ�
				list.set(0, new Snake(1160,firstY));
			}
			else
				list.set(0, new Snake(firstX-20,firstY));
			
			if(eat()==1) {
				s=new Snake(oldX+20,oldY);
				   list.add(s);
			}
			break;
		case RIGHT:
			for(int i=list.size()-1;i>0;i--) {
				
				list.set(i, list.get(i-1));//����һ������⣬ÿ���ڵ㶼��ǰһ���Ĵ���		�������ƶ���
			}
			if(firstX>1200) {//�����ͷԽ���ұ߽磬����һ��������õ�����߽�����������ƶ�
				list.set(0, new Snake(30,firstY));
			}
			else
				list.set(0, new Snake(firstX+20,firstY));
		
		if(eat()==1) {
			s=new Snake(oldX-20,oldY);
			   list.add(s);
		}
		break;
		}
		
		//���ͷ�������Ƿ���ײ
		for(int i=1;i<list.size();i++) {
			if(Math.abs(list.get(0).getX()-list.get(i).getX())<20&&Math.abs(list.get(0).getY()-list.get(i).getY())<20) {
				live=false;
			}
		}
	}
	
//	public void CrossWall() {
//		int headX=list.get(0).getX();
//		int headY=list.get(0).getY();
//		if(headX<0) {
//			list.get(0).setX(1200);
//		}
//		if(headX>1200) {
//			list.get(0).setX(0);
//		}
//		if(headY<0) {
//			list.get(0).setY(1000);
//		}
//		if(headY>0) {
//			list.get(0).setY(0);
//		}
//	}
	
	class SnakeThread extends Thread{ //�ߵ��߳�
		public void run() {
			while(true) {
				//System.out.println("����"+direction);
				if(start&&live) {
					
					move(direction);
					repaint();
			}
				try {
					Thread.sleep(100);
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {//�����ͷ�ĸı�
		//if(!start&&!live) return ;
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_UP:
			
			if(direction!=Direction.DOWN)
			
			direction=Direction.UP;
			
			break;
		case KeyEvent.VK_DOWN:
			if(direction!=Direction.UP)
			direction=Direction.DOWN;
		
			
			break;
		case KeyEvent.VK_LEFT:
			if(direction!=Direction.RIGHT)
			direction=Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			if(direction!=Direction.LEFT)
			direction=Direction.RIGHT;
			break;
		default: break;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			
		if(e.getSource()==start_button) {
			start=true;
			frame.requestFocus();     //�����ť��JFrame�ļ��̼�����ʧȥ���㣬Ҫ���»��
			start_button.setText("����");;
			start_button.setEnabled(false);
			end_button.setEnabled(true);
			restart_button.setEnabled(false);
		}
		if(e.getSource()==end_button) {
			start=false;
			frame.requestFocus();
			start_button.setEnabled(true);
			end_button.setEnabled(false);
			restart_button.setEnabled(true);
		}
		if(e.getSource()==restart_button) {
			frame.requestFocus();
			start=true;
			live=true;
			score=0;
			list.clear();
			food=new Food(400,300);
			 snake=new Snake(400,400);
			list.add(snake);
			start_button.setEnabled(false);
			end_button.setEnabled(true);
			restart_button.setEnabled(false);
			switch((int)(Math.random()*1000)%4){  //���¿�ʼ���������
				case 0: direction=Direction.UP;break;
				case 1:direction=Direction.DOWN;break;
				case 2:direction=Direction.LEFT;break;
				case 3:direction=Direction.RIGHT;break;
			}
		}
	}

}

