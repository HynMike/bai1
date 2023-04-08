package PONG_GAME;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class GameFrame extends JFrame {

	GamePanel panel;
	Button myButton;
	private JMenuBar Jmenubar;
	private JMenu 
		JMGame=new JMenu("Trò chơi"),
		JMHelp=new JMenu("Trợ giúp");  
	private JMenuItem 
		JMIGameMoi=new JMenuItem("Trò chơi mới"),
		JMIStop = new JMenuItem("Tạm Dừng"),
		JMIResume = new JMenuItem("Tiếp Tục"),
		JMIEnd = new JMenuItem("Kết Thúc"),
		JMIExit = new JMenuItem("Thoát"),
		JMILuatChoi=new JMenuItem("Luật chơi"), 
		JMIThongTin=new JMenuItem("Thông tin");

	GameFrame() {
		panel = new GamePanel();
		this.add(panel);
		addMenu();
		this.setTitle("PONG GAME NGUYỄN CÔNG MINH VÀ ĐINH THỊ THU HIỀN");
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	void addMenu(){
		Jmenubar=new JMenuBar();  
		Jmenubar.setBounds(0,0,500,30);  
		setJMenuBar(Jmenubar);			
		JMGame.add(JMIGameMoi);
		JMGame.add(JMIStop);
		JMGame.add(JMIResume);
		JMGame.add(JMIEnd);
		JMGame.add(JMIExit);
		JMHelp.add(JMILuatChoi);
		JMHelp.add(JMIThongTin);
		
		Jmenubar.add(JMGame);
		Jmenubar.add(JMHelp);
	
		JMIGameMoi.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        panel.newGame();
		    }
		});
		JMIStop.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	panel.display(getGraphics());
		        panel.pause();
		    }
		});
		JMIResume.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        panel.resume();
		    }
		});
		JMIEnd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	Graphics g = getGraphics();
		    	panel.gameOver(g);
		        panel.stop();
		    }
		});
	JMIExit.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	    }
	});
	JMIThongTin.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	JOptionPane.showMessageDialog(rootPane, ""
	                + "Pong là trò chơi thể thao đồ họa hai chiều mô phỏng môn bóng"
	                + "\nbàn. Người chơi điều khiển vợt là thanh thẳng lên xuống ở cạnh"
	                + "\ntrái hoặc cạnh phải màn hình. Người chơi khác có thể điều khiển"
	                + "\nthanh thẳng còn lại ở phía bên kia để thi đấu. Thanh thẳng có tác"
	                + "\ndụng dùng để đánh bóng qua lại. Mục tiêu là đạt được 11 điểm"
	                + "\ntrước đối thủ; điểm được tính khi đối phương không đánh bóng trở"
	                + "\nlại được.",
	                "Hướng dẫn", JOptionPane.INFORMATION_MESSAGE);
	}
	    
	});
	JMILuatChoi.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	JOptionPane.showMessageDialog(rootPane, ""
	                + "=> Nhấn 'Play' để chơi game"
	                + "\n=> Dùng W và S để điều khiển thanh điều hướng bên trái."
	                + "\n=> Dùng Lên và Xuống để điều khiển thanh điều hướng bên phải."
	                + "\n=> Điều khiển hai thanh điều hướng để đánh bóng qua lại"
	                + "\n=> Không được để bóng đập vào hai biên trái phải "
	                + "\n=> Mỗi khi bóng va chạm vào hai thanh điều hướng sẽ tăng tốc độ"
	                + "\n=> SPACE : PAUSE ; ENTER : RESUME ; ESC : END",
	                "Hướng dẫn", JOptionPane.INFORMATION_MESSAGE);
	}
	    
	});
	}
}
	

