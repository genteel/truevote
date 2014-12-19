package co.afrolabs.truevotenaija;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.appspot.afrolabs_truevote1.trueVoteService.TrueVoteService;
import com.appspot.afrolabs_truevote1.trueVoteService.model.RequestObject;
import com.appspot.afrolabs_truevote1.trueVoteService.model.VoteStatisticsResponse;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.json.gson.GsonFactory;

import java.io.IOException;


public class Result extends Activity {

    TrueVoteService.Builder builder = new TrueVoteService.Builder(
            AndroidHttp.newCompatibleTransport(), new GsonFactory(), null);
    TrueVoteService service = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class VoteResultTask extends AsyncTask<Void, Void, VoteStatisticsResponse> {
        Context context;

        public VoteResultTask(Context context) {
            this.context = context;
        }

        protected VoteStatisticsResponse doInBackground(Void... unused) {
            VoteStatisticsResponse scores = null;
            RequestObject request = new RequestObject();
            request.setMessage("PDP");
            TrueVoteService.Vote.Statistics stat;
            request.setRequestType("Status");
            try {
                scores=service.vote().statistics(request).execute();
            } catch (IOException e) {
                Log.d("TicTacToe", e.getMessage(), e);
            }
            return scores;
        }

        protected void onPostExecute(VoteStatisticsResponse scores) {
            // Do something with the result.
        }
    }
}
