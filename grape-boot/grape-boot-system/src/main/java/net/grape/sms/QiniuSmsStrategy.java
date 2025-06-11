package net.grape.sms;

import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import net.grape.framework.common.exception.ServerException;
import net.grape.sms.config.SmsConfig;

import java.util.Map;

/**
 * 七牛云短信
 *
 * @author 1161095164@qq.com
 *  
 */
public class QiniuSmsStrategy implements SmsStrategy {
    private final SmsConfig smsConfig;
    private final SmsManager smsManager;

    public QiniuSmsStrategy(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;

        Auth auth = Auth.create(smsConfig.getAccessKey(), smsConfig.getSecretKey());
        smsManager = new SmsManager(auth);
    }

    @Override
    public void send(String mobile, Map<String, String> params) {
        try {
            Response response = smsManager.sendSingleMessage(smsConfig.getTemplateId(), mobile, params);

            // 是否发送成功
            if (!response.isOK()) {
                throw new ServerException(response.error);
            }
        } catch (Exception e) {
            throw new ServerException(e.getMessage());
        }
    }
}
