package timetabler;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  Update_Timetable extends JFrame{
	private static final long serialVersionUID = 1L;
	JPanel jp1,jp2,jp3;
	Connection con;
	int i;
	ResultSet rs;
	java.sql.Statement stmt;
	JLabel day,clss,section,starttime,endtime,worktobedone,workleft; 
	JTextField bday,bclass,bsection,bstarttime,bendtime,bworktobedone,bworkleft;
	TextArea ta;
	JButton wrkleft,wrktobedone;
	public  Update_Timetable() {
		try {
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
		
		ta=new TextArea(10,50);
		wrkleft=new JButton("Upadte Work Left");
		wrktobedone=new JButton("Update work to be done");
		
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
		jp2.add(wrkleft);
		jp2.add(wrktobedone);
		
		jp3.add(ta);
		add(jp1);
		add(jp2);
		add(jp3);
		setVisible(true);
		setSize(500,600);
		setTitle("Update your Timetable:");
		setLayout(new GridLayout(3,2));
		pack();
			
		DefaultTableModel model=new DefaultTableModel();
		String colHeads[]= {"day","class","section","starttime","endtime","worktobedone","workleft"};
		model.setColumnIdentifiers(colHeads);
		JTable table=new JTable();
		table.setModel(model);
		JScrollPane scroll=new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(
			JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(
			JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		setVisible(true);
		setSize(500,600);
		String paneday="",paneclass="",panesection="", panestarttime="";
		String paneendtime="",paneworktobedone="",paneworkleft="";
		try {
			rs=stmt.executeQuery("select day,class,section,starttime,endtime,worktobedone,workleft from timetable");
			while(rs.next()) {
				paneday=rs.getString("day");
				paneclass=rs.getString("class");
				panesection=rs.getString("section");
				panestarttime=rs.getString("starttime");
				paneendtime=rs.getString("endtime");
				paneworktobedone=rs.getString("worktobedone");
				paneworkleft=rs.getString("workleft");
				model.addRow(new Object[]{paneday,paneclass,panesection,panestarttime,paneendtime,paneworktobedone,paneworkleft});
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int selectedRow=table.getSelectedRow();
				bday.setText(model.getValueAt(selectedRow,0).toString());
				bclass.setText(model.getValueAt(selectedRow,1).toString());
				bsection.setText(model.getValueAt(selectedRow,2).toString());
				bstarttime.setText(model.getValueAt(selectedRow,3).toString());
				bendtime.setText(model.getValueAt(selectedRow,4).toString());
				bworktobedone.setText(model.getValueAt(selectedRow,5).toString());
				bworkleft.setText(model.getValueAt(selectedRow,6).toString());
			}
		});
		
		wrkleft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String sql="UPDATE timetable SET workleft=? where day=? and class=? and section=? and starttime=? and endtime=?";
					PreparedStatement statement=con.prepareStatement(sql);
					statement.setString(1,bworkleft.getText());
					statement.setString(2,bday.getText());
					statement.setString(3,bclass.getText());
					statement.setString(4,bsection.getText());
					statement.setString(5,bstarttime.getText());
					statement.setString(6,bendtime.getText());
					i=statement.executeUpdate(); 
				} 
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i==1) {
					ta.append("\n updated your given workleft successfully");
				}
				else {
					ta.append("\n Cannot update your query ");
				}
				bday.setText("");
				bclass.setText("");
				bsection.setText("");
				bstarttime.setText("");
				bendtime.setText("");
				bworktobedone.setText("");
				bworkleft.setText("");
			}
		});

		wrktobedone.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					String sql="UPDATE timetable SET worktobedone=? where day=? and class=? and section=? and starttime=? and endtime=?";
					PreparedStatement statement=con.prepareStatement(sql);
					statement.setString(2,bday.getText());
					statement.setString(1,bworktobedone.getText());
					statement.setString(3,bclass.getText());
					statement.setString(4,bsection.getText());
					statement.setString(5,bstarttime.getText());
					statement.setString(6,bendtime.getText());
					i=statement.executeUpdate(); 
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(i==1) {
					ta.append("\n updated given query successfully");
				}
				else {
					ta.append("\n Cannot update your query ");
				}
				bday.setText("");
				bclass.setText("");
				bsection.setText("");
				bworktobedone.setText("");
				bworkleft.setText("");
			}
		});
	}
}