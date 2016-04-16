# Funiture

## 前台 - 用户页面 + 管理界面
* 一个主要供展示家具产品的网站, 项目名称因此而来
* [jquery](http://jquery.com/) 框架
* [bootstrap](http://v3.bootcss.com/) 主要样式
* [mustache](https://github.com/janl/mustache.js) 引擎
* [Ace](http://responsiweb.com/themes/preview/ace/1.4/index.html) 模板渲染
* [zTree](http://www.ztree.me/v3/main.php) jQuery树插件
* 做个后台，管理前台展示数据及权限相关

## 后台 - 各种技术演练
* 通用的 spring 框架搭建
* maven 管理 jar 包
* 通用的权限管理系统 - 通过角色维护用户和权限之间的关系
  * 正常的部门、用户、角色、权限点、权限模块维护
  * 部门树、权限模块树、用户权限树、角色权限树展示
  * 操作记录查询、还原操作
  * 根据配置记录动态渲染后台菜单
  * 根据配置记录拦截请求的url
* 验证码生成及校验
* redis等缓存使用, 控制台管理
  * [RedisAdminUI](https://github.com/ServiceStackApps/RedisAdminUI)
  * [phpmemcacheadmin](https://code.google.com/archive/p/phpmemcacheadmin/)
* 动态配置, 埋点 - 动态加载
* logback, email 通知
* mybatis, sql监控(出现异常, 返回行)
* 线程池使用, 异步回调
* json(jackson)序列化与反序列化
* 通用邮件配置及发送
* 文件上传与管理，md5 计算
* httpClient 封装, 按需指定各项参数
* cookie 管理
* excel XSSFWorkbook(大数据量)使用, 相关报表导出
* quartz 定时任务
* hibernate validator校验
* threadLocal 管理进程信息, 按需使用
* Junit 测试
* RabbitMQ 队列, 生产-消费, 控制台管理
* zookeeper client
* 支持多数据源, 根据需要切换数据库
* 支持 aop
* 短链接服务, 生成\跳转

## 后续动作
* 日历插件, 展示任务及跳转
* 工作流

