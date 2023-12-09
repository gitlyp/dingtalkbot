package com.rock.ai.bot;

import com.aliyun.dingtalkrobot_1_0.models.QueryRobotPluginResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import org.junit.jupiter.api.Test;

/**
 * @author: Rock.L
 * @date: 2023/11/21
 */
public class BotTest {

    @Test
    public  void getToken() {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
            OapiGettokenRequest req = new OapiGettokenRequest();
            req.setAppkey("ding1rsbqftsykfoqk3c");
            req.setAppsecret("jHxLYHT2nPju3kUGWphJ0t55ykox18WWTY90n6mmXqzVHqcd9vi4fUwClZ7s8cCr");
            req.setHttpMethod("GET");
            OapiGettokenResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用 Token 初始化账号Client
     * @return Client
     * @throws Exception
     */
    public static com.aliyun.dingtalkrobot_1_0.Client createClient() throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config();
        config.protocol = "https";
        config.regionId = "central";
        return new com.aliyun.dingtalkrobot_1_0.Client(config);
    }

    @Test
    public void getLink() throws Exception {
        com.aliyun.dingtalkrobot_1_0.Client client = BotTest.createClient();
        com.aliyun.dingtalkrobot_1_0.models.QueryRobotPluginHeaders queryRobotPluginHeaders = new com.aliyun.dingtalkrobot_1_0.models.QueryRobotPluginHeaders();
        queryRobotPluginHeaders.xAcsDingtalkAccessToken = "2a623584cc0c3d11bd82a9cc9d490602";
        com.aliyun.dingtalkrobot_1_0.models.QueryRobotPluginRequest queryRobotPluginRequest = new com.aliyun.dingtalkrobot_1_0.models.QueryRobotPluginRequest()
                .setRobotCode("ding1rsbqftsykfoqk3c");
        try {
            QueryRobotPluginResponse queryRobotPluginResponse = client.queryRobotPluginWithOptions(queryRobotPluginRequest, queryRobotPluginHeaders, new RuntimeOptions());
            System.out.println(queryRobotPluginResponse.getBody());
        } catch (TeaException err) {
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        } catch (Exception _err) {
            TeaException err = new TeaException(_err.getMessage(), _err);
            if (!com.aliyun.teautil.Common.empty(err.code) && !com.aliyun.teautil.Common.empty(err.message)) {
                // err 中含有 code 和 message 属性，可帮助开发定位问题
            }

        }
    }
}
