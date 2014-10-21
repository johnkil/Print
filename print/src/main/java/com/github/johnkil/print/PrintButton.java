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

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageButton;

/**
 * Button for displaying icons from iconic fonts.
 *
 * @author Evgeny Shishkin
 */
public class PrintButton extends ImageButton implements IPrintView {

    public PrintButton(Context context) {
        super(context);
        init(context, null);
    }

    public PrintButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PrintButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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
    public void setIconText(int resId) {
        getIcon().setIconText(resId);
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
    public void setIconColor(int resId) {
        getIcon().setIconColor(resId);
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
    public void setIconSize(int resId) {
        getIcon().setIconSize(resId);
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