package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.dto.Andamento;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class AndamentosExpandListAdapter extends BaseExpandableListAdapter {
	private LayoutInflater mInflater;
	private List<Andamento> _movimentDTO;

	public AndamentosExpandListAdapter(Context context,
			List<Andamento> movimentData) throws Exception {

		mInflater = LayoutInflater.from(context);
		_movimentDTO = movimentData;

		if (context == null || _movimentDTO == null)
			throw new Exception("Null args");
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {

		return _movimentDTO.get(groupPosition);
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
			convertView = mInflater.inflate(R.layout.moviments_expand_item,
					null);

			holder = new ViewHolderChild();

			holder.txtDescription = (TextView) convertView
					.findViewById(R.id.txt_moviment_description);

			holder.txtResponsible = (TextView) convertView
					.findViewById(R.id.txt_moviment_responsible);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolderChild) convertView.getTag();
		}

		Andamento mov = _movimentDTO.get(groupPosition);

		holder.txtDescription.setText(mov.getDescricao());
		holder.txtResponsible.setText(mov.getResponsavel());

		return convertView;

	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {

		return _movimentDTO.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return _movimentDTO.size();
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
			convertView = mInflater.inflate(R.layout.moviments_layout, null);

			holder = new ViewHolderGroup();

			holder.txtDate = (TextView) convertView
					.findViewById(R.id.txt_moviment_date);

			holder.txtType = (TextView) convertView
					.findViewById(R.id.txt_moviment_type);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolderGroup) convertView.getTag();
		}

		Andamento andamento = _movimentDTO.get(groupPosition);

		holder.txtDate.setText(andamento.getData().substring(0, 10)  );
		holder.txtType.setText(andamento.getTipo().getDescricao());

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
		TextView txtId, txtDate, txtType;
	}

	static class ViewHolderChild {
		TextView txtDescription, txtResponsible;
	}
}
