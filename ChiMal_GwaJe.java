package vaa;

import java.awt.BorderLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChiMal_GwaJe extends JFrame {

    JPanel northPanel, centerPanel, southPanel;
    JLabel subjectLabel, scoreLabel;
    JTextField subjectField, scoreField;
    JButton addButton, deleteButton;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane tableScroll;

    HashMap<String, String> subjectMap = new HashMap<>();

    ChiMal_GwaJe() {
        setTitle("기말 과제 (학점 계산기)");
        setSize(400, 540);

        setLayout(new BorderLayout());

        setupNorthPanel();
        setupCenterPanel();
        setupSouthPanel();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void setupNorthPanel() {

        northPanel = new JPanel();

        subjectLabel = new JLabel("과목 이름: ");
        subjectField = new JTextField(10);
        addButton = new JButton("추가");
        deleteButton = new JButton("삭제");

        addButton.addActionListener(e -> {
            String subject = subjectField.getText().trim();
            String grade = scoreField.getText().trim().toUpperCase();

            if (subject.isEmpty() || grade.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "과목 이름과 학점을 모두 입력해주십시오.",
                        "오류",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!isValidGrade(grade)) {
                JOptionPane.showMessageDialog(
                        this,
                        "본인이 받은 학점을 입력하십시오.",
                        "오류",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            if (!subjectMap.containsKey(subject)) {
                subjectMap.put(subject, grade);
                updateTable();
                subjectField.setText("");
                scoreField.setText("");
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "이미 추가된 과목입니다.",
                        "오류",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        deleteButton.addActionListener(e -> {
            String subject = subjectField.getText().trim();
            if (subject.isEmpty() || !subjectMap.containsKey(subject)) {
                JOptionPane.showMessageDialog(
                        this,
                        "삭제할 과목 이름을 입력해주십시오.",
                        "오류",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            subjectMap.remove(subject);
            updateTable();
            subjectField.setText("");
        });

        northPanel.add(subjectLabel);
        northPanel.add(subjectField);
        northPanel.add(addButton);
        northPanel.add(deleteButton);

        add(northPanel, BorderLayout.NORTH);
    }

    void setupCenterPanel() {
        centerPanel = new JPanel();
        scoreLabel = new JLabel("학점: ");
        scoreField = new JTextField(10);

        centerPanel.add(scoreLabel);
        centerPanel.add(scoreField);
        add(centerPanel, BorderLayout.CENTER);
    }

    void setupSouthPanel() {
        southPanel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel(new String[] { "과목 이름", "학점" }, 0);
        table = new JTable(tableModel);
        tableScroll = new JScrollPane(table);

        southPanel.add(tableScroll);
        add(southPanel, BorderLayout.SOUTH);
    }

    void updateTable() {
        tableModel.setRowCount(0);
        for (Map.Entry<String, String> entry : subjectMap.entrySet()) {
            tableModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
    }

    boolean isValidGrade(String grade) {
    	return grade.matches("A\\+|A0|A-|B\\+|B0|B-|C\\+|C0|C|D\\+|D0|D|F\\+|F");
    	
    }

    public static void main(String[] args) {
        new ChiMal_GwaJe();
    }
}
