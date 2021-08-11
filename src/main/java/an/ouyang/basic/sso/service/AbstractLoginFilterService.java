package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.filter.LoginAfterFilter;
import an.ouyang.basic.sso.filter.LoginLogAfterFilter;
import an.ouyang.basic.sso.filter.LoginPreFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractLoginFilterService implements
        LoginAfterFilterService,
        LoginPreFilterService,
        ServiceLifeCycle {
    private final static Logger log = LoggerFactory.getLogger(AbstractLoginFilterService.class);

    private final List<LoginPreFilter> loginPreFilters = new ArrayList<>();

    private final List<LoginAfterFilter> loginAfterFilters = new ArrayList<>();

    @Override
    public void addPreFilter(LoginPreFilter loginFilter) {
        loginPreFilters.add(loginFilter);
        log.info("[{}] add preFilter:{}", this.getClass().getSimpleName(), loginFilter.getClass().getSimpleName());
        loginPreFilters.sort(Comparator.comparingInt(LoginPreFilter::getOrder));
    }

    @Override
    public void addAfterFilter(LoginAfterFilter loginAfterFilter) {
        loginAfterFilters.add(loginAfterFilter);
        log.info("[{}] add afterFilter:{}", this.getClass().getSimpleName(), loginAfterFilter.getClass().getSimpleName());
        loginPreFilters.sort(Comparator.comparingInt(LoginPreFilter::getOrder));
    }

    @Override
    public void init() {
        //统一增加日志打印filter
        addAfterFilter(new LoginLogAfterFilter());
    }

    @Override
    public void close() {
        loginPreFilters.clear();
        loginAfterFilters.clear();
    }
}
