import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.time.LocalDate;
import java.time.Month;

public class OperazioneCinque extends JFrame implements ActionListener{

	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JButton bottone= null;
	JPanel pannello=null;
	JLabel testo=null;
	
	public OperazioneCinque() {
		setTitle("Operazione 5");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannello= new JPanel();
		testo= new JLabel("Questa operazione ricerca le tutte fatture del mese precedente");
		bottone= new JButton("Ricerca");
		
		pannello.add(testo);
		pannello.add(bottone);
		add(pannello);
		bottone.addActionListener(this);
		setLocationRelativeTo(null);
		setSize(500,200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bottone) {
		try {
		Connection con=getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String Query="SELECT fattura.num_ordine, possiede.data_acquisto,fattura.importo_totale, fattura.nome_cliente FROM fattura JOIN possiede ON fattura.nome_cliente = possiede.username and fattura.num_ordine=possiede.num_ordine WHERE MONTH(data_acquisto) = month(date_sub(now(),interval 1 month)) and year(data_acquisto) = year(date_sub(now(), interval 1 month));";
		//System.out.println("sono dopo la stringa query");
		ResultSet rs= st.executeQuery(Query);
		if(rs!=null) {
			System.out.println("sono in if");
			//ResultSetMetaData rsmd = rs.getMetaData();
			FinestraPrincipale fp = new FinestraPrincipale();
			fp.stampaInTextArea("Query eseguita: ");
			fp.stampaInTextArea(Query);
			fp.stampaInTextArea(rs.getMetaData().getColumnName(1)+ " "+ rs.getMetaData().getColumnName(2)+ " "+rs.getMetaData().getColumnName(3)+ " "+rs.getMetaData().getColumnName(4));
			while(rs.next()) {
				fp.stampaInTextArea("         "+ rs.getInt(1) + "              " + rs.getDate(2) + "          "+ rs.getDouble(3) + "          " + rs.getString(4));
				fp.setVisible(true);
				setVisible(false);
				}
			}
		else {
			FinestraPrincipale fp= new FinestraPrincipale();
			
			fp.stampaInTextArea("Non ci sono fatture relative al mese precedente;");
			fp.setVisible(true);
			setVisible(false);
		}
		}catch (Exception ev) {
		// TODO: handle exception
		}
		
		}
	}

}
