xc# API网关Java-SDK使用指南
## 1 SDK简介

欢迎使用API网关开发者工具套件(SDK)。API网关SDK是根据您自定义的所有API接口，自动生成的Java调用代码，让您无需复杂编程即可访问阿里云的API网关服务。这里向您介绍如何使用API网关SDK。

目前API网关JavaSDK已在github上开源，[点击前往](https://github.com/aliyun/apigateway-sdk-core)

需要注意的一点是，所有API和文档都会按照`RegionId`、`Group`分组。下文中所有出现的`{{group}}`都是指API所属Group的名称，`{{regionId}}`都是指Group所在的地域(可用区)。

代码文件的层级结构如下：

* SDK文件夹
	* sdk/{{regionId}}		`JavaSDK文件夹，包含每个Group的所有API的接口调用代码`
		* SyncApiClient_{{group}}.java	`包含对应Group所有API接口的同步调用方法` 
		* AsyncApiClient_{{group}}.java	`包含对应Group所有API接口的异步调用方法` 
		* SyncDemo_{{group}}.java	`同步调用示例代码` 
		* AsyncDemo_{{group}}.java	`异步调用示例代码` 	
	* doc/{{regionId}} 
		* ApiDocument_{{group}}.md	`对应Group的API接口文档`
	* lib 
		* sdk-core-java-1.0.2.jar `sdk的core包，为本sdk的依赖包`
		* sdk-core-java-1.0.2-sources.jar		`上述依赖包的源码`
	* Readme.md	`本SDK使用指南`
	* LICENSE `版权许可`




## 2 SDK使用
### 2.1 环境准备

 1. 阿里云API网关Java SDK适用于`JDK 1.6`及以上版本
 2. 您需要准备一对授权密钥供SDK生成鉴权和签名信息，即 [AppKey和AppSecret](https://help.aliyun.com/document_detail/29488.html?spm=5176.product29462.6.552.HqvvXr)
 
 **重要提示：`AppKey`和`AppSecret`是网关认证用户请求的密钥，这两个配置如果保存在客户端，请妥善加密。** 
 3. 在pom.xml中添加如下依赖：

```html
<dependency>
	<groupId>com.aliyun.api.gateway</groupId>
	<artifactId>sdk-core-java</artifactId>
	<version>1.0.4</version>
</dependency>
```
		

### 2.2 引入SDK的API接口调用类

1. 把sdk文件夹中`AsyncApiClient_*.java`和`SyncApiClient_*.java`文件复制到您的项目文件夹中；
2. 修正这些类文件的package；


### 2.3 创建ApiClient
要提交请求至阿里云API网关，您首选要创建一个`ApiClient`对象。您可以参考`(A)SyncDemo_*.java`中的示例代码，使用`newBuilder()`获得一个`ApiClientBuilder`对象来构建您的`ApiClient`：

```java
public AsyncDemo_{{group}}() {
    this.asyncClient = AsyncApiClient_{{group}}.newBuilder()
        .appKey("your app key here")
        .appSecret("your app secret here")
        .build();
}
```

> ####注意####
- `ApiClientBuilder` 的大多数方法会返回当前对象自身，由此可以将方法调用组合起来，这样不仅方便而且代码更加便于阅读。
- 在配置需要的所有属性后，可以调用 `build()` 方法创建客户端，创建的`ApiClient`不可更改。
- `ApiClientBuilder`可以使用相同的配置创建多个客户端，在编写代码时，请注意该对象是 **可变且非线程安全的**。
- 创建的`ApiClient`是**线程安全**的，且有其独立的连接池/线程池资源，为了获得最佳性能，应该将其作为永久对象。


### 2.4 调用API接口

SDK是根据您在API网关自定义的参数进行生成的，每个API都被封装成了method，您可以参照Demo中的示例代码进行调用。
另外，SDK为您封装了单例模式的调用方法，您可以使用`AsyncDemo_{{group}}.getInstance()`方法来获得ApiClient对象。
> ####注意####
- 您必须先`build()`一个对象后，才可以调用`getInstance()`方法，否则会抛出异常。
- 在您`build()`了多个同类型的ApiClient后，`getInstance()`只会返回最后一次`build()`所创建的对象。

#3. 高级使用场景
`sdk-core-java-1.0.3`使用了ApacheHttpClient_4.5.2作为底层http客户端，其包含了跟多复杂的高级配置。虽然`ApiClientBuilder`中只涵盖了绝大多数常用的场景，但提供了灵活便捷的接口，以便您使用这些特性；您甚至可以通过这些接口去使用自定义的底层http客户端，例如okHttp3等等。

### 3.1 使用更全面的ApacheHttpClient配置
您可以直接按照[ApacheHttpClient使用文档](https://hc.apache.org/httpcomponents-client-4.5.x/tutorial/html/index.html)中的方式，创建自己的[HttpClientBuilder](http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/impl/client/HttpClientBuilder.html)，然后只需在 2.3 章节的`ApiClientBuilder`中，调用`builder.setExtraParam("apache.httpclient.builder", ${apacheBuilder})`，即可将`HttpClientBuilder`的所有参数传入`ApiClientBuilder`。

```java
HttpClientBuilder apacheHttpClientBuilder = HttpClientBuilder.create()
    .setHttpProcessor(new MyHttpProcessor())
    .setDefaultRequestConfig(
        RequestConfig.custom()
            .setConnectTimeout(5000)
            .build())
    .disableAuthCaching();
            
SyncApiClient_{{group}} syncClient = SyncApiClient_{{group}}.newBuilder()
    .appKey("your app key here")
    .appSecret("your app secret here")
    .connectionTimeoutMillis(10000L) //this will overwrite 5000 to 10000
    .setExtParams("apache.httpclient.builder", apacheHttpClientBuilder)
    .build();
```
      
> **注意**
若您在`HttpClientBuilder`和`ApiClientBuilder`为同一参数配置了不同的值，不论赋值顺序如何，最终都会以`ApiClientBuilder`的为准。上述示例代码中，创建的`SyncApiClient`的`connectionTimeout`配置为`10000L`。

### 3.2 使用自定义的HttpClient
如果您希望使用自定的HttpClient(如 OKHttp3 等)，只需编写实现`com.alibaba.cloudapi.sdk.core.HttpClient`的所有虚方法即可。

在您调用builder传参时，可以使用`setExtParams`来传入自定义参数，这些参数会在`HttpClient`接口的`init()`方法中作为参数传入，具体代码可以参考`com.alibaba.cloudapi.sdk.core.http.ApacheHttpClient`。
在您使用自定义HttpClient后，请在启动您的服务时，增加参数 `-Daliyun.sdk.httpclient="${class}"`，`${class}`为您自定义的`HttpClient`接口实现类的完整名称(包含命名空间)。
> `-Daliyun.sdk.httpclient` 的默认值为 `"com.alibaba.cloudapi.sdk.core.http.ApacheHttpClient"`

```java
import com.alibaba.cloudapi.sdk.core.HttpClient
    
public class MyHttpClient extends HttpClient{
    
    private CustomHttpClient customHttpClient;
    
    @Override
    protected void init(BuilderParams builderParams){
        // init your customHttpClient with params
        Object config1 = builderParams.getExtra("key1");
        Object config2 = builderParams.getExtra("key2");
        customHttpClient = new CustomHttpClient(config1, config2);
    }
        
    @Override
    public ApiResponse syncInvoke(ApiRequest request) throws IOException{
        // parse request
        CustomeHttpRequest httpRequest = parseToHttpRequest(request);
            
        // send http request
        CustomeHttpResponse httpResponse = customHttpClient.execute(httpRequest);
            
        // parse response
        return parseToApiResponse(httpResponse);
    }
        
    @Override
    public Future<ApiResponse> asyncInvoke(ApiRequest request, ApiCallBack callback){
        // do async
    }
        
    @Override
    public void shutdown(){
        // release your custom httpclient
        customHttpClient.shutdown();
    }
}
```


#4.	人工帮助
如果在使用中遇到棘手的问题，请加入我们官方用户支持群来找我们

 - 旺旺群号 1640106170 
 - 钉钉群号 11747055
