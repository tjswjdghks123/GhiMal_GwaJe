package vaa;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ChiMal_GwaJe extends JFrame {

    JPanel northPanel, centerPanel, southPanel;
    JLabel subjectLabel, scoreLabel;
    JTextField subjectField, displayField, scoreField;
    JButton addButton, deleteButton;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane tableScroll;

    HashMap<String, Integer> subjectMap = new HashMap<>();

    ChiMal_GwaJe() {

        setTitle("기말 과제 (학점 계산기)");
        setSize(500, 700);

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
        scoreLabel = new JLabel("학점");
        scoreField = new JTextField(10);
        addButton = new JButton("추가");
        deleteButton = new JButton("삭제");

        addButton.addActionListener(e -> {
            String subject = subjectField.getText().trim();
            if (!subject.isEmpty() && !subjectMap.containsKey(subject)) {
                subjectMap.put(subject, null);
                subjectField.setText("");
                displayField.setText(subject);
                updateTable();
            }
        });

        deleteButton.addActionListener(e -> {
            String subject = displayField.getText().trim();
            if (!subject.isEmpty() && subjectMap.containsKey(subject)) {
                subjectMap.remove(subject);
                displayField.setText("");
                updateTable();
            }
        });

        northPanel.add(subjectLabel);
        northPanel.add(subjectField);
        northPanel.add(addButton);
        northPanel.add(deleteButton);

        add(northPanel, BorderLayout.NORTH);

    }

    void setupCenterPanel() {

        centerPanel = new JPanel();
        displayField = new JTextField(20);
        displayField.setEditable(false);

        centerPanel.add(displayField);
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
        for (Map.Entry<String, Integer> entry : subjectMap.entrySet()) {
            tableModel.addRow(new Object[] { entry.getKey(), entry.getValue() });
        }
    }

    public static void main(String[] args) {
        new ChiMal_GwaJe();
    }
}

