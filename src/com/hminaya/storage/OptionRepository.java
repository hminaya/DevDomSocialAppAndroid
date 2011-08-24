package com.hminaya.storage;

import java.util.ArrayList;
import java.util.List;

import com.hminaya.devdomlib.R;
import com.hminaya.models.Mem;
import com.hminaya.models.OpcionInfo;

public class OptionRepository {
	
	public void LoadInfo(){
		
		if (Mem.opciones == null) {
			Mem.opciones = getOpcionesFromAPI();
		}
	}
	
	public List<OpcionInfo> getOpciones(){
		
		this.LoadInfo();
		
		return Mem.opciones;
	}
	
	public List<OpcionInfo> getOpcionesFromAPI(){
		
		List<OpcionInfo> opciones = new ArrayList<OpcionInfo>();
		
		opciones.add(new OpcionInfo("Tutoriales", R.drawable.icon_tutorial));
		opciones.add(new OpcionInfo("Eventos", R.drawable.icon_calendar));
		opciones.add(new OpcionInfo("Noticias", R.drawable.icon_news));
		opciones.add(new OpcionInfo("Empleos", R.drawable.icon_empleos));
		opciones.add(new OpcionInfo("Colaboradores", R.drawable.icon_colaboradores));
		opciones.add(new OpcionInfo("Comunidades", R.drawable.icon_community));
		
		return opciones;
		
	}

}
