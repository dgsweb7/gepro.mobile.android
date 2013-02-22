package gepro.mobile.android;

import gepro.mobile.android.adapters.MenuItemListAdapter;
import gepro.mobile.android.holder.MenuEntry;
import gepro.mobile.util.GeproKeys;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PastaTipoAct extends Activity {

	private static final int CALL_PASTA = 0;

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

		setContentView(R.layout.pessoas_perfil_list);

		final ListView lv = (ListView) findViewById(R.id.lst_pessoas_perfil);
		lv.setTextFilterEnabled(true);

		MenuEntry menu = null;
		List<MenuEntry> lst = new ArrayList<MenuEntry>();

		menu = new MenuEntry(GeproKeys.PastaTipo.Contencioso, 
				R.drawable.pasta_contencioso, "Contencioso", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.PastaTipo.Contencioso, 
				R.drawable.pasta_consultivo, "Consultivo", "");
		lst.add(menu);
		
		menu = new MenuEntry(GeproKeys.PastaTipo.Contencioso, 
				R.drawable.pasta_contratos, "Contratos", "");
		lst.add(menu);
		
		menu = new MenuEntry(GeproKeys.PastaTipo.Contencioso, 
				R.drawable.pasta_marcas_patentes, "Marcas e Patentes", "");
		lst.add(menu);
		
		menu = new MenuEntry(GeproKeys.PastaTipo.Contencioso, 
				R.drawable.pasta_procuracao, "Procuração", "");
		lst.add(menu);
		
		menu = new MenuEntry(GeproKeys.PastaTipo.Contencioso, 
				R.drawable.pasta_societario, "Societário", "");
		lst.add(menu);	

		MenuItemListAdapter mad = new MenuItemListAdapter(this, lst);
		lv.setAdapter(mad);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				MenuEntry menu = (MenuEntry) lv.getItemAtPosition(arg2);

				if (menu != null) {
					Intent itn = new Intent(PastaTipoAct.this,
						PastaSearchAct.class);

					itn.setAction(PastaTipoAct.this.getIntent()
									.getAction());

					itn.putExtra(GeproKeys.PASTA_TIPO_SELECIONADA, menu
							.getId());

					PastaTipoAct.this.startActivityForResult(itn,
							CALL_PASTA);
				}
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CALL_PASTA && resultCode == RESULT_OK) {
				setResult(resultCode, data);
				finish();
		}
	}
	
}
