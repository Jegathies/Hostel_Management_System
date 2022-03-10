
package hostelmanagementsystem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class ViewRoom extends JFrame{
    
    String[] columnNames = {"Room Number","Active Status","Room Status"};
    JButton close;
    public ViewRoom(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
     
     //Table
        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(columnNames);
        JTable t = new JTable();
        t.setModel(model);
        JScrollPane scroll = new JScrollPane(t);
        
        
        close = new JButton("X");
        close.setFont(new Font("Arial", Font.PLAIN, 20));
        close.setBounds(768,0,50,20);
        add(close);
       close.addActionListener(new ActionListener(){  
    public void actionPerformed(ActionEvent e){  
           setVisible(false);  
    }});

    //Getting Details
     String roomNo;
     String activeStatus;
     String roomStatus;
     
     
     
     try {
    	 	Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","rajam","jega");
    	 	Statement st= connection.createStatement();
    	 	ResultSet rs = st.executeQuery("select * from room");
    	 	while(rs.next()) 
    	 	{
    	 	 roomNo = rs.getString("room_no");
                 activeStatus = rs.getString("active_status");
                 roomStatus = rs.getString("room_status");
        	 
        	 

             model.addRow(new Object[]{roomNo,activeStatus,roomStatus});
        }

     } catch (Exception ex) {

         JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

     }
     add(scroll);
     setUndecorated(true);
     setBackground(Color.white);
     setVisible(true);
    
     setBounds(400, 120, 820, 550);
     
     
	}
	public static void main(String args[])
	{
		new ViewRoom();
	}
	
}