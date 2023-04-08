package PONG_GAME;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JTextField;

public class Ponggame extends JFrame {

	private JPanel contentPane;// cả khung trang chủ
	private JButton btnNewButton;
	private JButton btnScore;
    private History htr;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ponggame frame = new Ponggame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Ponggame() {
		
		setTitle("PONG GAME NGUYỄN CÔNG MINH VÀ ĐINH THỊ THU HIỀN");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 481);
		contentPane = new JPanel();
		contentPane.setBackground(Color.pink);

		setContentPane(contentPane);// giện khung trang chủ
		contentPane.setLayout(null);//sắp xếp các mục trên 1 dòng
		
		JLabel lblNewLabel = new JLabel("PONG GAME");
		lblNewLabel.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 60));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setBounds(34, 0, 524, 111);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("PLAY");
		btnNewButton.setBackground(new Color(220, 220, 220));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GameFrame();//bắt đầu chơi
			}
		});
		btnNewButton.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.setBounds(184, 145, 176, 50);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("MATCH POINT");
		lblNewLabel_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(132, 101, 294, 42);
		contentPane.add(lblNewLabel_1);
		
		JButton btnScore = new JButton("HELP");
		btnScore.setBackground(new Color(220, 220, 220));
		btnScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane là hộp thoại mở ra và chọn: showMessageDialog
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
		
		
		btnScore.setForeground(Color.RED);
		btnScore.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnScore.setBounds(184, 206, 176, 50);//tương tự size nhma có tọa độ điểm trái cùng
		contentPane.add(btnScore);
		JButton btnHistory = new JButton("HISTORY");
		btnHistory.setBackground(new Color(220, 220, 220));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				htr = new History();
				htr.setVisible(true);
			}
		});
		btnHistory.setForeground(Color.RED);
		btnHistory.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnHistory.setBounds(184, 267, 176, 50);
		contentPane.add(btnHistory);
		
		JLabel lblNewLabel_1_1 = new JLabel("GOOK LUCK");
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel_1_1.setBounds(156, 389, 234, 42);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setForeground(Color.RED);
		btnExit.setFont(new Font("Snap ITC", Font.BOLD | Font.ITALIC, 20));
		btnExit.setBackground(new Color(220, 220, 220));
		btnExit.setBounds(184, 328, 176, 50);
		contentPane.add(btnExit);
		
		
	}
}
