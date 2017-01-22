package common.model;

import android.content.ContentValues;

public abstract class Article implements DbWritable {

    private long _id;
    private String _name;
    private ArticleType _type;

    public Article(long _id, String _name, ArticleType _type) {
        this._id = _id;
        this._name = _name;
        this._type = _type;
    }

    public Article(String _name, ArticleType _type) {
        this._name = _name;
        this._type = _type;
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public ArticleType getType() {
        return _type;
    }

    @Override
    public ContentValues toContentValues() {
        ContentValues vals = new ContentValues();
        vals.put("type", _type.toString());
        vals.put("name", _name);
        return vals;
    }
}
