package common.services.contracts;

import java.util.List;

import common.model.Shirt;

public interface IArticleReadRepo {
    List<Shirt> getAllShirts();
}
