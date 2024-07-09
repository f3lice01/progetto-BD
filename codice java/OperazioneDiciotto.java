import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OperazioneDiciotto extends JFrame implements ActionListener{

	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JPanel pannelloCentrale = null, pannelloBottoni = null, pannelloLabel = null;
	JTextArea stampa = null;
	JScrollPane stampa1= null;
	JButton acquista = null, indietro=null;
	JLabel label = null;
	Login l = new Login();
	int idCar = 0, idIns = 0, codArt = 0, qt = 0, idCompa=0, idFat=0, numFat=0, num_pos=0;
	double costoCar=0, spedizione=0;
	String s="",s1="", s2="", s3="";
	String desc="";
	FinestraPrincipale fp = null;
	
	
	public OperazioneDiciotto() throws SQLException, ClassNotFoundException{
		setTitle("Operazione 18");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		pannelloLabel = new JPanel();
		pannelloCentrale = new JPanel(); 
		pannelloBottoni = new JPanel(new GridLayout(1,2));
		label = new JLabel("Carrello dell'account: "+l.getUsername());
		acquista = new JButton("Acquista");
		indietro = new JButton("Indietro");
		stampa = new JTextArea();
		stampa1 = new JScrollPane(stampa);
		stampa.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stampa.setPreferredSize(new Dimension(1100, 550));
		stampa.setEditable(false);
		pannelloLabel.add(label);
		
		pannelloCentrale.add(stampa1);
		pannelloBottoni.add(acquista);
		pannelloBottoni.add(indietro);
		add(pannelloLabel, BorderLayout.NORTH);
		add(pannelloCentrale, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
		setSize(1200, 690);
		setVisible(true);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		indietro.addActionListener(this);
		acquista.addActionListener(this);
		popolaTestoAcquisto();
		s="SELECT MAX(id_comp) FROM compare";
		idCompa=controllaESettaInd(s);
		s2="SELECT MAX(num_fatt) FROM FATTURA";
		numFat=controllaESettaInd(s2);
		s3="select max(num_poss) from possiede";
		num_pos=controllaESettaInd(s3);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == indietro) {
			fp = new FinestraPrincipale();
			fp.setVisible(true);
			setVisible(false);
		}
		if(e.getSource() == acquista) {
			try {
				Connection c = getConnection();
				Statement s = c.createStatement();
				s.execute("USE autoricambi");
				String str="select i.codice_articolo,r.quantita, i.quantita from carrello as c join inserito as i on c.id_carrello=i.id_carrello join ricambio as r on i.codice_articolo=r.codice_articolo where c.id_cliente='"+l.getUsername()+"'";
				ResultSet rs1=s.executeQuery(str);
				if(rs1.next()) {
			fp= new FinestraPrincipale();
			try {
				int codiceArt=0,disponibili=0, voluti=0, rimasti=0;
				Connection c1 = getConnection();
				Statement s1 = c1.createStatement();
				s1.execute("USE autoricambi");
				String str1="select i.codice_articolo,r.quantita, i.quantita from carrello as c join inserito as i on c.id_carrello=i.id_carrello join ricambio as r on i.codice_articolo=r.codice_articolo where c.id_cliente='"+l.getUsername()+"'";
				ResultSet rs=s1.executeQuery(str1);
				System.out.println("sono nello scalo quantita");
				while(rs.next()) {
					System.out.println("prendo i valori");
					codiceArt=rs.getInt(1);
					disponibili=rs.getInt(2);
					voluti=rs.getInt(3);
					System.out.println(codiceArt+"   "+disponibili+"   "+voluti);
					try {
						Connection c3 = getConnection();
						Statement s3 = c3.createStatement();
						s3.execute("USE autoricambi");
						String str3="update ricambio set quantita="+disponibili+"-"+voluti+" where codice_articolo="+codiceArt+"";
						s3.executeUpdate(str3);
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				Connection c1 = getConnection();
				Statement s1 = c1.createStatement();
				String str1="INSERT INTO fattura(num_fatt, num_ordine, nome_cliente) values("+numFat+","+fp.getCarrello()+",'"+l.getUsername()+"')";
				s1.execute("USE autoricambi");				
				s1.executeUpdate(str1);
			} catch (Exception e21) {
				// TODO: handle exception
				e21.printStackTrace();
			}
			try {
				Connection c2 = getConnection();
				Statement s2 = c.createStatement();
				s.execute("USE autoricambi");
				String q = "SELECT i.id_carrello,i.id_ins,i.codice_articolo,i.quantita, r.descrizione FROM account as a JOIN inserito as i ON a.id_carrello = i.id_carrello  JOIN ricambio as r ON r.codice_articolo=i.codice_articolo WHERE a.username = '"+l.getUsername()+"' AND a.id_carrello = i.id_carrello";
				ResultSet rs2 = s2.executeQuery(q);
				while(rs2.next()) {
					idCar = rs2.getInt(1);
					idIns = rs2.getInt(2);
					codArt = rs2.getInt(3);
					qt = rs2.getInt(4);
					desc= rs2.getString(5);
					Connection c1 = getConnection();
					Statement s1 = c1.createStatement();
					String str1="INSERT INTO compare(id_comp, num_ordine, codice_articolo, quantita) values("+idCompa+","+fp.getCarrello()+","+codArt+","+qt+")";
					s1.execute("USE autoricambi");
					s1.executeUpdate(str1);
					idCompa++;
				}
			} catch (Exception e21) {
				// TODO: handle exception
				e21.printStackTrace();
			}
			try {
				Connection c6 = getConnection();
				Statement s6 = c6.createStatement();
				s6.execute("USE autoricambi");
				String q = "update account set num_acquisti=num_acquisti+1 where username='"+l.getUsername()+"'";
				s6.executeUpdate(q);
			} catch (Exception e21) {
				// TODO: handle exception
				e21.printStackTrace();
			}
			try {
				Connection c4 = getConnection();
				Statement s4 = c4.createStatement();
				s4.execute("USE autoricambi");
				String q = "insert into possiede(num_poss,username, num_ordine, data_acquisto) values("+num_pos+",'"+l.getUsername()+"','"+fp.getCarrello()+"',NOW())";
				s4.executeUpdate(q);
			} catch (Exception e21) {
				e21.printStackTrace();
				// TODO: handle exception
			}
			try {
				Connection c5 = getConnection();
				Statement s5 = c5.createStatement();
				s5.execute("USE autoricambi");
				String q = "Select importo_parziale, costo_spedizione from carrello where id_cliente='"+l.getUsername()+"'";
				ResultSet rs=s.executeQuery(q);
				while(rs.next()) {
					costoCar=rs.getDouble(1);
					spedizione=rs.getDouble(2);
				}
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			try {
				Connection c1 = getConnection();
				Statement s1 = c1.createStatement();
				s1.execute("USE autoricambi");
				String str1="DELETE i FROM carrello as c JOIN inserito as i ON c.id_carrello=i.id_carrello WHERE i.id_carrello='"+ fp.getCarrello()+"'";
				s1.execute(str1);	
				s1.executeUpdate("update carrello set importo_parziale=0 where id_cliente='"+l.getUsername()+"'");
			} catch (Exception e21) {
				// TODO: handle exception
				e21.printStackTrace();
			}
			
			fp.setVisible(true);
			fp.stampaInTextArea("Acquisto completato.");
			fp.stampaInTextArea("Costo articoli: "+ costoCar+" €");
			fp.stampaInTextArea("Costospedizione: "+spedizione+" €");
			fp.stampaInTextArea("Totale: "+ (costoCar+spedizione)+" €");
			setVisible(false);
		}
				else {
					JOptionPane.showMessageDialog(null, "il carrello e' vuoto, impossibile procedere all'acquisto.");
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void popolaTestoAcquisto() {
		int codice =0;
		double importoTotale = 0, temp = 0, prezzo =0;
		try {
			fp= new FinestraPrincipale();
			fp.setVisible(false);
			Connection c = getConnection();
			Statement s = c.createStatement();
			s.execute("USE autoricambi");
			String q = "SELECT i.id_carrello,i.id_ins,i.codice_articolo,i.quantita, r.prezzo-r.sconto as importo, r.descrizione FROM account as a JOIN inserito as i ON a.id_carrello = i.id_carrello  JOIN ricambio as r ON r.codice_articolo=i.codice_articolo WHERE a.username = '"+l.getUsername()+"' AND a.id_carrello = i.id_carrello";
			ResultSet rs = s.executeQuery(q);
			if(rs!=null) {
			stampa.append("Il tuo carrello è: \n");
			stampa.append("Codice articolo\t\t Quantita' \t\t Prezzo \t\t Descrizione\n");
			while(rs.next()) {
				idCar = rs.getInt(1);
				idIns = rs.getInt(2);
				codArt = rs.getInt(3);
				qt = rs.getInt(4);
				prezzo = rs.getDouble(5);
				desc= rs.getString(6);
				stampa.append(codArt+"\t\t  "+qt+"\t\t  "+prezzo+"\t\t"+desc+"\n");
				}
			}
			else {stampa.append("carrello vuoto");
			}
		}
		catch(Exception e3) {
			e3.printStackTrace(); 
		}
		

		try {
			Connection cn = getConnection();
			Statement st3 = cn.createStatement();
			st3.execute("USE autoricambi");
			String quer = "SELECT i.codice_articolo, r.prezzo-r.sconto FROM carrello as c JOIN inserito as i ON c.id_carrello = i.id_carrello JOIN ricambio as r ON i.codice_articolo = r.codice_articolo WHERE c.id_cliente = '"+l.getUsername()+"'";
			ResultSet rs = st3.executeQuery(quer);
			while(rs.next()) {
				codice = rs.getInt(1);
				temp = rs.getDouble(2);
				importoTotale += temp;
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		
		try {
			Connection con1 = getConnection();
			Statement st1 = con1.createStatement();
			st1.execute("USE autoricambi");				
			st1.executeUpdate("UPDATE carrello SET importo_parziale="+importoTotale+" WHERE (id_cliente='"+l.getUsername()+"')");		
		}
		catch(Exception exxx) {
			exxx.printStackTrace();
		}
		
		try {
			double importo = 0;
			Connection c = getConnection();
			Statement s = c.createStatement();
			s.execute("USE autoricambi");
			String q = "SELECT importo_parziale FROM carrello WHERE id_cliente = '"+l.getUsername()+"'";
			ResultSet rs = s.executeQuery(q);
			while(rs.next()) {
				importo = rs.getDouble(1);
				stampa.append("Importo Parziale Carrello: € "+importo);
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	public int controllaESettaInd(String sql) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		ResultSet rs = st.executeQuery(sql);
		int cod = 0;
		while(rs.next()) {
			cod = rs.getInt(1);
		}
		return cod + 1;	
	}
}
