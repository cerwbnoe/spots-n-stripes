package common.di;

import android.content.Context;

import common.DatabaseHelper;
import common.services.ArticleReadRepo;
import common.services.ArticleReadService;
import common.services.contracts.IArticleReadRepo;
import common.services.contracts.IArticleReadService;
import dagger.Module;
import dagger.Provides;

@Module
public class ServicesModule {

    private Context _ctx;

    public ServicesModule(Context ctx) {
        _ctx = ctx;
    }

    @Provides IArticleReadService provideArticleReadService(IArticleReadRepo repo){
        return new ArticleReadService(repo);
    }

    @Provides IArticleReadRepo provideArticleReadRepo(DatabaseHelper dbHelper) {
        return new ArticleReadRepo(dbHelper);
    }

    @Provides DatabaseHelper provideDbHelper() {
        return new DatabaseHelper(_ctx);
    }
}
