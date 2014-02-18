一.项目分析：
    国内已经有很多介绍汽车的网站，介绍国产汽车的也不少，而且网站的内容也很丰富。评测、参数、报价等等这些资讯，现有的网站都已经做得很好，而如果我们还继续做这些则很难有突破点，所以我们要做的这个国产汽车介绍网站要有自己的特点：社交！就是将社交融入到汽车网站中。微信的点赞和微博的认证可以用在我们汽车品牌的点赞和车主实名认证中（在汽车点评里，通过认证的车主会有特别的身份标识）。
    
二.项目需求：
    这里我就不说那些笼统的话了，直接说页面架构。首页：
    1.顶部20%用来简单介绍网站，包括右上角有注册和登录按钮；
　　2.中间10%用来设置搜索选择项，两种个搜索条件：车型和价格。车型包括微型车、小型车、紧凑型车、中型车、中大型车、豪华型车、SUV、MPV、跑车和商用车；价格用两个输入框（最低价格、最高价格），最右边有一个“搜索”按钮；车型和价格的条件是组合的，例如选了中型车和最低价格是50000，表示搜索出所有高于50000的中型车，然后跳转到另一个页面，显示出所有符合的汽车系列，每一行显示两个系列，显示的内容为该系列车的图片+底部除了有最低价和油耗+一行星级和点评（星级就是这个系列的车达到5星中的多少星（暂时由我们后台评星），点评就是类似微博的那种评论，点击了“点评”按钮后就可以查看其他人的点评，用户自己也可以提交点评)。
　　3.接下来的20%用来展示汽车图片和小道消息（左边滚动展示图片，右边显示新闻）
　　4.底下40%用来展示国产品牌的标志，总共约有40个国产自主品牌，初期我们先选择其中比较出名的20个左右，每个标志的左下角有点赞标志和点赞数，点击了点赞标志点赞数会增加1，点击标志的其他地方跳转到另一个页面，这个页面列出这个品牌所有系列的车辆(例如奥迪的这个页面http://www.carsdirect.com/audi?tab=new )。每个系列图片的底部除了有最低价和油耗外这一行外，我们还增加一行星级和点评（星级就是这个系列的车达到5星中的多少星（暂时由我们后台评星），点评就是类似微博的那种评论，点击了“点评”按钮后就可以查看其他人的点评，用户自己也可以提交点评，通过了车主认证的用户会在这里有特别的身份标识，类似微博的V）。每个用户点评一次能获得一个积分，认证用户点评一次获得两个积分，积分的作用我们后续再实现。
　　5.10%用作留言板，留言板的页面分页和后台实现已经完成。
    6.车主认证实现：所有用户注册的时候都只需要填写邮箱、密码和昵称（登录账号是昵称）。需要车主认证的用户可以登录后在自己的个人中心那里上传身份证、驾照和汽车证明等照片，这些照片上传到服务器后我们后台人工审核。用户登录后点击右上角“个人中心”就可以进入自己的用户资料页面，这个页面包括修改密码、和上传图片的输入框（暂定一个输入框，要上传的图片打包压缩上传）。

三.项目进度安排：
    我们的申报书最主要两点就是实现HTML5的离线存储功能和有车主认证，所以我们咱时实现这些功能，到时如果还有时间我们可以迭代开发增加新功能。希望能在1月29日前开发出一个初版，因为2月下旬就要结题了。我在安排工作方面做得不是很好，本来打算回家专心搞项目的，不过我奶奶这些天身体一直不好需要照顾，所以我没什么心机做项目，一直让大家学习技术，到现在才把这份东西写好。这也是我的失策。希望大家尽量抽时间出来做一个初版，因为到时结不了题，对我们来说只是扣综合测评分数而已，但是对于指导老师是两年内不能参加与科创相关的东西的。所以希望不要在大学留下这种遗憾。
    另外群共享那个用play实现的华农酸奶推广网站有很多地方在我们这个项目也用得上，我们直接在这个项目的基础上修改成我们的汽车网站，方便加快我们的进度。


