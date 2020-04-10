# yzp_sqoop
compiled base on sqoop-1.5.0-SNAPSHOT, did some changes for columns mapping/create_table_statement etc.

# changelog
## 2020.04.10
1. add columns' comment for MySQL & Oracle DB source
2. map `date`, `datetime`, `timestamp` to STRING type
3. add `--calculate-rows` option for eval command, which could return total row count of query string
4. add `--formatted` option for `eval` command, original sqoop version will output dataset as formatted mode( without `--formated` option ), now if you want to get the output in formatted mode, you just need to add `--formatted` option, otherwise the output will be output as raw mode.


1. 为MySQL和Oracle数据源添加列名注释
2. 将 `date`, `datetime`, `timestamp` 三种类型映射为STRING类型
3. 为 `eval` 命令添加 `--calculate-rows` 选项（这个翻译实在是鸡肋，但是我实在懒得改了），将输出查询字符串返回的行数
4. 为 `eval` 命令添加 `--formatted` 选项，原始的sqoop版本默认按格式化输出，目前修改之后的逻辑是：如果想按照格式化输出，则添加`--formatted` 选项，否则按原始格式输出（方便用户通过SHELL获取返回值，减少字符处理）

ps.可以通过添加选项直接以parquet格式导入到hive。
```
sample code:
[hive@etl01 sqoop]$ sqoop import  -Dsqoop.parquet.logical_types.decimal.enable=true \
-Dparquetjob.configurator.implementation=hadoop \
-Dmapred.child.java.opts="-Djava.security.egd=file:/dev/../dev/urandom" \
--hive-import --connect jdbc:mysql://192.168.XXX.XXX:3306/sqoop_test \
--username sqoop_test --password sqoop_test --table test_datatypes \
--hive-database sqoop_test --create-hive-table \
--fields-terminated-by '\001' --as-parquetfile --verbose -m 1 --delete-target-dir 

20/04/10 10:18:57 DEBUG manager.CatalogQueryManager: Using query: SELECT COLUMN_NAME, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = (SELECT SCHEMA())   AND TABLE_NAME = 'test_datatypes'  to look up column's comment.
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_bigint=整型}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_binary=二进制}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_bit=位}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_blob=blob}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_char=字符类型}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_date=日期类型}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_datetime=日期时间}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_decimal=数字}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_double=double}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_float=float}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_int=int}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_integer=}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_numeric=数字}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_real=实数}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_smallint=}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_text=文本}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_time=}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_timestamp=时间戳}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_tinyint=}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: {c_varchar=字符串}
20/04/10 10:18:57 DEBUG hive.TableDefWriter: Create statement: CREATE TABLE `sqoop_test`.`test_datatypes` ( `c_bigint` BIGINT comment '整型', `c_binary` BINARY comment '二进制', `c_bit` BOOLEAN comment '位', `c_blob` BINARY comment 'blob', `c_char` STRING comment '字符类型', `c_date` STRING comment '日期类型', `c_datetime` STRING comment '日期时间', `c_decimal` DECIMAL (32, 8) comment '数字', `c_double` DOUBLE comment 'double', `c_float` FLOAT comment 'float', `c_int` INT comment 'int', `c_integer` INT comment '', `c_numeric` DECIMAL (32, 8) comment '数字', `c_real` DOUBLE comment '实数', `c_smallint` INT comment '', `c_text` STRING comment '文本', `c_time` STRING comment '', `c_timestamp` STRING comment '时间戳', `c_tinyint` INT comment '', `c_varchar` STRING comment '字符串') COMMENT 'Imported by sqoop on 2020/04/10 10:18:57' STORED AS PARQUET
```
