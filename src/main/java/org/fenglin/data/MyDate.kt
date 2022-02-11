package org.fenglin.data

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value

class MyDate {
   companion object MyDate : AutoSavePluginData("world") { // 词条库
       var world: List<Map<String,String>> by value()
   }
}