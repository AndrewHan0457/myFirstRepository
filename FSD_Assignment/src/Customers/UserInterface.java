package Customers;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserInterface extends JFrame {
	
	private JTextField accField;
	private JTextField pwdField;
	private JTextField tabField;
	private JTextField sleField;
	private JTextField delField;
	private JTextField colField;
	private JTextField valField;
	private JTextField colFilterField;
	private JTextField valFilterField;

	
	private static final long serialVersionUID = 1L;
	private Customer customer;
	private Connection connection;
	
	public static final String URL = "jdbc:mysql://localhost:3306/Customer?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "password";
    
    public JButton btnOK;
    public JButton btnCreate;
    public JButton btnSelect;
    public JButton btnDelete;
    public JButton btnUpd;
    public JButton btnClose;
    
	public Statement statement;
	public ResultSet results;

	
	public UserInterface(Customer customer) {
		//set the frame
		setResizable(true);
		setTitle("Manage Customers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(1000, 50, 600, 1000);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setForeground(Color.ORANGE);
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		//insert
		JLabel lblAcc = new JLabel("Account");
		contentPane.add(lblAcc);
		lblAcc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAcc.setBounds(32, 58, 53, 22);
		
		accField = new JTextField();
		contentPane.add(accField);
		accField.setBounds(119, 64, 240, 22);
		accField.setColumns(10);
			
		JLabel lblPwd = new JLabel("Password");
		contentPane.add(lblPwd);
		lblPwd.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPwd.setBounds(29, 103, 90, 16);
		
		pwdField = new JTextField();
		contentPane.add(pwdField);
		pwdField.setBounds(119, 103, 240, 22);
		pwdField.setColumns(10);
		
		btnOK = new JButton("INSERT");
		contentPane.add(btnOK);
		btnOK.setBounds(169, 133, 97, 25);
		//create
		JLabel lblTable = new JLabel("Table");
		contentPane.add(lblTable);
		lblTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTable.setBounds(32, 178, 53, 22);
		
		tabField = new JTextField();
		contentPane.add(tabField);
		tabField.setBounds(119, 178, 240, 22);
		tabField.setColumns(10);
		
		btnCreate = new JButton("CREATE");
		contentPane.add(btnCreate);
		btnCreate.setBounds(169, 208, 97, 25);
		
		//select
		JLabel lblSelect = new JLabel("Selected Table");
		contentPane.add(lblSelect);
		lblSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelect.setBounds(20, 263, 100, 22);
		
		sleField = new JTextField();
		contentPane.add(sleField);
		sleField.setBounds(119, 263, 240, 22);
		sleField.setColumns(10);
		
		btnSelect = new JButton("SELECT");
		contentPane.add(btnSelect);
		btnSelect.setBounds(169, 293, 97, 25);
		
		//delete
		JLabel lblDelete = new JLabel("Delete");
		contentPane.add(lblDelete);
		lblDelete.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDelete.setBounds(32, 358, 53, 22);
		
		delField = new JTextField();
		contentPane.add(delField);
		delField.setBounds(119, 358, 240, 22);
		delField.setColumns(10);
		
		btnDelete = new JButton("DELETE");
		contentPane.add(btnDelete);
		btnDelete.setBounds(169, 388, 97, 25);
		
		//update
		JLabel lblCol = new JLabel("Colunm");
		contentPane.add(lblCol);
		lblCol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCol.setBounds(32, 443, 53, 22);
		
		colField = new JTextField();
		contentPane.add(colField);
		colField.setBounds(119, 450, 240, 22);
		colField.setColumns(10);
			
		JLabel lblval = new JLabel("Value");
		contentPane.add(lblval);
		lblval.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblval.setBounds(29, 488, 90, 16);
		
		valField = new JTextField();
		contentPane.add(valField);
		valField.setBounds(119, 488, 240, 22);
		valField.setColumns(10);
		
		JLabel lblColFil = new JLabel("ColunmFilter");
		contentPane.add(lblColFil);
		lblColFil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblColFil.setBounds(20, 518,100, 22);
		
		colFilterField = new JTextField();
		contentPane.add(colFilterField);
		colFilterField.setBounds(119, 518, 240, 22);
		colFilterField.setColumns(10);
			
		JLabel lblvalFil = new JLabel("ValueFilter");
		contentPane.add(lblvalFil);
		lblvalFil.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblvalFil.setBounds(29, 548, 90, 16);
		
		valFilterField = new JTextField();
		contentPane.add(valFilterField);
		valFilterField.setBounds(119, 548, 240, 22);
		valFilterField.setColumns(10);
		
		btnUpd = new JButton("UPDATE");
		contentPane.add(btnUpd);
		btnUpd.setBounds(169, 600, 97, 25);
		
		//exit
		btnClose = new JButton("CLOSE");
		contentPane.add(btnClose);
		btnClose.setBounds(450, 500, 97, 25);

	
		this.customer = customer;
		//Connect to Database

		
	}

	public void manageCustomer() {
		try {
			//insert account and password of advertisers into database.
			connection = DriverManager.getConnection(URL,USER,PASSWORD );
			statement = this.getConnection().createStatement();
	    	System.out.println("Connected!");
	    	btnOK.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent arg0) {
	    			try {
	    				statement.execute("INSERT INTO login VALUES ('"+accField.getText()+"','"+pwdField.getText()+"')");
	    				
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			System.out.println("Customer with Account "+ accField.getText()+" Password "+ pwdField.getText()+" is created");
	    		}
	    	});
	    	
	    	btnCreate.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent arg0) {
	    			try {
	    				statement.execute("CREATE TABLE IF NOT EXISTS "+tabField.getText()+" (Id int)");
	    				
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			System.out.println("Table:"+tabField.getText()+" is created");
	    		}
	    	});
	    
	    	btnSelect.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent arg0) {
	    			try {
	    				results = statement.executeQuery("SELECT * FROM "+ sleField.getText());
						String account = "";
						String pwd = "";
						System.out.println("Account\tPassword");
				    	while(results.next())
				    	{
				    		account = results.getString("Account");
				    		pwd = results.getString("Password");

				    		System.out.println(account+"\t"+pwd);
				       	}
	    			} catch (SQLException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			System.out.println("Table:"+sleField.getText()+" is showing");
	    		}
	    	});
	    
		} catch (Exception e) {
			// TODO: handle exception
	    	e.printStackTrace();
		}
		
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					results.close();
					statement.close();
					connection.close();
					System.exit(0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Closed!");
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
    				statement.execute("DROP TABLE "+delField.getText());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Table: "+delField.getText()+" is deleted!");
			}
		});
		
		btnUpd.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent arg0) {
    			try {
    				statement.execute("UPDATE login SET "+colField.getText()+" =  '"+valField.getText()+"' WHERE "+colFilterField.getText()+" = '"+valFilterField.getText()+"'");
    				
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			System.out.println("Table: login is updated");
    		}
    	});
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public static void main(String[] args) {
		UserInterface ui = new UserInterface(new Customer("Real Tom"));
		ui.setVisible(true);
//		ui.getCustomer().setAccount("Tom");
//		ui.getCustomer().setPwd("123465");
		ui.manageCustomer();
	}
}
