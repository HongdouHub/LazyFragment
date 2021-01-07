package chivas.example.com.mylazyviewpager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.btn_simple)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TestDelayLoadFragmentSimpleActivity.class);
            }
        });
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        super.startActivity(intent);
    }
}
