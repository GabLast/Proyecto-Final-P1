package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Empresa;
import logica.Participante;
import logica.Persona;
import logica.Trabajo;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class ListarTrabajos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	String id = "";

	JButton btnModificar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Participante duenio = null;
			ListarTrabajos dialog = new ListarTrabajos(duenio);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarTrabajos(Participante duenio) {
		setTitle("Listado de trabajos de:" + duenio.getNombre());
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .70;
		dim.height *= .92;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado de trabajos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"ID", "Nombre", "Área", "Descripción"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadTrabajos(duenio);
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								id = String.valueOf(table.getValueAt(index, 0));
								btnModificar.setEnabled(true);
								
								
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
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(id!="")
						{
							
							
							
							Trabajo work= duenio.buscarTrabajoByName(id);
							
							//System.out.println(clienteModi.getCedula());
							int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea modificar la cuenta: " 
									   + work.getId(),"Información",JOptionPane.WARNING_MESSAGE);
							
							if(option == JOptionPane.OK_OPTION && work != null)
							{
								//new ModificarTrabajo(work).setVisible(true);
								//new Facturar(true, clienteModi);
								ModificarTrabajo v1 = new ModificarTrabajo(work);
								v1.setModal(true);
								v1.setVisible(true);
							}
					}
					}
					});
				
				btnModificar.setActionCommand("OK");
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}


	public static void loadTrabajos(Participante duenio) 
	{
		model.setRowCount(0);
		//{"ID", "Nombre", "Área", "Descripción"};
		row = new Object[model.getColumnCount()];
		
		for (int i = 0; i < duenio.getMisTrabajos().size(); i++) 
		{
			row[0] = duenio.getMisTrabajos().get(i).getId();
			row[1] = duenio.getMisTrabajos().get(i).getArea();
			row[2] = duenio.getMisTrabajos().get(i).getDescripcion();
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
