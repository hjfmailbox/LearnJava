package rabbitMQ.simple;

import com.rabbitmq.client.*;

;import java.io.IOException;

public class Consumer {
    public static void main(String[] args) {
        // 所有中间件技术都是基于tcp/ip协议
        // rabbitmq基于amqp，amqp又基于tcp/ip
        //ip port

        // 1: 创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");

        Connection connection=null;
        Channel channel =null;
        try {
            // 2：创建连接Connection
            connection= connectionFactory.newConnection("生产者");
            // 3：通过连接获取通道Channel
            channel = connection.createChannel();
            // 4：通过通道创建交换机，声明队列，绑定关系，路由key，发送消息，接收消息

            channel.basicConsume("queue1", true, new DeliverCallback() {
                @Override
                public void handle(String s, Delivery delivery) throws IOException {
                    System.out.println("接收消息是：" + new String(delivery.getBody(),"UTF-8"));
                }
            }, new CancelCallback(){
                @Override
                public void handle(String s) throws IOException {
                    System.out.println("接收失败了……");
                }
            });
            System.out.println("开始接受消息");
            System.in.read();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(channel!=null && channel.isOpen()){
                try {
                    channel.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            if(connection!=null && connection.isOpen()){
                try {
                    connection.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
