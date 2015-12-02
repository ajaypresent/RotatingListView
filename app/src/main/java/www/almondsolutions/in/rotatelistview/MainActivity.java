package www.almondsolutions.in.rotatelistview;

import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ListActivity {

    private Integer a[] = new Integer[100];
    private int max_count = 100, retain_item, retain_item1;
    private ArrayAdapter<Integer> arrayAdapter;
    final Handler handler = new Handler();
    private int position;
    private boolean CONTINUE = true;
    private TextView displayTime;
    private Handler timeHandler;
    private long startTime;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayTime = (TextView) findViewById(R.id.displayTime);
        timeHandler = new Handler();
        arrayAdapter = new ArrayAdapter<Integer>(MainActivity.this, android.R.layout.simple_list_item_1, a);
        timer = new Timer();

        startTime = System.currentTimeMillis();

        for (int i = 0; i < max_count; i++) {
            a[i] = i;
        }

        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            CONTINUE = true;
                            position = 0;
                            retain_item = a[position];
                            retain_item1 = a[position + 5];
                            while (CONTINUE) {
                                a[position] = a[position + 1];
                                a[position + 5] = a[position + 6];
                                position++;
                                if (position == 4) {
                                    CONTINUE = false;
                                }
                            }
                            a[position] = retain_item;
                            a[position + 5] = retain_item1;
                            arrayAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 60000, 60000);

        timeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000);
                seconds = seconds % 60;
                displayTime.setText("" + seconds);
                timeHandler.postDelayed(this, 500);
            }
        }, 0);

        setListAdapter(arrayAdapter);
    }
}
