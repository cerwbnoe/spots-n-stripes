package common.services;

import java.util.List;

import javax.inject.Inject;

import common.model.Shirt;
import common.services.contracts.IArticleReadRepo;
import common.services.contracts.IArticleReadService;

public class ArticleReadService implements IArticleReadService {

    private IArticleReadRepo _repo;

    @Inject
    public ArticleReadService(IArticleReadRepo _repo) {
        this._repo = _repo;
    }

    @Override
    public List<Shirt> getAllShirts() {
        return _repo.getAllShirts();
    }
}
