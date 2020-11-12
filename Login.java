package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Logica;
import TDACola.EmptyQueueException;
import TDAPila.EmptyStackException;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login<E> extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 554746015142815461L;

	private Logica<E> logica = new Logica<E>();
	int xx, xy;
	private JPanel contentPaneLOGIN;
	private JPasswordField passwordFieldLOGIN;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login<?> frame = new Login<Object>();
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
	public Login() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPaneLOGIN = new JPanel();
		contentPaneLOGIN.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneLOGIN);
		contentPaneLOGIN.setLayout(null);

		JLabel X = new JLabel("");
		X.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		X.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/icons8-eliminar-32.png")));
		X.setBounds(958, 10, 32, 32);
		contentPaneLOGIN.add(X);

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
				Login.this.setLocation(x - xx, y - xy);  
			}
		});

		barraSuperior.setBounds(0, 0, 1000, 50);
		contentPaneLOGIN.add(barraSuperior);

		JPanel panelIzq = new JPanel();
		panelIzq.setBorder(null);
		panelIzq.setBackground(new Color(0, 174, 239));
		panelIzq.setBounds(0, 0, 372, 600);
		contentPaneLOGIN.add(panelIzq);
		panelIzq.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/Imagenes/FF.png")));
		lblNewLabel_1.setBounds(38, 150, 290, 236);
		panelIzq.add(lblNewLabel_1);

		JPanel panelDer = new JPanel();
		panelDer.setBorder(null);
		panelDer.setBackground(new Color(0, 91, 170));
		panelDer.setBounds(373, 0, 627, 600);
		contentPaneLOGIN.add(panelDer);
		panelDer.setLayout(null);

		JLabel codigoDeAcceso = new JLabel("CODIGO DE ACCESO");
		codigoDeAcceso.setHorizontalAlignment(SwingConstants.CENTER);
		codigoDeAcceso.setBounds(172, 200, 280, 40);
		panelDer.add(codigoDeAcceso);
		codigoDeAcceso.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 24));

		passwordFieldLOGIN = new JPasswordField();
		passwordFieldLOGIN.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char vchar = e.getKeyChar();
				if (!(Character.isAlphabetic(vchar) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		passwordFieldLOGIN.setBounds(172, 250, 280, 40);
		panelDer.add(passwordFieldLOGIN);
		passwordFieldLOGIN.setToolTipText("Codigo de acceso");
		passwordFieldLOGIN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordFieldLOGIN.setBackground(new Color(213, 213, 213));

		JButton botonIngresar = new JButton("Login");
		botonIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(logica.validarCodigoDeAcceso(passwordFieldLOGIN.getPassword())==true) {
						setVisible(false);
						Principal<E> v = new Principal<E>();
						v.main(null);
					}else 
						JOptionPane.showMessageDialog(null, "Su codigo de acceso es erroneo", "Error", JOptionPane.ERROR_MESSAGE);
				}catch(EmptyStackException | EmptyQueueException e1) {
					JOptionPane.showMessageDialog(null, "Su codigo de acceso es erroneo", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		botonIngresar.setBounds(172, 300, 280, 40);
		botonIngresar.setForeground(Color.WHITE);
		botonIngresar.setFont(new Font("Microsoft YaHei Light", Font.PLAIN, 20));
		botonIngresar.setBorder(null);
		botonIngresar.setBackground(new Color(51, 51, 51));
		panelDer.add(botonIngresar);
	}

}
