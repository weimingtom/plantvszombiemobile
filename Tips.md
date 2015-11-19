# Introduction #

开发过程中积累的小知识，请放在这里


# Details #
### SVN与Eclipse结合使用，忽略目录的设置 ###
Eclipse结合SVN进行版本控制，进行开发时，即使在TortoiseSVN中设置bin目录设置为ignore目录，但是Eclipse还是会将代码目录中的.svn拷贝到bin，造成界面上显示bin发生变化，并且有可能影响项目编译。解决方法见：

打开Eclipse中的Project->Properties->JavaBuildPath菜单，在右侧面板中的"Source"选项卡，在Excluded中加入"/.svn/"。

不需要版本控制的目录bin, gen