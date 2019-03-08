package com.boot.demo.utils;

/**
 * 类 名: HttpCallerConfig <br>
 * 描 述: http属性配置参数 <br>
 *
 * @author chenkaihua
 * @since 2017/12/12 21:19.
 */
public class HttpProxyConfig {
	/**
	 * 最大连接数
	 */
	public static int MAX_TOTAL_CONNECTIONS = 800;
	/**
	 * 每个路由最大连接数
	 */
	public static int MAX_ROUTE_CONNECTIONS = 400;
	/**
	 * 连接超时时间
	 */
	public static int CONNECT_TIMEOUT = 10000;
	/**
	 * 读取超时时间
	 */
	public static int READ_TIMEOUT = 10000;

}
