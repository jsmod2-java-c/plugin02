package net.noyark.command;

import cn.jsmod2.core.CommandSender;
import cn.jsmod2.core.Powers;
import cn.jsmod2.core.annotations.Assembly;
import cn.jsmod2.core.annotations.Auto;
import cn.jsmod2.core.command.Command;
import cn.jsmod2.core.plugin.Plugin;
import cn.jsmod2.core.plugin.PluginBase;

/**
 * 如果进行自动注册，必须存在的构造方法参数必须是一个Plugin类型的
 * 这里是命令部分
 *
 */
@Assembly
public class TestCommand extends Command {

    //直接把PluginBase的单例对象注入
    @Auto
    private PluginBase base;
    /**
     * 注册构造方法
     * commandName是命令名称
     * Power是权限
     *          * 级别
     *          * all 所有人
     *          * console 控制台
     *          * cn.jsmod2.admin 管理员
     *          * cn.jsmod2.player 玩家
     *          * nobody 任何人不可以
     *          * noconsole 控制台不可以
     *          *
     *          * 权限类型
     *          * 控制台可以操作的指令有以下权限
     *          * ！noconsole
     *          * 允许普通玩家使用的指令权限
     *          * cn.jsmod2.player all
     * description是命令介绍 使用HELP时会有这个介绍
     * plugin是插件对象，如果采用自动注册，必须有这个构造方法的参数
     * @param plugin 插件对象
     */
    public TestCommand(Plugin plugin) {
        super("test", Powers.ALL,"hello command", plugin);
    }

    /**
     * 当执行命令时，会跑这个方法
     * @param commandSender 可能是Player对象或者Console对象，或者是GameServer对象
     * @param strings
     * @return
     */
    @Override
    public boolean execute(CommandSender commandSender, String[] strings) {
        System.out.println(base);
        base.info("执行命令成功");
        return true;//true代表执行成功
    }
}
