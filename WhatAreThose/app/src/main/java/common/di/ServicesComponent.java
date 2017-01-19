package common.di;

import javax.inject.Singleton;

import common.services.contracts.IArticleReadService;
import dagger.Component;

@Singleton
@Component(modules = {ServicesModule.class})
public interface ServicesComponent {
    IArticleReadService provideArticleReadService();
}
