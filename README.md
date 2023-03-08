# sample-stream
HexaDB 流式数据开发jar包方式样例工程


本工程由数翊智慧大数据团队提供，面向合作单位和项目交付部门提供实时数据开发的SDK使用范例


## 范例说明

共包含3个子工程，分别为：

### sdk-no-result-demo
范例中最简单的demo，根据降雨量阈值产生告警信息，并在控制台打印

### sdk-rain-warning-demo
实时流数据与数据库中的维度表关联的范例，根据降雨数据超过阈值则产生预警，预警中包含测站名称。其中测站名称来源于MySQL数据库

### sdk-rain-warning-coutput-demo
与 sdk-rain-warning-demo 类似，不同点在于，最后数据不是输出到队列，而是在processor中自定义(mysql),

### sdk-rain-window-warning-demo
带滑动窗口的计算范例，根据N分钟降雨总量计算是否超过阈值，如果超过则产生预警。

## 使用方式

注意，SDK版本与平台版本需要一致，假如2.2版本中使用了2.1版本的SDK，则任务中消费的数据量统计不准确。
```xml

<dependency>
    <groupId>com.exadb.bigdata.streaming</groupId>
    <artifactId>streaming-sdk</artifactId>
    <scope>system</scope>
    <systemPath>${project.basedir}/lib/streaming-sdk.jar</systemPath>
</dependency>
        <!--配置打包插件，内容如下所示：-->

<build>
<plugins>
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.2.1</version>
        <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
                <configuration>
                    <transformers>
                        <transformer implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                            <resource>reference.conf</resource>
                        </transformer><!--此为流计算启动入口MainClass，请不要修改-->
                        <transformer
                                implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                            <mainClass>com.exadb.bigdata.streaming.bootstrap.StreamBootstrap</mainClass>
                        </transformer>
                    </transformers>
                    <createDependencyReducedPom>false</createDependencyReducedPom>
                </configuration>
            </execution>
        </executions>
    </plugin>
</plugins>
</build>
```

如果需要完全本地开发，请在lib目录下使用mvn命令安装到本地，如：

```
#streaming-sdk-1.0.0-SNAPSHOT.jar
mvn install:install-file -Dfile=streaming-sdk-1.0.0-SNAPSHOT.jar -DgroupId=com.exadb.bigdata.streaming -DartifactId=streaming-sdk -Dversion=1.0.0-RELEASE -Dpackaging=jar

```
然后再运行本范例

## IDEA下运行本范例方式

    1、显示工具栏（View-appearance-toolbar）
    2、Select Run/Debug configuration
    3、编辑配置
    4、Add Application
    5、Main class固定填写：com.exadb.bigdata.streaming.bootstrap.StreamBootstrap
    6、Use classpath of module: 选择3个子工程的任意一个
    7、Working directory选择对应的文件夹路径
    8、运行或调试即可
