# yzp_sqoop
compiled base on sqoop-1.5.0-SNAPSHOT, did some changes for columns mapping/create_table_statement etc.

# changelog
### 2020.04.10
1. add columns' comment for MySQL & Oracle DB source
2. map `date`, `datetime`, `timestamp` to STRING type
3. add `--calculate-rows` option for `eval` command, which could return total row count of query string
4. add `--formatted` option for `eval` command, original sqoop version will output dataset as formatted mode( without `--formated` option ), now if you want to get the output in formatted mode, you just need to add `--formatted` option, otherwise the output will be output as raw mode.

1. 为MySQL和Oracle数据源添加列名注释
2. 将 `date`, `datetime`, `timestamp` 三种类型映射为STRING类型
3. 为 `eval` 命令添加 `--calculate-rows` 选项（这个翻译实在是鸡肋，但是我实在懒得改了），将输出查询字符串返回的行数
4. 为 `eval` 命令添加 `--formatted` 选项，原始的sqoop版本默认按格式化输出，目前修改之后的逻辑是：如果想按照格式化输出，则添加`--formatted` 选项，否则按原始格式输出（方便用户通过SHELL获取返回值，减少字符处理）

ps.可以通过添加选项直接以parquet格式导入到hive。
```
sample code:
[hive@etl01 sqoop]$ sqoop import  -Dsqoop.parquet.logical_types.decimal.enable=true -Dparquetjob.configurator.implementation=hadoop -Dmapred.child.java.opts="-Djava.security.egd=file:/dev/../dev/urandom" --hive-import --connect jdbc:mysql://192.168.XXX.XXX:3306/sqoop_test --username sqoop_test --password sqoop_test --table test_datatypes --hive-database sqoop_test --create-hive-table --fields-terminated-by '\001' --as-parquetfile --verbose -m 1 --delete-target-dir 
```
