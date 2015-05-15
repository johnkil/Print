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

package com.github.johnkil.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * View for displaying icons from iconic fonts.
 *
 * @author Evgeny Shishkin
 */
public class PrintView extends ImageView implements IPrintView {

    public PrintView(Context context) {
        super(context);
        init(context, null);
    }

    public PrintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PrintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PrintView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        PrintDrawable icon = PrintViewUtils.initIcon(context, attrs, isInEditMode());
        setImageDrawable(icon);
    }


    @Override
    public PrintDrawable getIcon() {
        return (PrintDrawable) getDrawable();
    }

    @Override
    public void setIconTextRes(@StringRes int resId) {
        getIcon().setIconTextRes(resId);
    }

    @Override
    public void setIconCodeRes(@IntegerRes int resId) {
        getIcon().setIconCodeRes(resId);
    }

    @Override
    public void setIconCode(int code) {
        getIcon().setIconCode(code);
    }

    @Override
    public void setIconText(CharSequence text) {
        getIcon().setIconText(text);
    }

    @Override
    public CharSequence getIconText() {
        return getIcon().getIconText();
    }

    @Override
    public void setIconColorRes(@ColorRes int resId) {
        getIcon().setIconColorRes(resId);
    }

    @Override
    public void setIconColor(int color) {
        getIcon().setIconColor(color);
    }

    @Override
    public void setIconColor(ColorStateList colors) {
        getIcon().setIconColor(colors);
    }

    @Override
    public final ColorStateList getIconColor() {
        return getIcon().getIconColor();
    }

    @Override
    public void setIconSizeRes(@DimenRes int resId) {
        getIcon().setIconSizeRes(resId);
    }

    @Override
    public void setIconSizeDp(float size) {
        getIcon().setIconSizeDp(size);
        // hack for calling resizeFromDrawable()
        setSelected(isSelected());
    }

    @Override
    public void setIconSize(int unit, float size) {
        getIcon().setIconSize(unit, size);
        // hack for calling resizeFromDrawable()
        setSelected(isSelected());
    }

    @Override
    public int getIconSize() {
        return getIcon().getIconSize();
    }

    @Override
    public void setIconFont(String path) {
        getIcon().setIconFont(path);
    }

    @Override
    public void setIconFont(Typeface font) {
        getIcon().setIconFont(font);
    }

    @Override
    public Typeface getIconFont() {
        return getIcon().getIconFont();
    }

}