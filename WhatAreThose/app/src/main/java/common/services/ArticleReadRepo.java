package common.services;

import android.content.ContentValues;

import java.util.List;

import javax.inject.Inject;

import common.DatabaseHelper;
import common.model.Shirt;
import common.services.contracts.IArticleReadRepo;

public class ArticleReadRepo implements IArticleReadRepo {

    private DatabaseHelper _dbHelper;

    @Inject
    public ArticleReadRepo(DatabaseHelper _dbHelper) {
        this._dbHelper = _dbHelper;
    }

    @Override
    public List<Shirt> getAllShirts() {
        ContentValues vals = new ContentValues();
        vals.put("type", "SHIdsfRT");
        vals.put("fileName", "shirt.png");
        _dbHelper.getWritableDatabase().insert("wat", null, vals);
        _dbHelper.close();
        return null;
    }
}
