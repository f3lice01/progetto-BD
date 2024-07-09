import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FinestraPrincipale extends JFrame implements ActionListener{

	
	private Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
		String url= "jdbc:mysql://127.0.0.1:3306/?user=autoricambi";
		Connection con = DriverManager.getConnection(url, "autoricambi","autoricambi");
		con.setAutoCommit(true);
		return con;
	}
	
	JPanel pannello=null;
	JPanel bottoni=null;
	JTextArea textArea=null;
	JPanel paragrafi= null;
	JLabel accesso=null, vuoto=null, chooseLabel=null;
	JButton op2, op3, op4, op5, op6, op7, op8, op9, op10, op11, op12, op13, op14,op15,op16,op17,op18,op19, home=null;
	
	Login l = new Login();
	
	
	public void resetTextArea() {
		textArea.setText("");
	}
	
	public void stampaInTextArea(String s) {
	
		textArea.append(s+"\n");
	}
	
	
    public FinestraPrincipale() {
    	setIconImage(new ImageIcon("C:\\Users\\felic\\eclipse-workspace\\Autoricambi\\src\\icon.png").getImage());
    	System.out.println(l.getUsername());
        setTitle("Accesso Effettuato");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Creazione del layout della finestra
        pannello = new JPanel(new BorderLayout(10, 10)); // Aggiunto spazio tra le componenti
        JPanel bottoni = new JPanel(new GridLayout(20, 1, 0, 5)); // Aggiunto spazio tra le righe
        pannello.add(bottoni, BorderLayout.WEST);
        textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        textArea.setEditable(false);
        pannello.add(textArea, BorderLayout.CENTER);

        // Aggiunta dei paragrafi in cima alla finestra
        paragrafi = new JPanel(new GridLayout(3, 1));
        
        accesso = new JLabel("Accesso effettuato come : "+l.getUsername());
        accesso.setHorizontalAlignment(SwingConstants.CENTER);
        vuoto = new JLabel("");
        vuoto.setHorizontalAlignment(SwingConstants.CENTER);
        chooseLabel = new JLabel("Scegli che operazione effettuare");
        chooseLabel.setHorizontalAlignment(SwingConstants.CENTER);
        paragrafi.add(accesso);
        paragrafi.add(vuoto);
        paragrafi.add(chooseLabel);
        pannello.add(paragrafi, BorderLayout.NORTH);

        // Creazione dei bottoni 
       
        
        op2 = new JButton("Operazione 2");
        bottoni.add(op2);
        op3 = new JButton("Operazione 3");
        bottoni.add(op3);
        op4 = new JButton("Operazione 4");
        bottoni.add(op4);
        op5 = new JButton("Operazione 5");
        bottoni.add(op5);
        op6 = new JButton("Operazione 6");
        bottoni.add(op6);
        op7 = new JButton("Operazione 7");
        bottoni.add(op7);
        op8 = new JButton("Operazione 8");
        bottoni.add(op8);
        op9 = new JButton("Operazione 9");
        bottoni.add(op9);
        op10 = new JButton("Operazione 10");
        bottoni.add(op10);
        op11 = new JButton("Operazione 11");
        bottoni.add(op11);
        op12 = new JButton("Operazione 12");
        bottoni.add(op12);
        op13 = new JButton("Operazione 13");
        bottoni.add(op13);
        op14 = new JButton("Operazione 14");
        bottoni.add(op14);
        op15 = new JButton("Operazione 15");
        bottoni.add(op15);
        op16 = new JButton("Operazione 16");
        bottoni.add(op16);
        op17 = new JButton("Operazione 17");
        bottoni.add(op17);
        op18 = new JButton("Operazione 18");
        bottoni.add(op18);
        op19 = new JButton("Operazione 19");
        bottoni.add(op19);
        
        
        // Aggiunta del quindicesimo bottone "Esci"
        home = new JButton("Home");
        home.setPreferredSize(new Dimension(90, 20));
        bottoni.add(home);
        
        // Aggiunta del layout alla finestra e impostazione delle dimensioni
        add(pannello);
        setSize(1024, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        
        home.addActionListener(this);
        op2.addActionListener(this);
        op3.addActionListener(this);
        op4.addActionListener(this);
        op5.addActionListener(this);
        op6.addActionListener(this);
        op7.addActionListener(this);
        op8.addActionListener(this);
        op9.addActionListener(this);
        op10.addActionListener(this);
        op11.addActionListener(this);
        op12.addActionListener(this);
        op13.addActionListener(this);
        op14.addActionListener(this);
        op15.addActionListener(this);
        op16.addActionListener(this);
        op17.addActionListener(this);
        op18.addActionListener(this);
        op19.addActionListener(this);
        
        home.setToolTipText("Torna alla schermata di Login");
        op2.setToolTipText("OPERAZIONE 2 : selezionare uno specifico modello di auto");
        op3.setToolTipText("OPERAZIONE 3 : inserire una nuova categoria");
        op4.setToolTipText("OPERAZIONE 4 : inserire una nuova auto");
        op5.setToolTipText("OPERAZIONE 5 : selezionare tutti gli ordini del mese precedente");
        op6.setToolTipText("OPERAZIONE 6 : cancellazione storico ordini anno precedente");
        op7.setToolTipText("OPERAZIONE 7 : cancellazione degli account con num_acquisti=0");
        op8.setToolTipText("OPERAZIONE 8 : cancellazione di tutti i pezzi usati venduti");
        op9.setToolTipText("OPERAZIONE 9 : inserimento di pezzi nuovi");
        op10.setToolTipText("OPERAZIONE 10 : inserimento di pezzi usati");
        op11.setToolTipText("OPERAZIONE 11 : stampare tutti i dati degli account che hanno effettuato almeno un acquisto");
        op12.setToolTipText("OPERAZIONE 12 : eliminazione di un prodotto dal carrello dell’utente che effettua l’accesso");
        op13.setToolTipText("OPERAZIONE 13 : selezionare tutti gli ordini del mese precedente per quantificare il numero di prodotti venduti");
        op14.setToolTipText("OPERAZIONE 14 : stampare tutte le ricerche effettuate da un account");
        op15.setToolTipText("OPERAZIONE 15 : stampare tutti i pezzi di ricambio per una specifica auto");
        op16.setToolTipText("OPERAZIONE 16 : stampa del riepilogo dell'account connesso");
        op17.setToolTipText("OPERAZIONE 17 : salvataggio di un carrello nell, account");
        op18.setToolTipText("OPERAZIONE 18 : acquisto del carrello dell' account");
        op19.setToolTipText("OPERAZIONE 19 : rifornimento ricambi pari a zero");
    }
	
    
    @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == home) {
			Login l = new Login();
			l.setVisible(true);
			setVisible(false);
		}
		if(e.getSource() == op2) {
			try {
				new OperazioneDue();
				setVisible(false);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == op3) {
			new OperazioneTre();
			setVisible(false);
		}
		if(e.getSource() == op4) {
			new OperazioneQuattro();
			setVisible(false);
		}
		if(e.getSource() == op5) {
			try {
			Connection con=getConnection();
			Statement st = con.createStatement();
			st.execute("USE autoricambi");
			String Query="SELECT fattura.num_ordine, possiede.data_acquisto, fattura.nome_cliente FROM fattura JOIN possiede ON fattura.nome_cliente = possiede.username and fattura.num_ordine=possiede.num_ordine WHERE MONTH(data_acquisto) = month(date_sub(now(),interval 1 month)) and year(data_acquisto) = year(date_sub(now(), interval 1 month));";
			ResultSet rs= st.executeQuery(Query);
			resetTextArea();
			if(rs!=null) {
				stampaInTextArea("Query eseguita: ");
				stampaInTextArea(Query);
				stampaInTextArea(rs.getMetaData().getColumnName(1)+ " "+ rs.getMetaData().getColumnName(2)+ " "+rs.getMetaData().getColumnName(3));
				while(rs.next()) {
					stampaInTextArea("         "+ rs.getInt(1) + "              " + rs.getDate(2) + "          " + rs.getString(3));
					}
				}
			else {
				JOptionPane.showMessageDialog(null,"Non ci sono fatture relative al mese precedente" );
				//stampaInTextArea("Non ci sono fatture relative al mese precedente");
			}
			}catch (Exception ev) {
			// TODO: handle exception
				ev.printStackTrace();
			}
			
		}

		
		if(e.getSource() == op6) {
			new OperazioneSei();
			setVisible(false);
		}
		if(e.getSource() == op7) {
			new OperazioneSette();
			setVisible(false);
		}
		if(e.getSource() == op8) {
			new OperazioneOtto();
			setVisible(false);
		}
		if(e.getSource() == op9) {
			try {
				new OperazioneNove();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		}
		if(e.getSource() == op10) {
			try {
				new OperazioneDieci();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		}
		if(e.getSource() == op11) {
			try {
				Connection con=getConnection();
				Statement st= con.createStatement();
				st.execute("USE autoricambi");
				String query=("select * from account where num_acquisti>=1;");
				ResultSet rs= st.executeQuery(query);
				if(rs!=null) {
					
					stampaInTextArea("Query eseguita: ");
					stampaInTextArea(query);
					stampaInTextArea(rs.getMetaData().getColumnName(1)+ "\t"+ rs.getMetaData().getColumnName(2)+ "\t"+rs.getMetaData().getColumnName(3)+ "\t"+rs.getMetaData().getColumnName(4)+ "\t"+rs.getMetaData().getColumnName(5)+ "\t"+rs.getMetaData().getColumnName(6));
					while(rs.next()) {
						stampaInTextArea(rs.getString(1) + "\t "+ rs.getString(2) + "\t "+ rs.getString(3) + "\t " + rs.getString(4)+ "\t " + rs.getString(5) + "\t " + rs.getInt(6));
						}
					}
				else {
					stampaInTextArea("Non esistono Account con almeno un acquisto.");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if(e.getSource() == op12) {
			try {
				new OperazioneDodici();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false);
		}
		if(e.getSource() == op13) {
			try {
				Connection con = getConnection();
				Statement st = con.createStatement();
				String query = "SELECT COUNT(codice_articolo) AS ProdottiVenduti FROM fattura AS f INNER JOIN possiede as p ON f.num_ordine = p.num_ordine INNER JOIN compare as c ON f.num_ordine = c.num_ordine where month(p.data_acquisto) = month(date_sub(now(),interval 1 MONTH)) AND year(p.data_acquisto) = year(date_sub(now(),interval 1 YEAR))";
				st.execute("USE autoricambi");
				ResultSet rs = st.executeQuery(query);
				resetTextArea();
				if(rs != null) {
					while(rs.next()) {
						
						stampaInTextArea("Query eseguita");
						stampaInTextArea(query);
						stampaInTextArea("Numero di prodotti venduti: "+rs.getInt(1));
					}
				}else {
					JOptionPane.showMessageDialog(null, "Errore durante il conteggio");
				}
					
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource() == op14) {
			try {
				Connection con=getConnection();
				Statement st= con.createStatement();
				st.execute("USE autoricambi");
				String query="select codice, username, marca, modello from ricerca as r join auto as a on r.id_auto=a.id_auto where a.id_auto=r.id_auto and username ='"+l.getUsername()+"'";
				ResultSet rs=st.executeQuery(query);
				resetTextArea();
				if (rs!=null) {
					stampaInTextArea("Query eseguita: ");
					stampaInTextArea(query);
					stampaInTextArea(rs.getMetaData().getColumnName(1)+ "    "+ rs.getMetaData().getColumnName(2)+ "        "+rs.getMetaData().getColumnName(3)+ "         "+rs.getMetaData().getColumnName(4));
					while (rs.next()) {
						stampaInTextArea(rs.getInt(1) + "              " + rs.getString(2) + "          "+ rs.getString(3) + "          " + rs.getString(4));
						
					}
				}
				else {
					stampaInTextArea("Non ci sono ricerche relative aquesto account");
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		if(e.getSource() == op15) {
			try {
				new OperazioneQuindici();
				setVisible(false);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		if (e.getSource()==op16) {
            String nom = "",cognom = "",ind = "",user = "",pasw = "",tel = "";
            int num_acq = 0;
            resetTextArea();
            
			try {
				 Connection c = getConnection();
				 Statement st = c.createStatement();
				 st.execute("USE autoricambi");
				 String queryTel = "SELECT numero FROM telefono WHERE username='"+l.getUsername()+"'";
				 ResultSet rs2 = st.executeQuery(queryTel);
				 while(rs2.next()) {
					 tel = rs2.getString(1);
				 }
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
			try {
                Connection con= getConnection();
                Statement st= con.createStatement();
                st.execute("USE autoricambi");
                String query="SELECT * from account WHERE username = '"+l.getUsername()+"'";
                
                ResultSet rs=st.executeQuery(query);
                stampaInTextArea("query eseguita.");
                stampaInTextArea(query);
                
                while (rs.next()) {
                    nom = rs.getString(2);
                    cognom = rs.getString(3);
                    ind = rs.getString(4);
                    user = rs.getString(1);
                    pasw = rs.getString(5);
                    num_acq = rs.getInt(6);
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }
			stampaInTextArea("Nome: "+nom);
			stampaInTextArea("Cognome: "+cognom);
			stampaInTextArea("Indirizzo: "+ ind);
			stampaInTextArea("Telefono: "+ tel);
			stampaInTextArea("Username: "+ user);
			stampaInTextArea("Password: "+ pasw);
			stampaInTextArea("Acquisti effettuati: "+num_acq);
        }
		if(e.getSource() == op17) {
			try {
				new OperazioneDiciassette();
				setVisible(false);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getSource()==op18) {
            try {
                if(popolaTestoAcquisto()) {
                	new OperazioneDiciotto();
                	setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(null, "il carrello e' vuoto, impossibile procedere all'acquisto.");
                }
            } catch (ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
		if (e.getSource()==op19) {
			try {
				Connection c=getConnection();
				Statement st=c.createStatement();
				st.execute("USE autoricambi");
				String query="SELECT * FROM ricambio WHERE quantita=0";
				ResultSet rs=st.executeQuery(query);
				if (rs.next()) {
					new OperazioneDiciannove();
					setVisible(false);
				}
				else {
					stampaInTextArea("non ci sono ricambi con qunatita'=0 ");
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

    public int getCarrello() {
    	int codCar=0;
    	try {
    		Connection con = getConnection();
    		Statement st = con.createStatement();
    		st.execute("USE autoricambi");
    		String s1="select id_carrello from account where username='"+l.getUsername()+"'";
    		ResultSet rs=st.executeQuery(s1);
    		while (rs.next()) {
    			codCar=rs.getInt(1);
    		}
		}catch (Exception e) {
		// TODO: handle exception
			e.printStackTrace();
		}
    	return codCar;
    }

	public boolean popolaTestoAcquisto() {
		boolean flag=false;
		try {
			int idCar=0, idIns=0, codArt=0, qt=0;
			String desc="";
			Connection c = getConnection();
			Statement s = c.createStatement();
			s.execute("USE autoricambi");
			String q = "SELECT i.id_carrello,i.id_ins,i.codice_articolo,i.quantita, r.descrizione FROM account as a JOIN inserito as i ON a.id_carrello = i.id_carrello  JOIN ricambio as r ON r.codice_articolo=i.codice_articolo WHERE a.username = '"+l.getUsername()+"' AND a.id_carrello = i.id_carrello";
			ResultSet rs = s.executeQuery(q);
			if(rs.next()) {
				flag=true;
            }
		}
    	catch(Exception e3) {
    		e3.printStackTrace(); 
        }
        return flag;
    }
	
	
}
