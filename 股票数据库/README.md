## 修正 2019-5-15

如果使用导入的时候新建表格，则会出现无法取值stock_code的情况。

先创建一个表格，命名为stock，内容如下：

![](https://cdn.sinaimg.cn.52ecy.cn/large/005BYqpggy1g322us7skrj30o404ydgr.jpg)

然后再右键表格：

![2](https://imgchr.com/i/ETvIaT)

注意与下面方法不同的地方：

![3](https://imgchr.com/i/ETvcGQ)

其余点击下一步即可。



## 如何使用股票数据

打开MySQL Workbench，新建一个Schema，命名为stock_trading_system。

在Table位置右键，点击Table Data Import Wizard。

![4](https://imgchr.com/i/ETvhq0)

选择从群文件里下载的`股票信息(精简-用于导入).csv`

![5](https://imgchr.com/i/ETv2xs)

修改表名为stock

![6](https://imgchr.com/i/ETvg2j)

确定encoding选择utf-8，下一步。结束后，点下面这个按钮刷新。

![7](https://imgchr.com/i/ETvWMn)

查看是否成功导入。

![8](https://imgchr.com/i/ETvfrq)













