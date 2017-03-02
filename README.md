# spring project

围绕spring一系列框架的demo

## spring boot

## spring mvc

## spring security

## spring data


## 跨域CORS
https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS
~~~java
@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurerAdapter() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			// 参数为url
			registry.addMapping("/**")
			// 允许的ip，参数字符串或者字符串数组 *=匹配所有  
			.allowedOrigins(new String[]{"http://localhost","http://localhost:8888"});
		}
	};
}
~~~


## 缓存redis 
http://www.cnblogs.com/softidea/p/5801499.html