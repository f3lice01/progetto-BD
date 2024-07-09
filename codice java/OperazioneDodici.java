import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OperazioneDodici extends JFrame implements ActionListener{

	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JPanel pannello,pannelloBot;
	JTextArea areaCarrello;
	JScrollPane car;
	JLabel sceltaCarrello, insertCod;
	JButton cancella, indietro;
	JComboBox<Integer> listaCod;
	Login l = new Login();
	int codiceArt = 0;
	FinestraPrincipale fp = null;
	
	public OperazioneDodici() throws ClassNotFoundException, SQLException {
		setTitle("Operazione 12");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello = new JPanel(new GridLayout(5,1));
		sceltaCarrello = new JLabel("Questo Ã¨ il tuo carrello");
		areaCarrello = new JTextArea();
		areaCarrello.setEditable(false);
		areaCarrello.setSize(300, 300);
		car = new JScrollPane(areaCarrello);
		insertCod = new JLabel("Inserisci Codice articolo");
		listaCod = new JComboBox<Integer>();
		listaCod.setSize(200, 100);
		pannelloBot = new JPanel();
		cancella = new JButton("Cancella");
		indietro = new JButton("Indietro");
		fillCarrello();
		setComboBoxCodice();
		pannello.add(sceltaCarrello);
		pannello.add(car);
		pannello.add(insertCod);
		pannello.add(listaCod);
		pannelloBot.add(cancella);
		pannelloBot.add(indietro);
		pannello.add(pannelloBot);
		add(pannello);
		
		setVisible(true);
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		cancella.addActionListener(this);
		indietro.addActionListener(this);
		listaCod.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		double importoTotale = 0;
		int codice;
		if(e.getSource() == indietro) {
			fp = new FinestraPrincipale();
			setVisible(false);
			fp.setVisible(true);
		}
		if(e.getSource() == cancella) {
			Connection con;
			Statement st ;
			
			try {
				con = getConnection();
				st = con.createStatement();
				st.execute("USE autoricambi");
				String query = "DELETE i FROM carrello as c JOIN inserito as i ON c.id_carrello=i.id_carrello WHERE i.codice_articolo="+codiceArt+" AND c.id_cliente='"+l.getUsername()+"'";
				int op = st.executeUpdate(query);
				if(op!=0) {
					JOptionPane.showMessageDialog(null, "Cancellazione eseguita correttamente");
					fp = new FinestraPrincipale();
					fp.setVisible(true);
					fp.stampaInTextArea("Cancellazione eseguita");
					fp.stampaInTextArea(query);
					fp.stampaInTextArea("L'articolo con codice: ["+codiceArt+"] e' stato eliminato dal carrello.");
					setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Errore durante la cancellazione");
					fp = new FinestraPrincipale();
					fp.setVisible(true);
					setVisible(false);
				}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == listaCod) {
			codiceArt = (int)listaCod.getSelectedItem();
			System.out.println(codiceArt);
		}
	}

	private void setComboBoxCodice() throws ClassNotFoundException, SQLException{
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String query = "SELECT * FROM carrello as c JOIN inserito as i ON c.id_carrello = i.id_carrello WHERE id_cliente='"+l.getUsername()+"'";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			listaCod.addItem(rs.getInt(6));
		}
		listaCod.setSelectedIndex(-1);
	}
	
	private void fillCarrello() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String query = "SELECT * FROM carrello JOIN inserito ON carrello.id_carrello = inserito.id_carrello WHERE id_cliente='"+l.getUsername()+"'";
		ResultSet rs = st.executeQuery(query);
		String cliente="";
	
		int parziale = 0;
		int numArt = 0;
		double costoSped = 0;
		if(rs != null) {
			areaCarrello.append(rs.getMetaData().getColumnName(1)+"\t"+rs.getMetaData().getColumnName(6)+"\t\t"+rs.getMetaData().getColumnName(8)+"\t"+rs.getMetaData().getColumnName(4)+"\n");
			while(rs.next()) {
				cliente = rs.getString(1);
				
				parziale = rs.getInt(6);
				numArt = rs.getInt(8);
				costoSped = rs.getDouble(4);
				areaCarrello.append(cliente+"\t"+parziale+"\t\t    "+numArt+"\t"+costoSped+"\n");
			}
		}
	}
	
	

}
