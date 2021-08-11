package an.ouyang.basic.sso;

import java.util.Map;

public class DefaultLoginToken implements LoginToken {
    private final Long userId;
    private final String username;
    private final String headImg;
    private final Long merchantId;
    private final String token;
    private final Integer tokenExpire;
    private final LoginType loginType;
    private final Long createTime;
    private final Map<String, Object> extProperty;


    public DefaultLoginToken(Long userId, String username, String headImg, Long merchantId, String token,
                             Integer tokenExpire, LoginType loginType, Long createTime, Map<String, Object> extProperty) {
        this.userId = userId;
        this.username = username;
        this.headImg = headImg;
        this.merchantId = merchantId;
        this.token = token;
        this.tokenExpire = tokenExpire;
        this.loginType = loginType;
        this.createTime = createTime;
        this.extProperty = extProperty;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getHeadImg() {
        return headImg;
    }

    @Override
    public Long getMerchantId() {
        return merchantId;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Integer getTokenExpireTime() {
        return tokenExpire;
    }

    @Override
    public LoginType getLoginType() {
        return loginType;
    }

    @Override
    public Long getCreateTime() {
        return createTime;
    }

    @Override
    public Map<String, Object> getExtProperty() {
        return extProperty;
    }

}
