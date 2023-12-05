package com.haohaohu.springbootdemo;

import cn.hutool.core.io.BufferUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.socket.SocketUtil;
import cn.hutool.socket.aio.AioClient;
import cn.hutool.socket.aio.AioSession;
import cn.hutool.socket.aio.SimpleIoAction;
import cn.hutool.socket.nio.NioClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * @author haohao(ronghao3508 gmail.com) on 2023/11/15 14:37
 * @version v1.0
 */
public class SocketTool {

    public static void send() {
        NioClient client = new NioClient("221.237.108.49", 2080);

        client.setChannelHandler((sc) -> {
            ByteBuffer readBuffer = ByteBuffer.allocate(1024);
            //从channel读数据到缓冲区
            int readBytes = sc.read(readBuffer);
            if (readBytes > 0) {
                readBuffer.flip();
                // 要读取的字节长度
                byte[] bytes = new byte[readBuffer.remaining()];
                readBuffer.get(bytes);
                String body = StrUtil.utf8Str(bytes);
                Console.log("[{}]: {}", sc.getRemoteAddress(), body);
                Console.log("关闭");
                readBuffer.clear();
                IoUtil.close(sc);
            } else if (readBytes < 0) {
                sc.close();
            }
        });
        client.listen();


        client.write(BufferUtil.createUtf8("##0325QN=20231116114325977;ST=31;CN=60001;MN=9151011239691383331;DATE=20231116114320;F_JCWZ=模拟入口;F_CPHM=闽PA0001;F_CPYS=蓝牌;F_JCLX=进;Flag=5;CP=&&;RtdInterval=30&&A9C1\r\n"));
        //ThreadUtil.execAsync(() -> {
        //    ThreadUtil.safeSleep(1000);
        //    Console.log("关闭");
        //    IoUtil.close(client);
        //});
        //ThreadUtil.safeSleep(1000);
        //Console.log("关闭");
        //IoUtil.close(client);
        //client.close();
    }

    public static void send2() {
        AioClient client = new AioClient(new InetSocketAddress("221.237.108.49", 2080), new SimpleIoAction() {

            @Override
            public void doAction(AioSession session, ByteBuffer data) {
                if (data.hasRemaining()) {
                    Console.log(StrUtil.utf8Str(data));
                    session.read();
                }
                Console.log("OK");
                IoUtil.close(session);
            }
        });

        client.write(BufferUtil.createUtf8("##0325QN=20231115142511123;ST=31;CN=60001;MN=915101123969138333;DATE=20231115142511;F_JCWZ=大门岗亭进通道;F_CPHM=川A12345;F_CPYS=黄牌;F_JCLX=进;Flag=5;CP=&&;RtdInterval=30&&A9C1\r\n"));
        client.read();

        //IoUtil.close(client);
        //client.close();
    }

    public static void send3() throws IOException {
        //Socket connect = SocketUtil.connect("221.237.108.49", 2080, 5000);
        //OutputStream out = connect.getOutputStream();
        //InputStream in = connect.getInputStream();
        //String data = "##0325QN=20231115142511123;ST=31;CN=60001;MN=915101123969138333;DATE=20231115142511;F_JCWZ=大门岗亭进通道;F_CPHM=川A12345;F_CPYS=黄牌;F_JCLX=进;Flag=5;CP=&&;RtdInterval=30&&A9C1\r\n";
        //out.write(data.getBytes("UTF-8"));
        //out.flush();
        //
        //StringBuilder sb = new StringBuilder();
        //byte[] buffer = new byte[1024];
        //int len;
        //while ((len = in.read(buffer)) != -1) {
        //    sb.append(new String(buffer, 0, len));
        //}
        //Console.log("---" + sb.toString());
        //connect.close();


        Socket s = new Socket("221.237.108.49", 2080);

        //构建IO
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
        //向服务器端发送一条消息
        bw.write("##0325QN=20231116111601111;ST=31;CN=60001;MN=915101123969138333;DATE=20231116111554;F_JCWZ=模拟入口;F_CPHM=闽PA00011;F_CPYS=蓝牌;F_JCLX=进;Flag=5;CP=&&;RtdInterval=30&&A9C1\r\n");
        bw.flush();
        ThreadUtil.safeSleep(1000);

        //读取服务器返回的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String mess = null;
        try {
            mess = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("服务器：" + mess);

    }

    public static void main(String[] args) {
        //ThreadUtil.execute(new Runnable() {
        //    @Override
        //    public void run() {
        //        send();
        //    }
        //});
        //send();
        //try {
        //    send3();
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}

        String localhostStr = NetUtil.getLocalhostStr();
        System.out.println(localhostStr);
    }
}
