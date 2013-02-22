package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.android.adapters.DesdobramentoArrayAdapter.ViewHolder;
import gepro.mobile.android.holder.MenuEntry;
import gepro.mobile.dto.Desdobramento;
import gepro.mobile.util.Base64Coder;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemListAdapter extends BaseAdapter {

	LayoutInflater _inflater = null;
	List<MenuEntry> _data = null;

	public MenuItemListAdapter(Context ctx, List<MenuEntry> data) {

		_inflater = LayoutInflater.from(ctx);

		_data = data;

	}

	@Override
	public int getCount() {
		return _data.size();
	}

	@Override
	public MenuEntry getItem(int position) {
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
			convertView = _inflater.inflate(R.layout.menu_prinipal_item, null);

			holder = new ViewHolder();
			holder.txt = (TextView) convertView

			.findViewById(R.id.txt_menu_principal_nome_item);


			holder.img = (ImageView) convertView
					.findViewById(R.id.img_menu_principal_item);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		MenuEntry menu = getItem(position);

		holder.txt.setText(menu.getNome());

		if (menu.getImageId() != 0) {
			holder.img.setImageResource(menu.getImageId());
		}

		return convertView;

	}

	static class ViewHolder {
		TextView txt, txtDesc;

		ImageView img;
	}

}
