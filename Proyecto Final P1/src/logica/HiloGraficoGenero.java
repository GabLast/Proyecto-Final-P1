package logica;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import visual.Principal;

public class HiloGraficoGenero extends Thread {

	public HiloGraficoGenero() {
		super();
	}

	@Override
	public void run()
	{

		try {
			while(true) 
			{
				int[] cantidades = Empresa.getInstance().cantidadPersonasRegistradasPorGenero();

				DefaultPieDataset data = Principal.data;

		        data.setValue("Hombres", cantidades[0]);
		        data.setValue("Mujeres", cantidades[1]);

				Principal.chart2 = ChartFactory.createPieChart("Personas registradas por género", data, true, true, false);

				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Sleep del hilo de GraficoGenero se detuvo con error: "+e.getMessage());
			e.printStackTrace();
		}

	}
}
