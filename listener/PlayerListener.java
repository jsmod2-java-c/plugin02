package net.noyark.listener;

import cn.jsmod2.api.event.admin.IBanEvent;
import cn.jsmod2.api.event.player.IPlayerJoinEvent;
import cn.jsmod2.core.annotations.EventManager;
import cn.jsmod2.core.event.Listener;
import cn.jsmod2.core.utils.EventPriority;
import net.noyark.ExamplePlugin04;

/**
 * 玩家监听器，监听玩家的一系列信息
 * 如加入等等
 */
public class PlayerListener implements Listener {

    //@EventManager的作用是标记这个方法是一个监听器方法，之后事件调度器会把它加载到映射
    //最终实现事件触发的切面处理(就是触发事件执行这个方法)
    //然后参数会传入事件对象，可以对其进行操作和事件参数的修改，事件参数是事件的类型
    /**
     * 这个方法会在触发玩家加入事件的时候执行
     * 如果玩家人数大于等于2那么执行一以下操作
     *  -发送信息 ${playerName}你好
     *  -杀死玩家
     *  -结束
     * @param e 监听的方法对象
     */
    @EventManager
    public void onPlayerJoin(IPlayerJoinEvent e){
        if(ExamplePlugin04.getPluginBase().getServer().getGameServer().getPlayers().size()>=2) {
            e.getPlayer().personalBroadcast(10, e.getPlayer().getName() + "你好", false);
            e.getPlayer().kill();
        }
    }



    //可以存在两个事件监听器，会根据优先级执行，最高的优先级最后执行，普通优先级是normal，默认
    @EventManager(priority= EventPriority.HIGH)
    public void onPlayerJoin02(IPlayerJoinEvent e){

    }

    //如果不知道需要什么事件，可以从这里查询
    @EventManager
    public void onBan(IBanEvent e){
        //也可以设置Result，最终在事件执行完成后，放行
        e.setResult("test test");
    }
}
