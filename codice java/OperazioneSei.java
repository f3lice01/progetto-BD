import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OperazioneSei extends JFrame implements ActionListener{
	
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
		FinestraPrincipale fp = null;;
		
	public OperazioneSei(){
		setTitle("Operazione 6");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello = new JPanel();
		testo= new JLabel("Questa operazione cancella tutte le fatture relative all'anno precedente.");
		cancella = new JButton("Cancella");
		indietro = new JButton("Indietro");
		pannello.add(testo);
		pannello.add(cancella);
		pannello.add(indietro);
		add(pannello);
		setLocationRelativeTo(null);
		setSize(500,200);
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
				String Query="delete p,f from possiede as p inner join fattura as f on f.nome_cliente = p.username and f.num_ordine= p.num_ordine where year(p.data_acquisto) = year(curdate())-1;";
				st.executeUpdate(Query);
				fp = new FinestraPrincipale();
				setVisible(false);
				fp.setVisible(true);
				fp.stampaInTextArea("Query eseguita");
				fp.stampaInTextArea("Tutte le fatture dello scorso anno sono state cancellate.");
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
