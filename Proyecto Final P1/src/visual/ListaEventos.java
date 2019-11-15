package visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Empresa;
import logica.Evento;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ListaEventos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	private JTable table_1;
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
					
					
					String[] header = {"ID", "Nombre", "Tipo", "Fecha de la actividad", "Estado"};
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
					table_1 = new JTable();
					scrollPane.setViewportView(table_1);
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
						
						RegComision comi = new RegComision(elEvento);
						comi.setModal(true);
						comi.setVisible(true);
					}
				});
				btnCrear.setActionCommand("OK");
				buttonPane.add(btnCrear);
				btnCrear.setEnabled(false);
				getRootPane().setDefaultButton(btnCrear);
			}
			{
				btnGestionarComisiones = new JButton("Gestionar Comisiones");
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
		//Empresa.getInstance();
		
		row = new Object[model.getColumnCount()];
		
		
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
