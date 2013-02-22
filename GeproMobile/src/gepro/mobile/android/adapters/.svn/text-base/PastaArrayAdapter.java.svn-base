package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.dto.Pasta;
import gepro.mobile.util.Util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PastaArrayAdapter extends BaseAdapter {

	LayoutInflater _inf;
	List<Pasta> _data;

	public PastaArrayAdapter(Context ctx, List<Pasta> data) {
		_inf = LayoutInflater.from(ctx);
		_data = data;
	}

	@Override
	public int getCount() {
		return _data.size();
	}

	@Override
	public Object getItem(int position) {
		return _data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			convertView = _inf.inflate(R.layout.pasta_list_item, null);

			holder = new ViewHolder();
			holder.txt_pastaid = (TextView) convertView
					.findViewById(R.id.txt_pasta_list_item_pastaid);
			holder.txt_nproc = (TextView) convertView
					.findViewById(R.id.txt_pasta_list_item_nprocesso);
			holder.txt_solicitante = (TextView) convertView
					.findViewById(R.id.txt_pasta_list_item_partesolicitante);
			holder.txt_contraria = (TextView) convertView
					.findViewById(R.id.txt_pasta_list_item_partecontraria);
			holder.txtForum = (TextView) convertView
					.findViewById(R.id.txt_pasta_list_item_partforum);
			holder.txtVara = (TextView) convertView
					.findViewById(R.id.txt_pasta_list_item_vara);

			convertView.setTag(holder);

		} else {

			holder = (ViewHolder) convertView.getTag();
		}

		Pasta pasta = _data.get(position);
		
		holder.txt_pastaid.setText("[" + pasta.getPastaId() + "]");

		holder.txt_nproc
				.setText(Util.IsNullOrEmpty(pasta.getNProcesso()) ? "N/A"
						: pasta.getNProcesso());

		holder.txt_solicitante.setText(pasta.getParteSolicitante());
		
		holder.txt_contraria.setText(pasta.getParteContrario());
		
		holder.txtForum.setText(Util.IsNullOrEmpty(pasta.getForo()) ? "N/A"
				: pasta.getForo());
		
		holder.txtVara.setText(Util.IsNullOrEmpty(pasta.getVara()) ? "N/A"
				: pasta.getVara());

		return convertView;
	}

	static class ViewHolder {
		TextView txt_pastaid, txt_nproc, txt_solicitante, txt_contraria,
				txtForum, txtVara;
	}

}
