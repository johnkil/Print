/*
 * Copyright 2014 Evgeny Shishkin
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

package com.github.johnkil.print.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.github.johnkil.print.R;
import com.github.johnkil.print.drawable.PrintDrawable;

/**
 * Button for displaying icons from iconic fonts.
 *
 * @author Evgeny Shishkin
 */
public class PrintButton extends ImageButton {

    private PrintDrawable mIcon;
    private CharSequence mIconText;
    private ColorStateList mIconColor;
    private String mFontName;

    private int mIconSize;
    private int mCurIconColor;

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
        if (!isInEditMode()) {

            // setup default values
            CharSequence iconText = null;
            ColorStateList iconColor = getResources().getColorStateList(R.color.icon_color);
            int iconSize = getResources().getDimensionPixelSize(R.dimen.icon_size);
            String fontName = null;

            if (attrs != null) {

                TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PrintView);

                if (a.hasValue(R.styleable.PrintView_iconText)) {
                    iconText = a.getString(R.styleable.PrintView_iconText);
                }

                if (a.hasValue(R.styleable.PrintView_iconColor)) {
                    iconColor = a.getColorStateList(R.styleable.PrintView_iconColor);
                }

                if (a.hasValue(R.styleable.PrintView_fontName)) {
                    fontName = a.getString(R.styleable.PrintView_fontName);
                }

                iconSize = a.getDimensionPixelSize(R.styleable.PrintView_iconSize, iconSize);

                a.recycle();
            }

            setIconSize(iconSize);
            setIconText(iconText);
            setIconColor(iconColor);
            setFontName(fontName);

            invalidateIcon();
        }
    }

    /**
     * Invalidates the icon drawable.
     */
    protected void invalidateIcon() {
        mIcon = new PrintDrawable()
                .fontName(mFontName)
                .iconText(mIconText)
                .iconColor(mCurIconColor)
                .iconSize(mIconSize);
        setImageDrawable(mIcon);
    }

    private void updateIconColors() {
        int color = mIconColor.getColorForState(getDrawableState(), 0);
        if (color != mCurIconColor) {
            mCurIconColor = color;
            if (mIcon != null) {
                mIcon.iconColor(mCurIconColor);
            }
        }
    }

    /**
     * Return the icon, or null if no icon has been assigned.
     */
    public PrintDrawable getIcon() {
        return mIcon;
    }

    /**
     * Set icon text.
     *
     * @attr ref R.styleable#PrintView_iconText
     * @see #setIconText(CharSequence)
     * @see #getIconText()
     */
    public void setIconText(int resId) {
        setIconText(getContext().getResources().getText(resId));
    }

    /**
     * Set icon text.
     *
     * @attr ref R.styleable#PrintView_iconText
     * @see #setIconText(int)
     * @see #getIconText()
     */
    public void setIconText(CharSequence text) {
        mIconText = text;
        if (mIcon != null) {
            mIcon.iconText(mIconText);
        }
    }

    /**
     * Return the icon text the PrintButton is displaying
     *
     * @attr ref R.styleable#PrintView_iconText
     * @see #setIconText(int)
     * @see #setIconText(CharSequence)
     */
    public CharSequence getIconText() {
        return mIconText;
    }

    /**
     * Sets the icon color for all the states (normal, selected, focused) to be this color.
     *
     * @attr ref R.styleable#PrintView_iconColor
     * @see #setIconColor(ColorStateList)
     * @see #getIconColors()
     */
    public void setIconColor(int color) {
        setIconColor(ColorStateList.valueOf(color));
    }

    /**
     * Sets the icon color.
     *
     * @attr ref R.styleable#PrintView_iconColor
     * @see #setIconColor(int)
     * @see #getIconColors()
     */
    public void setIconColor(ColorStateList colors) {
        if (colors == null) {
            throw new NullPointerException();
        }
        mIconColor = colors;
        updateIconColors();
    }

    /**
     * Gets the icon colors for the different states (normal, selected, focused) of the PrintView.
     *
     * @attr ref R.styleable#PrintView_iconColor
     * @see #setIconColor(ColorStateList)
     * @see #setIconColor(int)
     */
    public final ColorStateList getIconColors() {
        return mIconColor;
    }

    /**
     * Set the icon size in pixels.
     *
     * @attr ref R.styleable#PrintView_iconSize
     * @see #getIconSize()
     */
    public void setIconSize(int size) {
        mIconSize = size;
        if (mIcon != null) {
            mIcon.iconSize(mIconSize);
            // hack for calling resizeFromDrawable()
            setSelected(isSelected());
        }
    }

    /**
     * Get the size (in pixels) of the icon in this PrintButton.
     *
     * @attr ref R.styleable#PrintView_iconSize
     * @see #setIconSize(int)
     */
    public int getIconSize() {
        return mIconSize;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (mIconColor != null && mIconColor.isStateful()) {
            updateIconColors();
        }
    }

    /**
     * Set the font name to be used for this button.
     *
     * @param fontName  The reference name of the font.
     */
    public void setFontName(String fontName) {
        this.mFontName = fontName;

        if(mIcon != null)
        {
            mIcon.fontName(fontName);
        }
    }
}