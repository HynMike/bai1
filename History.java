package PONG_GAME;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class History extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private PongDatabase pdt;

	

	/**
	 * Launch the application.
	 */
	public void showdata(List<ScoreHistory> score1l) {
		List<ScoreHistory> listscore1 = new ArrayList<>();
		listscore1 = score1l;
		DefaultTableModel tablemodel;
		table.getModel();
		tablemodel = (DefaultTableModel) table.getModel();
		tablemodel.setRowCount(0);
		listscore1.forEach((score1) -> {
			tablemodel.addRow(new Object[] { score1.getName(), score1.getScore() });
		});
	}

	/**
	 * Create the frame.
	 */
	public History() {
		pdt = new PongDatabase();
		setTitle("HISTORY");
		this.setFont(new Font("Ink Free", Font.BOLD | Font.ITALIC, 15));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		setBounds(100, 100, 450, 95);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setBackground(new Color(211, 211, 211));
		table.setForeground(new Color(255, 0, 0));
		table.setFont(new Font("Ink Free", Font.BOLD, 15));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null }, { null, null }, },
				new String[] { "player", "score" }));
		table.setBounds(0, 0, 1, 1);
		contentPane.add(table);
		try {
			showdata(pdt.findAll());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 434, 59);
		contentPane.add(scrollPane);
	}
}
