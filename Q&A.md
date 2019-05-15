# Q&A 

## 项目安排相关

#### 第一次合代码的DDL时间？

2019-5-20左右，看小组进度

#### 各小组间通信如何实现？

除中央交易系统外，其余模块的通信在v1版本都先通过数据库实现。



## 数据库相关

#### 股票数据库无法使用，取不到`stock_code`的值？

见`股票数据库 -> README.md`（更新）



##  JavaFX使用相关

#### 出现了如下图情况怎么办？

![QA1](https://github.com/Great-Keith/SbockBradingBystem/raw/master/assets/QA1.png)

重启。

#### Scene Builder打不开FXML文件？

1） 检查是否导入JFoenix的JAR包到Scene Builder（具体方法见`代码规范.md`）；

2）Scene Builder版本是否吻合；

3）使用Gluon Scene Builder（下载地址见`代码规范.md`）

#### TableView报错，取不到某列的值？

TableView支持的数据格式需要Readable，需要使用Property，并且要标明每个Property的set/get等方法（命名方法有要求），详细百度。













