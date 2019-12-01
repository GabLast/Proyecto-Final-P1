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

		try 
		{
			while(true) 
			{
				
				int contador = 0;
				Empresa.getInstance().insertionSort();
				ArrayList<Evento> evePops = Empresa.getInstance().getEventos();

				DefaultCategoryDataset line_chart_dataset = Principal.line_chart_dataset;
				
				for(Evento evento : evePops)
				{
					line_chart_dataset.addValue(evento.totalTrabajos(), "Trabajos", evento.getNombre());
					contador++;
					if(contador >= 5)
					{
						break;
					}
				}
				
				contador = 0;

				Principal.chart1=ChartFactory.createLineChart("Eventos más populares de acuerdo a trabajos", "Nombre","Trabajos",line_chart_dataset,PlotOrientation.VERTICAL,
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
