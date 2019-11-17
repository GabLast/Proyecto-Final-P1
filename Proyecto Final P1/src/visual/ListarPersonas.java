package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Empresa;
import logica.Jurado;
import logica.Participante;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarPersonas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	String id = "";

	
	JButton btnLista;
	JButton btnAgregar;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPersonas dialog = new ListarPersonas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPersonas() {
		setTitle("Lista del personal");
		setBounds(100, 100, 583, 300);
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
			panel.setBorder(new TitledBorder(null, "Listado de las personas registradas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"Cédula", "Nombre", "Función en el evento","Teléfono", "Dirección", "Sexo", "Grado Académico"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadPersonas();
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								id = String.valueOf(table.getValueAt(index, 0));
								btnLista.setEnabled(true);
								btnAgregar.setEnabled(true);
								
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
				btnAgregar = new JButton("Agregar un trabajo");
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Participante duenio = (Participante) Empresa.getInstance().searchPersonabyCedula(id);
						RegistrarTrabajo window = new RegistrarTrabajo(duenio);
						window.setModal(true);
						window.setVisible(true);
						
					}
				});
				btnAgregar.setActionCommand("OK");
				btnAgregar.setEnabled(false);
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				btnLista = new JButton("Listar trabajos");
				btnLista.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Participante duenio = (Participante) Empresa.getInstance().searchPersonabyCedula(id);
						ListarTrabajos window = new ListarTrabajos(duenio);
						window.setModal(true);
						window.setVisible(true);
						
					}
				});
				btnLista.setActionCommand("OK");
				btnLista.setEnabled(false);
				buttonPane.add(btnLista);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
	
	public static void loadPersonas() 
	{
		model.setRowCount(0);
		Empresa miEmpresa = Empresa.getInstance();
		//{"Cédula", "Nombre", "Teléfono", "Dirección", "Sexo", "Grado Académico"};
		row = new Object[model.getColumnCount()];
		
		for (int i = 0; i < miEmpresa.getPersonasRegistradas().size(); i++) 
		{
			row[0] = miEmpresa.getPersonasRegistradas().get(i).getCedula();
			row[1] = miEmpresa.getPersonasRegistradas().get(i).getNombre();
			
			if(miEmpresa.getPersonasRegistradas().get(i) instanceof Participante)
			{
				row[2] = "Participante";
			}
			else if(miEmpresa.getPersonasRegistradas().get(i) instanceof Jurado)
			{
				row[2] = "Juez";
			}
			row[3] = miEmpresa.getPersonasRegistradas().get(i).getTelefono();
			row[4] = miEmpresa.getPersonasRegistradas().get(i).getDireccion();
			row[5] = miEmpresa.getPersonasRegistradas().get(i).getSexo();
			row[6] = miEmpresa.getPersonasRegistradas().get(i).getGradoAcademico();
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}

}
