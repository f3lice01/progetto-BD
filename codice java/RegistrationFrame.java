

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.protocol.ResultStreamer;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.GridBagConstraints;

public class RegistrationFrame extends JFrame implements ActionListener{

	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
	
		con.setAutoCommit(true);
		return con;
	}
	


	/**
	 * Create the frame.
	 */
	
	JButton registrami,esci;
	JTextField nome, cognome,  username, indirizzo, password,telefono;
	JLabel telefonoLabel, nomeLabel, cognomeLabel,  usernameLabel, indirizzoLabel, passwordLabel,registrazioneAccount,labelVuoto;
	JPanel pannelloCentrale,pannelloReg;
	String tel = "";
	double costoSpedizione = 15.0;
	private static int codCarrello;
	
	public RegistrationFrame() {
		setTitle("Pannello Registrazione Account");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		registrami = new JButton("Registrami");
		esci = new JButton("Esci");
		
		registrazioneAccount = new JLabel();
		registrazioneAccount.setText("Registrazione Account");
		
		nomeLabel = new JLabel();
		cognomeLabel = new JLabel();
		usernameLabel = new JLabel();
		indirizzoLabel = new JLabel();
		passwordLabel = new JLabel();
		labelVuoto = new JLabel();
		telefonoLabel = new JLabel("Telefono");
		
		nomeLabel.setText("Nome");
		cognomeLabel.setText("Cognome");
		usernameLabel.setText("Username");
		indirizzoLabel.setText("Indirizzo");
		passwordLabel.setText("Password");
		
		nome = new JTextField();
		cognome = new JTextField();
		username = new JTextField(16);
		indirizzo = new JTextField();
		password = new JPasswordField(16);
		telefono = new JTextField();
		
		pannelloReg = new JPanel();
		pannelloReg.add(registrazioneAccount);
		pannelloCentrale = new JPanel(new GridLayout(8,1));
		pannelloCentrale.add(pannelloReg);
		pannelloCentrale.add(labelVuoto);
		pannelloCentrale.add(nomeLabel);
		pannelloCentrale.add(nome);
		pannelloCentrale.add(cognomeLabel);
		pannelloCentrale.add(cognome);
		pannelloCentrale.add(usernameLabel);
		pannelloCentrale.add(username);
		pannelloCentrale.add(indirizzoLabel);
		pannelloCentrale.add(indirizzo);
		pannelloCentrale.add(telefonoLabel);
		pannelloCentrale.add(telefono);
		pannelloCentrale.add(passwordLabel);
		pannelloCentrale.add(password);
		pannelloCentrale.add(registrami);
		pannelloCentrale.add(esci);
		
		
		add(pannelloCentrale);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 644, 418);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		registrami.addActionListener(this);
		esci.addActionListener(this);
		registrami.setToolTipText("Operazione 1: Creare un nuovo Account");
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==registrami) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		tel = telefono.getText();
		try {
			con = getConnection();
			st = con.createStatement();
			
			String nomeText = nome.getText();
			String cognomeText = cognome.getText();
			String usernameText = username.getText();
			String indirizzoText = indirizzo.getText();
			String passwordText = password.getText();
			
			String queryUsername = "SELECT username FROM account WHERE username ='"+usernameText+"'";
			st.execute("USE autoricambi");
			rs = st.executeQuery(queryUsername);
			while(rs.next()) {
				
				if(usernameText.equals(rs.getString(1))) 
					JOptionPane.showMessageDialog(null, "Username già in uso, sei pregato di cambiarlo");
			}
			if(isLong(usernameText)) {
				if(isLong(passwordText)) {
					if(isNumero(passwordText) && isSpecialChar(passwordText) && isMaiuscolo(passwordText)) {
						
						String carrello = "SELECT MAX(id_carrello) FROM carrello";
						String use = "USE autoricambi";
						codCarrello = controllaESettaCodice(carrello);
						String sql = "INSERT INTO account(username,nome,cognome,indirizzo,password,num_acquisti,id_carrello) values('" + usernameText + "', '" + nomeText + "', '" + cognomeText + "', '" + indirizzoText + "','" + passwordText + "',0,"+codCarrello+")";
						st.execute(use);
						st.executeUpdate(sql);
						
						String sqlTel = "INSERT INTO telefono(numero,username) values('"+tel+"','"+usernameText+"')";
						String queryCarrello = "INSERT INTO carrello(id_carrello,id_cliente,importo_parziale,costo_spedizione) values ("+codCarrello+",'"+usernameText+"',0,"+costoSpedizione+")";
						st.executeUpdate(sqlTel);
						JOptionPane.showMessageDialog(null, "Registrazione andata a buon fine!");
						st.executeUpdate(queryCarrello);
						
						Login l = new Login();
						l.setVisible(true);
						l.setLocationRelativeTo(null);
						setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "La password deve contenere almeno una maiuscola, un numero e un carattere speciale");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "La password è più lunga di 16 caratteri.");
				}	
			}else {
				JOptionPane.showMessageDialog(null, "L'username è più lungo di 16 caratteri.");
			}
		
		} catch (SQLException e2) {
			// TODO: handle exception
			e2.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		if(e.getSource() == esci) {
			Login l = new Login();
			l.setVisible(true);
			l.setLocationRelativeTo(null);
			setVisible(false);
		}
	}
		
		

		

	public boolean isLong(String stringa) {
		boolean lunga = false;
		if(stringa.length()<=16) {
			lunga=true;
		}
	    return lunga;
	}
	
	//funzione che controlla se almeno un carattere è un numero 
		public boolean isNumero(String stringa) {
			boolean contieneNumeri = false;
	        for (int i = 0; i < stringa.length(); i++) {
	            if (Character.isDigit(stringa.charAt(i))) {
	                contieneNumeri = true;
	                break;
	            }
	        }
	        return contieneNumeri;
	    }
		
		//funzione che controlla se almeno un carattere è un carattere speciale
		public boolean isSpecialChar(String stringa) {
			boolean isSpecial=false;
			for (int i = 0; i < stringa.length(); i++) {
	            char carattere = stringa.charAt(i);
	            if (!Character.isLetterOrDigit(carattere) && !Character.isWhitespace(carattere)) {
	            	isSpecial = true;
	                break;
	            }
	        }
	        return isSpecial;
		}
		
		//funzione che controlla se almeno un carattere è maiuscolo
		public boolean isMaiuscolo(String stringa) {
		boolean contieneMaiuscole = false;
	    
	    for (int i = 0; i < stringa.length(); i++) {
	        char carattere = stringa.charAt(i);
	        if (Character.isUpperCase(carattere)) {
	            contieneMaiuscole = true;
	            break;
	        }
	    }
	    return contieneMaiuscole;
	}
		
		public int controllaESettaCodice(String query) throws ClassNotFoundException, SQLException {
			
			Connection con = getConnection();
			Statement st = con.createStatement();
			st.execute("USE autoricambi");
			
			ResultSet rs = st.executeQuery(query);
			int cod = 0;
			while(rs.next()) {
				cod = rs.getInt(1);
			}
			return cod + 1;	
		}
		
		public int getCarrello() {
			return codCarrello;
			
		}
}
