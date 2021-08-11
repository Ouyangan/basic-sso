package an.ouyang.basic.sso.filter;

import an.ouyang.basic.sso.LoginToken;
import an.ouyang.basic.sso.LoginParam;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 登录日志,最后执行
 */
public class LoginLogAfterFilter implements LoginAfterFilter {


    private final static Logger log = LoggerFactory.getLogger(LoginLogAfterFilter.class);

    @Override
    public void filter(LoginParam loginParam, Triple<Integer, String, LoginToken> result) {
        log.info("账户登录. 耗时={} param={},code={} msg={} data={}",
                TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - loginParam.getCreateTime()),
                loginParam,
                result.getLeft(),
                result.getMiddle(),
                result.getRight());
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
