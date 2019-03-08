package com.boot.demo.io;

/**
 * @author chenkaihua
 * @date 2018/5/30 11:25
 */

/**
 * ---基于字节的I/O操作接口
 * InputStream: 输入流
 * OutputStream:输出流
 *
 * ---基于字符的I/O操作接口
 * Writer:写字符
 * Reader:读字符
 *
 * ---字节与字符的转化接口
 * InputStreamReader:字节到字符的转化
 * OutputStreamWriter: 字符到字节的编码
 *
 * ---读取文件
     * StringBuffer str = new StringBuffer();
     * char[] buf = new char[1024];
     * FileReader f = new FileReader("file");
     * while(f.read(buf)>0){
     *     str.append(buf);
     * }
 *
 *  Java 中通常的 File 并不代表一个真实存在的文件对象，当你通过指定一个路径描述符时，它就会返回一个代表这个路径相关联的一个虚拟对象，这个可能是一个真实存在的文件或者是一个包含多个文件的目录。
 *
 *
 *  主机 A 的应用程序要能和主机 B 的应用程序通信，必须通过 Socket 建立连接，而建立 Socket 连接必须需要底层 TCP/IP 协议来建立 TCP 连接。建立 TCP 连接需要底层 IP 协议来寻址网络中的主机。我们知道网络层使用的 IP 协议可以帮助我们根据 IP 地址来找到目标主机，但是一台主机上可能运行着多个应用程序，如何才能与指定的应用程序通信就要通过 TCP 或 UPD 的地址也就是端口号来指定。这样就可以通过一个 Socket 实例唯一代表一个主机上的一个应用程序的通信链路了
 *
 *
 *
 */
public class TestIODemo01 {
}
