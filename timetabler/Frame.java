package timetabler;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Frame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	JMenuBar menubar;
	JMenu menu1;
	JMenuItem item1;
	JMenuItem up1,dl1;
	
	public  void firstframe() {
		
		menubar=new JMenuBar();
		menu1=new JMenu("Timetable");
		
		item1=new JMenuItem("Insert");
		up1=new JMenuItem("Update");
		dl1=new JMenuItem("Delete");
		
		getContentPane().setBackground(Color.blue);
		setVisible(true);
		setSize(500,600);
		setTitle("Set your time table");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setJMenuBar(menubar);
		menubar.add(menu1);
		
		menu1.add(item1);
		menu1.add(up1);
		menu1.add(dl1);
		
		item1.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Insert_Timetable();
			
			}
		});
		

		up1.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
				new Update_Timetable();
			
			}
		});
	
		dl1.addActionListener(new ActionListener() {
		
		@Override
			public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
				new Delete_Timetable();
			
			}
		});
	}
	
	public static void main(String[] args) {
		
		Frame f = new Frame();
		f.firstframe();

	}

}
