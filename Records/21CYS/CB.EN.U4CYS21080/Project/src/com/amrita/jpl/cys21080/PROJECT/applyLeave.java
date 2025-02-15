package com.amrita.jpl.cys21080.project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.io.IOException;


public class applyLeave {
    private JFrame frame;
    private JFrame parentFrame;

    String b;


    public applyLeave(JFrame parentFrame,String a) {
        this.parentFrame = parentFrame;
        this.b = a;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle(b +" Leave Application");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocation(760,390);


        JLabel titleLabel = new JLabel("LEAVE APPLYING FORM");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel leaveFromLabel = new JLabel("Leave From:");
        JSpinner leaveFromSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor leaveFromEditor = new JSpinner.DateEditor(leaveFromSpinner, "dd/MM/yyyy");
        leaveFromSpinner.setEditor(leaveFromEditor);

        JLabel leaveUntilLabel = new JLabel("Leave Until:");
        JSpinner leaveUntilSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor leaveUntilEditor = new JSpinner.DateEditor(leaveUntilSpinner, "dd/MM/yyyy");
        leaveUntilSpinner.setEditor(leaveUntilEditor);

        JLabel reasonLabel = new JLabel("Reason:");
        JTextField reasonField = new JTextField(20);

        JLabel applyToLabel = new JLabel("Apply To:");
        String[] applyToOptions = {"Advisor", "HOD", "Warden"};
        JComboBox<String> applyToComboBox = new JComboBox<>(applyToOptions);

        JButton submitButton = new JButton("Submit");
        JButton exitButton = new JButton("Exit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date leaveFromDate = (Date) leaveFromSpinner.getValue();
                Date leaveUntilDate = (Date) leaveUntilSpinner.getValue();
                String textFieldValue = reasonField.getText();

                if (leaveFromDate.compareTo(leaveUntilDate) > 0) {
                    JOptionPane.showMessageDialog(null, "Leave Until date should be after Leave From date");
                } else if (textFieldValue.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Reason is required.");

                }
                else {
                    // Process leave application
                    String reason = reasonField.getText();
                    String applyTo = applyToComboBox.getSelectedItem().toString();
                    String message = "Leave Application Submitted:\n" +
                            "Leave From: " + leaveFromDate.toString() + "\n" +
                            "Leave Until: " + leaveUntilDate.toString() + "\n" +
                            "Reason: " + reason + "\n" +
                            "Apply To: " + applyTo;
                    JOptionPane.showMessageDialog(null, message);
                    appendToFile(b, leaveFromDate, leaveUntilDate, reason, applyTo);
                    exitButton.doClick();
                   // int option = JOptionPane.showOptionDialog(null, "Exit?", "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

                   /* if (option == JOptionPane.OK_OPTION) {
                        exitButton.doClick();
                    }*/

                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                parentFrame.setVisible(true);
            }
        });

        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.add(titleLabel);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.add(leaveFromLabel);
        formPanel.add(leaveFromSpinner);
        formPanel.add(leaveUntilLabel);
        formPanel.add(leaveUntilSpinner);
        formPanel.add(reasonLabel);
        formPanel.add(reasonField);
        formPanel.add(applyToLabel);
        formPanel.add(applyToComboBox);
        formPanel.add(submitButton);
        formPanel.add(exitButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void appendToFile(String rollNumber, Date leaveFromDate, Date leaveUntilDate, String reason, String applyTo) {
        String directoryPath = "src/com/amrita/jpl/cys21080/project/Leave_History";
        String filename = rollNumber + "_leave.txt";
        try {
            String filePath = directoryPath + File.separator + filename;

            FileWriter writer = new FileWriter(filePath, true);
            writer.write("Leave Application Submitted:\n");
            writer.write("Leave From: " + leaveFromDate.toString() + "\n");
            writer.write("Leave Until: " + leaveUntilDate.toString() + "\n");
            writer.write("Reason: " + reason + "\n");
            writer.write("Apply To: " + applyTo + "\n");
            writer.write("\n");
            writer.close();
            System.out.println("Leave application appended to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
