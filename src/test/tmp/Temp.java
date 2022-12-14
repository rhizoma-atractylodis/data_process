package tmp;

import base.JsonUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.junit.Test;

import javax.xml.transform.Source;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Temp {
    @Test
    public void netId() {
        String[] arr = new String[]{"41.77.176.0",
                "41.77.183.0",
                "41.96.0.0",
                "41.111.255.0",
                "41.191.252.0",
                "41.191.255.0",
                "41.200.0.0",
                "41.201.255.0",
                "41.210.64.0",
                "41.210.127.0",
                "41.220.144.0",
                "41.220.159.0",
                "41.221.16.0",
                "41.221.31.0",
                "41.223.176.0",
                "41.223.179.0",
                "41.223.236.0",
                "41.223.239.0",
                "57.82.32.0",
                "57.82.47.0",
                "80.88.12.0",
                "80.88.15.0",
                "80.231.192.0",
                "80.231.193.0",
                "80.246.0.0",
                "80.246.15.0",
                "80.249.64.0",
                "80.249.79.0",
                "88.202.105.0",
                "88.202.105.0",
                "88.202.109.0",
                "88.202.109.0",
                "105.96.0.0",
                "105.111.255.0",
                "105.235.128.0",
                "105.235.143.0",
                "129.45.0.0",
                "129.45.127.0",
                "154.73.92.0",
                "154.73.95.0",
                "154.121.0.0",
                "154.121.255.0",
                "154.127.96.0",
                "154.127.111.0",
                "154.240.0.0",
                "154.255.255.0",
                "168.253.96.0",
                "168.253.111.0",
                "192.52.232.0",
                "192.52.232.0",
                "192.245.148.0",
                "192.245.148.0",
                "193.41.146.0",
                "193.41.147.0",
                "193.194.64.0",
                "193.194.95.0",
                "193.251.169.0",
                "193.251.169.0",
                "193.251.174.0",
                "193.251.175.0",
                "195.24.80.0",
                "195.24.87.0",
                "195.39.218.0",
                "195.39.219.0",
                "196.11.75.0",
                "196.11.75.0",
                "196.20.64.0",
                "196.20.127.0",
                "196.29.40.0",
                "196.29.43.0",
                "196.32.16.0",
                "196.32.31.0",
                "196.41.224.0",
                "196.41.255.0",
                "196.46.248.0",
                "196.46.255.0",
                "197.112.0.0",
                "197.119.255.0",
                "197.140.0.0",
                "197.143.255.0",
                "197.200.0.0",
                "197.207.255.0",
                "213.177.168.0",
                "213.177.168.0",
                "213.179.160.0",
                "213.179.191.0",
                "2.21.128.0",
                "2.21.131.0",
                "23.50.192.0",
                "23.50.207.0",
                "23.218.228.0",
                "23.218.231.0",
                "41.32.0.0",
                "41.47.255.0",
                "41.64.0.0",
                "41.65.255.0",
                "41.67.80.0",
                "41.67.87.0",
                "41.68.0.0",
                "41.69.255.0",
                "41.72.64.0",
                "41.72.95.0",
                "41.77.136.0",
                "41.77.141.0",
                "41.78.20.0",
                "41.78.23.0",
                "41.78.60.0",
                "41.78.63.0",
                "41.78.148.0",
                "41.78.151.0",
                "41.79.208.0",
                "41.79.211.0",
                "41.88.0.0",
                "41.88.255.0",
                "41.91.0.0",
                "41.91.255.0",
                "41.128.0.0",
                "41.131.255.0",
                "41.152.0.0",
                "41.153.255.0",
                "41.155.128.0",
                "41.155.255.0",
                "41.176.0.0",
                "41.176.255.0",
                "41.178.0.0",
                "41.179.255.0",
                "41.187.0.0",
                "41.187.255.0",
                "41.190.248.0",
                "41.190.251.0",
                "41.191.80.0",
                "41.191.83.0",
                "41.196.0.0",
                "41.196.255.0",
                "41.199.0.0",
                "41.199.255.0",
                "41.205.96.0",
                "41.205.127.0",
                "41.206.128.0",
                "41.206.159.0",
                "41.206.176.0",
                "41.206.176.0",
                "41.206.189.0",
                "41.206.189.0",
                "41.209.192.0",
                "41.209.255.0",
                "41.215.240.0",
                "41.215.243.0",
                "41.217.160.0",
                "41.217.191.0",
                "41.217.224.0",
                "41.217.231.0",
                "41.218.128.0",
                "41.218.191.0",
                "41.221.128.0",
                "41.221.143.0",
                "41.222.128.0",
                "41.222.135.0",
                "41.222.168.0",
                "41.222.175.0",
                "41.223.20.0",
                "41.223.23.0",
                "41.223.52.0",
                "41.223.55.0",
                "41.223.196.0",
                "41.223.199.0",
                "41.223.240.0",
                "41.223.243.0",
                "41.232.0.0",
                "41.239.255.0",
                "45.96.0.0",
                "45.111.255.0",
                "45.240.0.0",
                "45.247.255.0",
                "57.83.0.0",
                "57.83.15.0",
                "57.88.48.0",
                "57.88.63.0",
                "57.188.16.0",
                "57.188.16.0",
                "62.12.120.0",
                "62.12.127.0",
                "62.68.224.0",
                "62.68.255.0",
                "62.114.0.0",
                "62.114.255.0",
                "62.117.32.0",
                "62.117.63.0",
                "62.135.0.0",
                "62.135.127.0",
                "62.139.0.0",
                "62.139.255.0",
                "62.140.64.0",
                "62.140.127.0",
                "62.193.64.0",
                "62.193.127.0",
                "62.240.96.0",
                "62.240.127.0",
                "62.241.128.0",
                "62.241.159.0",
                "63.223.12.0",
                "63.223.12.0",
                "63.223.12.0",
                "63.223.12.0",
                "64.147.68.0",
                "64.147.68.0",
                "74.63.22.0",
                "74.63.22.0",
                "74.80.117.0",
                "74.80.117.0",
                "80.75.160.0",
                "80.75.191.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.12.0",
                "80.77.13.0",
                "80.77.13.0",
                "81.10.0.0",
                "81.10.55.0",
                "81.10.57.0",
                "81.10.80.0",
                "81.10.84.0",
                "81.10.113.0",
                "81.10.115.0",
                "81.10.115.0",
                "81.10.120.0",
                "81.10.121.0",
                "81.21.96.0",
                "81.21.111.0",
                "81.29.96.0",
                "81.29.111.0",
                "82.129.128.0",
                "82.129.255.0",
                "82.201.128.0",
                "82.201.255.0",
                "84.36.0.0",
                "84.36.255.0",
                "84.205.96.0",
                "84.205.127.0",
                "84.233.0.0",
                "84.233.127.0",
                "88.202.105.0",
                "88.202.105.0",
                "88.202.109.0",
                "88.202.109.0",
                "89.221.43.0",
                "89.221.43.0",
                "95.100.16.0",
                "95.100.31.0",
                "95.101.100.0",
                "95.101.103.0",
                "102.8.0.0",
                "102.15.255.0",
                "102.40.0.0",
                "102.47.255.0",
                "102.56.0.0",
                "102.63.255.0",
                "102.64.58.0",
                "102.64.58.0",
                "102.68.22.0",
                "102.68.22.0",
                "102.68.127.0",
                "102.68.127.0",
                "102.69.149.0",
                "102.69.150.0",
                "102.128.176.0",
                "102.128.183.0",
                "102.131.32.0",
                "102.131.35.0",
                "102.132.97.0",
                "102.132.97.0",
                "102.164.114.0",
                "102.164.115.0",
                "102.164.122.0",
                "102.164.122.0",
                "102.184.0.0",
                "102.191.255.0",
                "102.221.68.0",
                "102.221.71.0",
                "102.221.116.0",
                "102.221.116.0",
                "102.223.94.0",
                "102.223.94.0",
                "102.223.144.0",
                "102.223.147.0",
                "102.223.172.0",
                "102.223.172.0",
                "102.223.242.0",
                "102.223.243.0",
                "102.223.250.0",
                "102.223.250.0",
                "104.44.40.0",
                "104.44.40.0",
                "104.133.45.0",
                "104.133.45.0",
                "105.32.0.0",
                "105.47.255.0",
                "105.80.0.0",
                "105.95.255.0",
                "105.180.0.0",
                "105.183.255.0",
                "105.192.0.0",
                "105.207.255.0",
                "114.198.235.0",
                "114.198.236.0",
                "114.198.239.0",
                "114.198.239.0",
                "129.230.241.0",
                "129.230.241.0",
                "129.230.243.0",
                "129.230.243.0",
                "139.181.35.0",
                "139.181.35.0",
                "139.181.38.0",
                "139.181.38.0",
                "152.62.204.0",
                "152.62.204.0",
                "152.62.213.0",
                "152.62.213.0",
                "154.128.0.0",
                "154.143.255.0",
                "154.176.0.0",
                "154.191.255.0",
                "154.236.0.0",
                "154.239.255.0",
                "155.11.0.0",
                "155.11.255.0",
                "155.12.128.0",
                "155.12.191.0",
                "156.160.0.0",
                "156.223.255.0",
                "157.207.32.0",
                "157.207.35.0",
                "163.121.0.0",
                "163.121.255.0",
                "163.171.180.0",
                "163.171.180.0",
                "164.160.64.0",
                "164.160.67.0",
                "164.160.104.0",
                "164.160.107.0",
                "167.1.178.0",
                "167.1.178.0",
                "169.239.36.0",
                "169.239.39.0",
                "175.41.14.0",
                "175.41.14.0",
                "184.29.4.0",
                "184.29.5.0",
                "185.84.18.0",
                "185.84.18.0",
                "185.133.16.0",
                "185.133.19.0",
                "185.187.178.0",
                "185.187.178.0",
                "192.23.152.0",
                "192.23.152.0",
                "192.101.142.0",
                "192.101.142.0",
                "193.19.232.0",
                "193.19.235.0",
                "193.227.0.0",
                "193.227.63.0",
                "193.227.128.0",
                "193.227.128.0",
                "194.79.96.0",
                "194.79.127.0",
                "194.133.22.0",
                "194.133.22.0",
                "194.133.31.0",
                "194.133.31.0",
                "194.133.177.0",
                "194.133.178.0",
                "194.246.24.0",
                "194.246.25.0",
                "195.43.0.0",
                "195.43.31.0",
                "195.234.168.0",
                "195.234.168.0",
                "195.234.185.0",
                "195.234.185.0",
                "195.234.252.0",
                "195.234.255.0",
                "195.246.32.0",
                "195.246.63.0",
                "196.1.143.0",
                "196.1.143.0",
                "196.2.192.0",
                "196.2.223.0",
                "196.3.14.0",
                "196.3.15.0",
                "196.6.120.0",
                "196.6.120.0",
                "196.6.185.0",
                "196.6.185.0",
                "196.6.236.0",
                "196.6.236.0",
                "196.10.97.0",
                "196.10.97.0",
                "196.10.120.0",
                "196.10.120.0",
                "196.12.11.0",
                "196.12.11.0",
                "196.13.206.0",
                "196.13.206.0",
                "196.13.244.0",
                "196.13.244.0",
                "196.13.253.0",
                "196.13.253.0",
                "196.20.32.0",
                "196.20.63.0",
                "196.22.5.0",
                "196.22.5.0",
                "196.22.7.0",
                "196.22.7.0",
                "196.22.130.0",
                "196.22.130.0",
                "196.41.73.0",
                "196.41.73.0",
                "196.41.83.0",
                "196.41.83.0",
                "196.43.198.0",
                "196.43.198.0",
                "196.43.201.0",
                "196.43.201.0",
                "196.43.219.0",
                "196.43.219.0",
                "196.43.237.0",
                "196.43.237.0",
                "196.46.22.0",
                "196.46.22.0",
                "196.46.26.0",
                "196.46.26.0",
                "196.46.29.0",
                "196.46.29.0",
                "196.46.188.0",
                "196.46.191.0",
                "196.50.22.0",
                "196.50.23.0",
                "196.128.0.0",
                "196.159.255.0",
                "196.201.3.0",
                "196.201.3.0",
                "196.201.24.0",
                "196.201.31.0",
                "196.201.240.0",
                "196.201.247.0",
                "196.202.0.0",
                "196.202.127.0",
                "196.204.0.0",
                "196.205.255.0",
                "196.216.24.0",
                "196.216.31.0",
                "196.216.140.0",
                "196.216.143.0",
                "196.216.206.0",
                "196.216.206.0",
                "196.216.240.0",
                "196.216.241.0",
                "196.216.246.0",
                "196.216.246.0",
                "196.216.252.0",
                "196.216.252.0",
                "196.218.0.0",
                "196.218.247.0",
                "196.219.0.0",
                "196.219.247.0",
                "196.221.0.0",
                "196.221.255.0",
                "196.223.7.0",
                "196.223.7.0",
                "196.223.16.0",
                "196.223.17.0",
                "197.32.0.0",
                "197.63.255.0",
                "197.120.0.0",
                "197.127.255.0",
                "197.132.0.0",
                "197.135.255.0",
                "197.150.0.0",
                "197.151.255.0",
                "197.160.0.0",
                "197.167.255.0",
                "197.192.0.0",
                "197.199.255.0",
                "197.222.0.0",
                "197.223.255.0",
                "197.246.0.0",
                "197.246.255.0",
                "199.6.10.0",
                "199.6.10.0",
                "212.12.224.0",
                "212.12.255.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.73.201.0",
                "212.103.160.0",
                "212.103.191.0",
                "212.122.224.0",
                "212.122.255.0",
                "213.131.64.0",
                "213.131.95.0",
                "213.152.64.0",
                "213.152.95.0",
                "213.154.32.0",
                "213.154.63.0",
                "213.158.160.0",
                "213.158.191.0",
                "213.181.224.0",
                "213.181.255.0",
                "213.212.192.0",
                "213.212.255.0",
                "217.20.224.0",
                "217.20.239.0",
                "217.52.0.0",
                "217.55.255.0",
                "217.139.0.0",
                "217.139.255.0",
                "217.163.45.0",
                "217.163.45.0",
                "217.163.45.0",
                "217.163.45.0",
                "5.63.0.0",
                "5.63.7.0",
                "41.74.64.0",
                "41.74.79.0",
                "41.208.64.0",
                "41.208.127.0",
                "41.242.12.0",
                "41.242.31.0",
                "41.252.0.0",
                "41.255.255.0",
                "57.83.192.0",
                "57.83.207.0",
                "62.68.32.0",
                "62.68.63.0",
                "62.240.32.0",
                "62.240.63.0",
                "81.85.220.0",
                "81.85.223.0",
                "88.202.105.0",
                "88.202.105.0",
                "88.202.106.0",
                "88.202.106.0",
                "88.202.110.0",
                "88.202.110.0",
                "102.23.220.0",
                "102.23.223.0",
                "102.38.0.0",
                "102.38.31.0",
                "102.68.128.0",
                "102.68.135.0",
                "102.69.0.0",
                "102.69.127.0",
                "102.164.96.0",
                "102.164.103.0",
                "102.220.140.0",
                "102.220.143.0",
                "102.221.8.0",
                "102.221.11.0",
                "102.221.56.0",
                "102.221.59.0",
                "102.221.224.0",
                "102.221.227.0",
                "102.222.252.0",
                "102.222.253.0",
                "102.223.112.0",
                "102.223.113.0",
                "102.223.156.0",
                "102.223.159.0",
                "154.73.28.0",
                "154.73.31.0",
                "154.73.52.0",
                "154.73.55.0",
                "154.73.108.0",
                "154.73.111.0",
                "154.73.128.0",
                "154.73.135.0",
                "154.127.64.0",
                "154.127.79.0",
                "156.38.32.0",
                "156.38.63.0",
                "160.19.96.0",
                "160.19.103.0",
                "164.160.144.0",
                "164.160.147.0",
                "165.16.0.0",
                "165.16.127.0",
                "169.239.92.0",
                "169.239.95.0",
                "169.239.116.0",
                "169.239.119.0",
                "169.255.108.0",
                "169.255.111.0",
                "185.10.240.0",
                "185.10.243.0",
                "188.164.64.0",
                "188.164.64.0",
                "188.164.65.0",
                "188.164.65.0",
                "188.164.82.0",
                "188.164.83.0",
                "195.234.120.0",
                "195.234.123.0",
                "197.215.128.0",
                "197.215.159.0",
                "197.231.228.0",
                "197.231.231.0",
                "13.104.140.0",
                "13.104.140.0",
                "40.90.65.0",
                "40.90.65.0",
                "41.87.128.0",
                "41.87.159.0",
                "41.92.0.0",
                "41.92.127.0",
                "41.137.0.0",
                "41.137.224.0",
                "41.137.224.0",
                "41.137.255.0",
                "41.140.0.0",
                "41.143.255.0",
                "41.205.192.0",
                "41.205.223.0",
                "41.214.128.0",
                "41.214.255.0",
                "41.216.224.0",
                "41.216.227.0",
                "41.248.0.0",
                "41.251.255.0",
                "45.216.0.0",
                "45.219.255.0",
                "46.243.137.0",
                "46.243.137.0",
                "57.84.32.0",
                "57.84.47.0",
                "62.251.128.0",
                "62.251.255.0",
                "80.250.32.0",
                "80.250.47.0",
                "81.192.0.0",
                "81.192.255.0",
                "88.202.106.0",
                "88.202.106.0",
                "88.202.110.0",
                "88.202.110.0",
                "102.48.0.0",
                "102.55.255.0",
                "102.64.4.0",
                "102.64.5.0",
                "102.67.148.0",
                "102.67.151.0",
                "102.68.8.0",
                "102.68.11.0",
                "102.72.0.0",
                "102.79.255.0",
                "102.96.0.0",
                "102.103.255.0",
                "102.132.124.0",
                "102.132.124.0",
                "102.135.249.0",
                "102.135.251.0",
                "102.165.189.0",
                "102.165.189.0",
                "102.222.176.0",
                "102.222.179.0",
                "104.212.67.0",
                "104.212.67.0",
                "105.64.0.0",
                "105.73.30.0",
                "105.73.30.0",
                "105.79.255.0",
                "105.128.0.0",
                "105.159.255.0",
                "105.188.0.0",
                "105.191.255.0",
                "144.36.151.0",
                "144.36.151.0",
                "147.243.102.0",
                "147.243.102.0",
                "147.243.107.0",
                "147.243.107.0",
                "154.70.205.0",
                "154.70.205.0",
                "154.70.207.0",
                "154.70.207.0",
                "154.144.0.0",
                "154.151.255.0",
                "155.91.75.0",
                "155.91.75.0",
                "160.77.0.0",
                "160.77.255.0",
                "160.89.0.0",
                "160.90.255.0",
                "160.105.0.0",
                "160.105.255.0",
                "160.160.0.0",
                "160.179.255.0",
                "169.255.176.0",
                "169.255.179.0",
                "172.68.164.0",
                "172.68.165.0",
                "192.12.117.0",
                "192.12.117.0",
                "193.188.7.0",
                "193.188.7.0",
                "193.194.1.0",
                "193.194.5.0",
                "193.194.32.0",
                "193.194.63.0",
                "194.6.224.0",
                "194.6.224.0",
                "194.204.192.0",
                "194.204.255.0",
                "196.2.80.0",
                "196.2.95.0",
                "196.11.103.0",
                "196.11.103.0",
                "196.12.192.0",
                "196.12.255.0",
                "196.13.108.0",
                "196.13.108.0",
                "196.32.216.0",
                "196.32.218.0",
                "196.32.221.0",
                "196.32.223.0",
                "196.43.236.0",
                "196.43.236.0",
                "196.60.74.0",
                "196.60.74.0",
                "196.61.232.0",
                "196.61.239.0",
                "196.64.0.0",
                "196.95.255.0",
                "196.112.0.0",
                "196.127.255.0",
                "196.200.128.0",
                "196.200.191.0",
                "196.200.240.0",
                "196.200.255.0",
                "196.206.0.0",
                "196.206.255.0",
                "196.217.0.0",
                "196.217.255.0",
                "196.223.176.0",
                "196.223.191.0",
                "197.128.0.0",
                "197.131.255.0",
                "197.144.0.0",
                "197.147.255.0",
                "197.153.0.0",
                "197.153.255.0",
                "197.230.0.0",
                "197.230.255.0",
                "197.247.0.0",
                "197.247.255.0",
                "197.253.128.0",
                "197.253.255.0",
                "212.217.0.0",
                "212.217.127.0",
                "41.62.0.0",
                "41.62.255.0",
                "41.224.0.0",
                "41.231.255.0",
                "88.202.107.0",
                "88.202.107.0",
                "88.202.111.0",
                "88.202.111.0",
                "89.202.179.0",
                "89.202.179.0",
                "102.24.0.0",
                "102.31.255.0",
                "102.104.0.0",
                "102.111.255.0",
                "102.128.0.0",
                "102.128.63.0",
                "102.141.204.0",
                "102.141.207.0",
                "102.152.0.0",
                "102.159.255.0",
                "102.164.112.0",
                "102.164.113.0",
                "102.168.0.0",
                "102.175.255.0",
                "102.221.128.0",
                "102.221.131.0",
                "102.240.0.0",
                "102.243.255.0",
                "154.72.224.0",
                "154.72.239.0",
                "154.104.0.0",
                "154.111.255.0",
                "160.156.0.0",
                "160.159.255.0",
                "164.160.0.0",
                "164.160.3.0",
                "165.50.0.0",
                "165.51.255.0",
                "167.1.180.0",
                "167.1.181.0",
                "169.255.68.0",
                "169.255.71.0",
                "169.255.92.0",
                "169.255.95.0",
                "172.68.89.0",
                "172.68.89.0",
                "192.68.138.0",
                "192.68.138.0",
                "192.170.4.0",
                "192.170.4.0",
                "193.95.0.0",
                "193.95.127.0",
                "196.41.95.0",
                "196.41.95.0",
                "196.176.0.0",
                "196.179.255.0",
                "196.184.0.0",
                "196.187.255.0",
                "196.203.0.0",
                "196.203.255.0",
                "196.216.156.0",
                "196.216.156.0",
                "196.224.0.0",
                "196.239.255.0",
                "197.0.0.0",
                "197.29.124.0",
                "197.29.126.0",
                "197.29.126.0",
                "197.29.126.0",
                "197.29.126.0",
                "197.29.126.0",
                "197.29.126.0",
                "197.29.126.0",
                "197.31.255.0",
                "197.238.0.0",
                "197.238.255.0",
                "197.240.0.0",
                "197.240.255.0",
                "197.244.0.0",
                "197.244.255.0",
                "212.73.201.0",
                "212.73.201.0",
                "213.150.160.0",
                "213.150.191.0"};
        BufferedWriter writer = null;
        String errorS = "";
        try {
            writer = new BufferedWriter(new FileWriter("/home/na.txt"));
            for (int i = 0; i < arr.length-1; i+=2) {
                String start = arr[i];
                String end = arr[i+1];
                List<Integer> startSeg = Arrays.stream(start.split("\\.")).map(Integer::parseInt).toList();
                List<Integer> endSeg = Arrays.stream(end.split("\\.")).map(Integer::parseInt).toList();
                int[] startArr = new int[]{startSeg.get(0), startSeg.get(1), startSeg.get(2), startSeg.get(3)};
                int[] endArr = new int[]{endSeg.get(0), endSeg.get(1), endSeg.get(2), endSeg.get(3)};
                for (int j = startArr[0]; j <= endArr[0]; j++) {
                    for (int k = startArr[1]; k <= endArr[1]; k++) {
                        for (int l = startArr[2]; l <= endArr[2]; l++) {
                            writer.write(j+"."+k+"."+l+".0/24");
                            writer.newLine();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("process "+errorS+" error: "+e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    System.out.println("close error");
                }
            }
        }
    }

    @Test
    public void ips() {
        List<File> files = FileUtils.listFiles(new File("/home/falcon-data/trace/"), new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    return file.getName().endsWith(".txt");
                }
                return true;
            }

            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        }, new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isFile()) {
                    return file.getName().endsWith(".txt");
                }
                return true;
            }

            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        }).stream().toList();
        List<String> allRouters = new ArrayList<>();
        files.forEach(file -> {
            String line="";
            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while ((line = reader.readLine()) != null) {
                    String info = line.split("\t")[1];
                    Map<String, Object> values = JsonUtil.fromJson(info);
                    if (values == null) {continue;}
                    List<String> routers = values.values().stream().map(e -> {
                                String s = String.valueOf(e);
                                return s.substring(1, s.length() - 1);
                            })
                            .map(e -> e.split(","))
                            .map(l -> Arrays.stream(l).map(e -> e.split(":")[1]).toList())
                            .reduce((e1, e2) -> {
                                List<String> except = e1.stream().filter(e -> !e2.contains(e)).toList();
                                List<String> result = new ArrayList<>(e2);
                                result.addAll(except);
                                return result;
                            }).stream().toList().get(0)
                            .stream().filter(e -> !e.equals("*")).distinct()
                            .sorted().toList();
                    allRouters.addAll(routers);
                }
            } catch (IOException e) {
                System.out.println(line);
            }
            System.out.println("finish " + file.getName());
        });
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("/home/ips.txt"))) {
            List<String> routers = allRouters.stream().distinct().sorted().toList();
            for (String router : routers) {
                writer.write(router);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
