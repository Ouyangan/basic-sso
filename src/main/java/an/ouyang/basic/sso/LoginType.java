package an.ouyang.basic.sso;

import java.util.concurrent.TimeUnit;

public enum LoginType {
    namePw(7, TimeUnit.DAYS),
    mobilePw(7, TimeUnit.DAYS),
    emailPw(7, TimeUnit.DAYS),
    mobileCode(7, TimeUnit.DAYS),
    qqOauth2(7, TimeUnit.DAYS),
    wechatOauth2(7, TimeUnit.DAYS),
    wxCpOauth2(7, TimeUnit.DAYS),
    dingdingOauth2(7, TimeUnit.DAYS),
    feishuOauth2(7, TimeUnit.DAYS);

    private final static String separator = ":";
    private final int expire;
    private final TimeUnit timeUnit;

    LoginType(int expire, TimeUnit timeUnit) {
        this.expire = expire;
        this.timeUnit = timeUnit;
    }

    public int getExpire() {
        return expire;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public String key(String token) {
        return this.name() + separator + token;
    }
}
