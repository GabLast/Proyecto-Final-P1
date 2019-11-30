package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Empresa;
import logica.Evento;
import logica.Jurado;
import logica.Participante;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarMiembrosComi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	JButton btnEliminar;
	String id = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Comision comi = null;
			ListarMiembrosComi dialog = new ListarMiembrosComi(comi);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarMiembrosComi(Comision comi) {
		setTitle("Lista de miembros");
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .70;
		dim.height *= .80;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado de integrantes de la comisi\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"Cédula", "Nombre", "Función en la comisión","Teléfono", "Dirección", "Sexo", "Grado Académico", "Área de estudio"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadPersonas(comi);
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								id = String.valueOf(table.getValueAt(index, 0));
								btnEliminar.setEnabled(true);
								
								
							}
						}
					});
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
				btnEliminar = new JButton("Eliminar");
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(id != "")
						{
							Jurado juez = comi.buscarJuezBYID(id);
							
							int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea sacar a [" 
									   + juez.getNombre()+"] de la comisión?", "Notificación",JOptionPane.WARNING_MESSAGE);
							
							if(option == JOptionPane.OK_OPTION && juez != null)
							{
								comi.deleteJuez(juez);
								JOptionPane.showMessageDialog(null, "Juez removido satisfactoriamente"
										, "Notificación", JOptionPane.INFORMATION_MESSAGE);
								ListarMiembrosComi.loadPersonas(comi);
							}
						}
					}
				});
				btnEliminar.setActionCommand("OK");
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static void loadPersonas(Comision comi) 
	{
		model.setRowCount(0);
		
		//{"Cédula", "Nombre", "Función en la comisión","Teléfono", "Dirección", "Sexo", "Grado Académico", "Área de estudio"};
		row = new Object[model.getColumnCount()];
		
		System.out.println("ListaMiembrosComi Jueces: " + comi.getMiJurado().size());
		
		for (int i = 0; i < comi.getMiJurado().size(); i++) 
		{
			row[0] = comi.getMiJurado().get(i).getCedula();
			row[1] = comi.getMiJurado().get(i).getNombre();
			
			if(comi.getMiJurado().get(i).isPresidente())
			{
				row[2] = "Presidente";
			}
			else
			{
				row[2] = "Miembro";
			}
			row[3] = comi.getMiJurado().get(i).getTelefono();
			row[4] = comi.getMiJurado().get(i).getDireccion();
			row[5] = comi.getMiJurado().get(i).getSexo();
			row[6] = comi.getMiJurado().get(i).getGradoAcademico();
			row[7] = comi.getMiJurado().get(i).getAreaEstudio();
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}

}
