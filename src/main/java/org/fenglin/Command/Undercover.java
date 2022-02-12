package org.fenglin.Command;

import net.mamoe.mirai.console.command.CommandSender;
import net.mamoe.mirai.console.command.java.JCompositeCommand;
import net.mamoe.mirai.contact.User;
import org.fenglin.Mypaly;
import org.fenglin.data.MyDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;


public class Undercover extends JCompositeCommand implements Runnable  {
    private List<Mypaly> data=new ArrayList<>();
    private CommandSender sender_run=null;
    private Map<String,String> world_data;
    public Undercover(){
        super(org.fenglin.WhoUndercover.INSTANCE, "谁是卧底");
        setPrefixOptional(true);
    }
    //另开线程时事检测游戏进程
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//          描述阶段检测
            if (c1==codplay.describe){
                AtomicReference<Boolean> isD= new AtomicReference<>(false);
                data.forEach(v->{
                    if (v.getDescribe().equals("空")){
                        isD.set(true);
                    };
                });
                if (!isD.get()){
                    c1=codplay.vote;
                    sender_run.sendMessage("描述阶段结束，开始投票阶段");

                }
            }
            //          投票阶段检测
            if (c1==codplay.vote){
                AtomicReference<Boolean> isD= new AtomicReference<>(false);
//                v.setIsviod(false); 完成投票  全部为false即为投票完成 有一个ture跳过！
                data.forEach(v->{
                    if (v.getIsviod()){
                        isD.set(true);
                    };
                });
                if (isD.get() == false){
                    System.out.println("投票完成！开始判决");
//                  寻找最高票
                    int max_v=0;int max_i=0;
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getViod()>max_v){
                            max_v=data.get(i).getViod();
                            max_i=i;
                        }
                    }
//                  判断胜负


                    String info = "----本局战况\n----";
                    info +=  "普通玩家胜利";
                    if (data.get(max_i).getIsundercover()){

                        for (Map.Entry<String, String> entry : world_data.entrySet()) {
                            info+="玩家词条：" + entry.getKey() + ",卧底词条：" + entry.getValue()+"\n";
                        }
//                  卧底信息
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getIsundercover()) {
                                info += "本局卧底：" + data.get(i).getName();
                            }
                        }
                        sender_run.sendMessage(info);
                        c1=codplay.Cpaly;
                        data.clear();
                      return;
                    }else {
//                      删除查看剩余人数
                        data.remove(max_i);
                        if (data.size()>1){
                            String infoW = "----本局战况\n----";
                            infoW +=  "普通玩家胜利";
                            for (Map.Entry<String, String> entry : world_data.entrySet()) {
                                infoW+="玩家词条：" + entry.getKey() + ",卧底词条：" + entry.getValue()+"\n";
                            }
                            for (int i = 0; i < data.size(); i++) {
                                if (data.get(i).getIsundercover()) {
                                    infoW += "本局卧底：" + data.get(i).getName();
                                }
                            }
                            sender_run.sendMessage(info);
                            c1=codplay.Cpaly;
                            data.clear();
                            return;
                        }
                    }
