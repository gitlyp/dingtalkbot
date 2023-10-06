## 钉钉 gpt 机器人

钉钉 + FastGpt java版机器人,在钉钉对话中实现自动答疑功能.
该机器人使用钉钉 stream 模式，无需公共 ip 在本地部署即可实现对单聊、群聊消息的响应。

## 快速开始

1、准备 FastGPT、钉钉 AppKey、ClientSecret, 修改配置文件

2、ide 中启动应用或者打包后启动应用，即可直接使用

打包应用
```bash
mvn clean package
```
启动应用
```bash
 java -jar bot-0.0.1-SNAPSHOT.jar
```

## 相关链接-参考文档
[FastGPT](https://github.com/labring/FastGPT) \n
[钉钉开放平台](https://open.dingtalk.com/document/orgapp/dingtalk-chatbot-for-one-on-one-query) \n
[DingTalk Stream Mode demo](https://github.com/open-dingtalk/dingtalk-stream-sdk-java-quick-start) 机器人初始化部分代码直接 copy 这里的 \n

## 计划

未完待续，后续完善。。。
