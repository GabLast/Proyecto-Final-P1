package logica;

import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import visual.Principal;

public class HiloEventoPops extends Thread {

	public HiloEventoPops() 
	{
		super();
	}

	@Override
	public void run()
	{

		try {
			while(true) 
			{
				
				int contador = 0;
				Empresa.getInstance().insertionSort();
				ArrayList<Evento> evePops = Empresa.getInstance().getEventos();

				DefaultCategoryDataset line_chart_dataset = Principal.line_chart_dataset;
				
				//for(Evento evento : evePops)
				line_chart_dataset.addValue(evePops.get(0).totalTrabajos(), "Trabajos", evePops.get(0).getNombre());
				line_chart_dataset.addValue(evePops.get(1).totalTrabajos(), "Trabajos", evePops.get(1).getNombre());
				line_chart_dataset.addValue(evePops.get(2).totalTrabajos(), "Trabajos", evePops.get(2).getNombre());
				line_chart_dataset.addValue(evePops.get(3).totalTrabajos(), "Trabajos", evePops.get(3).getNombre());
				line_chart_dataset.addValue(evePops.get(4).totalTrabajos(), "Trabajos", evePops.get(4).getNombre()); 

				Principal.chart1=ChartFactory.createLineChart("Eventos más populares",
						"Nombre","Trabajos",line_chart_dataset,PlotOrientation.VERTICAL,
						true,true,false);

				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("Sleep del hilo de EventoPops se detuvo con error: "+e.getMessage());
			e.printStackTrace();
		}




	}

}
