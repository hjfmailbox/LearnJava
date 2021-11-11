package rabbitMQ.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description 简单模式
 */
public class Producer {
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
            String queueName= "queue1";

            /*
            * @params1 队列名称
            * @params2 是否持久化durable，消息是否存盘
            * @params3 排他性，是否独占
            * @params4 是否自动删除，随着最后一个消费者消费完毕是否删除
            * @params5 携带附属消息
            */
            channel.queueDeclare(queueName, false,false,false,null);

            // 5：准备消息内容
            String message = "冠杰卖个萌";
            // 6：发送消息给队列queue
            channel.basicPublish("",queueName,null,message.getBytes());

            System.out.println("消息发送成功");
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
