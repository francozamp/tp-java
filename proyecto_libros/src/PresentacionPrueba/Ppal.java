package PresentacionPrueba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Entidades.Usuario;
import Negocio.NegocioUsuario;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ppal {

	private JFrame frame;
	private JTextField txtUsuario;
	private JPasswordField txtPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Ppal window = new Ppal();
					//window.frame.setVisible(true);
                                    frmUsuarios window = new frmUsuarios();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ppal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 470);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Email");
		lblUsuario.setBounds(10, 11, 68, 14);
		frame.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(10, 36, 68, 14);
		frame.getContentPane().add(lblContrasea);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(88, 8, 182, 20);
		frame.getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(88, 33, 182, 20);
		frame.getContentPane().add(txtPass);
		
		JLabel lblnombre = new JLabel("[nombre]");
		lblnombre.setBounds(280, 11, 103, 14);
		frame.getContentPane().add(lblnombre);
		
		JLabel lblapellido = new JLabel("[apellido]");
		lblapellido.setBounds(280, 36, 103, 14);
		frame.getContentPane().add(lblapellido);
		
		JButton btnNuevaCategoria = new JButton("Nueva categoria");
		btnNuevaCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				frmABMCategorias frmABMCategorias = new frmABMCategorias();
				frame.setEnabled(false);
				frmABMCategorias.setVisible(true);
			}
		});
		btnNuevaCategoria.setEnabled(false);
		btnNuevaCategoria.setBounds(393, 7, 131, 23);
		frame.getContentPane().add(btnNuevaCategoria);
		
		JButton btnNuevoLibro = new JButton("Nuevo libro");
		btnNuevoLibro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmLibros frmLibros = new frmLibros();
				frame.setEnabled(false);
				frmLibros.setVisible(true);
			}
		});
		btnNuevoLibro.setEnabled(false);
		btnNuevoLibro.setBounds(393, 32, 131, 23);
		frame.getContentPane().add(btnNuevoLibro);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = null;
				
				NegocioUsuario nUsuario=new NegocioUsuario();
				usuario=nUsuario.hacerLogin(txtUsuario.getText(), txtPass.getText());
				
				if (usuario!=null){
					
					lblnombre.setText(usuario.getNombre());
					lblapellido.setText(usuario.getApellido());
					
					if(usuario.getTipoUsuario().getNombre().equals("Administrador")){
						
						btnNuevaCategoria.setEnabled(true);
						btnNuevoLibro.setEnabled(true);
					}	
				}
				else{
					JOptionPane.showMessageDialog(null, "Usuario o contrase�a incorrectos");
				}
				
			}
		});
		btnLogin.setBounds(88, 64, 86, 23);
		frame.getContentPane().add(btnLogin);
	}
}
