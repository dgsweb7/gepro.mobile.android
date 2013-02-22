package gepro.mobile.android;

import gepro.mobile.android.adapters.MenuItemListAdapter;
import gepro.mobile.android.holder.MenuEntry;
import gepro.mobile.util.Config;
import gepro.mobile.util.GeproKeys;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class PessoasPerfilAct extends Activity {


	private static final int CALL_PESSOA = 0;

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

		menu = new MenuEntry(GeproKeys.Perfil.Colaborador,
				R.drawable.pessoa_colaborador, "Colaboradores", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.Perfil.Empresa, R.drawable.pessoa_empresas,
				"Empresas", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.Perfil.EscritorioParceiro,
				R.drawable.pessoa_escritorio, "Escritorios Parceiros", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.Perfil.Filial, R.drawable.pessoas_filiais,
				"Filiais", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.Perfil.Fornecedor,
				R.drawable.pessoa_fornecedores, "Fornecedores", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.Perfil.ParteContraria,
				R.drawable.pessoas_parte_contraria, "Parte Contraria", "");
		lst.add(menu);

		menu = new MenuEntry(GeproKeys.Perfil.Outros, R.drawable.pessoa_outros,
				"Outros", "");
		lst.add(menu);

		MenuItemListAdapter mad = new MenuItemListAdapter(this, lst);
		lv.setAdapter(mad);

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				MenuEntry menu = (MenuEntry) lv.getItemAtPosition(arg2);

				if (menu != null) {
					Intent itn = new Intent(PessoasPerfilAct.this,
							PessoasSearchAct.class);

					itn
							.setAction(PessoasPerfilAct.this.getIntent()
									.getAction());

					itn.putExtra(GeproKeys.PERFIL_PESSOA_SELECIONADO, menu
							.getId());

					PessoasPerfilAct.this.startActivityForResult(itn,
							CALL_PESSOA);
				}

			}

		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == CALL_PESSOA && resultCode == RESULT_OK) {
				setResult(resultCode, data);
				finish();
		}
	}

}
