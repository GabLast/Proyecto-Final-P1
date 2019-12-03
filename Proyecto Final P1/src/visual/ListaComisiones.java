package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logica.Comision;
import logica.Empresa;
import logica.Evento;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;

public class ListaComisiones extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] row;
	private static DefaultTableModel model;
	private Dimension dim;
	String id = "";
	JButton btnModificar;
	JButton btnListarTrabajos;
	JButton btnListarMiembros;
	JButton btnEliminar;
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
			Evento miEvento = null;
			ListaComisiones dialog = new ListaComisiones(miEvento);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaComisiones(Evento miEvento) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListaComisiones.class.getResource("/imagen/redlist.png")));
		setTitle("Lista de comisiones");
		setBounds(100, 100, 450, 300);
		dim = super.getToolkit().getScreenSize();
		dim.width *= .70;
		dim.height *= .80;
		super.setSize(dim.width, dim.height);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(204, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 255, 255));
			panel.setBorder(new TitledBorder(null, "Listado de comisiones", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
					
					
					String[] header = {"ID", "Área", "Presidente", "Fecha de creación", "Cantidad de trabajos", "Cantidad de jueces"};
					model.setColumnIdentifiers(header);
					
					table = new JTable();
					
					loadComision(miEvento.getMisComisiones());
					
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							if(table.getSelectedRow()>-1){
								int index = table.getSelectedRow();
								id = String.valueOf(table.getValueAt(index, 0));
								btnModificar.setEnabled(true);
								btnListarMiembros.setEnabled(true);
								btnListarTrabajos.setEnabled(true);
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
			buttonPane.setBackground(new Color(224, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar comisi\u00F3n");
				btnModificar.setEnabled(false);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Comision comiModificar = miEvento.buscarComisionByID(id);
						RegComision comi = new RegComision(miEvento, true, comiModificar);
						comi.setModal(true);
						comi.setVisible(true);
					}
				});
				{
					btnEliminar = new JButton("Eliminar comisi\u00F3n");
					btnEliminar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(id != "")
							{
								Comision laComision = miEvento.buscarComisionByID(id);
								
								int option = JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el comision: " 
										   + laComision.getId(), "Notificación",JOptionPane.WARNING_MESSAGE);
								
								if(option == JOptionPane.OK_OPTION && laComision != null)
								{
									
									miEvento.deleteComision(laComision);
									JOptionPane.showMessageDialog(null, "Comisión eliminada satisfactoriamente"
											, "Notificación", JOptionPane.INFORMATION_MESSAGE);
									ListaComisiones.loadComision(miEvento.getMisComisiones());
								}
							}
							
						}
						
					});
					btnEliminar.setEnabled(false);
					buttonPane.add(btnEliminar);
				}
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Salir");
				buttonPane.setBackground(new Color(224, 255, 255));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				{
					btnListarMiembros = new JButton("Listar miembros");
					btnListarMiembros.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Comision comi = miEvento.buscarComisionByID(id);
							
							ListarMiembrosComi window = new ListarMiembrosComi(comi);
							window.setModal(true);
							window.setVisible(true);
						}
					});
					btnListarMiembros.setEnabled(false);
					btnListarMiembros.setActionCommand("OK");
					buttonPane.add(btnListarMiembros);
				}
				{
					btnListarTrabajos = new JButton("Listar trabajos");
					btnListarTrabajos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Comision comi = miEvento.buscarComisionByID(id);
							System.out.println("ListaComisiones: Trabajos: " + comi.getTrabajosParticipantes().size() 
							+"\t\tJueces: " + comi.getMiJurado().size());
							ListaTrabajosComi window = new ListaTrabajosComi(comi);
							window.setModal(true);
							window.setVisible(true);
						}
					});
					btnListarTrabajos.setEnabled(false);
					btnListarTrabajos.setActionCommand("OK");
					buttonPane.add(btnListarTrabajos);
				}
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void loadComision(ArrayList<Comision> comisionesEvento) 
	{
		model.setRowCount(0);
//		System.out.println("Lista Comisiones: cantComi: " + comisionesEvento.size() + "cantJueces: "	+ comisionesEvento.get(0).getMiJurado().size() + " || cantTrab: " + 
//		comisionesEvento.get(0).getTrabajosParticipantes().size());
		// {"ID", "Área", "Presidente", "Fecha de creación", "Cantidad de trabajos", "Cantidad de jueces"};
		row = new Object[model.getColumnCount()];

		
		for(Comision comi : comisionesEvento)
		{
			row[0] = comi.getId();
			row[1] = comi.getArea();
			row[2] = comi.getPresidente().getNombre();
			row[3] = new SimpleDateFormat("dd/MM/yyyy").format(comi.getFechaCreacion());
			row[4] = comi.getTrabajosParticipantes().size();
			row[5] = comi.getMiJurado().size();
			model.addRow(row);
		}
		
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.getTableHeader().setReorderingAllowed(false);
		
	}
}
