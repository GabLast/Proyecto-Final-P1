package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Empresa;
import logica.Recurso;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Font;

public class ListaRecursos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	private static JTable table;
	private String Tipo = "";
	private JButton btnModificar;
	JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaRecursos dialog = new ListaRecursos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaRecursos() {
		setTitle("Lista de Recursos");
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .70;
		dim.height *= .80;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		super.setSize(dim.width, dim.height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado de recursos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					model = new DefaultTableModel() {
						@Override
					    public boolean isCellEditable(int row, int column) {
					       //all cells false
					       return false;
					    }
					};
					
					
					String[] header = {"Nombre", "Cantidad disponible"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					table.setFont(new Font("Roboto", Font.PLAIN, 12));
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								Tipo = String.valueOf(table.getValueAt(index, 0));
								btnModificar.setEnabled(true);
								btnEliminar.setEnabled(true);
						}
					}
					});
					
					loadRecursos();					
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.setFont(new Font("Roboto", Font.PLAIN, 12));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				{
					btnModificar = new JButton("Modificar");
					btnModificar.setFont(new Font("Roboto", Font.PLAIN, 12));
					btnModificar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if (Tipo != "") {
							Recurso resource = Empresa.getInstance().searchRecursoByTipo(Tipo);
							ModifcarRecursos v1= new ModifcarRecursos(resource);
							v1.setModal(true);
							v1.setVisible(true);
						}
						}
					});
					{
						btnEliminar = new JButton("Eliminar");
						btnEliminar.setFont(new Font("Roboto", Font.PLAIN, 12));
						btnEliminar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(Tipo != "")
								{
									Recurso losrecursos = Empresa.getInstance().searchRecursoByTipo(Tipo);
									
									int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el recurso: " 
											   + losrecursos.getTipo(), "Notificación",JOptionPane.WARNING_MESSAGE);
									
									if(option == JOptionPane.OK_OPTION && losrecursos != null)
									{
										
										Empresa.getInstance().deleteRecurso(losrecursos);
										JOptionPane.showMessageDialog(null, "Recurso eliminado satisfactoriamente"
												, "Notificación", JOptionPane.INFORMATION_MESSAGE);
										ListaRecursos.loadRecursos();
									}
								}
							}
						});
						buttonPane.add(btnEliminar);
						btnEliminar.setEnabled(false);
					}
					btnModificar.setEnabled(false);
					buttonPane.add(btnModificar);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void loadRecursos() 
	{
		model.setRowCount(0);
		Empresa miEmpresa = Empresa.getInstance();
		//{"Nombre", "Cantidad"
		row = new Object[model.getColumnCount()];
		
		for (int i = 0; i < miEmpresa.getRecursos().size(); i++) 
		{
			row[0] = miEmpresa.getRecursos().get(i).getTipo();
			row[1] = miEmpresa.getRecursos().get(i).getCantidad();
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
