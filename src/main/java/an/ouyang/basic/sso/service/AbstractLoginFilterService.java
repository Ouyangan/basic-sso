package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.filter.LoginAfterFilter;
import an.ouyang.basic.sso.filter.LoginBeforeFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class AbstractLoginFilterService implements
        LoginAfterFilterService,
        LoginBeforeFilterService{
    private final static Logger log = LoggerFactory.getLogger(AbstractLoginFilterService.class);

    protected final List<LoginBeforeFilter> loginBeforeFilters = new ArrayList<>();
    protected final List<LoginAfterFilter> loginAfterFilters = new ArrayList<>();

    @Override
    public void addBeforeFilter(LoginBeforeFilter filter) {
        loginBeforeFilters.add(filter);
        log.info("[{}] add beforeFilter:{}", this.getClass().getSimpleName(), filter.getClass().getSimpleName());
        loginBeforeFilters.sort(Comparator.comparingInt(LoginBeforeFilter::getOrder));
    }

    @Override
    public void addAfterFilter(LoginAfterFilter filter) {
        loginAfterFilters.add(filter);
        log.info("[{}] add afterFilter:{}", this.getClass().getSimpleName(), filter.getClass().getSimpleName());
        loginAfterFilters.sort(Comparator.comparingInt(LoginAfterFilter::getOrder));
    }

}
