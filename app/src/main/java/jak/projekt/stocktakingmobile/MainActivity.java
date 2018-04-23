package jak.projekt.stocktakingmobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onStartClick(View view){
        if (view.getId() == R.id.btnStart){
            Intent i = new Intent(MainActivity.this, Stocktaking.class);
            startActivity(i);
        }
    }
}
