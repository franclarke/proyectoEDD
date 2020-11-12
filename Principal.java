package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Logica.Logica;
import TDAColaCP.EmptyPriorityQueueException;
import TDADiccionario.Entry;
import TDADiccionario.InvalidKeyException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;

import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class Principal<E> extends JFrame {

	/**
	 * 
	 */

	private JPanel contentPanePRINCIPAL;
	protected Logica<E> logica = new Logica<E>();
	int xx, xy;
	private JTextField textMismoM;
	private JTextPane panelSaldo = new JTextPane();

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal<?> frame = new Principal<Object>();
					frame.setUndecorated(true);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPanePRINCIPAL = new JPanel();
		contentPanePRINCIPAL.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePRINCIPAL);
		contentPanePRINCIPAL.setLayout(null);


		JLabel X = new JLabel("");
		X.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		X.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/icons8-eliminar-32.png")));
		X.setBounds(958, 10, 32, 32);
		contentPanePRINCIPAL.add(X);

		JLabel barraSuperior = new JLabel("");
		barraSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xx = e.getX();
				xy = e.getY();
			}
		});
		barraSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Principal.this.setLocation(x - xx, y - xy);  
			}
		});

		barraSuperior.setBounds(0, 0, 1000, 50);
		contentPanePRINCIPAL.add(barraSuperior);

		JPanel panelIzq = new JPanel();
		panelIzq.setBorder(null);
		panelIzq.setBackground(new Color(0, 174, 239));
		panelIzq.setBounds(0, 0, 372, 600);
		contentPanePRINCIPAL.add(panelIzq);
		panelIzq.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/FF.png")));
		lblNewLabel_1.setBounds(38, 150, 290, 236);
		panelIzq.add(lblNewLabel_1);

		JPanel panelDer = new JPanel();
		panelDer.setBorder(UIManager.getBorder("TextPane.border"));
		panelDer.setBackground(new Color(0, 91, 170));
		panelDer.setBounds(373, 0, 627, 600);
		contentPanePRINCIPAL.add(panelDer);
		panelDer.setLayout(null);

		
		panelSaldo.setEditable(false);
		panelSaldo.setText(Integer.toString(logica.getSaldo()));
		panelSaldo.setFont(new Font("Microsoft YaHei", Font.BOLD | Font.ITALIC, 20));
		panelSaldo.setBounds(123, 503, 424, 37);
		panelDer.add(panelSaldo);


		JButton btnRealizarNuevaTransaccion = new JButton("Realizar nueva transaccion");
		btnRealizarNuevaTransaccion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaccion t = new Transaccion();
				t.main(null);
			}
		});
		btnRealizarNuevaTransaccion.setBounds(40, 48, 507, 126);
		btnRealizarNuevaTransaccion.setForeground(new Color(212, 212, 212));
		btnRealizarNuevaTransaccion.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 24));
		btnRealizarNuevaTransaccion.setBorder(null);
		btnRealizarNuevaTransaccion.setBackground(new Color(47, 46, 45));
		panelDer.add(btnRealizarNuevaTransaccion);

		JComboBox<String> consultaDesplegable = new JComboBox<String>();

		consultaDesplegable.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 18));
		consultaDesplegable.setToolTipText("");
		consultaDesplegable.setBounds(40, 184, 507, 37);

		consultaDesplegable.addItem( "Consultar...");
		consultaDesplegable.addItem( "Operacion mas reciente");
		consultaDesplegable.addItem( "Operacion mas historica");
		consultaDesplegable.addItem( "Operacion mas costosa");

		panelDer.add(consultaDesplegable);

		JLabel lblSaldo = new JLabel("SALDO:");
		lblSaldo.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 20));
		lblSaldo.setBounds(40, 503, 80, 37);
		panelDer.add(lblSaldo);

		JTextPane panelInfo = new JTextPane();
		panelInfo.setForeground(new Color(0, 0, 0));
		panelInfo.setEditable(false);
		panelInfo.setBackground(new Color(225, 225, 225));
		panelInfo.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 20));
		panelInfo.setBounds(40, 278, 507, 204);
		panelDer.add(panelInfo);

		consultaDesplegable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch(consultaDesplegable.getSelectedIndex())
				{
				case 1:
				{
					if(logica.getUltimaT().getKey()==null)
						panelInfo.setText("No se encontraron transacciones");
					else
						panelInfo.setText("Ultima operacion:\r\nMonto: $" + Integer.toString(logica.getMonto(logica.getUltimaT())) + "\r\nDescripcion: " + logica.getDescripcion(logica.getUltimaT()));
					break;
				}	
				case 2:
				{
					if(logica.getHistoricaT().getKey()!=null)
						panelInfo.setText("Operacion mas historica:\r\nMonto: $" + Integer.toString(logica.getMonto(logica.getHistoricaT())) + "\r\nDescripcion: " + logica.getDescripcion(logica.getHistoricaT()));
					else
						panelInfo.setText("No se encontraron transacciones");
					break;
				}
				case 3:
				{	
					if(logica.sizeCCP()!=0)
						try {
							panelInfo.setText("Operacion mas costosa:\r\nMonto: $" + Integer.toString(logica.getMonto(logica.getCostosaT())) + "\r\nDescripcion: " + logica.getDescripcion(logica.getCostosaT()));
						} catch (EmptyPriorityQueueException e1) {
							e1.printStackTrace();
						}
					else
						panelInfo.setText("No se encontraron transacciones");
					break;
				}
				}
			}
		});

		JLabel lblBuscar = new JLabel("Buscar transacciones por:    $");
		lblBuscar.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 20));
		lblBuscar.setBounds(40, 231, 283, 37);
		panelDer.add(lblBuscar);

		textMismoM = new JTextField();
		textMismoM.setBounds(310, 231, 185, 37);
		panelDer.add(textMismoM);
		textMismoM.setColumns(10);

		JButton btnBuscar = new JButton("");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iterable<Entry<Integer, String>> it = null;
				Iterator<Entry<Integer, String>> it2 = null;
				try {
					if(logica.sizeBDD()!=0) {
						it = logica.buscarBDD(Integer.parseInt(textMismoM.getText()));
						it2= it.iterator();

						if(textMismoM.getText().length()==0)
							JOptionPane.showMessageDialog(null, "Ingrese un monto valido", "Error", JOptionPane.ERROR_MESSAGE);
						else if(Integer.parseInt(textMismoM.getText())==0)
							JOptionPane.showMessageDialog(null, "Monto 0 no es una transaccion valida", "Error", JOptionPane.ERROR_MESSAGE);
						else if(it!=null && !it.iterator().hasNext())
							JOptionPane.showMessageDialog(null, "No hay transacciones registradas con ese monto", "Error", JOptionPane.ERROR_MESSAGE);
						else 
							if(it2.hasNext()) {
								panelInfo.setText("");
								while(it2.hasNext())
									panelInfo.setText(panelInfo.getText()  + it2.next().getValue() + "\r\n");
							}
					}else
						JOptionPane.showMessageDialog(null, "No hay transacciones registradas con ese monto", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException | InvalidKeyException e1) {
					e1.printStackTrace();
				}
			}
		});


		btnBuscar.setBounds(505, 231, 42, 37);
		panelDer.add(btnBuscar);

		textMismoM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if (!(Character.isDigit(vchar) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE) || (vchar == '-'))){
					e.consume();
				}
			}
		});



	}

	private class Transaccion extends JFrame {

		private static final long serialVersionUID = -8106862830322504860L;
		private JPanel contentPaneT;
		private JPanel panelT;
		private JTextField text_montoT;
		private JLabel lbl_montoT;
		private JButton btn_confirmT;
		int xx,xy;
		private JLabel lbl_descT;

		public void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Transaccion frame = new Transaccion();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the frame.
		 */
		public Transaccion() {

			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(500, 300, 400, 363);
			contentPaneT = new JPanel();
			contentPaneT.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPaneT);
			contentPaneT.setLayout(null);

			JLabel barraSuperior = new JLabel("");
			barraSuperior.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					xx = e.getX();
					xy = e.getY();
				}
			});
			barraSuperior.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					int x = e.getXOnScreen();
					int y = e.getYOnScreen();
					Transaccion.this.setLocation(x - xx, y - xy);  
				}
			});

			JLabel X = new JLabel("");
			X.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
				}
			});
			X.setIcon(new ImageIcon(Transaccion.class.getResource("/Imagenes/icons8-eliminar-32.png")));
			X.setBounds(358, 10, 32, 32);
			contentPaneT.add(X);

			barraSuperior.setBounds(0, 0, 400, 50);
			contentPaneT.add(barraSuperior);

			panelT = new JPanel();
			panelT.setBackground(new Color(51, 51, 51));
			panelT.setBounds(0, 0, 400, 363);
			contentPaneT.add(panelT);
			panelT.setLayout(null);

			text_montoT = new JTextField();
			text_montoT.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char vchar = e.getKeyChar();
					if (!(Character.isDigit(vchar) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE) || (vchar == '-'))) {
						e.consume();
					}
				}
			});
			text_montoT.setColumns(10);
			text_montoT.setBounds(38, 91, 325, 33);
			panelT.add(text_montoT);

			lbl_montoT = new JLabel("Ingrese el monto de su transaccion");
			lbl_montoT.setForeground(new Color(240, 248, 255));
			lbl_montoT.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 20));
			lbl_montoT.setBounds(38, 48, 339, 33);
			panelT.add(lbl_montoT);

			JTextArea text_desc = new JTextArea();
			text_desc.setBounds(38, 177, 325, 84);
			panelT.add(text_desc);

			btn_confirmT = new JButton("Confirmar");
			btn_confirmT.setBorder(null);
			btn_confirmT.setBackground(new Color(196, 90, 82));
			btn_confirmT.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(text_montoT.getText().length()==0 || Integer.parseInt(text_montoT.getText())==0)
						JOptionPane.showMessageDialog(null, "Su monto es insuficiente para realizar una transaccion", "Error", JOptionPane.ERROR_MESSAGE);
					else if(logica.getSaldo()+Integer.parseInt(text_montoT.getText() )<0) 
						JOptionPane.showMessageDialog(null, "No dispone de suficiente saldo para realizar la transaccion", "Error", JOptionPane.ERROR_MESSAGE);
					else {
						logica.transaccion(Integer.parseInt(text_montoT.getText()));						//Calcula el saldo
						panelSaldo.setText(" $ " + Integer.toString(logica.getSaldo()));					//Actualiza el saldo

						logica.actualizar(Integer.parseInt(text_montoT.getText()),text_desc.getText());		//Actualiza el registro de transacciones

						setVisible(false);
					}
				}
			});
			btn_confirmT.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 16));
			btn_confirmT.setBounds(142, 271, 121, 45);
			panelT.add(btn_confirmT);

			lbl_descT = new JLabel("Descripcion");
			lbl_descT.setForeground(new Color(240, 248, 255));
			lbl_descT.setFont(new Font("Microsoft YaHei Light", Font.BOLD, 20));
			lbl_descT.setBounds(38, 134, 339, 33);
			panelT.add(lbl_descT);


		}
	}
}
