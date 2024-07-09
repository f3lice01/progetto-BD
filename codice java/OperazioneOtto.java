import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OperazioneOtto extends JFrame implements ActionListener {

	private Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		System.out.println("connessione ok");
		con.setAutoCommit(true);
		return con;
	}
	
	/*public static void main(String[] args) {
		OperazioneOtto op = new OperazioneOtto();
	}*/
	
	
	JPanel pannello= null;
	JLabel testo= null;
	JButton cancella= null,indietro = null;
	FinestraPrincipale fp = null;
	
	
	public OperazioneOtto() {
		setTitle("Operazione 8");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello = new JPanel();
		testo= new JLabel("Questa operazione cancella tutti i pezzi di ricambio usati venduti");
		cancella = new JButton("Cancella");
		indietro = new JButton("Indietro");
		
		pannello.add(testo);
		pannello.add(cancella);
		pannello.add(indietro);
		add(pannello);
		setLocationRelativeTo(null);
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		cancella.addActionListener(this);
		indietro.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancella) {
		try {
			Connection con= getConnection();
			Statement st = con.createStatement();
			st.execute("USE autoricambi");
			String Query="delete r from ricambio as r JOIN compare as c ON r.codice_articolo = c.codice_articolo WHERE r.quantita = 0 and r.tipo= 'usato'";
			int op = st.executeUpdate(Query);
			if(op !=0) {
				fp= new FinestraPrincipale();
				setVisible(false);
				fp.setVisible(true);
				
				fp.stampaInTextArea("Query eseguita");
				fp.stampaInTextArea("Tutti i ricambi usati venduti sono stati cancellati.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Non ci sono ricambi usati venduti da cancellare.");
				fp= new FinestraPrincipale();
				setVisible(false);
				fp.setVisible(true);
			}
			
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
		if(e.getSource() == indietro) {
			fp = new FinestraPrincipale();
			fp.setVisible(true);
			setVisible(false);
		}
	}
	
}
