package com.game.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	//private Pattern patternForPing;
	//private String regexForPing;
	//private Matcher matcherForPing;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String data = "ping 192.168.253.133 -c 5PING 192.168.253.133 (192.168.253.133) 56(84) bytes of data.64 bytes from 192.168.253.133: icmp_seq=1 ttl=64 time=1.20 ms64 bytes from 192.168.253.133: icmp_seq=2 ttl=64 time=0.858 ms64 bytes from 192.168.253.133: icmp_seq=3 ttl=64 time=0.855 ms64 bytes from 192.168.253.133: icmp_seq=4 ttl=64 time=0.801 ms64 bytes from 192.168.253.133: icmp_seq=5 ttl=64 time=0.884 ms--- 192.168.253.133 ping statistics ---5 packets transmitted, 5 received, 0% packet loss, time 4004msrtt min/avg/max/mdev = 0.801/0.920/1.205/0.148 ms";
		String regexForPing = "(\\d+)\\s*packets.*(\\d+)\\s*received.*(\\d+).*\\s*packet loss";
		//String regex = "(\\d+)\\s*packets.*";
		Pattern patternForPing = Pattern.compile(regexForPing);
		Matcher match = patternForPing.matcher(data);
		if (match.find()){
			System.out.println("yes");
		}
		System.out.println(match.group(0));
		System.out.println(match.group(1));
		System.out.println(match.group(2));
		System.out.println(match.group(3));
	}

}
