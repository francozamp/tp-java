package PresentacionPrueba;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entidades.Categoria;
import Negocio.NegocioCategoria;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmNuevoIngreso extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextPane txtDescripcion;
	
	private Categoria categoria;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmNuevoIngreso frame = new frmNuevoIngreso();
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
	public frmNuevoIngreso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 46, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(66, 8, 318, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 36, 58, 14);
		contentPane.add(lblDescripcin);
		
		JTextPane txtDescripcion = new JTextPane();
		txtDescripcion.setBounds(10, 61, 374, 296);
		contentPane.add(txtDescripcion);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				NegocioCategoria nCategoria = new NegocioCategoria();
				Categoria resul = null;
				if (categoria==null){
					resul = nCategoria.guardarCategoria(txtNombre.getText(),txtDescripcion.getText());
				}
				else
				{
					resul = nCategoria.guardarCategoria(categoria.getId(), txtNombre.getText(),txtDescripcion.getText());
				}
				
				if (resul!=null){
					JOptionPane.showMessageDialog(null, "Categoria guardada"+System.lineSeparator()+"Id: "+resul.getId()+System.lineSeparator()+"Nombre:"+resul.getNombre()+System.lineSeparator()+"Descripción: "+resul.getDescripcion());
				}
				else{
					JOptionPane.showMessageDialog(null, "Hubo un problema");
				}
				
			}
		});
		btnGuardar.setBounds(295, 368, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(196, 368, 89, 23);
		contentPane.add(btnCancelar);
	}

	public void setCategoria(Object categoria) {
		this.categoria=(Categoria)categoria;
		
		txtNombre.setText(this.categoria.getNombre());
	}

}
