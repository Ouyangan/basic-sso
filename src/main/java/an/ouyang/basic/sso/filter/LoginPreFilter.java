package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginParam;
import an.ouyang.basic.sso.LoginPreParam;
import org.apache.commons.lang3.tuple.Triple;

public interface LoginPreFilter {

    Triple<Integer, String, LoginParam> filter(LoginPreParam param);

    int getOrder();

}
