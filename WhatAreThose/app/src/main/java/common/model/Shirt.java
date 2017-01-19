package common.model;

import java.util.UUID;

public class Shirt extends Article {
    public Shirt(UUID _id, String _name, ArticleType _type) {
        super(_id, _name, _type);
    }

    public Shirt(String _name, ArticleType _type) {
        super(_name, _type);
    }
}
