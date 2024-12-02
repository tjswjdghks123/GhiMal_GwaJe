package vaa;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChiMal_GwaJe extends JFrame {

	JPanel p1, p2, p3, p4;
	JLabel la1, la2, la3;
	JTextField tf1, tf2, tf3;
	JButton bt1, bt2, bt3;

	ChiMal_GwaJe() {
		setTitle("기말 과제 (학점 계산기)");
		setSize(500, 500);

		setLayout(new BorderLayout());

		north();
		Center();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}

	void north() {

		p1 = new JPanel();
		p2 = new JPanel(new BorderLayout());

		la1 = new JLabel("과목 이름 : ");
		tf1 = new JTextField(5);
		bt1 = new JButton("추가");
		bt2 = new JButton("삭제");

		ActionListener l1 = e -> {
			if (e.getSource() == bt1)
				
			if (tf1.getText().isEmpty()) {

			} else { // 비어있지 않으면
				String a = tf1.getText();
				tf1.setText(" "); //버튼을 누르면 값이 초기화
				tf3.setText(a);
			}
		};
		
		ActionListener l2 = e -> {
			if (e.getSource() == bt2)
				
			if (tf3.getText().isEmpty()) {

			} else {
				tf3.setText(" ");
			}
		};

		bt1.addActionListener(l1);
		bt2.addActionListener(l2);

		p1.add(la1);
		p1.add(tf1);
		p1.add(bt1);
		p1.add(bt2);

		p2.add(p1);

		add(p2, BorderLayout.NORTH);

	}

	void Center() {

		p3 = new JPanel();
		p4 = new JPanel(new BorderLayout());

		tf3 = new JTextField(20);

		p3.add(tf3);
		p4.add(p3);
		add(p4, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new ChiMal_GwaJe();
	}

}
