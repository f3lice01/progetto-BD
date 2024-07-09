import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
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

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperazioneDiciassette extends JFrame implements ActionListener{

	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JPanel panel = null, pannelloAreaDiTesto = null, pannelloDati = null;
	JLabel scegliMarca = null;
	JLabel scegliModello = null;
	JLabel scegliVersione = null;
	JLabel scriviCodice = null, scriviQuantita = null;
	JButton ricerca = null,indietro = null;
	JButton acquista = null, salvaInCarrello = null;
	JTextArea textArea = null;
	JTextField cod, qnt = null;
	
	JComboBox<String> produttore;
	JComboBox<String> modello;
	JComboBox<String> versione;
	GridBagConstraints gbc = new GridBagConstraints();
	DefaultComboBoxModel<String> boxModelli = new DefaultComboBoxModel<>();
	DefaultComboBoxModel<String> boxVersioni = new DefaultComboBoxModel<>();
	String stringaMarca, stringaModello,stringaVersione;
	String resultQueryOP2;
	RegistrationFrame rf = new RegistrationFrame();
	Login l = new Login();
	FinestraPrincipale fp = null;
	int idIns, idCom=0;
	
	public OperazioneDiciassette() throws ClassNotFoundException {
		setTitle("Operazione 17");
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
	    acquista = new JButton("Acquista");
	    salvaInCarrello = new JButton("Salva in Carrello");
	    scriviCodice = new JLabel("Scrivi il codice: ");
	    scriviQuantita = new JLabel("Scrivi la quantita': ");
	    cod = new JTextField();
	    qnt = new JTextField();
	    // Crea un pannello per contenere la JComboBox
	    panel = new JPanel(new GridLayout(5,1));
	    pannelloAreaDiTesto = new JPanel();
	    pannelloDati = new JPanel(new GridLayout(4,2));
	    textArea = new JTextArea();
	    textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea.setEditable(false);
        textArea.setPreferredSize(new Dimension(1100, 550));
       
	    panel.add(scegliMarca);
	    panel.add(produttore);
	    panel.add(scegliModello);
	    panel.add(modello);
	    panel.add(scegliVersione);
	    panel.add(versione);
	    panel.add(ricerca);
	    
	    pannelloAreaDiTesto.add(textArea);
	    pannelloDati.add(scriviCodice);
	    pannelloDati.add(cod);
	    pannelloDati.add(scriviQuantita);
	    pannelloDati.add(qnt);
	    pannelloDati.add(indietro);
	    pannelloDati.add(salvaInCarrello);
	    add(panel,BorderLayout.NORTH);
	    add(pannelloAreaDiTesto,BorderLayout.CENTER);
	    add(pannelloDati,BorderLayout.SOUTH);
	    produttore.addActionListener(this);
	    modello.addActionListener(this);
	    versione.addActionListener(this);
	    ricerca.addActionListener(this);
	    indietro.addActionListener(this);
	    salvaInCarrello.addActionListener(this);
	    setSize(1200, 620);
	    setVisible(true);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	  }

	
	
	public void resetTextArea() {
		textArea.setText("");
	}
	
	
	
	public void stampaInTextArea(String s) {
	
		textArea.append(s+"\n");
	}
	
	  private List<String> getMarche() throws ClassNotFoundException {
	    List<String> marche = new ArrayList<>();

	    // Esegue una query per ottenere le marche dalla tabella "auto"
	    try {
	      Connection conn = getConnection();
	      Statement stmt = conn.createStatement();
	      stmt.execute("USE autoricambi");
	      ResultSet rs = stmt.executeQuery("SELECT DISTINCT marca FROM auto");
	      marche.add("-----");
	      // Aggiunge le marche alla lista
	      while (rs.next()) {
	        marche.add(rs.getString("marca"));
	      }
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
		      modelli.add("-----");
		      // Aggiunge i modelli alla lista
		      while (rs.next()) {
		        modelli.add(rs.getString("modello"));
		      }
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
		      versioni.add("-----");
		      // Aggiunge le marche alla lista
		      while (rs.next()) {
		    	  versioni.add(rs.getString("versione"));
		      }

		    } catch (SQLException e) {
		      e.printStackTrace();
		    }

		    return versioni;
		  }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == produttore) {
			stringaMarca = (String) produttore.getSelectedItem();
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
				resetTextArea();
				queryOP2();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == indietro) {
			fp = new FinestraPrincipale();
			fp.setVisible(true);
			setVisible(false);
		}
		
		if(e.getSource() == salvaInCarrello) {
			int idIns = 0;
			String query ="SELECT MAX(id_ins) FROM inserito";
			try {
				idIns = controllaESettaCodice(query);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int codice = Integer.parseInt(cod.getText());
			int q = Integer.parseInt(qnt.getText());
			double costoParziale = 0.0,costoDaCarrello = 0.0;
			int qDisp = 0;
			try {
				Connection c1 = getConnection();
				Statement s = c1.createStatement();
				String q1 = "SELECT importo_parziale FROM carrello WHERE id_cliente='"+l.getUsername()+"'";
				s.execute("USE autoricambi");
				ResultSet rs1 = s.executeQuery(q1);
				while(rs1.next()) {
					costoDaCarrello = rs1.getDouble(1);
				}
				//temp = costoDaCarrello + costoParziale;
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			FinestraPrincipale fp = null;
			String tipo = "";
			double sconto = 0;
			try {
				Connection cn = getConnection();
				Statement st = cn.createStatement();
				st.execute("USE autoricambi");
				String queryPrezzo = "SELECT prezzo,quantita,tipo,sconto FROM ricambio WHERE codice_articolo = "+codice+"";
				ResultSet rs = st.executeQuery(queryPrezzo);
				if(rs!=null) {
					while(rs.next()) {
						if(q <= rs.getInt(2)) { 
							costoParziale = rs.getDouble(1);
							qDisp = rs.getInt(2);
							tipo = rs.getString(3);
							sconto = rs.getDouble(4);
							Connection con = getConnection();
							Statement st1 = con.createStatement();
							st1.execute("USE autoricambi");
							st1.execute("INSERT INTO inserito(id_ins, codice_articolo, id_carrello, quantita) values(" + idIns + ", " + codice + ", (select id_carrello from account where username='" + l.getUsername() + "')," + q + ")");
							if(tipo.equals("nuovo"))
								st1.executeUpdate("UPDATE carrello SET importo_parziale="+(costoDaCarrello +(costoParziale*q))+" WHERE (id_cliente='"+l.getUsername()+"')");
							else
								st1.executeUpdate("UPDATE carrello SET importo_parziale="+(costoDaCarrello +((costoParziale-sconto)*q))+" WHERE (id_cliente='"+l.getUsername()+"')");
							fp = new FinestraPrincipale();
							fp.setVisible(true);
							fp.stampaInTextArea("Il ricambio e' stato aggiunto al carrello.");
							setVisible(false);
							
						}
						else {JOptionPane.showMessageDialog(null, "quantita' non disponibile");
								
							}
						}
						
					}
				} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
		}
			
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


		private void queryOP2() throws SQLException, ClassNotFoundException {
			Connection c = getConnection();
			Statement st = c.createStatement();
		    st.execute("USE autoricambi");
		    String query = "SELECT marca, modello, versione FROM auto WHERE marca='"+stringaMarca+"' AND modello='"+stringaModello+"' AND versione='"+stringaVersione+"'";
		    ResultSet rs = st.executeQuery(query);
		    if(rs==null) {
		    	JOptionPane.showMessageDialog(null, "Non ci sono ricambi per l'auto selezionata");
		    }else {
		    	String queryCat = "SELECT * FROM ricambio WHERE descrizione LIKE '%"+stringaMarca+"%' OR descrizione LIKE '%"+stringaModello+"%' OR cod_categoria = 4";
		    	ResultSet rs1 = st.executeQuery(queryCat);
		    	
	    		stampaInTextArea("Query eseguita: "+ queryCat);
	    		stampaInTextArea("Questi sono tutti i ricambi disponibili per quest'auto:");
	    		
		    	while(rs1.next()) {
		    		stampaInTextArea("Codice: "+String.valueOf(rs1.getInt(1))+"|Descrizione: "+rs1.getString(2)+"|Quantita: "+String.valueOf(rs1.getInt(3))+"|Sconto: "+String.valueOf(rs1.getInt(4))+"|Tipo: "+rs1.getString(5)+"|Prezzo: "+String.valueOf(rs1.getDouble(6))+"|Codice Categoria: "+String.valueOf(rs1.getInt(7)));
		    	}
		    }
		}
		
}
