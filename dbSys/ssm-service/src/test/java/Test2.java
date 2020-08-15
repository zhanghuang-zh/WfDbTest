import redis.clients.jedis.Jedis;

public class Test2 {


    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println(jedis.get("name"));
        jedis.set("zh","哈哈");
        System.out.println(jedis.exists("name"));


    }


}
