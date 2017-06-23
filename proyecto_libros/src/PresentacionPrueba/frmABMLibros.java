package PresentacionPrueba;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import Entidades.Categoria;
import Entidades.Libro;
import Negocio.NegocioCategoria;
import Negocio.NegocioLibro;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;
import java.awt.event.ActionEvent;

public class frmABMLibros extends JFrame {

	private JPanel contentPane;
	private JTextField txtIsbn;
	private JTextField txtTitulo;
	private JTextField textAutor;
	private JTextField txtEditorial;
	private JTextField txtEdicion;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmABMLibros frame = new frmABMLibros();
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
	public frmABMLibros() {
		List<Categoria> categoriasTodas = new NegocioCategoria().getCategorias();
		List<Categoria> categoriasSeleccionadas = java.util.Collections.emptyList();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 494, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 11, 46, 14);
		contentPane.add(lblIsbn);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo:");
		lblTtulo.setBounds(10, 36, 46, 14);
		contentPane.add(lblTtulo);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 61, 46, 14);
		contentPane.add(lblAutor);
		
		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 86, 46, 14);
		contentPane.add(lblEditorial);
		
		JLabel lblEdicin = new JLabel("Edici\u00F3n:");
		lblEdicin.setBounds(10, 111, 46, 14);
		contentPane.add(lblEdicin);
		
		JLabel lblDescripcin = new JLabel("Descripci\u00F3n:");
		lblDescripcin.setBounds(10, 136, 76, 14);
		contentPane.add(lblDescripcin);
		
		txtIsbn = new JTextField();
		txtIsbn.setBounds(66, 8, 402, 20);
		contentPane.add(txtIsbn);
		txtIsbn.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(66, 33, 402, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		
		textAutor = new JTextField();
		textAutor.setBounds(66, 58, 402, 20);
		contentPane.add(textAutor);
		textAutor.setColumns(10);
		
		txtEditorial = new JTextField();
		txtEditorial.setBounds(66, 83, 402, 20);
		contentPane.add(txtEditorial);
		txtEditorial.setColumns(10);
		
		txtEdicion = new JTextField();
		txtEdicion.setBounds(66, 108, 402, 20);
		contentPane.add(txtEdicion);
		txtEdicion.setColumns(10);
		
		JTextPane txtDescripcion = new JTextPane();
		txtDescripcion.setBounds(76, 136, 392, 152);
		contentPane.add(txtDescripcion);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(10, 298, 76, 14);
		contentPane.add(lblCategorias);
		
		JList<?> listTodas = new JList<Object>(categoriasTodas.toArray());
		listTodas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listTodas.setCellRenderer(new DefaultListCellRenderer(){
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus){
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Categoria){
					((JLabel)renderer).setText(((Categoria)value).getNombre());
				}
				return renderer;
			}
		});
		listTodas.setBounds(10, 324, 125, 126);
		contentPane.add(listTodas);
		
		JList<?> listSeleccionadas = new JList<Object>(categoriasSeleccionadas.toArray());
		listSeleccionadas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listSeleccionadas.setCellRenderer(new DefaultListCellRenderer(){
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus){
				Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (renderer instanceof JLabel && value instanceof Categoria){
					((JLabel)renderer).setText(((Categoria)value).getNombre());
				}
				return renderer;
			}
		});
		listSeleccionadas.setBounds(195, 324, 125, 126);
		contentPane.add(listSeleccionadas);
		
		JButton btnAgregar = new JButton(">");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<Categoria> dlmt = (DefaultListModel<Categoria>) listTodas.getModel();
				DefaultListModel<Categoria> dlms = (DefaultListModel<Categoria>) listSeleccionadas.getModel();
				if (listTodas.getSelectedIndices().length > 0){
					int[] selectedIdices = listTodas.getSelectedIndices();
					for (int i = 0; i < selectedIdices.length; i++) {
						dlms.addElement(dlmt.elementAt(i));
						dlmt.remove(selectedIdices[i]);	
					}
				}
			}
		});
		btnAgregar.setBounds(145, 341, 41, 23);
		contentPane.add(btnAgregar);
		
		JButton btnSacar = new JButton("<");
		btnSacar.setBounds(145, 375, 41, 23);
		contentPane.add(btnSacar);
		
		JCheckBox chckbxDisponible = new JCheckBox("Disponible");
		chckbxDisponible.setBounds(395, 321, 73, 23);
		contentPane.add(chckbxDisponible);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(379, 427, 89, 23);
		contentPane.add(btnCancelar);
		
		/*JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Libro libro = new Libro(txtIsbn.getText(),txtTitulo.getText(),textAutor.getText(),txtEditorial.getText(),txtEdicion.getText(),txtDescripcion.getText(),chckbxDisponible.isSelected(),listTodas.getSelectedValuesList());
				NegocioLibro negocioLibro = new NegocioLibro();
				Libro guardado = null;
				guardado = negocioLibro.guardarLibro(libro);
				
				if (guardado != null){
					JOptionPane.showMessageDialog(null, "El libro " + libro.getISBN() + " fue guardado correctamente!", "Libro guardado", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "El libro no pudo ser guardado!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAceptar.setBounds(379, 393, 89, 23);
		contentPane.add(btnAceptar);*/
	}
}
