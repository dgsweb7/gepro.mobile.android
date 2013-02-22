package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.dto.Agenda;
import gepro.mobile.util.Util;

import java.sql.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class AgendaExpandListAdapter extends BaseExpandableListAdapter {

	private LayoutInflater mInflater;
	private List<Agenda> _taskData;
	private String _mode;

	public AgendaExpandListAdapter(Context context, List<Agenda> taskData,
			String MODE) {

		mInflater = LayoutInflater.from(context);
		_taskData = taskData;
		_mode = MODE;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return _taskData.get(groupPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {

		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		ViewHolderChild holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.task_expand_item, null);

			holder = new ViewHolderChild();
			holder.txtDateIni = (TextView) convertView
					.findViewById(R.id.txt_task_expand_date_ini);

			holder.txtDateEnd = (TextView) convertView
					.findViewById(R.id.txt_task_expand_date_end);

			holder.txtDescription = (TextView) convertView
					.findViewById(R.id.txt_task_expand_desc);

			holder.txtLocation = (TextView) convertView
					.findViewById(R.id.txt_task_expand_location);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolderChild) convertView.getTag();
		}

		Agenda agenda = _taskData.get(groupPosition);

		holder.txtDescription
				.setText(Util.IsNullOrEmpty(agenda.getDescricao()) ? ""
						: agenda.getDescricao());

		holder.txtDateIni.setText(String.format("Inicio: %s", DateFormat
				.format("kk:mm", Date.parse(agenda.getDataInicial()))
				.toString()));

		holder.txtDateEnd
				.setText(String.format("Termino: %s", DateFormat.format(
						"kk:mm", Date.parse(agenda.getDataFinal())).toString()));

		holder.txtLocation.setText(String.format("Local: %s", Util
				.IsNullOrEmpty(agenda.getLocal()) ? "" : agenda.getLocal()));

		return convertView;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return _taskData.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return _taskData.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		ViewHolderGroup holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.task_list_item, null);

			holder = new ViewHolderGroup();
			holder.txtTitle = (TextView) convertView
					.findViewById(R.id.txt_task_list_title);

			holder.txtDate = (TextView) convertView
					.findViewById(R.id.txt_task_list_date);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolderGroup) convertView.getTag();
		}

		Agenda agenda = _taskData.get(groupPosition);

		String dateFormat;

		if (_mode == "D")
			dateFormat = "kk:mm"; // hora no formato de 24 horas
		else
			dateFormat = "dd/MM kk:mm";

		CharSequence format = DateFormat.format(dateFormat, Date.parse(agenda
				.getDataInicial()));

		holder.txtDate.setText(format.toString());
		holder.txtTitle.setText(agenda.getTitulo());

		return convertView;

	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	static class ViewHolderGroup {
		TextView txtTitle, txtDate;
	}

	static class ViewHolderChild {
		TextView txtDateIni, txtDateEnd, txtDescription, txtLocation;
	}

}
