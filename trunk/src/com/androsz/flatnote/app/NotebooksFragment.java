/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androsz.flatnote.app;

// Need the following import to get access to the app resources, since this
// class is in a sub-package.

import java.util.ArrayList;

import com.androsz.flatnote.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

/**
 * This application demonstrates the ability to transform views in 2D and 3D,
 * scaling them, translating them, and rotating them (in 2D and 3D). Use the
 * seek bars to set the various transform properties of the button.
 */
public class NotebooksFragment extends Fragment {

	@Override
	public void onActivityCreated(final Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// go "up"
		// one activity in the app's Activity heirarchy.
		Activity activity = this.getActivity();

		final NotebooksScrollView container = (NotebooksScrollView) activity
				.findViewById(R.id.notebooks_scroll);

		ArrayList<Button> notebooks = new ArrayList<Button>();

		Button b1 = (Button) activity.findViewById(R.id.rotatingButton);
		notebooks.add(b1);
		notebooks.add((Button) activity.findViewById(R.id.rotatingButton2));
		notebooks.add((Button) activity.findViewById(R.id.rotatingButton3));
		notebooks.add((Button) activity.findViewById(R.id.rotatingButton4));
		notebooks.add((Button) activity.findViewById(R.id.rotatingButton5));
		container.setNotebooks(notebooks);

		if (savedInstanceState != null) {
			container.post(new Runnable() {

				@Override
				public void run() {
					container.smoothScrollTo(savedInstanceState.getInt("sX"),
							savedInstanceState.getInt("sY"));
				}
			});
		}
		else
		{
			container.post(new Runnable() {

				@Override
				public void run() {
					container.smoothScrollTo(1,0);
				}
			});
		}
	}
	
	public void onClick(View v)
	{
	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		NotebooksScrollView container = (NotebooksScrollView) getActivity()
				.findViewById(R.id.notebooks_scroll);
		outState.putInt("sX", container.getScrollX());
		outState.putInt("sY", container.getScrollY());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_notebooks, container);
		return view;
	}
}