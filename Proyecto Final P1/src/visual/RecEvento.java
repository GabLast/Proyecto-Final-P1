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

import logica.Empresa;
import logica.Evento;
import logica.Recurso;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecEvento extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Dimension dim;
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	JButton btnDevolver;
	String tipo = "";

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
			Evento event = null;
			RecEvento dialog = new RecEvento(event);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RecEvento(Evento evento) {
		setTitle("Recursos del evento" + evento.getNombre());
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .40;
		dim.height *= .40;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Recursos utilizados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"Nombre", "Cantidad utilizada"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								tipo = String.valueOf(table.getValueAt(index, 0));
								btnDevolver.setEnabled(true);
						}
					}
					});
					
					loadRecursos(evento);					
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
				btnDevolver = new JButton("Devolver recurso");
				btnDevolver.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Recurso rec = evento.searchRecursoByTipo(tipo);
						Empresa.getInstance().retornoRecursosDesdeEvento(rec);
						evento.getRecursosUsados().remove(rec);
						JOptionPane.showMessageDialog(null, "Recurso retornado"
								, "Notificación", JOptionPane.INFORMATION_MESSAGE);
						RecEvento.loadRecursos(evento);
					}
				});
				btnDevolver.setActionCommand("OK");
				buttonPane.add(btnDevolver);
				btnDevolver.setEnabled(false);
				getRootPane().setDefaultButton(btnDevolver);
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

	public static void loadRecursos(Evento miEve) 
	{
		model.setRowCount(0);
		//{"Nombre", "Cantidad"
		row = new Object[model.getColumnCount()];
		
		for (int i = 0; i < miEve.getRecursosUsados().size(); i++) 
		{
			row[0] = miEve.getRecursosUsados().get(i).getTipo();
			row[1] = miEve.getRecursosUsados().get(i).getCantUsadaEvento();
			
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
