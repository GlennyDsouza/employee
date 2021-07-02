import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
public class Employee {

	private JFrame frame;
	private JTextField jtxtEmpID;
	private JTable table;
	private JTextField jtxtID;
	private JTextField jtxtlastName;
	private JTextField jtxtFirstName;
	private JTextField jtxtStartDate;
	private JTextField jtxtAddress;
	private JTextField jtxtDepartment;
	private JTextField jtxtDesignation;
	private JTextField jtxtDOB;
	private JTextField jtxtGender;

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	DefaultTableModel model = new DefaultTableModel();
	
	/**
	 * Launch the application.
	 */
	
	public void updateTable()
	{
		conn = EmployeeData.ConnectDB();
		
		if (conn !=null)
		{
			String sql = "Select EmpID, ID, lastname, Firstname, StartDate, Address, Department, Designation, DOB, Gender";
		
			
		try
		{
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			Object[] columnData = new Object [10];
			
			while(rs.next())  {
				columnData [0] = rs.getString("EmpID");
				columnData [0] = rs.getString("ID");
				columnData [0] = rs.getString("lastname");
				columnData [0] = rs.getString("Firstname");
				columnData [0] = rs.getString("StartDate");
				columnData [0] = rs.getString("Address");
				columnData [0] = rs.getString("Department");
				columnData [0] = rs.getString("Designation");
				columnData [0] = rs.getString("DOB");
				columnData [0] = rs.getString("Gender");
				
				model.addRow(columnData);
				
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			
		}
		}
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee window = new Employee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Employee() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col[] = {"EmpID","ID","lastname","Firstname","StartDate","Address","Department","Designation","DOB","Gender"};
		model.setColumnIdentifiers(col);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1450, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Emp ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(54, 167, 129, 33);
		frame.getContentPane().add(lblNewLabel);
		
		jtxtEmpID = new JTextField();
		jtxtEmpID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtEmpID.setBounds(193, 166, 246, 35);
		frame.getContentPane().add(jtxtEmpID);
		jtxtEmpID.setColumns(10);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO employee(EmpID, ID, lastname, Firstname, StartDate, Address, Department, Designation, DOB ,Gender)VALUES(?,?,?,?,?,?,?,?,?,?)";
				
				try
				{
				pst = conn.prepareStatement(sql);
				pst.setString(1, jtxtEmpID.getText());
				pst.setString(2, jtxtID.getText());
				pst.setString(3, jtxtlastName.getText());
				pst.setString(4, jtxtFirstName.getText());
				pst.setString(5, jtxtStartDate.getText());
				pst.setString(6, jtxtAddress.getText());
				pst.setString(7, jtxtDepartment.getText());
				pst.setString(8, jtxtDesignation.getText());
				pst.setString(9, jtxtDOB.getText());
				pst.setString(10, jtxtGender.getText());
				
				pst.execute();
				
				rs.close();
				pst.close();
				
				}
				catch (Exception ev)
				{
					JOptionPane.showMessageDialog(null,"System Update Completed");
					
				}
				
				DefaultTableModel model = (DefaultTableModel) table.getModel ();
				model .addRow(new Object[] {
						 
						jtxtEmpID.getText(),
					    jtxtID.getText(),
						jtxtlastName.getText(),
						jtxtFirstName.getText(),
						jtxtStartDate.getText(),
						jtxtAddress.getText(),
						jtxtDepartment.getText(),
						jtxtDesignation.getText(),
						jtxtDOB.getText(),
					    jtxtGender.getText(),
						
				});
				if (table.getSelectedRow()  == -1){
					if (table.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Membership Update confirmed","Employee Database System",
							JOptionPane.OK_OPTION);
					}
				}
				
			}
		});
		btnAddNew.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddNew.setBounds(53, 660, 220, 56);
		frame.getContentPane().add(btnAddNew);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(489, 132, 899, 469);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"EmplID", "ID", "First Name", "Last Name", "Start date", "Designation", "Department", "DOB", "Gender", "Address"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		jtxtID = new JTextField();
		jtxtID.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtID.setColumns(10);
		jtxtID.setBounds(193, 210, 246, 35);
		frame.getContentPane().add(jtxtID);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setBounds(54, 211, 129, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		jtxtlastName = new JTextField();
		jtxtlastName.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtlastName.setColumns(10);
		jtxtlastName.setBounds(193, 299, 246, 35);
		frame.getContentPane().add(jtxtlastName);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(54, 300, 129, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_2.setBounds(54, 256, 129, 33);
		frame.getContentPane().add(lblNewLabel_2);
		
		jtxtFirstName = new JTextField();
		jtxtFirstName.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtFirstName.setColumns(10);
		jtxtFirstName.setBounds(193, 255, 246, 35);
		frame.getContentPane().add(jtxtFirstName);
		
		jtxtStartDate = new JTextField();
		jtxtStartDate.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtStartDate.setColumns(10);
		jtxtStartDate.setBounds(193, 387, 246, 35);
		frame.getContentPane().add(jtxtStartDate);
		
		JLabel lblNewLabel_1_2 = new JLabel("Start date");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(54, 389, 129, 33);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_3 = new JLabel("Address ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_3.setBounds(54, 345, 129, 33);
		frame.getContentPane().add(lblNewLabel_3);
		
		jtxtAddress = new JTextField();
		jtxtAddress.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtAddress.setColumns(10);
		jtxtAddress.setBounds(193, 344, 246, 35);
		frame.getContentPane().add(jtxtAddress);
		
		jtxtDepartment = new JTextField();
		jtxtDepartment.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDepartment.setColumns(10);
		jtxtDepartment.setBounds(193, 476, 246, 35);
		frame.getContentPane().add(jtxtDepartment);
		
		JLabel lblNewLabel_1_3 = new JLabel("Department");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(54, 477, 129, 33);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_4 = new JLabel("Designation");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setBounds(54, 433, 129, 33);
		frame.getContentPane().add(lblNewLabel_4);
		
		jtxtDesignation = new JTextField();
		jtxtDesignation.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDesignation.setColumns(10);
		jtxtDesignation.setBounds(193, 432, 246, 35);
		frame.getContentPane().add(jtxtDesignation);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page {0, number, integer}");
				
				try
				{
					table.print();
					
				}
				catch(java.awt.print.PrinterException ev)  {
					System.err.format("No Printer Found", ev.getMessage());
				}
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(314, 660, 220, 56);
		frame.getContentPane().add(btnPrint);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame =new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Employee Database System",
						JOptionPane.YES_NO_OPTION)== JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
								
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1_1.setBounds(848, 660, 220, 56);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jtxtEmpID.setText(null);
				jtxtID.setText(null);
				jtxtlastName.setText(null);
				jtxtFirstName.setText(null);
				jtxtStartDate.setText(null);
				jtxtAddress.setText(null);
				jtxtDepartment.setText(null);
				jtxtDesignation.setText(null);
				jtxtDOB.setText(null);
				jtxtGender.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(587, 660, 220, 56);
		frame.getContentPane().add(btnReset);
		
		jtxtDOB = new JTextField();
		jtxtDOB.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDOB.setColumns(10);
		jtxtDOB.setBounds(193, 521, 246, 35);
		frame.getContentPane().add(jtxtDOB);
		
		JLabel lblNewLabel_4_1 = new JLabel("DOB");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4_1.setBounds(54, 522, 129, 33);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Gender");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1_3_1.setBounds(54, 567, 129, 33);
		frame.getContentPane().add(lblNewLabel_1_3_1);
		
		jtxtGender = new JTextField();
		jtxtGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtGender.setColumns(10);
		jtxtGender.setBounds(193, 566, 246, 35);
		frame.getContentPane().add(jtxtGender);
		
		JLabel lblNewLabel_5 = new JLabel("Employee Database Management ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_5.setBounds(227, 21, 653, 82);
		frame.getContentPane().add(lblNewLabel_5);
	}
}
