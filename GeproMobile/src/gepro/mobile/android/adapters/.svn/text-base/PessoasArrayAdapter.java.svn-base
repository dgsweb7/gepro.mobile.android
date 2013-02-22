package gepro.mobile.android.adapters;

import gepro.mobile.android.R;
import gepro.mobile.dto.Pessoa;
import gepro.mobile.util.Util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PessoasArrayAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<Pessoa> _customerData;
	
	public PessoasArrayAdapter(Context context) {
		mInflater = LayoutInflater.from(context);
	}

	public PessoasArrayAdapter(Context context, List<Pessoa> pessoas) {
		mInflater = LayoutInflater.from(context);
		_customerData = pessoas;
	}

	public void SetData(List<Pessoa> data) {
		_customerData = data;
	}

	public int getCount() {
		if (_customerData == null)
			return 0;

		return _customerData.size();
	}

	public Object getItem(int position) {
		if(_customerData == null) return null;
		
		return _customerData.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	

	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;

		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.customer_list_item, null);

			holder = new ViewHolder();
			//holder.txt_id = (TextView) convertView.findViewById(R.id.txt_customer_item_id);
			holder.txt_name = (TextView) convertView.findViewById(R.id.txt_customer_item_name);
			//holder.txt_phone = (TextView) convertView.findViewById(R.id.txt_customer_item_phone);
			convertView.setTag(holder);

		} else {
		
			holder = (ViewHolder) convertView.getTag();
		}
		
		Pessoa cust = _customerData.get(position);
		//holder.txt_id.setText( String.format("[%s]", cust.getID() ));
		holder.txt_name.setText(cust.getNome());
		//holder.txt_phone.setText("Tel: " + ( Util.IsNullOrEmpty( cust.getTelefone() ) ? "" : cust.getTelefone()  ));    	
		return convertView;
	}

	static class ViewHolder {
		TextView txt_name, txt_phone, txt_id;
	}
}