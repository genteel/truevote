package co.afrolabs.truevotenaija;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import co.afrolabs.truevotenaija.adapters.ContestantsListAdapter;
import co.afrolabs.truevotenaija.data.Contestant;


public class Main extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ArrayList<Contestant> details = getListData();
        final ListView lv1 = (ListView) findViewById(R.id.listView);
        lv1.setAdapter(new ContestantsListAdapter(this, details));
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                Contestant contestantData = (Contestant) o;
                new AlertDialog.Builder(Main.this).setMessage("Vote "+contestantData.getFullName()+"?")
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(Main.this, "Thank you for voting! OneNigeria!", Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private ArrayList<Contestant> getListData() {
        ArrayList<Contestant> results = new ArrayList<Contestant>();
        Contestant newsData = new Contestant(R.drawable.gejcool,"Goodluck Jonathan (PDP)", "PDP");
        results.add(newsData);

        newsData = new Contestant(R.drawable.buhari,"Muhammadu Buhari (APC)", "APC");
        results.add(newsData);

        return results;
    }

}
