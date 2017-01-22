package common.model;

import android.database.Cursor;

import java.util.UUID;

public class Shirt extends Article {
    public Shirt(long _id, String _name, ArticleType _type) {
        super(_id, _name, _type);
    }

    public Shirt(String _name, ArticleType _type) {
        super(_name, _type);
    }

    public static Shirt createFromCursor(Cursor c) {
        int idIndex = c.getColumnIndex("id");
        int nameIndex = c.getColumnIndex("name");
        int typeIndex = c.getColumnIndex("type");
        long id = c.getLong(idIndex);
        String name = c.getString(nameIndex);
        ArticleType type = ArticleType.valueOf(c.getString(typeIndex));
        return new Shirt(id, name, type);
    }
}
