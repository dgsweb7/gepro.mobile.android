package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.dto.Tipo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TipoArrayAdapter extends BaseAdapter {
	
	List<Tipo> _data = null;
	LayoutInflater _inf;

	public TipoArrayAdapter(Context ctx,  List<Tipo> data) {
		
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
			convertView = _inf.inflate(R.layout.drop_item, null);
			
			holder = new ViewHolder();
			holder.txt = (TextView)convertView.findViewById(R.id.txt_drop_item);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.txt.setText( _data.get(position).getDescricao() );
		
		return convertView;
		
	}
	
	static class ViewHolder {
		TextView txt;
	}

}
