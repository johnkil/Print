package com.github.johnkil.print;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by orlandoleite on 2/28/18.
 */

public class PrintButton extends Button implements IPrintView
{
	PrintDrawable firstFound = null;
	
	public PrintButton( Context context )
	{
		super( context );
		init( context, null );
	}
	
	public PrintButton( Context context, AttributeSet attrs )
	{
		super( context, attrs );
		init( context, attrs );
	}
	
	public PrintButton( Context context, AttributeSet attrs, int defStyleAttr )
	{
		super( context, attrs, defStyleAttr );
		init( context, attrs );
	}
	
	@TargetApi( Build.VERSION_CODES.LOLLIPOP )
	public PrintButton( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes )
	{
		super( context, attrs, defStyleAttr, defStyleRes );
		init( context, attrs );
	}
	
	private void init( Context context, AttributeSet attrs )
	{
		PrintDrawable left = null, right = null, start = null, end = null, top = null, bottom = null;
		String iconFontPath = null;
		ColorStateList iconColor = null;
		float iconSize;
		
		if (attrs != null)
		{
			boolean inEditMode = isInEditMode();
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PrintView);
			
			if (!inEditMode && a.hasValue(R.styleable.PrintView_print_iconFont)) {
				iconFontPath = a.getString(R.styleable.PrintView_print_iconFont);
			}
			
			iconColor = a.hasValue(R.styleable.PrintView_print_iconColor) ? 
					a.getColorStateList(R.styleable.PrintView_print_iconColor) :
					getTextColors();
			
			iconSize = a.getDimensionPixelSize(R.styleable.PrintView_print_iconSize, 0);
			if( iconSize == 0 ) iconSize = getTextSize();
			
			if( a.hasValue( R.styleable.PrintView_print_iconTextLeft ) )
			{
				left = PrintViewUtils.buildIcon(context, a.getString( R.styleable.PrintView_print_iconTextLeft ),
						0, iconFontPath, iconColor, iconSize, inEditMode);
				firstFound = left;
			}
			
			if( a.hasValue( R.styleable.PrintView_print_iconTextRight ) )
			{
				right = PrintViewUtils.buildIcon(context, a.getString( R.styleable.PrintView_print_iconTextRight ),
						0, iconFontPath, iconColor, iconSize, inEditMode);
				if( firstFound == null ) firstFound = right;
			}
			
			if( a.hasValue( R.styleable.PrintView_print_iconTextStart ) )
			{
				start = PrintViewUtils.buildIcon(context, a.getString( R.styleable.PrintView_print_iconTextStart ), 
						0, iconFontPath, iconColor, iconSize, inEditMode);
				firstFound = start;
			}
			
			if( a.hasValue( R.styleable.PrintView_print_iconTextEnd ) )
			{
				end = PrintViewUtils.buildIcon(context, a.getString( R.styleable.PrintView_print_iconTextEnd ), 
						0, iconFontPath, iconColor, iconSize, inEditMode);
				if( firstFound == null ) firstFound = end;
			}
			
			if( a.hasValue( R.styleable.PrintView_print_iconTextTop ) )
			{
				top = PrintViewUtils.buildIcon(context, a.getString( R.styleable.PrintView_print_iconTextTop ), 
						0, iconFontPath, iconColor, iconSize, inEditMode);
				if( firstFound == null ) firstFound = top;
			}
			
			if( a.hasValue( R.styleable.PrintView_print_iconTextBottom ) )
			{
				bottom = PrintViewUtils.buildIcon(context, a.getString( R.styleable.PrintView_print_iconTextBottom ),
						0, iconFontPath, iconColor, iconSize, inEditMode);
				if( firstFound == null ) firstFound = bottom;
			}
			
			a.recycle();
		}
		
		if( start != null || end != null )
			setCompoundDrawablesRelativeWithIntrinsicBounds( start, end, top, bottom );
		else
			setCompoundDrawables( left, right, top, bottom );
	}
	
	@Override
	public PrintDrawable getIcon()
	{
		return firstFound;
	}
	
	@Override
	public void setIconTextRes( @StringRes int resId )
	{
		getIcon().setIconTextRes( resId );
	}
	
	@Override
	public void setIconCodeRes( @IntegerRes int resId )
	{
		getIcon().setIconCodeRes( resId );
	}
	
	@Override
	public void setIconCode( int code )
	{
		getIcon().setIconCode( code );
	}
	
	@Override
	public void setIconText( CharSequence text )
	{
		getIcon().setIconText( text );
	}
	
	@Override
	public CharSequence getIconText()
	{
		return getIcon().getIconText();
	}
	
	@Override
	public void setIconColorRes( @ColorRes int resId )
	{
		getIcon().setIconColorRes( resId );
	}
	
	@Override
	public void setIconColor( int color )
	{
		getIcon().setIconColor( color );
	}
	
	@Override
	public void setIconColor( ColorStateList colors )
	{
		getIcon().setIconColor( colors );
	}
	
	@Override
	public final ColorStateList getIconColor()
	{
		return getIcon().getIconColor();
	}
	
	@Override
	public void setIconSizeRes( @DimenRes int resId )
	{
		getIcon().setIconSizeRes( resId );
	}
	
	@Override
	public void setIconSizeDp( float size )
	{
		getIcon().setIconSizeDp( size );
		// hack for calling resizeFromDrawable()
		setSelected( isSelected() );
	}
	
	@Override
	public void setIconSize( int unit, float size )
	{
		getIcon().setIconSize( unit, size );
		// hack for calling resizeFromDrawable()
		setSelected( isSelected() );
	}
	
	@Override
	public int getIconSize()
	{
		return getIcon().getIconSize();
	}
	
	@Override
	public void setIconFont( String path )
	{
		getIcon().setIconFont( path );
	}
	
	@Override
	public void setIconFont( Typeface font )
	{
		getIcon().setIconFont( font );
	}
	
	@Override
	public Typeface getIconFont()
	{
		return getIcon().getIconFont();
	}
	
}