# andorid_ip_detect
因为手机上的穿梭vpn是免费的，所以想通过代理的方式将局域网设备都连接到手机vpn上，但是发现其他设备只能连接到原始ip而不是vpn代理后的ip，于是有了两个推断:<br>
1. 穿梭根据当前的应用来更换全局代理
2. 穿梭为特定的应用有专属的代理通道
<br>
为了验证是不是推断1，写了这个程序在通知栏实时显示当前的ip地址，发现ip地址并不会因为焦点应用的改变而改变。<br>
sad....
![8aecf0acfde5e252dce34392413e0bf](https://github.com/nitpicker55555/andorid_ip_detect/assets/91596298/ca478a6c-68f4-48ba-ad03-d4bae466fdca)
