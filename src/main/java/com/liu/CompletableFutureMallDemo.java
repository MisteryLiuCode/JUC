package com.liu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author liushuaibiao
 * @date 2023/6/26 14:28
 */
public class CompletableFutureMallDemo {
    //初始化多家平台
    static List<NetMall> list = Arrays.asList(new NetMall("jd"), new NetMall("taobao"), new NetMall("dangdang"));

    public static List<String> getPrice(List<NetMall> list, final String produceName) {
        return list.stream().map(netMall -> String.format(produceName + "in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(produceName))).collect(Collectors.toList());
    }
    public static List<String> getPriceByCompletableFuture(List<NetMall> list, final String produceName) {
        return list.stream().map(netMall -> CompletableFuture.supplyAsync(() -> String.format(produceName + "in %s price is %.2f", netMall.getNetMallName(), netMall.calcPrice(produceName)))).collect(Collectors.toList()).stream().map(s -> s.join()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = getPrice(list, "Mysql");
        for (String s : list1) {
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("-----costTime:" + (endTime - startTime) + "毫秒");

        System.out.println("----------");

        long start1Time = System.currentTimeMillis();
        List<String> list2 = getPriceByCompletableFuture(list, "Mysql");
        for (String s : list1) {
            System.out.println(s);
        }
        long end1Time = System.currentTimeMillis();
        System.out.println("-----costTime:" + (end1Time - start1Time) + "毫秒");

    }
}

@AllArgsConstructor
@NoArgsConstructor
@Data
class NetMall {
    private String netMallName;
    public double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
