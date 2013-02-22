package gepro.mobile.android;

import gepro.android.HttpRequest;
import gepro.android.Tools;
import gepro.mobile.android.adapters.AgendaExpandListAdapter;
import gepro.mobile.dto.Agenda;
import gepro.mobile.proxy.GeproMobileServiceProxy;
import gepro.mobile.util.GeproKeys;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.ExpandableListView.OnChildClickListener;

public class AgendaAct extends Activity implements Runnable {

	private final static String MODE_DAY = "D";
	private final static String MODE_WEEK = "W";
	private final static String MODE_MONTH = "M";
	private final static String MODE_PENDING = "P";
	private final static String MODE_PERIOD = "PP";

	public final static int ADD_AGENDA = 1;

	private static String MODO = MODE_DAY;
	static ProgressDialog pg = null;
	List<Agenda> agendas = null;

	static String dataIni;
	static String dataEnd;

	static ExpandableListView lst = null;
	static DatePicker dtpIni = null, dtpEnd = null;

	static Dialog dlgPeriod = null;
	static Agenda agendaRealiza = null;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == ADD_AGENDA && resultCode == RESULT_OK) {
			Refresh(MODE_PENDING);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);		
		
		MenuItem item = menu.add(R.string.task_day);
		item.setIcon(R.drawable.agenda_dia);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Refresh(MODE_DAY);
				return true;
			}
		});

		item = menu.add(R.string.task_week);
		item.setIcon(R.drawable.agenda_semana);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Refresh(MODE_WEEK);
				return true;
			}
		});

		item = menu.add(R.string.task_month);
		item.setIcon(R.drawable.agenda_mes);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Refresh(MODE_MONTH);

				return true;
			}
		});

		item = menu.add("Pendentes");
		item.setIcon(R.drawable.agenda_pendentes);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				Refresh(MODE_PENDING);
				return true;
			}
		});

		item = menu.add("Periodo");
		item.setIcon(R.drawable.agenda_periodo);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				dlgPeriod.show();
				return true;
			}
		});

		item = menu.add("Adicionar");
		item.setIcon(R.drawable.agenda_adicionar);
		item.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {

				Intent itn = new Intent(AgendaAct.this, AgendaManutAct.class);
				AgendaAct.this.startActivityForResult(itn, ADD_AGENDA);
				return true;
			}
		});

		return true;
	}

	private void CreateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflater = LayoutInflater.from(this);

		View dialogView = inflater.inflate(R.layout.task_date_picker, null);

		dtpEnd = (DatePicker) dialogView.findViewById(R.id.dtp_task_dataend);
		dtpIni = (DatePicker) dialogView.findViewById(R.id.dtp_task_dataini);

		builder.setView(dialogView);
		builder.setPositiveButton("Ok", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				dataEnd = String.format("%s-%s-%s", dtpEnd.getDayOfMonth(),
						dtpEnd.getMonth() + 1, dtpEnd.getYear());
				dataIni = String.format("%s-%s-%s", dtpIni.getDayOfMonth(),
						dtpIni.getMonth() + 1, dtpIni.getYear());
				Refresh(MODE_PERIOD);
			}
		});

		builder.setNegativeButton("Canecelar", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});

		dlgPeriod = builder.create();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_layout);
		
		View titleView = getWindow().findViewById(android.R.id.title);
		if (titleView != null) {
		  ViewParent parent = titleView.getParent();
		  if (parent != null && (parent instanceof View)) {
		    View parentView = (View)parent;
		    parentView.setBackgroundColor(Color.BLACK);
		  }
		}
		

		lst = (ExpandableListView) findViewById(R.id.lst_task);
		lst.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {

				ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

				int type = ExpandableListView
						.getPackedPositionType(info.packedPosition);

				if (type == 1) {

					menu.add(0, 1, 0, "Realizar Compromisso");
				}
			}
		});

		CreateDialog();

		try {
			Refresh(MODE_PENDING);
		} catch (Exception e) {
			Log.e("Login", e.getMessage());
		} finally {

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem menuItem) {

		ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuItem
				.getMenuInfo();

		int groupPos = 0;

		int type = ExpandableListView
				.getPackedPositionType(info.packedPosition);

		if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

			groupPos = ExpandableListView
					.getPackedPositionGroup(info.packedPosition);
		}

		Agenda agenda = (Agenda) lst.getItemAtPosition(groupPos);

		switch (menuItem.getItemId()) {
		case 1:
			RealizarAgenda(agenda);
		default:
			return super.onContextItemSelected(menuItem);
		}
	}

	private void RealizarAgenda(Agenda agenda) {

		agendaRealiza = agenda;
		pg = ProgressDialog.show(this, null, "Aguarde...");
		Thread t = new Thread(realizarAgenda, "REALIZAR");
		t.start();

	}

	private void Refresh(String MODE) {

		if (MODE.equals(MODE_DAY))
			this.setTitle("GeproMobile - Agenda : Dia");
		else if (MODE.equals(MODE_WEEK))
			this.setTitle("GeproMobile - Agenda : Semana");
		else if (MODE.equals(MODE_MONTH))
			this.setTitle("GeproMobile - Agenda : Mes");
		else if (MODE.equals(MODE_PENDING))
			this.setTitle("GeproMobile - Agenda : Pendentes");
		else if (MODE.equals(MODE_PERIOD))
			this.setTitle(String.format("Periodo de %s até %s", dataIni,
					dataEnd));

		MODO = MODE;
		pg = ProgressDialog.show(this, null, "Aguarde...");
		new Thread(this, "DO_TASK").start();
	}

	Runnable realizarAgenda = new Runnable() {
		@Override
		public void run() {

			if (agendaRealiza == null) {
				handlerRealizarAgenda
						.sendEmptyMessage(GeproKeys.RETURN_CODE.INVALID);
				return;
			}

			try {

				GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
						new HttpRequest());

				proxy.RealizarAgenda(gepro.mobile.util.Config
						.getCurrentSession(), agendaRealiza);

				handlerRealizarAgenda
						.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);

			} catch (Exception e) {
				Message msg = Tools.CreateMessageFomEx(e);
				msg.what = GeproKeys.RETURN_CODE.FAIL;
				handlerRealizarAgenda.sendMessage(msg);
			}
		}
	};

	Handler handlerRealizarAgenda = new Handler() {
		public void handleMessage(Message msg) {

			if (pg != null)
				pg.hide();

			if (msg.what == GeproKeys.RETURN_CODE.OK) {
				agendaRealiza = null;
				Refresh(MODO);
			} else if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
				Toast.makeText(AgendaAct.this,
						"Occorreu um erro durante a realização da Agenda",
						Toast.LENGTH_LONG).show();
			}
		};
	};

	Handler taskHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			try {
				if (MODO == MODE_PERIOD) {
					if (dlgPeriod != null) {
						dlgPeriod.hide();
					}
				}

				if (pg != null)
					pg.hide();

				if (msg.what == GeproKeys.RETURN_CODE.FAIL) {
					Toast.makeText(AgendaAct.this,
							"Occorreu um erro durante a busca da Agenda",
							Toast.LENGTH_LONG).show();

				} else if (msg.what == GeproKeys.RETURN_CODE.NULL) {

					Toast.makeText(AgendaAct.this,
							"Não há Items de Agenda para o modo pesquisado",
							Toast.LENGTH_SHORT).show();

				} else if (msg.what == GeproKeys.RETURN_CODE.OK) {

					AgendaExpandListAdapter caa = new AgendaExpandListAdapter(
							AgendaAct.this, agendas, MODO);

					lst.setAdapter(caa);
				}
			} catch (Exception e) {
				Toast.makeText(AgendaAct.this,
						"Occorreu um erro durante a busca da Agenda",
						Toast.LENGTH_LONG).show();
			}

		}
	};

	@Override
	public void run() {
		try {
			GeproMobileServiceProxy proxy = new GeproMobileServiceProxy(
					new HttpRequest());

			Agenda[] AgendaRetorno = null;

			if (MODO == MODE_PERIOD)
				AgendaRetorno = proxy.GetTasks(gepro.mobile.util.Config
						.getCurrentSession(), dataIni, dataEnd);
			else
				AgendaRetorno = proxy.GetTasks(gepro.mobile.util.Config
						.getCurrentSession(), MODO);

			if (AgendaRetorno != null && AgendaRetorno.length > 0) {
				agendas = Arrays.asList(AgendaRetorno);
				taskHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.OK);
			} else {
				taskHandler.sendEmptyMessage(GeproKeys.RETURN_CODE.NULL);
			}

		} catch (Exception e) {
			Message msg = Tools.CreateMessageFomEx(e);
			msg.what = GeproKeys.RETURN_CODE.FAIL;
			taskHandler.sendMessage(msg);
		}
	}
}