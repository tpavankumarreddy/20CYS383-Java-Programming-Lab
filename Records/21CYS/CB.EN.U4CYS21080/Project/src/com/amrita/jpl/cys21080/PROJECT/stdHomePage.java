package com.amrita.jpl.cys21080.project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class stdHomePage implements ActionListener {

    JFrame frame = new JFrame("LMS Student Home Page");

    JLabel LMSLabel = new JLabel("Leave Management System");
    JLabel welcomeLabel = new JLabel("Hello!");
    JLabel logo = new JLabel();
    JButton applyLeavebtn = new JButton("Apply Leave");

    JButton logout = new JButton();
    JButton attendence = new JButton();
    JButton leaveHistory = new JButton();
    JButton announcement = new JButton();
    JButton od = new JButton();

    JButton ac = new JButton();

    String userID;

    private JFrame parentFrame;
    stdHomePage(String userID,JFrame parentframe){

        this.parentFrame = parentframe;
        this.userID = userID;
        ImageIcon icon = new ImageIcon("src/com/amrita/jpl/cys21080/project/images/3889524.png");
        ImageIcon amritalogo = new ImageIcon("src/com/amrita/jpl/cys21080/project/images/AMRIT-removebg-preview_2.png");

        logo.setIcon(amritalogo);
        logo.setBounds(30,0,350,100);

        LMSLabel.setBounds(130,120,400,30);
        LMSLabel.setFont(new Font("MV Boli",Font.BOLD,25));


        welcomeLabel.setBounds(320,35,200,35);
        welcomeLabel.setFont(new Font(null,Font.PLAIN,18));
        welcomeLabel.setText("Hello "+userID);

        applyLeavebtn.setBounds(310, 320, 150, 30);
        applyLeavebtn.addActionListener(this);

        attendence.setText("View Attendance");
        attendence.setBounds(140,270,150,30);
        attendence.addActionListener(this);

        announcement.setText("Announcements");
        announcement.setBounds(140,220,150,30);
        announcement.addActionListener(this);

        leaveHistory.setText("Leave History");
        leaveHistory.setBounds(310,220,150,30);
        leaveHistory.addActionListener(this);

        od.setText("Apply Duty Leave");
        od.setBounds(310,270,150,30);
        od.addActionListener(this);

        ac.setText("Academic Calender");
        ac.setBounds(140,320,150,30);
        ac.addActionListener(this);

        logout.setIcon(icon);
        logout.setFocusable(false);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setBounds(500,35,100,35);



        frame.setLocation(660,240);
        frame.add(od);
        frame.add(ac);
        frame.add(announcement);
        frame.add(leaveHistory);
        frame.add(attendence);
        frame.add(logo);
        frame.add(LMSLabel);
        frame.add(welcomeLabel);
        frame.add(applyLeavebtn);
        frame.add(logout);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Logout successful.", "Information", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();

                parentFrame.setVisible(true);
            }
        });

        attendence.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceReportUI a = new AttendanceReportUI(userID,frame);
                a.show();
                frame.setVisible(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==announcement) {
            JOptionPane.showMessageDialog(null, "Coming Soon !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource()==applyLeavebtn) {
            applyLeave al = new applyLeave(frame,userID );
            al.show();
            frame.setVisible(false);
        }

        if (e.getSource()==ac){
            JOptionPane.showMessageDialog(null, "Coming Soon !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource()==leaveHistory) {
            JOptionPane.showMessageDialog(null, "Updating ...", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

        if (e.getSource()==od) {
            JOptionPane.showMessageDialog(null, "Coming Soon !!!", "Information", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}