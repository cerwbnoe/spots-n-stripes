package common.model;

import java.util.UUID;

public abstract class Article {

    private UUID _id;
    private String _name;
    private ArticleType _type;

    public Article(UUID _id, String _name, ArticleType _type) {
        this._id = _id;
        this._name = _name;
        this._type = _type;
    }

    public Article(String _name, ArticleType _type) {
        this._id = UUID.randomUUID();
        this._name = _name;
        this._type = _type;
    }

    public UUID getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public ArticleType getType() {
        return _type;
    }
}
