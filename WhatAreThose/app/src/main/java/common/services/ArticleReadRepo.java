package common.services;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import common.DatabaseHelper;
import common.model.Article;
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
        List<Shirt> list = new ArrayList<>();
        Cursor c = _dbHelper.query("SELECT id, name, type FROM Article");
        if(c.moveToFirst()) {
            do {
                list.add(Shirt.createFromCursor(c));
            } while(c.moveToNext());
        }
        c.close();
        return list;
    }
}
