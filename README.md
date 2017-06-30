# 自定义拖拽效果的DrawerLayout

## 效果

![有图有真相]()

## 分析思路

**1、拖拽效果**

拖拽效果可以用贝塞尔曲线实现。根据手指移动的Y轴位置来动态设置二阶贝塞尔曲线的控制点。控制点的X轴的值为DrawerLayout拖出来的进度（addDrawerListener()可以监听到），Y坐标为手指所在Y轴的值。

**2、DrawerLayout抽屉部分bug**

抽屉部分可能高度不够，导致bug出现，这是需要强行设置其填充屏幕，所以用一个自定义的RelativeLayout将其替换包装。遍历OverlayDrawerLayout中的布局如果是SliderBarLinearLayout，将其removeView，并新建一个MagicRelativeLayout添加到OverlayDrawerLayout中，这里将将SliderBarLinearLayout的LayoutParams设置给MagicRelativeLayout。
MagicRelativeLayout中需要给他添加一个BezierView作为背景，并将SliderBarLinearLayout的背景色设置给BezierView，如果没有背景色，设置默认背景色。

**3、子View跟随手指运动效果**

可以取一个跟用户滑动有关的百分比值来动态设置子View的偏移量。方案一：贝塞尔当前的控制点坐标C到起点A和终点B的距离AC和BC的比值？小于1的部分可以直接用，大于1的部分可以用2去减，大于2的部分？麻烦。
方案二：CAB的角度转换成百分比，麻烦。
方案三：控件所在位置坐标距离手指距离和手指滑动的距离的比值，这个靠谱。因为X轴方向不关心，直接计算Y轴即可。

![画图理解]()

**4、事件分发**




