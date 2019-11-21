# 背景
浦发银行自动化测试培训

# Changelog 
- 2019年11月 建立

# 目的：
- selenium webdriver 的核心
- 使用 junit4 搭建一个测试框架

# 核心组件， framework 包下
- TestBase 控制 webdriver 类型，控制测试重试次数
- WebUtil, 提供两个可靠的与元素交互的方法

# 分包目的
- helloworld  
  - 入门核心API 
  - 核心难点： 可维护的 驱动, 元素操作不可靠
- Inherited  
  - 在基类中通过 @Rule 来设置驱动
  - 抽取公共方法提高元素操作的可靠性
- framework 使用 junit4 的测试框架
  - cases 测试用例
  - common 基类和 工具类
  - pageobject 页面对象