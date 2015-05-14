/*
 * Copyright (C) 2014 Evgeny Shishkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.johnkil.print.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.johnkil.print.PrintDrawable;
import com.github.johnkil.print.PrintView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PrintView iconView = (PrintView) findViewById(R.id.icon);
        final TextView sizeValue = (TextView) findViewById(R.id.size_value);
        SeekBar sizeBar = (SeekBar) findViewById(R.id.size_bar);
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sizeValue.setText(getString(R.string.size_format, progress));
                iconView.setIconSizeDp(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sizeBar.setProgress(320);

        // enable states for icon.
        iconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on click process
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.action_info).setIcon(
                new PrintDrawable.Builder(this)
                        .iconTextRes(R.string.ic_holo_about)
                        .iconColorRes(R.color.ab_icon_color)
                        .iconSizeRes(R.dimen.ab_icon_size)
                        .iconFont("fonts/holo-icon-font.ttf")
                        .build()
        );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}