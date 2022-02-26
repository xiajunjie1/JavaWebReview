<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <% String path=request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<script type="text/javascript" src="<%=path%>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap.min.js"></script>

<title>Https测试</title>
</head>
<%--
	http是当前web项目中主要采用的处理协议，但是对于现代的应用，如访问百度，访问地址输入：
	http://www.baidu.com访问后地址被映射成了https://www.baidu.com
	
	https是一种带有安全加密的http协议
		传统的http协议是明文的方式进行访问，那么解决安全问题，最直观的方式就是对明文进行加密
	https的安全基础是SSL，这样每次通讯中都会进行加密数据的传输
		通过Https协议传输时，由于其使用了SSL进行处理，所以传输内容就全部为加密数据信息，这样一来
		实际上就是在网络模型中追加了一个安全处理层（SSL），以保证可以实现正常的加密和解密操作
		从OSI模型来理解，可以看做是应用层和传输层中间加了一个安全层
		
	SSL和TLS
		SSL（安全套接字层）通过互相认证、使用数字签名确保完整性、使用加密确保私密性，该协议由两层组成：SSL记录协议和SSL握手协议
		TLS（传输层安全协议）用于两个应用程序之间提供保密性和数据完整性。该协议由两层组成：TLS记录协议和TLS握手协议
	TLS是基于SSL3.0的后续版本，差别微小，它是写入了RFC的，如果没有特殊要求，可以简单的将SSL和TLS理解为同一协议
	
	实际上不管概念如何繁杂，协议的组成如何复杂，实际上如果想要实现加密的数据传输，核心要求只有一个：数字证书
	
	OpenSSL：通过该工具进行私有的CA搭建，由自己的私有CA进行证书的签发
	可以在Linux上试用OpenSSL工具进行私有的CA搭建，因为Linux上一般默认安装了该工具，相对而言比较简单
	具体操作：
		查看OpenSSL的版本：openssl version
		【Windows】一般如果想要进行证书的签发都需要与一个具体的域名做出绑定，本次可以模拟一个域名,建议直接修改本机的hosts文件（C:\Windows\System32\drivers\etc），做一个域名的映射
		在hosts中加上该映射：192.168.0.103 xiajayj.com，ip为Linux虚拟机的ip(openssl)
		【Linux】如果想生成证书，则一定要有个证书的存储路径，可以在Linux的usr/local下创建一个存储证书的目录，证书的存储目录中创建两个子文件夹（server、client），server保存服务器的证书，client保存客户端证书，根路径保存根证书
		【Linux】生成一个长度为1024的CA秘钥对：[root@JayJ xiajj]# openssl genrsa -out /usr/local/xiajj/cakey.pem 1024
		输出结果：
			Generating RSA private key, 1024 bit long modulus
			..................++++++
			....................................++++++
			e is 65537 (0x10001)
		【Linux】生成根证书签发申请
			openssl req -new -key /usr/local/xiajj/cakey.pem -out /usr/local/xiajj/cacert.csr -subj /CN=xiajayj.com
		【Linux】使用x509的格式创建根证书签发，有效期为10年
			openssl x509 -req -days 3650 -sha1 -extensions v3_ca -signkey /usr/local/xiajj/cakey.pem -in /usr/local/xiajj/cacert.csr -out /usr/local/xiajj/ca.cer
		【Linux】创建完根证书后，可以进行服务端证书的创建，首先生成服务器私钥，在私钥生成时需要设置一个服务端密钥的访问密码，本次设为"xia123"
			openssl genrsa -aes256 -out /usr/local/xiajj/server/server-key.pem 1024
		【Linux】生成服务器端的签发申请
			 openssl req -new -key /usr/local/xiajj/server/server-key.pem -out /usr/local/xiajj/server/server.csr -subj /CN=xiajayj.com
		【Linux】服务端证书签发处理
			openssl x509 -req -days 3650 -sha1 -extensions v3_req -CA /usr/local/xiajj/ca.cer -CAkey /usr/local/xiajj/cakey.pem -CAserial /usr/local/xiajj/server/ca.srl -CAcreateserial -in /usr/local/xiajj/server/server.csr -out /usr/local/xiajj/server/server.cer		
			执行结果：
				Signature ok
				subject=/CN=xiajayj.com
				Getting CA Private Key	
		【Linux】生成客户端的私钥，同样生成私钥时也要生成一个密码，本次将密码设置为：“xia123”
			openssl genrsa -aes256 -out /usr/local/xiajj/client/client-key.pem 1024
		【Linux】生成客户端的签发申请
			openssl req -new -key /usr/local/xiajj/client/client-key.pem -out /usr/local/xiajj/client/client.csr -subj /CN=xiajayj.com
		【Linux】生成客户端的证书签发，期限为1年
			openssl x509 -req -days 365 -sha1  -CA /usr/local/xiajj/ca.cer -CAkey /usr/local/xiajj/cakey.pem -CAserial /usr/local/xiajj/server/ca.srl  -in /usr/local/xiajj/client/client.csr -out /usr/local/xiajj/client/client.cer
		【Linux】所有生成的证书一定都是通过java去使用的，所以应该生成一个p12格式（公钥加密技术12号标准证书，CER证书+秘钥，可以防止证书丢失问题），生成一个p12的客户端证书
			openssl pkcs12 -export -clcerts -name xia-client -inkey /usr/local/xiajj/client/client-key.pem -in /usr/local/xiajj/client/client.cer -out /usr/local/xiajj/client/client.p12
			注，此处除了要输入客户端私钥密码还需要设置转p12的密码，全部采用“xia123”
		【Linux】生成一个服务端的p12证书
			openssl pkcs12 -export -clcerts -name xia-server -inkey /usr/local/xiajj/server/server-key.pem -in /usr/local/xiajj/server/server.cer -out /usr/local/xiajj/server/server.p12
			此处的操作和生成客户端p12证书类似，就是引用的私钥和证书的名字需要修改一下，密码仍然都用“xia123”
			
	在Tomcat中配置CA证书，本次配置
 --%>
<body>

</body>
</html>