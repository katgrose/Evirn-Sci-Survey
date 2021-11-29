package com.example.evirn_sci_survey;

import android.content.Context;
import android.content.Intent;

public interface EditListItem {

    public String getListItemText();
    public Intent getListItemEditIntent(Context context);
    public void DeleteSelf(Context context);
}
