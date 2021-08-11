package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.filter.LoginBeforeFilter;
import an.ouyang.basic.sso.filter.LoginPreFilter;

public interface LoginPreFilterService {
    /**
     * 前置过滤器
     */
    void addLoginPreFilter(LoginPreFilter filter);
}
