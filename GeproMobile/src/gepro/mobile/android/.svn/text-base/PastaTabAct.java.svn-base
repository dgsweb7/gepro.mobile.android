package gepro.mobile.android;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.TabHost;

public class PastaTabAct extends TabActivity {

	final String CUSTOMER = "CUSTOMER";
	final String CONTACTS = "CONTACTS";
	final String GED = "GED";

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
		
		
		final TabHost tabHost = getTabHost();
		final Resources res = getResources();
		final Bundle extras = getIntent().getExtras();

		try {

			Intent intent = new Intent(this, PastaAct.class);
			intent.putExtras(extras);
			tabHost.addTab(tabHost.newTabSpec(CUSTOMER).setIndicator(
					res.getString(R.string.pasta_detail),
					res.getDrawable(R.drawable.pasta)).setContent(intent));

			intent = new Intent(this, AndamentoAct.class);
			intent.putExtras(extras);
			tabHost.addTab(tabHost.newTabSpec(CONTACTS).setIndicator(
					res.getString(R.string.pasta_andamentos),
					res.getDrawable(R.drawable.andamentos)).setContent(
					intent));
			
			
			intent = new Intent(this, GedAct.class);
			intent.putExtras(extras);
			tabHost.addTab(tabHost.newTabSpec(GED).setIndicator(
					res.getString(R.string.pasta_GED),
					res.getDrawable(R.drawable.documentos)).setContent(
					intent));
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
}
