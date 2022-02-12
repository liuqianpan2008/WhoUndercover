# 谁是卧底

> 基于 [mamoe/mirai-console](https://github.com/mamoe/mirai-console) 开发的文字小游戏

**使用前应该查阅的相关文档或项目**

- [User Manual](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md)
- [Permission Command](https://github.com/mamoe/mirai/blob/dev/mirai-console/docs/BuiltInCommands.md#permissioncommand)
- [Chat Command](https://github.com/project-mirai/chat-command)

确保真正会使用mirai后在下载食用本插件。

# 游戏流程

给没有玩过同学介简单说下流程

在场7个人中6个人拿到相同的一个[词语](https://baike.baidu.com/item/词语/38321)，剩下的1个拿到与之相关的另一个词语。每人每轮只能说一句话描述自己拿到的词语（不能直接说出那个词语），既不能让卧底发现，也要给同伴以暗示。每轮描述完毕，7人投票选出怀疑是卧底的那个人，得票数最多的人出局；平票则进入下一轮描述。若最后仅剩三人（包含卧底），则卧底获胜；反之，则其他人获胜。

> 转自[谁是卧底（快乐大本营游戏）_百度百科 (baidu.com)](https://baike.baidu.com/item/谁是卧底/17577018)

为了防止玩的时候凑不起怎么多人，本插件优化至少三人参加。（一个卧底，2正常玩家）正常玩家剩余1个为卧底获胜反正玩家获胜。

## 下载

[Releases ](https://github.com/liuqianpan2008/WhoUndercover/releases)

# 插件流程

### 首先由任意一名群友发起创建游戏

![image.png](https://i.postimg.cc/wB5NSK8r/image.png)

### 其他群友可以输入指令加入游戏！

![image.png](https://i.postimg.cc/vHp6T8Gj/image.png)

### 人数足够后（至少三人），可以由任意群友发起开始游戏

* 发起游戏后，玩家**不能在加入游戏**中。只能暂停重新开始下一局游戏
* 请自行确认玩家是否都是与bot为**好友关系**，若无好友关系可能收不到词条
* 请确保**词库**内有词条否则无法正常开启游戏
* 开启游戏的指令环境为**自动判断游戏**进程的指令环境。

开始游戏图片

![开始游戏](https://i.postimg.cc/KzM8dBM8/image.png)

收到消息图片

![image.png](https://i.postimg.cc/Hn1kHnw4/image.png)

### 描述阶段

每个参与的玩家进行词条的描述。描述完毕后会自动进入投票环节。

* 目前只支持文字描述，不支持其他描述方式

描述

![image.png](https://i.postimg.cc/59mVjsbM/image.png)

描述状态结束提示

![image.png](https://i.postimg.cc/3r1s3WF8/image.png)

查看描述词

* 可在**描述**阶段和**投票**阶段随时查看**本轮**描述词

![image.png](https://i.postimg.cc/m2084GJZ/image.png)

### 投票阶段

每个玩家描述完成后就可以进入投票阶段。

* 每个参与玩家持有一票，只能投给参与玩家的票数。投票时候需要**`@`**对方才能使用。（若无法使用@则需要输入完整的QQ号）

![QQ-20220212085544.png](https://i.postimg.cc/qvxbx5Dk/QQ-20220212085544.png)

* 只能在**投票阶段**查看票数

![image.png](https://i.postimg.cc/3wwBCJDB/image.png)

投票完成后将继续判断玩家胜负！若未达到胜负条件即可返回描述阶段继续第二轮游戏。

## 配置设置

> 在``data``目录下会生成本插件相应的配置文件

第一次使用时需要自己写入`world.yml`配置文件

#### world.yml

```
world: 
  - 玩家词条: 卧底词条
  - 玩家词条2: 卧底词条2
```

## 支持帮助

本插件有很多设想未能实现的功能，如果你有更好的优化方法欢迎提交属于你的pr，同时欢迎提pr。

* 感谢 [SkyNet1748 ](https://github.com/SkyNet1748)，[LaoLittle](https://github.com/LaoLittle) 对本插件的测试帮助！

* Q群：877894787
