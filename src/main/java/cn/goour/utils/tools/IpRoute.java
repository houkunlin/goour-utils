package cn.goour.utils.tools;

/**
 * 计算IP的路由信息，类似路由器的路由选择。
 * 通过传入IP(192.168.1.100)信息和对应的路由信息(192.168.1.1/24)
 * 来判断这个IP是否在这个路由的网络上。
 * 
 * 写这个东西的目的是，准备写一个网站IP过滤器，
 * 如果访问的IP在数据库的路由信息黑名单里面则不允许访问。
 * 或者说访问者IP在路由信息的网络里面则允许访问
 * 
 * @author HouKunLin
 *
 */
public class IpRoute {
	public IpRoute() {
	}
	public static void main(String[] args) {
		String ip = "192.168.11.1";
		System.err.println(ip.split("/").length);
	}
	/**
	 * @param ip 例如：10.0.0.1
	 * @param netword 例如：10.0.0.1/24
	 * @return
	 */
	public static boolean isIpAtNetword(String ip,String netword) throws Exception{
		
		String[] net = netword.split("/");//路由ip信息
		//传入的网络地址只是一个ip，判断是否相等
		if (net.length == 1) {
			// 因为只是IP地址，没有掩码信息，所以直接判断IP是否相等
			if (ip.equals(netword)) {
				return true;
			}else {
				return false;
			}
		}
		long theip = getIpForStr(ip);//当前ip
		long gwip = getIpForStr(net[0]);//网关
		long maskip = getMaskForStr(net[1]);//子网掩码
//		if (RegexUtils.isValidIP(net[1])) {
//			maskip = getIpForStr(net[1]);
//		}else {
//			maskip = getMaskForStr(net[1]);
//		}
		if(getIpNetworkAddress(theip , maskip) == getIpNetworkAddress(gwip , maskip)){
			return true;
		}
		return false;
	}
	/**
	 * 通过ip和子网掩码获取该ip的网络地址
	 * 
	 * @param ip
	 * @param mask
	 * @return 网络地址
	 */
	public static long getIpNetworkAddress(long ip,long mask) {
		return ip&mask;
	}
	/**
	 * 字符串ip转为长整型ip
	 * 
	 * @param ip
	 * @return
	 */
	public static long getIpForStr(String ip) {
		return int2long(str2Ip(ip));
	}
	/**
	 * 数字型子网掩码转为长整型ip
	 * 
	 * @param maskNum
	 * @return
	 */
	public static long getMaskForStr(String maskNum) {
		return str2mask(maskNum);
	}
	/**
	 * 字符串ip转为整型ip
	 * 
	 * @param ip
	 * @return
	 */
	public static int str2Ip(String ip) {
		String[] ss = ip.split("\\.");
        int a, b, c, d;
        a = Integer.parseInt(ss[0]);
        b = Integer.parseInt(ss[1]);
        c = Integer.parseInt(ss[2]);
        d = Integer.parseInt(ss[3]);
        return (a << 24) | (b << 16) | (c << 8) | d;
	}
	/**
	 * 字符串数字子网掩码转为整型ip
	 * 
	 * @param maskNum
	 * @return
	 */
	public static long str2mask(String maskNum) {
		int num = Integer.parseInt(maskNum);
		long the = (long) Math.pow(2, num) - 1;
		return (the << (32-num));
	}
	public static int mask2masklength(long mask) {
		int len = 0;
		for(int i=0;i<32;++i){
			if (((mask >> i) & 0x1) == 1 ) {
				++len;
			}
		}
		return len;
	}
	public static long int2long(int i) {
        long l = i & 0x7fffffffL;
        if (i < 0) {
            l |= 0x080000000L;
        }
        return l;
    }
	public static String ip2str(long ip) {
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append(String.valueOf(ip>>24));
		sBuffer.append(".");
		sBuffer.append(String.valueOf((ip>>16)&0xff));
		sBuffer.append(".");
		sBuffer.append(String.valueOf((ip>>8)&0xff));
		sBuffer.append(".");
		sBuffer.append(String.valueOf((ip)&0xff));
		return sBuffer.toString();
	}
	public static long bytesToLong(byte a, byte b, byte c, byte d) {
        return int2long((((a & 0xff) << 24) | ((b & 0xff) << 16) | ((c & 0xff) << 8) | (d & 0xff)));
    }
}
