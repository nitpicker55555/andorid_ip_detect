# andorid_ip_detect
因为手机上的穿梭vpn是免费的，所以想通过代理的方式将局域网设备都连接到手机vpn上，但是发现其他设备只能连接到原始ip而不是vpn代理后的ip，于是有了两个推断:<br>
1. 穿梭根据当前的应用来更换全局代理
2. 穿梭为特定的应用有专属的代理通道
<br>
为了验证是不是推断1，写了这个程序在通知栏实时显示当前的ip地址，发现ip地址并不会因为焦点应用的改变而改变。<br>
sad....<br>
可以看到虽然vpn是在传输流量的运行状态，但是全局ip地址依然不是国内ip<br>

![8aecf0acfde5e252dce34392413e0bf](https://github.com/nitpicker55555/andorid_ip_detect/assets/91596298/d33a5de0-295b-4dd5-ab8c-c1aa063aec46)
