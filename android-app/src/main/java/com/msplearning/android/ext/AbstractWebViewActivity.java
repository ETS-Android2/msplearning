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
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.msplearning.android.app.MSPLearningApp;
import com.msplearning.android.app.R;

/**
 * @author Roy Clarkson
 */
public abstract class AbstractWebViewActivity extends Activity implements AsyncActivity {

	protected static final String TAG = AbstractWebViewActivity.class.getSimpleName();

	private Activity activity;

	private WebView webView;

	private ProgressDialog progressDialog = null;

	private boolean destroyed = false;

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public MSPLearningApp getApplicationContext() {
		return (MSPLearningApp) super.getApplicationContext();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
		this.getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
		this.webView = new WebView(this);
		this.setContentView(this.webView);
		this.activity = this;

		this.webView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int progress) {
				AbstractWebViewActivity.this.activity.setTitle("Loading...");
				AbstractWebViewActivity.this.activity.setProgress(progress * 100);
				if (progress == 100) {
					AbstractWebViewActivity.this.activity.setTitle(R.string.app_name);
				}
			}
		});
	}

	// ***************************************
	// Protected methods
	// ***************************************
	protected WebView getWebView() {
		return this.webView;
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
