package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.filter.LoginBeforeFilter;

public interface LoginBeforeFilterService {
    /**
     * 前置过滤器
     */
    void addBeforeFilter(LoginBeforeFilter loginFilter);
}
