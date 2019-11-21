package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Empresa;
import logica.Evento;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ListaEventos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	String id = "";
	private JButton btnCrear;
	private JButton btnGestionarComisiones;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaEventos dialog = new ListaEventos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaEventos() {
		setBounds(100, 100, 648, 407);
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
			panel.setBorder(new TitledBorder(null, "Listado de eventos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"ID", "Nombre", "Tipo", "Lugar", "Fecha de la actividad", "Estado"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadEventos();
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								id = String.valueOf(table.getValueAt(index, 0));
								btnCrear.setEnabled(true);
								btnGestionarComisiones.setEnabled(true);
								
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
				btnCrear = new JButton("Crear Comisi\u00F3n");
				btnCrear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Evento elEvento = Empresa.getInstance().searchEventoByID(id);
						
						RegComision comi = new RegComision(elEvento, false, null);
						comi.setModal(true);
						comi.setVisible(true);
					}
				});
				{
					JButton btnModifcar = new JButton("Modifcar");
					buttonPane.add(btnModifcar);
				}
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				btnCrear.setEnabled(false);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				btnGestionarComisiones = new JButton("Gestionar Comisiones");
				btnGestionarComisiones.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Evento elEvento = Empresa.getInstance().searchEventoByID(id);
						ListaComisiones window = new ListaComisiones(elEvento);
						window.setModal(true);
						window.setVisible(true);
					}
				});
				btnGestionarComisiones.setActionCommand("OK");
				buttonPane.add(btnGestionarComisiones);
				btnGestionarComisiones.setEnabled(false);
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

	public static void loadEventos() 
	{
		model.setRowCount(0);
		Empresa miEmpresa = Empresa.getInstance();
		//{"ID", "Nombre", "Tipo", "lugar", "Fecha de la actividad", "Estado"};
		row = new Object[model.getColumnCount()];
		
		for (int i = 0; i < miEmpresa.getEventos().size(); i++) 
		{
			row[0] = miEmpresa.getEventos().get(i).getId();
			row[1] = miEmpresa.getEventos().get(i).getNombre();
			row[2] = miEmpresa.getEventos().get(i).getTipo();
			row[3] = miEmpresa.getEventos().get(i).getLugar();
			row[4] = new SimpleDateFormat("dd/MM/yyyy").format(miEmpresa.getEventos().get(i).getFecha());
			
			if(miEmpresa.getEventos().get(i).isEstado())
			{
				row[5] = "Disponible";
			}
			else
			{
				row[5] = "Finalizado";
			}
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