//                  转化描述状态！
                    sender_run.sendMessage("卧底存在!继续描述.");
                    c1=codplay.describe;
                }else {

                }
            }
        }
    }

    enum codplay{
        Cpaly,//创建游戏时
        joinpaly,//游戏加入时
        describe,//描述
        vote,//投票
    }
    codplay c1 = codplay.Cpaly;
    @SubCommand("创建游戏")
    public void Cpaly(CommandSender sender) {
        if (c1 == codplay.Cpaly){
            Mypaly admin=new Mypaly();
            admin.setName(sender.getName());
            admin.setId(sender.getUser().getId());
            admin.setIsadmin(true);
            data.add(admin);
            c1=codplay.joinpaly;
            sender.sendMessage("创建游戏成功");
        }else {
            sender.sendMessage("游戏已创建");
        }
    }

    @SubCommand("加入游戏")
    public void joinpaly(CommandSender sender) {
        if (c1==codplay.joinpaly){
            Boolean Isboot=false;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getId()==sender.getUser().getId()){
                    Isboot=true;
                }
            }
            if (Isboot){
                sender.sendMessage("已加入到游戏中");
                return;
            }
            Mypaly admin=new Mypaly();
            admin.setName(sender.getName());
            admin.setId(sender.getUser().getId());
            admin.setIsadmin(false);
            data.add(admin);
            sender.sendMessage("加入游戏成功！当前人数：("+data.size()+">=3)");
        }else {
            sender.sendMessage("未在加入游戏状态。");
        }

    }
    @SubCommand("开始游戏")
    public void paly(CommandSender sender) {
        if (!(c1 == codplay.joinpaly)){
            sender.sendMessage("当前状态无法开始游戏");
        }else {
            if (data.size()>=3){
//          随机词条
                List<Map<String,String>> world= MyDate.MyDate.getWorld();
                if (world==null){
                    sender.sendMessage("无配置词库，无法开始");
                }
                Random random=new Random();
                world_data = world.get(random.nextInt(world.size()));
//          分配词条
                for (Map.Entry<String, String> entry : world_data.entrySet()) {
                    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                    for (int i=0;i<data.size();i++){
                        data.get(i).setWorld(entry.getKey());
                    }
                    //随机卧底
                    data.get(random.nextInt(data.size())).setWorld(entry.getValue());
                    data.get(random.nextInt(data.size())).setIsundercover(true);

                }
//            通告
                for (int i=0;i<data.size();i++){
                    sender.getBot().getFriend(data.get(i).getId()).sendMessage("本局你的词条为："+data.get(i).getWorld());
                }
//          更改状态
                c1=codplay.describe;
                sender.sendMessage("开始成功！你们将收到词条！");
//          开启游戏检测线程
                sender_run=sender;
                new Thread (this).start();
            }else {
                sender.sendMessage("人数未满足");
            }
        }

    }
    @SubCommand("停止游戏")
    public void stoppaly(CommandSender sender) {

            AtomicReference<Boolean> isf= new AtomicReference<>(false);
            data.forEach(v->{
                if (sender.getUser().getId()==v.getId() && v.getIsadmin()){

                    isf.set(true);
                }
            });
            if(isf.get()){
                sender.sendMessage("无管理权限，无法重置");
            }else {
                c1=codplay.Cpaly;
                data.clear();
                sender.sendMessage("已重置游戏！");
            }
    }
    @SubCommand("描述")
    public void describe(CommandSender sender, String message) {
        if (c1==codplay.describe){
            AtomicReference<Boolean> isD= new AtomicReference<>(false);
            data.forEach(v->{
                if (sender.getUser().getId()==v.getId() && v.getDescribe().equals("空")){
                    v.setDescribe(message);
                    sender.sendMessage("描述成功你的本轮描述词为："+ message);
                    isD.set(true);
                }
            });
            if (isD.get()){
                return ;
            }
            sender.sendMessage("你已经描述过了或者未加入本局游戏");
        }else {
            sender.sendMessage("当前状态无法描述");
        }
    }

    @SubCommand("投票")
    public void vote(CommandSender sender,User user) {
        if (c1==codplay.vote){
            AtomicReference<Boolean> vinfo= new AtomicReference<>(false);
            data.forEach(v->{
                if (sender.getUser().getId()==v.getId() && v.getIsviod()){
                    v.setIsviod(false);
                    data.forEach(v1->{
                        if (v1.getId()==user.getId()){
                            v1.setViod(v1.getViod()+1);
                            vinfo.set(true);
                        };
                    });
                }
            });
            if (vinfo.get()){
                sender.sendMessage("投票完成!");
                return;
            }
            sender.sendMessage("错误：无法完成投票(可能是已经投票或者未加入本局游戏)");
        }else {
            sender.sendMessage("当前状态无法进行投票");
        }

    }
    @SubCommand("查看描述")
    public void lookdescribe(CommandSender sender) {
        if (c1==codplay.describe || c1==codplay.vote){
            AtomicReference<String> a = new AtomicReference<>("");
            data.forEach(v->{
                a.set(a.get()+v.getName()+"描述是："+v.getDescribe()+"\n");
            });
            sender.sendMessage(a.get());
        }else {
            sender.sendMessage("当前状态无法查看描述情况");
        }
    }
    @SubCommand("查看投票")
    public void lookvote(CommandSender sender) {
        if (c1==codplay.vote){
            AtomicReference<String> a = new AtomicReference<>("");
            data.forEach(v->{
                a.set(a.get()+v.getName()+"票数是："+v.getViod()+"\n");
            });
            sender.sendMessage(a.get());
        }else {
            sender.sendMessage("当前状态无法查看投票情况");
        }
    }
}
