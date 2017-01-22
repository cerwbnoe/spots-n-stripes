package common.model;

import android.content.ContentValues;

public interface DbWritable {
    ContentValues toContentValues();
}
