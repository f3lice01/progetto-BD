
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import java.awt.Insets;
import java.awt.Toolkit;


class Login extends JFrame implements ActionListener, KeyListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		System.out.println("Connessione Ok");
		con.setAutoCommit(true);
		return con;
	}
	
	
	JButton b1, reg, esci;
	JPanel pannelloCentrale,exitPanel;
	JLabel userLabel, passwordLabel;
	JTextField text1, text2;
	private static String username;
	Icon icon = new ImageIcon("C:\\Users\\certo\\eclipse-workspace\\ProgettoAutoricambi\\src\\shutdown_105168.png");
	
	
	public static String getUsername() {
		return username;
	}
	
	public Login(){
		setTitle("Certo/Chirico");
		setIconImage(new ImageIcon("C:\\Users\\certo\\eclipse-workspace\\ProgettoAutoricambi\\src\\icon.png").getImage());
		userLabel = new JLabel();
		userLabel.setText("Username");
		
		text1 = new JTextField();
		
		passwordLabel = new JLabel();
		passwordLabel.setText("Password");
		
		text2 = new JPasswordField(15);
		
		b1 = new JButton("Login");
		b1.setToolTipText("Effettua il login");
		reg = new JButton("Registrati");
		reg.setToolTipText("OPERAZIONE 1: inserire un nuovo account");
		
		exitPanel = new JPanel();
		pannelloCentrale = new JPanel(new GridLayout(3,2));
		pannelloCentrale.add(userLabel);
		pannelloCentrale.add(text1);
		pannelloCentrale.add(passwordLabel);
		pannelloCentrale.add(text2);
		pannelloCentrale.add(b1);
		pannelloCentrale.add(reg);
		
		getContentPane().add(pannelloCentrale,BorderLayout.CENTER);
		esci = new JButton(icon);
		esci.setBackground(Color.red);
		esci.setToolTipText("Chiudi l'applicazione");
		exitPanel.add(esci);
		
		getContentPane().add(exitPanel,BorderLayout.SOUTH);
		b1.addActionListener(this);
		reg.addActionListener(this);
		text2.addKeyListener(this);
		esci.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setSize(300,200);
		setLocationRelativeTo(null);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b1) {
			Connection con = null;
			Statement st = null;
			ResultSet rs;
			if(text1.getText().equals("") || text2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Il campo Username o Password è vuoto, riprova.");
			}
			else {
				try {
					con = getConnection();
					st = con.createStatement();
					String user = text1.getText();
					String password = text2.getText();
					username = user;
					String query = "SELECT username, password FROM account WHERE username='"+user+"' AND password='"+password+"'";
					st.execute("USE autoricambi");
					rs = st.executeQuery(query);
					if(rs.next()) {
								FinestraPrincipale fp = new FinestraPrincipale();
								fp.setVisible(true);
								setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username o Password errate.");
					}
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == reg) {
			RegistrationFrame rf = new RegistrationFrame();
			rf.setVisible(true);
			setVisible(false);
		}
		if(e.getSource() == esci) {
			System.exit(0);
		}

}



	public static void main(String arg[]) {
		try {
			Login l = new Login();
			l.setVisible(true);
			l.setLocationRelativeTo(null);
			
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			Connection con = null;
			Statement st = null;
			ResultSet rs;
			if(text1.getText().equals("") || text2.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Il campo Username o Password è vuoto, riprova.");
			}
			else {
				try {
					con = getConnection();
					st = con.createStatement();
					String user = text1.getText();
					String password = text2.getText();
					username = user;
					String query = "SELECT username, password FROM account WHERE username='"+user+"' AND password='"+password+"'";
					st.execute("USE autoricambi");
					rs = st.executeQuery(query);
					if(rs.next()) {
								FinestraPrincipale fp = new FinestraPrincipale();
								fp.setVisible(true);
								setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username o Password errate.");
					}
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}