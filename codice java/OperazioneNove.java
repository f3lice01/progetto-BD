import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.StyleContext.SmallAttributeSet;

public class OperazioneNove extends JFrame implements ActionListener ,KeyListener{
	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		System.out.println("connessione ok");
		con.setAutoCommit(true);
		return con;
	}
	JPanel pannello= null;
	JLabel descrizione= null;
	JTextField desc= null;
	JLabel quantita= null;
	JTextField qt = null;
	JLabel sconto = null;
	JTextField dsc = null;
	JTextArea legenda = null;
	JLabel prezzo = null;
	JTextField costo= null;
	JLabel categoria= null;
	JComboBox<Integer> cod_cat= null;
	JButton btn=null, indietro=null;
	FinestraPrincipale fp = null;
	//String leg = "Legenda categorie:\n1: Pneumatici\n2:Olio e Filtri\n3:Freni\n4:Accessori auto\n5:Carrozzeria";
	GridBagConstraints gbc = null;
	GridBagConstraints gbc_legenda=null;
	int codCat=0;
	
	
	private void fillLegenda() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String query = "SELECT * FROM categoria";
		ResultSet rs = st.executeQuery(query);
		String tipo = "";
		int codice = 0;
		if(rs != null) {
			while(rs.next()) {
				tipo = rs.getString(1);
				codice = rs.getInt(2);
				legenda.append(codice+": "+tipo+"\n");
			}
		}
	}
	
	private void setCategoria() throws ClassNotFoundException, SQLException{
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String query = "SELECT codice FROM categoria";
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			cod_cat.addItem(rs.getInt(1));
		}
		cod_cat.setSelectedIndex(-1);
	}
	
	public OperazioneNove() throws ClassNotFoundException, SQLException {
		setTitle("Operazione 9");
		setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		btn = new JButton("Inserisci ricambio");
		indietro = new JButton("Indietro");
		legenda = new JTextArea("Categorie disponibili: \n");
		legenda.setEditable(false);
		pannello= new JPanel(new GridLayout(6, 3));
		descrizione = new JLabel("Inserisci descrizione prodotto: ");
		desc= new JTextField();
		quantita = new JLabel("Inserisci la quantita: ");
		qt= new JTextField();
		sconto = new JLabel("Inserisci lo sconto: €");
		dsc= new JTextField("0");
		dsc.setEditable(false);
		prezzo = new JLabel("Inserisci prezzo articolo: €");
		costo= new JTextField();
		categoria = new JLabel("Inserisci codice categoria: ");
		cod_cat= new JComboBox<Integer>();
		fillLegenda();
		setCategoria();
		gbc = new GridBagConstraints();
		
		pannello.add(descrizione,BorderLayout.WEST);
		pannello.add(desc);
		pannello.add(quantita,BorderLayout.WEST);
		pannello.add(qt);
		pannello.add(sconto,BorderLayout.WEST);
		pannello.add(dsc);
		pannello.add(prezzo,BorderLayout.WEST);
		pannello.add(costo);
		pannello.add(categoria,BorderLayout.WEST);
		pannello.add(cod_cat);
		pannello.add(btn,BorderLayout.CENTER);
		pannello.add(indietro);
		
		
		getContentPane().setLayout(new GridBagLayout());
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = pannello.getX()/2;
		gbc.gridy = pannello.getY()/2;
		getContentPane().add(pannello,gbc);
		
		gbc_legenda = new GridBagConstraints();
		gbc_legenda.gridx = 3;
		gbc_legenda.gridy = 2;
		getContentPane().add(legenda, gbc_legenda);
		
		add(pannello);
		add(legenda);
		setSize(500,400);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		btn.addActionListener(this);
		indietro.addActionListener(this);
		cod_cat.addActionListener(this);
		desc.addKeyListener(this);
		qt.addKeyListener(this);
		costo.addKeyListener(this);
	}

	public int controllaESettaCodice() throws ClassNotFoundException, SQLException {
		
		Connection con = getConnection();
		Statement st = con.createStatement();
		st.execute("USE autoricambi");
		String queryCount = "SELECT max(codice_articolo) FROM ricambio";
		ResultSet rs = st.executeQuery(queryCount);
		int cod = 0;
		while(rs.next()) {
			cod = rs.getInt(1);
		}
		return cod + 1;
		
	}
	
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		OperazioneNove o = new OperazioneNove();
	}*/
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == cod_cat) {
			codCat = (int) cod_cat.getSelectedItem();
		}
		if(e.getSource() == btn) {
			if(desc.getText().equals("") || qt.getText().equals("") || costo.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Alcuni campi sono vuoti, riprova");
			}
			else {
			 try {
			 	Connection con = getConnection();
			 	Statement st = con.createStatement();
			 	int cod = controllaESettaCodice();
			 	String query="INSERT INTO ricambio (codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria) values ("+cod+",'"+desc.getText()+"',"+Integer.parseInt(qt.getText())+","+Double.parseDouble(dsc.getText())+",'nuovo',"+Double.parseDouble(costo.getText())+","+codCat+")";
			 	st.execute("USE autoricambi");
			 	int q = st.executeUpdate(query);
			 	
			 	if(q!=0) {
			 		JOptionPane.showMessageDialog(null, "Ricambio inserito.");
			 		fp = new FinestraPrincipale();
			 		fp.stampaInTextArea("Query eseguita: ");
			 		fp.stampaInTextArea(query);
			 		fp.stampaInTextArea("Hai inserito il ricambio: ["+desc.getText()+", Quantita':"+Integer.parseInt(qt.getText())+", Sconto: "+Double.parseDouble(dsc.getText())+", Tipo: nuovo, Prezzo: "+Double.parseDouble(costo.getText())+", Codice Categoria: "+codCat+"]");
			 		fp.stampaInTextArea("Con codice: ["+cod+"]");
			 		fp.setVisible(true);
			 		setVisible(false);
			 	}
			 	else {
			 		JOptionPane.showMessageDialog(null, "Ricambio non inserito.");
			 		fp = new FinestraPrincipale();
			 		fp.setVisible(true);
			 		setVisible(false);
			 	}
			 } catch (SQLException | ClassNotFoundException e2) {
			 	// TODO: handle exception
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
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(desc.getText().equals("") || qt.getText().equals("") || costo.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Alcuni campi sono vuoti, riprova");
			}
			else {
			 try {
			 	Connection con = getConnection();
			 	Statement st = con.createStatement();
			 	int cod = controllaESettaCodice();
			 	String query="INSERT INTO ricambio (codice_articolo, descrizione, quantita, sconto, tipo, prezzo,cod_categoria) values ("+cod+",'"+desc.getText()+"',"+Integer.parseInt(qt.getText())+","+Double.parseDouble(dsc.getText())+",'nuovo',"+Double.parseDouble(costo.getText())+","+codCat+")";
			 	st.execute("USE autoricambi");
			 	int q = st.executeUpdate(query);
			 	
			 	if(q!=0) {
			 		JOptionPane.showMessageDialog(null, "Ricambio inserito.");
			 		fp = new FinestraPrincipale();
			 		fp.stampaInTextArea("Query eseguita: ");
			 		fp.stampaInTextArea(query);
			 		fp.stampaInTextArea("Hai inserito il ricambio: ["+desc.getText()+", Quantita':"+Integer.parseInt(qt.getText())+", Sconto: "+Double.parseDouble(dsc.getText())+", Tipo: nuovo, Prezzo: "+Double.parseDouble(costo.getText())+", Codice Categoria: "+codCat+"]");
			 		fp.stampaInTextArea("Con codice: ["+cod+"]");
			 		fp.setVisible(true);
			 		setVisible(false);
			 	}
			 	else {
			 		JOptionPane.showMessageDialog(null, "Ricambio non inserito.");
			 		fp = new FinestraPrincipale();
			 		fp.setVisible(true);
			 		setVisible(false);
			 	}
			 } catch (SQLException | ClassNotFoundException e2) {
			 	// TODO: handle exception
			 }
		 }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
