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

public class OperazioneTre extends JFrame implements ActionListener,KeyListener {

	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JPanel pannello = null , pannelloBottoni = null;
	JLabel tipoLabel = null;
	JTextField tipo = null;
	JButton inserisci = null, indietro = null;
	
	public OperazioneTre() {
		setTitle("Operazione 3");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello = new JPanel(new GridLayout(4,1));
		pannelloBottoni = new JPanel();
		tipoLabel  = new JLabel("Inserisci il tipo: ");
		tipo = new JTextField();
		pannello.add(tipoLabel);
		pannello.add(tipo);
		
		inserisci = new JButton("Inserisci Categoria");
		indietro = new JButton("Indietro");
		pannelloBottoni.add(inserisci);
		pannelloBottoni.add(indietro);
		pannello.add(pannelloBottoni);
		
		add(pannello);
		
		inserisci.addActionListener(this);
		indietro.addActionListener(this);
		tipo.addKeyListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(300,200);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	public int controllaESettaCodice() throws ClassNotFoundException, SQLException {
	
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String queryCount = "SELECT max(codice) FROM categoria";
		ResultSet rs = st.executeQuery(queryCount);
		int cod = 0;
		while(rs.next()) {
			cod = rs.getInt(1);
		}
		return cod + 1;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == inserisci) {
			if(tipo.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Il campo è vuoto, riprova");
			}
			else {
				try {
					int codice = controllaESettaCodice();
					Connection con = getConnection();
					Statement st = con.createStatement();
					st.execute("USE autoricambi");
					String queryOp3 = "INSERT INTO categoria(tipo,codice) VALUES('"+tipo.getText()+"','"+codice+"')";
					int op = st.executeUpdate(queryOp3);
					FinestraPrincipale fp = null;
					if(op!=0) {
						JOptionPane.showMessageDialog(null, "Categoria inserita.");
						fp = new FinestraPrincipale();
						fp.stampaInTextArea("Query eseguita: ");
						fp.stampaInTextArea(queryOp3);
						fp.stampaInTextArea("Hai inserito la categoria: "+tipo.getText()+" con codice: "+codice);
						fp.setVisible(true);
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Errore di inserimento.");
					
						fp.setVisible(true);
						setVisible(false);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			
			}
		}
		if(e.getSource() == indietro) {
			FinestraPrincipale fp = new FinestraPrincipale();
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
			if(tipo.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Il campo è vuoto, riprova");
			}
			else {
				try {
					int codice = controllaESettaCodice();
					Connection con = getConnection();
					Statement st = con.createStatement();
					st.execute("USE autoricambi");
					String queryOp3 = "INSERT INTO categoria(tipo,codice) VALUES('"+tipo.getText()+"','"+codice+"')";
					int op = st.executeUpdate(queryOp3);
					FinestraPrincipale fp = null;
					if(op!=0) {
						JOptionPane.showMessageDialog(null, "Categoria inserita.");
						fp = new FinestraPrincipale();
						fp.stampaInTextArea("Query eseguita: ");
						fp.stampaInTextArea(queryOp3);
						fp.stampaInTextArea("Hai inserito la categoria: "+tipo.getText()+" con codice: "+codice);
						fp.setVisible(true);
						setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(null, "Errore di inserimento.");
					
						fp.setVisible(true);
						setVisible(false);
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
		
	}

}
