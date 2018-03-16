package wizrole.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import wizrole.materialdesign.alipay.AlipayActivity;
import wizrole.materialdesign.nomal.NomalActivity;
import wizrole.materialdesign.tablayout.TabLayoutActivity;
import wizrole.materialdesign.title.TitleActivity;

/**
 * Created by liushengping on 2018/2/28.
 * 何人执笔？
 */

public class Main extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.alipay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this, AlipayActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this, NomalActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this, TitleActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tablayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this, TabLayoutActivity.class);
                startActivity(intent);
            }
        });
    }
}
