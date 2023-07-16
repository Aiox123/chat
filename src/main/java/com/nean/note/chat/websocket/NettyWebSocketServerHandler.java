package com.nean.note.chat.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class NettyWebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final List<ChannelHandlerContext> clients = new CopyOnWriteArrayList<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 新客户端连接时添加到客户端列表
        clients.add(ctx);
        log.info("Client connected: " + ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        // 客户端断开连接时从客户端列表中移除
        clients.remove(ctx);
        System.out.println("Client disconnected: " + ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) {
        // 处理收到的WebSocket消息
        String message = msg.text();
        log.info("Received message: " + message);

        // 在这里可以编写逻辑处理聊天消息、用户认证等业务

        // 发送消息给所有在线客户端
        for (ChannelHandlerContext client : clients) {
            client.writeAndFlush(new TextWebSocketFrame("Server received: " + message));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
