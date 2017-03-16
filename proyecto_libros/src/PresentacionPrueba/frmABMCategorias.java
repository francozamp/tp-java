package PresentacionPrueba;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import Entidades.Categoria;
import Negocio.NegocioCategoria;

public class frmABMCategorias {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmABMCategorias window = new frmABMCategorias();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmABMCategorias() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 471);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevoIngreso frm=new frmNuevoIngreso();
				frm.setVisible(true);
			}
		});
		
		List<Categoria> categorias = new NegocioCategoria().getCategorias();
		JList<?> listCategorias = new JList<Object>(categorias.toArray());
		listCategorias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listCategorias.setCellRenderer(new DefaultListCellRenderer(){
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus){
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Categoria){
					((JLabel)renderer).setText(((Categoria)value).getNombre());
				}
				return renderer;
			}
		});
		listCategorias.setBounds(10, 7, 203, 414);
		frame.getContentPane().add(listCategorias);
		btnAgregar.setBounds(223, 7, 201, 23);
		frame.getContentPane().add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(223, 75, 201, 23);
		frame.getContentPane().add(btnBorrar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmNuevoIngreso nuevoIngreso=new frmNuevoIngreso();
				nuevoIngreso.setCategoria(listCategorias.getSelectedValue());
				nuevoIngreso.setVisible(true);
			}
		});
		btnEditar.setBounds(223, 41, 201, 23);
		frame.getContentPane().add(btnEditar);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(223, 165, 58, 14);
		frame.getContentPane().add(lblDescripcion);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(223, 109, 58, 14);
		frame.getContentPane().add(lblCategoria);
		
		textField = new JTextField();
		textField.setBounds(223, 134, 201, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(223, 190, 201, 231);
		frame.getContentPane().add(textPane);
	}
	
	public void setVisible(boolean b){
		frame.setVisible(b);
	}
}
