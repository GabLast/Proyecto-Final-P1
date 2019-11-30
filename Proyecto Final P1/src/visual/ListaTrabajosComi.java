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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import logica.Comision;
import logica.Jurado;
import logica.Participante;
import logica.Trabajo;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListaTrabajosComi extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JButton btnEliminar;
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	String id = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Comision comi = null;
			ListaTrabajosComi dialog = new ListaTrabajosComi(comi);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaTrabajosComi(Comision comi) {
		setTitle("Lista de trabajos");
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
			panel.setBorder(new TitledBorder(null, "Listado de trabajos de la comisi\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"Tema", "Dueño", "Área", "Descripción"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadTrabajos(comi);
					
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
					public void actionPerformed(ActionEvent e) {
						if(id != "")
						{
							Trabajo job = comi.buscarTrabajoByName(id);
							
							int option = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea eliminar el trabajo [" 
									   + job.getTema()+"] de la comisión?", "Notificación",JOptionPane.WARNING_MESSAGE);
							
							if(option == JOptionPane.OK_OPTION && job != null)
							{
								comi.deleteTrabajo(job);
								JOptionPane.showMessageDialog(null, "Trabajo removido satisfactoriamente"
										, "Notificación", JOptionPane.INFORMATION_MESSAGE);
								ListaTrabajosComi.loadTrabajos(comi);
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

	public static void loadTrabajos(Comision comi) 
	{
		model.setRowCount(0);
		//{"ID", "Nombre", "Área", "Descripción"};
		row = new Object[model.getColumnCount()];
		System.out.println("ListaTrabajosComi Trabajos: " + comi.getTrabajosParticipantes().size() 
				+"\t\tJueces: " + comi.getMiJurado().size());
//		for (int i = 0; i < comi.getTrabajosParticipantes().size(); i++) 
//		{
//			
//			row[0] = comi.getTrabajosParticipantes().get(i).getTema();
//			row[1] = comi.getTrabajosParticipantes().get(i).getDuenio().getNombre();
//			row[2] = comi.getTrabajosParticipantes().get(i).getArea();
//			row[3] = comi.getTrabajosParticipantes().get(i).getDescripcion();
//			
//			model.addRow(row);
//		}
		
		
		for(Trabajo job : comi.getTrabajosParticipantes())
		{
			row[0] = job.getTema();
			row[1] = job.getDuenio().getNombre();
			row[2] = job.getArea();
			row[3] = job.getDescripcion();
			model.addRow(row);
		}
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
