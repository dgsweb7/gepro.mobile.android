package gepro.mobile.android;

import gepro.mobile.android.adapters.MenuItemListAdapter;
import gepro.mobile.android.holder.MenuEntry;
import gepro.mobile.util.Config;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MenuAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
	    
		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		
		setContentView(R.layout.menu_principal_list);

		final ListView lv = (ListView)findViewById(R.id.lst_menu_pricipal);
		lv.setTextFilterEnabled(true);

		MenuEntry Agenda = new MenuEntry(4, R.drawable.menu_agenda  , "Agenda",
		"Relação de Compromissos");
		
		MenuEntry Pastas = new MenuEntry(2, R.drawable.menu_arquivos , "Pastas",
		"Pesquisa de pastas por palavra chave");
		
		MenuEntry Pessoas = new MenuEntry(1, R.drawable.menu_pessoas, "Pessoas",
				"Pesquisa de Pessoas por Perfil");
				
		MenuEntry Documentos = new MenuEntry(3, R.drawable.menu_documentos  , "Documentos",
				"Pesquisa e Criação de novos documentos");
				
		MenuEntry Sair = new MenuEntry(5, R.drawable.menu_sair  , "Sair",
			"Limpar dados de sessão e sair do aplicativo");

		List<MenuEntry> lst = new ArrayList<MenuEntry>();
		lst.add(Agenda);
		lst.add(Pastas);
		lst.add(Pessoas);		
		lst.add(Documentos);		
		lst.add(Sair);

		MenuItemListAdapter mad = new MenuItemListAdapter(this, lst);
		lv.setAdapter(mad);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				MenuEntry menu = (MenuEntry) lv.getItemAtPosition(arg2);

				if (menu != null) {
					Intent itn = null;
					switch (menu.getId()) {
					case 1:
						itn = new Intent(MenuAct.this, PessoasPerfilAct.class);
						MenuAct.this.startActivity(itn);
						break;
					case 2:
						itn = new Intent(MenuAct.this, PastaTipoAct.class);
						MenuAct.this.startActivity(itn);
						break;
					case 3:
						itn = new Intent(MenuAct.this, MobileGedAct.class);
						MenuAct.this.startActivity(itn);
						break;
					case 4:
						itn = new Intent(MenuAct.this, AgendaAct.class);
						MenuAct.this.startActivity(itn);
						break;
					case 5:
						Config.setCurrentSession(null, "0");
						MenuAct.this.setResult(-99);
						MenuAct.this.finish();
						break;

					}
				}
			}
		});

	}

}
