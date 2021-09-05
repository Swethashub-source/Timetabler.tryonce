package timetabler;

import java.sql.SQLException;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Insert_Timetable extends JFrame{
	
		private static final long serialVersionUID = 1L;
		JPanel jp1,jp2,jp3;
		Connection con;
		int i;
		java.sql.Statement stmt;
		JLabel day,clss,section,starttime,endtime,worktobedone,workleft; 
		JTextField bday,bclass,bsection,bstarttime,bendtime,bworktobedone,bworkleft;
		TextArea ta;
		JButton in;
		
		public   Insert_Timetable()
		{
			try 
			{
				
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","swetha","manager");
				stmt=con.createStatement();
			
			} 
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			day=new JLabel("day");
			bday=new JTextField(12);
			clss=new JLabel("Class");
			bclass=new JTextField(20);
			section=new JLabel("Section");
			bsection=new JTextField(3);
			starttime=new JLabel("Start Time");
			bstarttime=new JTextField(5);
			endtime=new JLabel("End Time");
			bendtime=new JTextField(5);
			worktobedone=new JLabel("Work To Be Done");
			bworktobedone=new JTextField(100);
			workleft=new JLabel("Work left To Be Done");
			bworkleft=new JTextField(100);
		
			ta=new TextArea(10,40);
			in=new JButton("submit");
			getContentPane().setBackground(Color.blue);
			jp1=new JPanel(new GridLayout(4,2));
			jp2=new JPanel(new FlowLayout());
			jp3=new JPanel(new FlowLayout());
			jp1.add(day);
			jp1.add(bday);
			jp1.add(clss);
			jp1.add(bclass);
			jp1.add(section);
			jp1.add(bsection);
			jp1.add(starttime);
			jp1.add(bstarttime);
			jp1.add(endtime);
			jp1.add(bendtime);
			jp1.add(worktobedone);
			jp1.add(bworktobedone);
			jp1.add(workleft);
			jp1.add(bworkleft);
			jp2.add(in);
			jp3.add(ta);
			add(jp1);
			add(jp2);
			add(jp3);
			setVisible(true);
			setSize(500,600);
			setTitle("Enter following details:");
			setLayout(new GridLayout(3,2));
			pack();
			
			in.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
				 	// TODO Auto-generated method stub
					try {
						
						i=stmt.executeUpdate("insert into timetable values('"+bday.getText()+"','"+bclass.getText()+"','"+bsection.getText()+"','"+bstarttime.getText()+"','"+bendtime.getText()+"','"+bworktobedone.getText()+"','"+bworkleft.getText()+"')");
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					ta.append("\n Inserted "+i+" rows successfully");
					i=0;
				}
			});

		}
}
