package org.fenglin;

import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import org.fenglin.Command.Undercover;
import org.fenglin.data.MyDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WhoUndercover extends JavaPlugin {
    public static final WhoUndercover INSTANCE = new WhoUndercover();

    private WhoUndercover() {
        super(new JvmPluginDescriptionBuilder("org.fenglin.Undercover", "1.0")
                .name("谁是卧底")
                .author("枫叶秋林")
                .build());
    }

    @Override
    public void onEnable() {
        CommandManager.INSTANCE.registerCommand(new Undercover(),true);
        reloadPluginData(MyDate.MyDate);
        getLogger().info("谁是卧底，加载成功！");
    }
}