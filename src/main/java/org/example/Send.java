package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
  private static final String QUEUE_NAME = "hello";

  public static void main(String[] args) throws Exception {
    char letter = 'a';
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      String message = "Hello World! ";
      for (int i = 0; i < 10; i++) {
        String send = message + letter++;
        channel.basicPublish("", QUEUE_NAME, null, send.getBytes());
        System.out.println(" [x] Sent '" + send + "'");
        Thread.sleep(5000);
      }
    }
  }
}
