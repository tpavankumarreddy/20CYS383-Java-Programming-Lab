package com.amrita.jpl.cys21080.project;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.attribute.UserPrincipal;
import java.util.HashMap;


public class loginPage implements ActionListener {

    private JButton btn; // Declare btn as an instance variable
    private JTextField text1;
    private JPasswordField text2;
    private JFrame frame;
    private JLabel msglabel;

    private static String rolluser;

    HashMap<String,String> logininfo = new HashMap<String,String>();


    loginPage(HashMap<String,String> loginInfoOriginal) {

        rolluser="";
        frame = new JFrame();
        frame.setLocation(660,240);
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();

        logininfo = loginInfoOriginal;

        msglabel = new JLabel();

        btn = new JButton("login");
        text1 = new JTextField();
        text2 = new JPasswordField();
        JPanel panel1 = new JPanel();
        ImageIcon logo = new ImageIcon("src/com/amrita/jpl/cys21080/project/images/lms_icon.png");





        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("LMS - Login");
        frame.setLayout(null);
        frame.setResizable(false);

        panel1.setBounds(170,80,250,250);
        label3.setIcon(logo);


        label1.setText("Username : ");
        label1.setFont(new Font("MV Boli",Font.PLAIN,20));
        label1.setBounds(110, 300, 150, 30);
        label1.setOpaque(true);

        text1.setBounds(280, 300, 180, 30);

        label2.setText("Password : ");
        label2.setFont(new Font("MV Boli",Font.PLAIN,20));
        label2.setBounds(110, 360, 150, 30);
        label2.setOpaque(true);

        text2.setBounds(280, 360, 180, 30);

        btn.setText("login");
        btn.addActionListener(this);
        btn.setBounds(245, 450, 90, 30);

        msglabel.setBounds(200,500,280,30);

        frame.add(msglabel);

        panel1.add(label3);
        frame.add(label1);
        frame.add(label2);
        frame.add(panel1);
        frame.add(text1);
        frame.add(text2);
        frame.add(btn);



        frame.setVisible(true);



    }


    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btn) {

            String userID = text1.getText();
            String password = String.valueOf(text2.getPassword());
            if(logininfo.containsKey(userID)) {
                if(logininfo.get(userID).equals(password)) {
                    msglabel.setForeground(Color.green);
                    msglabel.setText("Login Success");
                    text1.setText("");
                    text2.setText("");
                    msglabel.setText("");
                    frame.dispose();
                    if (userID.matches("cb\\.en\\.u4cys210\\d{2}")) {
                        stdHomePage homePage =new stdHomePage(userID,frame);
                    }
                    else {
                        facHomePage homePage = new facHomePage(userID,frame);
                    }

                }
                else if (!logininfo.get(userID).equals(password)) {
                    msglabel.setForeground(Color.red);
                    msglabel.setText("Wrong password");
                }

            }
            else if (userID.isEmpty()&& password.isEmpty()) {
                msglabel.setForeground(Color.red);
                msglabel.setText("Please enter your username and password.");
            }
            else if (!logininfo.containsKey(userID)) {
                msglabel.setForeground(Color.red);
                msglabel.setText("User not found !!");
            }

            else {

            }


        }

    }

    public static String getRolluser() {
        return rolluser;
    }



}