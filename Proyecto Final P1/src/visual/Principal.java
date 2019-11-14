package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Principal extends JFrame {

	private JPanel contentPane;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Planificaci\u00F3n de eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGestinDeEventos = new JMenu("Gesti\u00F3n de Eventos");
		menuBar.add(mnGestinDeEventos);
		
		JMenuItem mntmRegistrarEvento = new JMenuItem("Registrar Evento");
		mnGestinDeEventos.add(mntmRegistrarEvento);
		
		JMenuItem mntmListarEventos = new JMenuItem("Listar Eventos");
		mnGestinDeEventos.add(mntmListarEventos);
		
		JMenu mnGestionDePersonal = new JMenu("Gestion de personal");
		menuBar.add(mnGestionDePersonal);
		
		JMenuItem mntmRegistrarPersona = new JMenuItem("Registrar persona");
		mnGestionDePersonal.add(mntmRegistrarPersona);
		
		JMenuItem mntmListarPersonal = new JMenuItem("Listar personal");
		mnGestionDePersonal.add(mntmListarPersonal);
		
		JMenu mnGestinDeRecursos = new JMenu("Gesti\u00F3n de Recursos");
		menuBar.add(mnGestinDeRecursos);
		
		JMenuItem mntmRegistrarRecurso = new JMenuItem("Registrar recurso");
		mnGestinDeRecursos.add(mntmRegistrarRecurso);
		
		JMenuItem mntmListarRecursos = new JMenuItem("Listar recursos");
		mnGestinDeRecursos.add(mntmListarRecursos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
