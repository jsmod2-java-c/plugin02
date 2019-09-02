package net.noyark;

import cn.jsmod2.core.annotations.Assembly;
import cn.jsmod2.core.annotations.EnableRegister;
import cn.jsmod2.core.annotations.LoadBefore;
import cn.jsmod2.core.annotations.Main;
import cn.jsmod2.core.plugin.ConfigManager;
import cn.jsmod2.core.plugin.ConfigSetting;
import cn.jsmod2.core.plugin.PluginBase;
import cn.jsmod2.core.utils.config.Config;
import cn.jsmod2.core.utils.config.JsonConfig;


/** 为了标记是一个main类,同时不能允许有多个类使用这个注解*/
@Main(name="HelloWorld")
/* 启用自动监听器和指令注册的注解,如果不想手动注册,可以加入这个注解 */
//假如不加这个注解，那么需要在onEnable中手动注册这个监听器
@EnableRegister
@LoadBefore(pluginName = "example")
//Assembly类似于组件,一旦使用了组件注解,它的单例对象就会注册到对象池中
//之后在有@Assembly注解的类中使用@Auto来注入,这里参见其他两个类如何使用的
@Assembly
public class ExamplePlugin04 extends PluginBase {

    /**
     * 它的作用是为了使得其他对象获得主类，以获取主类的信息
     * 这样我在监听器是可以获取到的
     * 但是如果要让监听器获取到这个对象
     * 必须要在注册监听器之前，添加这个代码
     * 但是如果使用了@EnableRegister注解,则不用去担心这个问题
     * 毕竟onEnable是在注册之前进行的
     */
    private static PluginBase pluginBase;

    /**
     * onLoad中，不能执行这些注册方法，只是作为一些初始化操作，它的执行优先级
     * 是高于onEnable，也就是最开始执行的方法
     */
    @Override
    public void onLoad() {
        info("开启ExamplePlugin04中");
        //添加配置
        //primary 插件独有的配置，所以为true，如果允许其他插件有相同的admin_name,可以为false
        addConfig(new ConfigSetting("admin_name","magiclu","none",true));

    }

    /**
     * 是onLoad后，执行完初始化，开始进行这个onEnable，一般进行一些注册的操作
     */
    @Override
    public void onEnable() {
        String name = getConfig("admin_name",String.class);
        pluginBase = this;
        info("加载ExamplePlugin04成功a,admin_name: "+name);
        ConfigManager.getManager().unregisterConfig(this,"admin_name");//卸载配置
        //这属于内存配置，这样可以直接自由的找到值
        //还有外存的配置,通过了Config框架
        //DataFolder为配置文件的文件夹
        Config config = new JsonConfig(this.getDataFolder()+"/hello.json",false);
        try {
            config.put("hello", 0);
            config.save();//保存配置,生成hello.json文件
            config.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //registerEvents(new PlayerListener());//这是不加@EnableRegister情况下，必须加这个代码使得这个PlayerListener是生效的
        //如果加入了这个注解@EnableRegister，不能再手动注册了，否则这个监听器会注册两次
    }

    /**
     * onDisable是服务器即将关闭时，执行，进行一些后初始化操作
     */
    @Override
    public void onDisable() {
        info("关闭ExamplePlugin04成功");
    }

    /**
     * 获得插件主类的对象，这个对象是唯一存在的
     * @return 插件主类对象
     */
    public static PluginBase getPluginBase() {
        return pluginBase;
    }
}
