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

import logica.Empresa;
import logica.Evento;
import logica.Jurado;
import logica.Participante;
import logica.Persona;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class ListPartiEvento extends JDialog {

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
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			Evento evento = null;
			ListPartiEvento dialog = new ListPartiEvento(evento);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListPartiEvento(Evento mievento) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListPartiEvento.class.getResource("/imagen/listPerson.png")));
		setTitle("Lista de Participantes");
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .70;
		dim.height *= .70;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Listado de Participantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"Cédula", "Nombre", "Teléfono", "Dirección", "Sexo", "Grado Académico"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadPersonas(mievento);
					
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
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				btnEliminar.setEnabled(false);
				getRootPane().setDefaultButton(btnEliminar);
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

	public static void loadPersonas(Evento evento) 
	{
		model.setRowCount(0);

//		{"Cédula", "Nombre", "Teléfono", "Dirección", "Sexo", "Grado Académico"};
		row = new Object[model.getColumnCount()];
		
		for (int i = 0; i < evento.getParticipantes().size(); i++) 
		{
			row[0] = evento.getParticipantes().get(i).getCedula();
			row[1] = evento.getParticipantes().get(i).getNombre();
			row[2] = evento.getParticipantes().get(i).getTelefono();
			row[3] = evento.getParticipantes().get(i).getDireccion();
			row[4] = evento.getParticipantes().get(i).getSexo();
			row[5] = evento.getParticipantes().get(i).getGradoAcademico();
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
