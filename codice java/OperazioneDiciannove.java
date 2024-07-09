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

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OperazioneDiciannove extends JFrame implements ActionListener {
	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	JPanel panel = null, pannelloAreaDiTesto = null, pannelloDati = null;
	JLabel scriviCodice = null, scriviQuantita = null;
	JButton aggiungi = null,indietro = null;
	JTextArea textArea = null;
	JTextField cod, quant = null;
	FinestraPrincipale fp=null;
	public OperazioneDiciannove() {
			setTitle("Operazione 19");
			setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
		 	aggiungi = new JButton("Aggiungi");
		    indietro = new JButton("Indietro");
		    scriviCodice = new JLabel("Scrivi il codice: ");
		    scriviQuantita = new JLabel("Scrivi la quantita': ");
		    cod = new JTextField();
		    quant = new JTextField();
		    // Crea un pannello per contenere la JComboBox
		    panel = new JPanel(new GridLayout(4,1));
		    pannelloAreaDiTesto = new JPanel();
		    pannelloDati = new JPanel(new GridLayout(4,2));
		    textArea = new JTextArea();
		    textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	        textArea.setEditable(false);
	        textArea.setPreferredSize(new Dimension(1100, 550));
	        
		    pannelloAreaDiTesto.add(textArea);
		    pannelloDati.add(scriviCodice);
		    pannelloDati.add(cod);
		    pannelloDati.add(scriviQuantita);
		    pannelloDati.add(quant);
		    pannelloDati.add(indietro);
		    pannelloDati.add(aggiungi);
		   
		    add(panel,BorderLayout.NORTH);
		    add(pannelloAreaDiTesto,BorderLayout.CENTER);
		    add(pannelloDati,BorderLayout.SOUTH);
		    aggiungi.addActionListener(this);
		    indietro.addActionListener(this);
		    setSize(1200, 620);
		    setVisible(true);
		    setResizable(false);
		    setLocationRelativeTo(null);
		    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		    
		    //riempio la textarea con prodotti che hanno 0 di quantit� attualmente 
		    try {
		    	int codice=0, qt=0;
		    	String desc="";
				Connection c= getConnection();
				Statement st=c.createStatement();
				st.execute("USE autoricambi");
				String query="SELECT codice_articolo, quantita, descrizione FROM ricambio WHERE quantita=0";
				ResultSet rs=st.executeQuery(query);
				if(rs!=null) {
					stampaInTextArea("Codice ricambio \t Quantita' attuale \t\t Descrizione");
					while(rs.next()) {
						codice=rs.getInt(1);
						qt=rs.getInt(2);
						desc=rs.getString(3);
						stampaInTextArea(codice+"\t\t  "+qt+"\t\t\t "+desc);
					}
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == indietro) {
			fp = new FinestraPrincipale();
			fp.setVisible(true);
			setVisible(false);
		}
		if (e.getSource()==aggiungi) {
			if (cod.getText().equals("") || quant.getText().equals("")){
				JOptionPane.showMessageDialog(null,"ATTENZIONE, alcuni campi risultano vuoti");
			}
			else {
			try {
				int codiceUt=Integer.parseInt(cod.getText());
				int qnt=Integer.parseInt(quant.getText());
				Connection c= getConnection();
				Statement st=c.createStatement();
				st.execute("USE autoricambi");
				String query="update ricambio set quantita="+qnt+" WHERE codice_articolo="+codiceUt+"";
				st.executeUpdate(query);
				System.out.println(qnt+"     "+codiceUt);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
			fp=new FinestraPrincipale();
			fp.setVisible(true);
			setVisible(false);
			}
		}
	}
	public void stampaInTextArea(String s) {
		
		textArea.append(s+"\n");
	}
}