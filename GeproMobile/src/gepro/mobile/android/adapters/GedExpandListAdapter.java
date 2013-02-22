package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.dto.Ged;
import gepro.mobile.dto.GedItem;
import gepro.mobile.dto.Andamento;
import gepro.mobile.dto.Tipo;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class GedExpandListAdapter extends BaseExpandableListAdapter {
	private LayoutInflater mInflater;
	private List<Ged> _geds;

	public GedExpandListAdapter(Context context, List<Ged> gedData)
			throws Exception {

		mInflater = LayoutInflater.from(context);
		_geds = gedData;

		if (context == null || _geds == null)
			throw new Exception("Null args");
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return _geds.get(groupPosition).getGedIems()[childPosition];
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
			convertView = mInflater.inflate(R.layout.ged_list_item, null);

			holder = new ViewHolderChild();

			holder.txtNome = (TextView) convertView
					.findViewById(R.id.txt_ged_nome);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolderChild) convertView.getTag();
		}

		GedItem ged = _geds.get(groupPosition).getGedIems()[childPosition];

		if (ged != null)
			holder.txtNome.setText(ged.getDescricao());

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return _geds.get(groupPosition).getGedIems().length;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return _geds.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return _geds.size();
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
			convertView = mInflater.inflate(R.layout.ged_expand_item, null);

			holder = new ViewHolderGroup();

			holder.txtDescricao = (TextView) convertView
					.findViewById(R.id.txt_ged_descricao);

			holder.txtTipoDocumento = (TextView) convertView
					.findViewById(R.id.txt_ged_tipodocumento);

			convertView.setTag(holder);

		} else {
			holder = (ViewHolderGroup) convertView.getTag();
		}

		Ged ged = _geds.get(groupPosition);

		holder.txtDescricao.setText(ged.getDescricao());

		holder.txtTipoDocumento.setText(ged.getTipoDocumento());

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
		TextView txtTipoDocumento, txtDescricao;
	}

	static class ViewHolderChild {
		TextView txtNome;
	}
}
