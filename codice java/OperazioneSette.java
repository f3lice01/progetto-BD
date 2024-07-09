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

public class OperazioneSette extends JFrame implements ActionListener{
	
	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		System.out.println("connessione ok");
		con.setAutoCommit(true);
		return con;
	}
	
	
	JPanel pannello= null;
	JLabel testo= null;
	JButton cancella= null,indietro = null;
	FinestraPrincipale fp = null;
	
	public OperazioneSette() {
		setTitle("Operazione 7");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello = new JPanel();
		testo= new JLabel("Questa operazione cancella tutti gli account che non hanno effettuato acquisti.");
		cancella = new JButton("Cancella");
		indietro = new JButton("Indietro");
		pannello.add(testo);
		pannello.add(cancella);
		pannello.add(indietro);
		add(pannello);
		setLocationRelativeTo(null);
		setSize(500,200);
		setVisible(true);
		
		cancella.addActionListener(this);
		indietro.addActionListener(this);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	  if(e.getSource() == cancella) {
		try {
			Connection con= getConnection();
			Statement st = con.createStatement();
			st.execute("USE autoricambi");
			String Query="delete from account where num_acquisti = 0";
			int op = st.executeUpdate(Query);
			
			if(op !=0) {
				fp =  new FinestraPrincipale();
				setVisible(false);
				fp.setVisible(true);
				fp.stampaInTextArea("Query eseguita");
				fp.stampaInTextArea("Tutti gli account con numero di acquisti pari a zero sono stati cancellati.");
			}
			else {
				JOptionPane.showMessageDialog(null, "Non ci sono account da cancellare.");
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
