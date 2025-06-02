package com.my.televip.features;

import static com.my.televip.MainHook.lpparam;


import com.my.televip.ClientChecker;
import com.my.televip.base.AbstractMethodHook;
import com.my.televip.obfuscate.AutomationResolver;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class TelePremium {

    public static void init(){
        Class<?> userConfigClass = XposedHelpers.findClassIfExists(AutomationResolver.resolve("org.telegram.messenger.UserConfig"), lpparam.classLoader);
if ( userConfigClass != null) {
    // استخدم hook لتعديل متغير isPremium في الكائن
    XposedHelpers.findAndHookMethod(userConfigClass, AutomationResolver.resolve("UserConfig","isPremium", AutomationResolver.ResolverType.Method), new AbstractMethodHook() {
        @Override
        public void beforeMethod(XC_MethodHook.MethodHookParam param) {
            param.setResult(true);
        }
    });
}
if (ClientChecker.check(ClientChecker.ClientType.iMe) || ClientChecker.check(ClientChecker.ClientType.iMeWeb)){
    Class<?> ForkPremiumPreferencClass = XposedHelpers.findClassIfExists("com.iMe.storage.data.locale.prefs.impl.ForkPremiumPreference", lpparam.classLoader);
    XposedHelpers.findAndHookMethod(ForkPremiumPreferencClass, "isPremium", new AbstractMethodHook() {
        @Override
        protected void beforeMethod(MethodHookParam param) {
                param.setResult(true);
        }
    });
}
    }

}
