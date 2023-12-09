## 钉钉 gpt 机器人

钉钉 + FastGpt java版机器人,在钉钉对话中实现自动答疑功能.
该机器人使用钉钉 stream 模式，无需公共 ip 在本地部署即可实现对单聊、群聊消息的响应。

## 快速开始

1、准备 FastGPT、钉钉 AppKey、ClientSecret, 修改配置文件

2、ide 中启动应用或者打包后启动应用，即可直接使用

2.1 打包应用

```bash
mvn clean package
```
2.2 启动应用

```bash
 java -jar bot-0.0.1-SNAPSHOT.jar
```

3、docker 运行

3.1

打包应用，项目根目录执行

```bash
mvn clean package
```

3.1 构建自己镜像，项目根目录执行

```bash
docker build -t botapp:v1 .
```
3.2准备配置文件，启动容器

```bash
 docker run -d -p 8110:8080 -v /<your-path>/conf/:/conf botapp:v1 --name bot
```

## 相关链接-参考文档

[FastGPT](https://github.com/labring/FastGPT)

[钉钉开放平台](https://open.dingtalk.com/document/orgapp/dingtalk-chatbot-for-one-on-one-query)

[DingTalk Stream Mode demo](https://github.com/open-dingtalk/dingtalk-stream-sdk-java-quick-start) 机器人初始化部分代码直接 copy 这里的

## 计划

未完待续，后续完善。。。
