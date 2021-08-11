package an.ouyang.basic.sso.service;

import an.ouyang.basic.sso.filter.LoginAfterFilter;

public interface LoginAfterFilterService {


    /**
     * 后置过滤器
     */
    void addAfterFilter(LoginAfterFilter loginAfterFilter);
}
