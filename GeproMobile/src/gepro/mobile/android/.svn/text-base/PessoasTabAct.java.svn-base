package gepro.mobile.android;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class PessoasTabAct extends TabActivity {

	final String CUSTOMER = "CUSTOMER";
	final String CONTACTS = "CONTACTS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final TabHost tabHost = getTabHost();
		final Resources res = getResources();
		final Bundle extras = getIntent().getExtras();

		try {

			Intent intent = new Intent(this, PessoasAct.class);
			intent.putExtras(extras);
			tabHost.addTab(tabHost.newTabSpec(CUSTOMER).setIndicator(
					res.getString(R.string.customer_detail),
					res.getDrawable(R.drawable.pessoa_pequeno)).setContent(intent));


		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
