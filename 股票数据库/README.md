## 修正 2019-5-15

如果使用导入的时候新建表格，则会出现无法取值stock_code的情况。

先创建一个表格，命名为stock，内容如下：

![1](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/1.png)

然后再右键表格：

![2](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/2.png)

注意与下面方法不同的地方：

![3](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/3.png)

其余点击下一步即可。



## 如何使用股票数据

打开MySQL Workbench，新建一个Schema，命名为stock_trading_system。

在Table位置右键，点击Table Data Import Wizard。

![4](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/4.png)

选择从群文件里下载的`股票信息(精简-用于导入).csv`

![5](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93库/assets/5.png)

修改表名为stock

![6](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/6.png)

确定encoding选择utf-8，下一步。结束后，点下面这个按钮刷新。

![7](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/7.png)

查看是否成功导入。

![8](https://github.com/Great-Keith/SbockBradingBystem/raw/master/%E8%82%A1%E7%A5%A8%E6%95%B0%E6%8D%AE%E5%BA%93/assets/8.png)













