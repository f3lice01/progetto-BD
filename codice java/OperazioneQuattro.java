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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OperazioneQuattro extends JFrame implements ActionListener, KeyListener {

	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JPanel pannello = null;
	JLabel marcaLabel = null;
	JTextField marca = null;
	JLabel modelloLabel = null;
	JTextField modello = null;
	JLabel versioneLabel = null;
	JTextField versione = null;
	JButton inserisci = null, indietro = null;
	FinestraPrincipale fp = null;
	
	public OperazioneQuattro() {
		setTitle("Operazione 4");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello = new JPanel(new GridLayout(4,1));
		marcaLabel  = new JLabel("Inserisci la marca: ");
		marca = new JTextField();
		modelloLabel  = new JLabel("Inserisci il modello: ");
		modello = new JTextField();
		versioneLabel  = new JLabel("Inserisci la versione: ");
		versione = new JTextField();
		
		
		pannello.add(marcaLabel);
		pannello.add(marca);
		pannello.add(modelloLabel);
		pannello.add(modello);
		pannello.add(versioneLabel);
		pannello.add(versione);
		
		inserisci = new JButton("Inserisci Auto");
		indietro = new JButton("Indietro");
		pannello.add(inserisci);
		pannello.add(indietro);
		
		add(pannello);
		inserisci.addActionListener(this);
		indietro.addActionListener(this);
		marca.addKeyListener(this);
		modello.addKeyListener(this);
		versione.addKeyListener(this);
		setSize(500,400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	
	public int controllaESettaCodice() throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String queryCount = "SELECT count(id_auto) FROM auto";
		ResultSet rs = st.executeQuery(queryCount);
		int cod = 0;
		while(rs.next()) {
			cod = rs.getInt(1);
		}
		return cod + 1;
		
	}
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == inserisci) {
			if(marca.getText().equals("") || modello.getText().equals("") || versione.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Alcuni campi risultano vuoti, riprova");
			}
			else {
				Connection con;
				Statement st;
				ResultSet rs;
				try {
					con = getConnection();
					st = con.createStatement();
					st.execute("USE autoricambi");
					String query = "SELECT id_auto FROM auto WHERE marca='"+marca.getText()+"' AND modello='"+modello.getText()+"' AND versione='"+versione.getText()+"'";
					rs = st.executeQuery(query);
					if (rs.next() == true) {
						JOptionPane.showMessageDialog(null, "Auto già presente in database.");
					} else {
						int codice = controllaESettaCodice();
						con = getConnection();
						st = con.createStatement();
						st.execute("USE autoricambi");
						String queryOp4 = "INSERT INTO auto(id_auto,marca,modello,versione) VALUES('"+codice+"','"+marca.getText()+"','"+modello.getText()+"','"+versione.getText()+"')";
						int op = st.executeUpdate(queryOp4);
						
						if(op!=0) {
							JOptionPane.showMessageDialog(null, "Auto inserita.");
							FinestraPrincipale fp = new FinestraPrincipale();
							fp.stampaInTextArea("Query eseguita: ");
							fp.stampaInTextArea(queryOp4);
							fp.stampaInTextArea("Hai inserito l'auto: ["+marca.getText()+","+modello.getText()+","+versione.getText()+"] con codice: ["+codice+"]");
							fp.setVisible(true);
							setVisible(false);
						}
						else 
							JOptionPane.showMessageDialog(null, "Errore di inserimento.");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		if(e.getSource() == indietro) {
			fp = new FinestraPrincipale();
			fp.setVisible(true);
			setVisible(false);
		}
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(marca.getText().equals("") || modello.getText().equals("") || versione.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Alcuni campi risultano vuoti, riprova");
			}
			else {
				Connection con;
				Statement st;
				ResultSet rs;
				try {
					con = getConnection();
					st = con.createStatement();
					st.execute("USE autoricambi");
					String query = "SELECT id_auto FROM auto WHERE marca='"+marca.getText()+"' AND modello='"+modello.getText()+"' AND versione='"+versione.getText()+"'";
					rs = st.executeQuery(query);
					if (rs.next() == true) {
						JOptionPane.showMessageDialog(null, "Auto già presente in database.");
					} else {
						int codice = controllaESettaCodice();
						con = getConnection();
						st = con.createStatement();
						st.execute("USE autoricambi");
						String queryOp4 = "INSERT INTO auto(id_auto,marca,modello,versione) VALUES('"+codice+"','"+marca.getText()+"','"+modello.getText()+"','"+versione.getText()+"')";
						int op = st.executeUpdate(queryOp4);
						
						if(op!=0) {
							JOptionPane.showMessageDialog(null, "Auto inserita.");
							FinestraPrincipale fp = new FinestraPrincipale();
							fp.stampaInTextArea("Query eseguita: ");
							fp.stampaInTextArea(queryOp4);
							fp.stampaInTextArea("Hai inserito l'auto: ["+marca.getText()+","+modello.getText()+","+versione.getText()+"] con codice: ["+codice+"]");
							fp.setVisible(true);
							setVisible(false);
						}
						else 
							JOptionPane.showMessageDialog(null, "Errore di inserimento.");
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}}
