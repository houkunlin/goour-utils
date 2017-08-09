package com.goour.utils;

import java.util.Random;

import cn.goour.utils.tools.IpRoute;

public class IpRouteTest {
	public static void main(String[] args) throws Exception {
		String ip ="192.168.199.12";// randomIp();
		String netword = "192.168.199.0/24";
		String[] net = netword.split("/");
		
		long iplong =IpRoute.getIpForStr(ip); 
		long gwiplong = IpRoute.getIpForStr(net[0]);
		long maskiplong = IpRoute.getMaskForStr(net[1]);
		System.out.println("ip:"+ip);
		System.out.println("ip:"+iplong);
		System.out.println("ip:"+IpRoute.ip2str(iplong));
		System.out.println();
		System.out.println("network:"+net[0]);
		System.out.println("network:"+gwiplong);
		System.out.println("network:"+IpRoute.ip2str(gwiplong));
		System.out.println();
		System.out.println("masknum:"+net[1]);
		System.out.println("masknum:"+maskiplong);
		System.out.println("masknum:"+IpRoute.ip2str(maskiplong));
		System.out.println("masklen:"+IpRoute.mask2masklength(maskiplong));
		System.out.println();
		System.out.println("ip net:"+IpRoute.getIpNetworkAddress(iplong,maskiplong));
		System.out.println("ip net:"+IpRoute.ip2str(IpRoute.getIpNetworkAddress(iplong,maskiplong)));
		System.out.println("network net:"+IpRoute.getIpNetworkAddress(gwiplong,maskiplong));
		System.out.println("network net:"+IpRoute.ip2str(IpRoute.getIpNetworkAddress(gwiplong,maskiplong)));
		System.out.println();
		System.out.println(IpRoute.isIpAtNetword(ip, netword));
		
		long start = System.currentTimeMillis();
		System.out.println("开始："+start);
        Random r = new Random();
        int i=0;
		for(i=0;i<10000;i++){
			String ip2 = randomIp();
			String ip3 = randomIp()+"/"+String.valueOf(r.nextInt(1000000) % 32);
			IpRoute.isIpAtNetword(ip2, ip3);
		}
		long end = System.currentTimeMillis();
		System.out.println("结束："+end);
		System.out.println("次数："+i);
		System.out.println("耗时："+(end-start)/1000.0);
		
	}

	public static String randomIp() {
        Random r = new Random();
        StringBuffer str = new StringBuffer();
        str.append(r.nextInt(1000000) % 255);
        str.append(".");
        str.append(r.nextInt(1000000) % 255);
        str.append(".");
        str.append(r.nextInt(1000000) % 255);
        str.append(".");
        str.append(r.nextInt(1000000) % 255);

        return str.toString();
    }
}
