/*
 * Copyright 2010-2014 the original author or authors.
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

package com.msplearning.android.ext;

import android.app.Activity;
import android.app.ProgressDialog;

import com.msplearning.android.app.MSPLearningApp;

/**
 * @author Roy Clarkson
 * @author Pierre-Yves Ricau
 */
public abstract class AbstractAsyncActivity extends Activity implements AsyncActivity {

	protected static final String TAG = AbstractAsyncActivity.class.getSimpleName();

	private ProgressDialog progressDialog;

	private boolean destroyed = false;

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public MSPLearningApp getApplicationContext() {
		return (MSPLearningApp) super.getApplicationContext();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.destroyed = true;
	}

	// ***************************************
	// Public methods
	// ***************************************
	@Override
	public void showLoadingProgressDialog() {
		this.showProgressDialog("Loading. Please wait...");
	}

	@Override
	public void showProgressDialog(CharSequence message) {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setIndeterminate(true);
		}

		this.progressDialog.setMessage(message);
		this.progressDialog.show();
	}

	@Override
	public void dismissProgressDialog() {
		if (this.progressDialog != null && !this.destroyed) {
			this.progressDialog.dismiss();
		}
	}

}
