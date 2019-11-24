# 背景
浦发银行自动化测试培训

# 运行
`mvn test` 因为配置了多线程运行，每个cpu core 一个线程，会打开多个浏览器窗口运行

# Changelog 
- 2019年11月 建立
- 2019年11月22日 增加在测试失败后截屏的功能
- 2019年11月24日 找到了测试不稳定的原因是因为没有刷新 dom, 之前定义的通过显式等待重试的方式显得多此一举，所以全部删除

# 目的：
- selenium webdriver 的核心
- 使用 junit4 搭建一个测试框架

# 核心组件， framework.common 包下
- TestBase 控制 webdriver 类型，控制测试重试次数
- PageBase 页面对象的基类，目前仅仅是为了将driver 保存为成员变量，以供子类使用
- WebUtil, 将 webdriver 绑定到线程 scope, 供测试和页面对象的基类使用；另外提供截屏的方法

# 分包目的
- helloworld  
  - 入门核心API 
  - 理解 webdriver 与窗口的一对多关系

- framework 使用 junit4 的测试框架
  - cases 测试用例
  - common 基类和 工具类
  - pageobject 页面对象