import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class OperazioneDue extends JFrame implements ActionListener {

	JPanel panel = null,pannelloBottoni = null;
	JLabel scegliMarca = null;
	JLabel scegliModello = null;
	JLabel scegliVersione = null;
	JButton ricerca = null,indietro = null;
	
	JComboBox<String> produttore;
	JComboBox<String> modello;
	JComboBox<String> versione;
	GridBagConstraints gbc = new GridBagConstraints();
	DefaultComboBoxModel<String> boxModelli = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<String> boxVersioni = new DefaultComboBoxModel<>();
	String stringaMarca, stringaModello,stringaVersione;
	String resultQueryOP2;
	Login l = new Login();

	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	
  public OperazioneDue() throws ClassNotFoundException {
   setTitle("Operazione 2");
   setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
   scegliMarca = new JLabel("Scegli marca: ");
  
    DefaultComboBoxModel<String> boxMarca = new DefaultComboBoxModel<>();
    List<String> marche = getMarche();
    for (String marca : marche) {
    	boxMarca.addElement(marca);
    }
    
    

    // Crea la JComboBox utilizzando la marca
    produttore = new JComboBox<>(boxMarca);
    scegliModello = new JLabel("Scegli modello: ");
    modello = new JComboBox<>(boxModelli);
    scegliVersione = new JLabel("Scegli versione: ");
    versione = new JComboBox<>(boxVersioni);
    ricerca = new JButton("Ricerca");
    indietro = new JButton("Indietro");
    // Crea un pannello per contenere la JComboBox
    panel = new JPanel(new GridLayout(5,1));
    
    panel.add(scegliMarca);
    panel.add(produttore);
    panel.add(scegliModello);
    panel.add(modello);
    panel.add(scegliVersione);
    panel.add(versione);
    panel.add(ricerca);
    panel.add(indietro);
     
    add(panel);
    
    produttore.addActionListener(this);
    modello.addActionListener(this);
    versione.addActionListener(this);
    ricerca.addActionListener(this);
    indietro.addActionListener(this);
    setSize(300, 200);
    setVisible(true);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
  }

  private List<String> getMarche() throws ClassNotFoundException {
    List<String> marche = new ArrayList<>();

    // Esegue una query per ottenere le marche dalla tabella "auto"
    try {
      Connection conn = getConnection();
      Statement stmt = conn.createStatement();
      stmt.execute("USE autoricambi");
      ResultSet rs = stmt.executeQuery("SELECT DISTINCT marca FROM auto");
      marche.add("------");
      // Aggiunge le marche alla lista
      while (rs.next()) {
        marche.add(rs.getString("marca"));
      }

      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return marche;
  }
  
  private List<String> getModelli() throws ClassNotFoundException {
	    List<String> modelli = new ArrayList<>();

	    // Esegue una query per ottenere i modelli dalla tabella "auto" con la marca selezionata
	    try {
	      Connection conn = getConnection();
	      Statement stmt = conn.createStatement();
	      stmt.execute("USE autoricambi");
	      ResultSet rs = stmt.executeQuery("SELECT DISTINCT modello FROM auto WHERE marca='"+stringaMarca+"'");
	      modelli.add("------");
	      // Aggiunge i modelli alla lista
	      while (rs.next()) {
	        modelli.add(rs.getString("modello"));
	      }

	      rs.close();
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return modelli;
	  }
  
  private List<String> getVersioni() throws ClassNotFoundException {
	    List<String> versioni = new ArrayList<>();

	    // Esegue una query per ottenere le marche dalla tabella "auto"
	    try {
	      Connection conn = getConnection();
	      Statement stmt = conn.createStatement();
	      stmt.execute("USE autoricambi");
	      ResultSet rs = stmt.executeQuery("SELECT DISTINCT versione FROM auto WHERE marca='"+stringaMarca+"' AND modello='"+stringaModello+"'");
	      versioni.add("------");
	      // Aggiunge le marche alla lista
	      while (rs.next()) {
	    	  versioni.add(rs.getString("versione"));
	      }

	      rs.close();
	      stmt.close();
	      conn.close();
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }

	    return versioni;
	  }


@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource() == produttore) {
		stringaMarca = (String) produttore.getSelectedItem();
		System.out.println(stringaMarca);
		boxModelli.removeAllElements();
		try {
			List<String> modelli = getModelli();
			for (String mod : modelli) {
		    	boxModelli.addElement(mod);}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	
	if(e.getSource() == modello) {
		stringaModello = (String) modello.getSelectedItem();
		System.out.println(stringaModello);
		boxVersioni.removeAllElements();
		try {
			List<String> versioni = getVersioni();
			for (String v : versioni) {
		    	boxVersioni.addElement(v);}
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	if(e.getSource() == versione) {
		stringaVersione = (String) versione.getSelectedItem();
	}
	if(e.getSource() == ricerca) {
		try {
			queryOP2();
			popolaRicerca();
			setVisible(false);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	if(e.getSource() == indietro) {
		FinestraPrincipale fp = new FinestraPrincipale();
		fp.setVisible(true);
		setVisible(false);
	}
}

public int controllaESettaCodice() throws ClassNotFoundException, SQLException {
	
	Connection con = getConnection();
	Statement st = con.createStatement();
	st.execute("USE autoricambi");
	String queryCount = "SELECT max(codice) FROM ricerca";
	ResultSet rs = st.executeQuery(queryCount);
	int cod = 0;
	while(rs.next()) {
		cod = rs.getInt(1);
	}
	return cod + 1;	
}

	private void popolaRicerca() throws SQLException, ClassNotFoundException{
		Connection c = getConnection();
		Statement st = c.createStatement();
		ResultSet rs = null;
	    st.execute("USE autoricambi");
	    int cod = controllaESettaCodice();
	    String q = "SELECT id_auto FROM auto WHERE marca='"+stringaMarca+"' AND modello='"+stringaModello+"' AND versione='"+stringaVersione+"'";
	    rs = st.executeQuery(q);
	    int id_auto = 0;
	    if(rs!=null) {
	    	while(rs.next()) {
	    		id_auto = rs.getInt(1);
	    	}
	    	String query = "INSERT INTO ricerca(codice,data,username,id_auto) values("+cod+",now(),'"+l.getUsername()+"',"+id_auto+")";
	    	st.executeUpdate(query);
	    }
	}

	private void queryOP2() throws SQLException, ClassNotFoundException {
		Connection c = getConnection();
		Statement st = c.createStatement();
	    st.execute("USE autoricambi");
	    String query = "SELECT marca, modello, versione FROM auto WHERE marca='"+stringaMarca+"' AND modello='"+stringaModello+"' AND versione='"+stringaVersione+"'";
	    ResultSet rs = st.executeQuery(query);
	    
	    if(rs==null) {
	    	JOptionPane.showMessageDialog(null, "Non ci sono risultati per la query");
	    }else {
	    	while(rs.next()) {
	    		FinestraPrincipale fp = new FinestraPrincipale();
	    		fp.stampaInTextArea("Query eseguita: ");
	    		fp.stampaInTextArea(query);
	    		fp.stampaInTextArea(rs.getString(1));
	    		fp.stampaInTextArea(rs.getString(2));
	    		fp.stampaInTextArea(rs.getString(3));
	    		fp.setVisible(true);
	    	}
	    }
	}
}
