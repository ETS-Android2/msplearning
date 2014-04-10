package com.msplearning.android.widget;

import org.androidannotations.annotations.EBean;

import com.msplearning.android.widget.generic.AbstractListAdapter;
import com.msplearning.entity.AppUser;

@EBean
public class UserRequestListAdapter extends AbstractListAdapter<AppUser, UserRequestItemView> {

	@Override
	public UserRequestItemView buildSpecificViewUsingAA() {
		return UserRequestItemView_.build(super.mContext);
	}

}