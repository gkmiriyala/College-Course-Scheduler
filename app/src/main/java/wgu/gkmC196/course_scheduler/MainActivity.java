package wgu.gkmC196.course_scheduler;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int TERM_VIEWER_ACTIVITY_CODE = 11111;
    private static final int TERM_LIST_ACTIVITY_CODE = 22222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(wgu.gkmC196.course_scheduler.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(wgu.gkmC196.course_scheduler.R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void openCurrentTerm(View view) {
        Cursor c = getContentResolver().query(DataProvider.TERMS_URI, null, DBOpenHelper.TERM_ACTIVE
                + " =1", null, null);
        while (c.moveToNext()) {
            Intent intent = new Intent(this, TermViewerActivity.class);
            long id = c.getLong(c.getColumnIndex(DBOpenHelper.TERMS_TABLE_ID));
            Uri uri = Uri.parse(DataProvider.TERMS_URI + "/" + id);
            intent.putExtra(DataProvider.TERM_CONTENT_TYPE, uri);
            startActivityForResult(intent, TERM_VIEWER_ACTIVITY_CODE);
            return;
        }
        Toast.makeText(this, getString(wgu.gkmC196.course_scheduler.R.string.no_active_term_set),
                Toast.LENGTH_SHORT).show();
    }

    public void openTermList(View view) {
        Intent intent = new Intent(this, TermListActivity.class);
        startActivityForResult(intent, TERM_LIST_ACTIVITY_CODE);
    }
}
