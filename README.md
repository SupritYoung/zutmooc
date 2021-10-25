# 基于协同过滤推荐算法的在线教育平台
个人毕业设计，基于协同过滤推荐算法的在线教育平台的后端模块（前后端分离）。

前端模块见https://github.com/a1097304791/zut-mooc-frontend

## 摘要
随着科技的不断变迁，信息技术的不断迭代升级。人们对于互联网方方面面的需求都开始涌现了出来，这其中就包括对于在线教育的需求。如今，大家都希望能够活到老、学到老，不管是职场人士、高校老师，还是渴望学习更多知识的在校学生，对于在线教育网站的需求已经非常迫切。

新冠疫情以来，由于全国实行居家隔离，所有的中小学、高校都不得不采用线上授课这一方式。通过这次疫情，可以说在线教育的理念已经大大深入人心，几乎所有的人都亲身参与和感受到了在线教育所带来的魅力。所以在线教育将会成为未来教育的一个新势力。

我所设计并实行的网络课程网站，在技术上使用了目前最为流行的一些框架和技术，后端包括Springboot、SpringMVC、SpringCloud、MybatisPlus、Nacos；前端包括Vue、JavaScript、Node.js、Nginx；推荐算法使用了基于物品的协同过滤算法，相比于其他简单的推荐算法而言，此类算法更加人性化，也能够更加准确地实现对用户的个性化推荐，从而避免信息冗杂。

经过测试，系统各方面功能如期运行，性能指标合理，推荐算法功能实现符合预期，并给出相应的推荐理由。

## 系统设计图
### 系统功能图
![image](https://user-images.githubusercontent.com/30597946/133762503-a2c14596-0f51-4b5d-9eb2-4619c2d6fdf4.png)
### 系统架构图
![image](https://user-images.githubusercontent.com/30597946/133762525-cbd94ef8-144b-4d88-91af-80a774004de1.png)
### 开发环境
| 名称             | 开发环境                         |
| ---------------- | -------------------------------- |
| 前端集成开发环境 | WebStorm 2021.1                  |
| 后端集成开发环境 | IntelliJ IDEA 2019.11            |
| 系统前端开发语言 | JavaScript ES6                   |
| 系统后端开发语言 | Java jdk 1.8                     |
| 系统前端框架     | Vue                              |
| 系统后端框架     | Springboot SpringMVC MybatisPlus |
| Web服务器        | Tomcat 9.0.41                    |
| 数据库版本       | MySQL 8.0.22                     |
| 本地操作系统     | Windows 10                       |
| 服务器操作系统   | Linux CentOS                     |

### 系统文件架构
```
zutmooc                 		// 后端根项目
├── common                      // 通用代码层
│       └── common_util             // 工具类
│       └── service_base            // 业务通用代码
├── service                     // 业务代码层
│       └── service_cms             // 前台内容api
│       └── service_edu             // 后台内容api
│       └── service_oss             // 阿里云oss文件存储
│       └── service_sms             // 阿里云短信分发
│       └── service_order           // 订单模块
│       └── service_ucenter         // 用户模块
│       └── service_vod             // vod视频存储
│       └── service_oss             // 阿里云oss文件存储
```

## 运行截图
![image](https://user-images.githubusercontent.com/30597946/133762935-b1e447c8-fb3d-45bd-bb83-c79e6a2d449e.png)
![image](https://user-images.githubusercontent.com/30597946/133762954-a05fec9d-172b-44a0-8194-829b89d52ed6.png)
![image](https://user-images.githubusercontent.com/30597946/133762961-5fe3bae0-66ba-4ce5-99c1-694d353dd8cd.png)
![image](https://user-images.githubusercontent.com/30597946/133762984-d88c829a-462d-4968-ada8-5fd7ef17350e.png)



