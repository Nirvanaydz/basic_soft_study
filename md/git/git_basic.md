### 关于git的使用：分布式	版本控制
#### 1.1、首先就是创建一个新的目录，mkdir
#### 1.2、将这个目录进行git初始化，在这个目录下执行git init，会产生.git文件
- // todo 学习.git文件的原理
#### 1.3.1、第一次使用git时需要设置全局变量，user.name|user.mail
- 设置方式如下：
    + git config —global user.name “自行设置”
    + git config —global user.mail “设置可以真正使用的邮箱”
- 查看全局设置：
    + git config —list
- //todo  除了查看global，再仔细研究一下local和system这两个选项
#### 1.3.2、关于本地仓库中的说明：
- 工作区：working directory，对应文件刚刚被创建，未被git track到 
- 缓存区：staging area，文件被提交到缓存区，被git所跟踪
- 仓库：repository，执行完commit指令后，文件保存到下一个版本中，处于已经提交的状态
- git add
- Untracked files:(use "git add <file>..." to include in what will be committed)
- git commit -m “create learn_git.html”	[git log]
- Changes to be committed:(use "git rm --cached <file>..." to unstage)
- 提交完成之后：git status
    + On branch master
    + nothing to commit, working tree clean
#### 1.4、创建远程仓库
- 打开github，创建仓库
- 使用https协议：每次都需要输入密码，较为繁琐，若想不输入密码的话，会本地保存明文密码，很不安全，不推荐
- 使用ssh协议：第一次使用的时候，创建好git密钥【公钥，私钥】即可
#### 1.5、将本地已经创建好的仓库与远程仓库关联
- git remote add origin git@github.com:Nirvanaydz/learn_git.git
    + 注释： origin 远程仓库别名，可以自行更改
    + 执行完成之后可以检验一下是否添加成功：git remote
#### 1.6、配置公钥和私钥
- 1、首先本地生成github账户对应的邮箱的公钥和私钥，保存在～/.ssh文件夹中
    + ssh-keygen -t rsa -C “1003082940@qq.com”
- 2、将生成的文件夹下的公钥文件复制到github的账户设置中，添加即可完成操作
- 3、验证本地和github的远程仓库是否连接成功
    + 执行：ssh -T git@github.com